package fm.api.rest.moneyflow;

import fm.api.rest.BaseResource;
import fm.api.rest.moneyflow.interfaces.IMoneyFlowService;
import fm.api.rest.moneyflow.validators.MoneyFlowDeleteValidation;
import fm.api.rest.moneyflow.validators.MoneyFlowEditValidation;
import fm.auth.UserDetailsImpl;
import fm.common.Validator;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.util.Assert;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by haho on 6/22/2017.
 */
@RestController
public class MoneyFlowResource extends BaseResource {

  private final IMoneyFlowService expensesService;
  private final Validator<MoneyFlowEditValidation> expenseEditValidator;
  private final Validator<MoneyFlowDeleteValidation> moneyFlowDeleteValidator;
  private final Validator<Item> expenseAddValidator;

  @Autowired
  public MoneyFlowResource(
      @Qualifier("moneyFlowService") IMoneyFlowService expensesService,
      @Qualifier("moneyFlowEditValidator") Validator<MoneyFlowEditValidation> expenseEditValidator,
      @Qualifier("moneyFlowDeleteValidator") Validator<MoneyFlowDeleteValidation> moneyFlowDeleteValidator,
      @Qualifier("moneyFlowAddValidator") Validator<Item> expenseAddValidator
  ) {
    Assert.notNull(expenseEditValidator);
    Assert.notNull(moneyFlowDeleteValidator);
    Assert.notNull(expensesService);
    Assert.notNull(expenseAddValidator);

    this.expensesService = expensesService;
    this.expenseEditValidator = expenseEditValidator;
    this.expenseAddValidator = expenseAddValidator;
    this.moneyFlowDeleteValidator = moneyFlowDeleteValidator;
  }

  /**
   * The service is to Add single expense into DB.
   * @param userDetails
   * @param itemCreation
   * @return HTTP code 201 as created.
   */
  @PostMapping("/moneyflow")
  @PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
  public ResponseEntity addExpense(
      @AuthenticationPrincipal UserDetailsImpl userDetails,
      @Valid @RequestBody Item itemCreation
  ) {

    // Validation
    this.expenseAddValidator.validate(itemCreation);

    Long id = this.expensesService.addExpense(itemCreation, userDetails.getUserId());
    return new ResponseEntity<>(new Object() {
      public final Long expenseId = id;
    }, HttpStatus.CREATED);
  }

  /**
   * The service is to update list of items sent from Front-end side.
   * @param userDetails
   * @param items
   * @return HTTP code 204 as NO_CONTENT.
   */
  @PatchMapping("/moneyflow/list")
  @PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
  public ResponseEntity updateItems(
      @AuthenticationPrincipal UserDetailsImpl userDetails,
      @RequestBody List<ItemPresenter> items
  ) {
    // Validation
    for(ItemPresenter item : items) {
      MoneyFlowEditValidation validation = new MoneyFlowEditValidation();
      validation.setUserId(userDetails.getUserId());
      validation.setExpense(item);
      expenseEditValidator.validate(validation);
    }

    for(ItemPresenter item : items) {
      if (item.getUpdated()) {
        this.expensesService.updateExpense(item);
      }
    }
    return new ResponseEntity(HttpStatus.NO_CONTENT);
  }

  /**
   * The service is to delete single expense.
   * @param userDetails
   * @param expenseId
   * @return HTTP code 204 as NO_CONTENT.
   */
  @DeleteMapping("/moneyflow/{expenseId}/delete")
  @PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
  public ResponseEntity deleteExpense(
      @AuthenticationPrincipal UserDetailsImpl userDetails,
      @PathVariable("expenseId") int expenseId
  ) {
    // Validation
    MoneyFlowDeleteValidation validation = new MoneyFlowDeleteValidation();
    validation.setUserId(userDetails.getUserId());
    validation.setExpenseId(expenseId);
    moneyFlowDeleteValidator.validate(validation);

    this.expensesService.deleteExpense(expenseId);
    return new ResponseEntity(HttpStatus.NO_CONTENT);
  }

  /**
   * The service is to get all expenses in current month.
   * @param userDetails
   * @return list of expenses.
   */
  @GetMapping("/moneyflow")
  @PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
  public ItemDetailsPresenter getExpenses(
      @AuthenticationPrincipal UserDetailsImpl userDetails,
      @RequestParam(value = "name", required = false) String name
    ) {
    return this.expensesService.getExpensesDetails(userDetails.getUserId(), name);
  }

  /**
   * Get list of years displayed on Money flow page. The purpose is to let user to decide which year details to be shown.
   * @param userDetails
   * @return list of years in number.
   */
  @GetMapping("/moneyflowyearlist")
  @PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
  public List<Integer> getYearsList(
      @AuthenticationPrincipal UserDetailsImpl userDetails
  ) {
      return this.expensesService.getYearsList(userDetails.getUserId());
  }

  /**
   * The service is to get list of expenses by specific year.
   * @param userDetails
   * @param year
   * @param name
   * @return list of expenses.
   */
  @GetMapping("/moneyflow/{year}")
  @PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
  public List<ItemDetailsPresenter> getExpensesByYear(
      @AuthenticationPrincipal UserDetailsImpl userDetails,
      @PathVariable("year") Integer year,
      @RequestParam(value = "name", required = false) String name
  ) {
    return this.expensesService.getExpensesByYear(userDetails.getUserId(), year, name);
  }

  /**
   * The service is to get list of expenses by year & month.
   * @param userDetails
   * @param year
   * @param month
   * @return list of expenses.
   */
  @GetMapping("/moneyflow/{year}/{month}")
  @PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
  public ItemDetailsPresenter getExpensesByYearAndMonth(
      @AuthenticationPrincipal UserDetailsImpl userDetails,
      @PathVariable("year") Integer year,
      @PathVariable("month") Integer month
  ) {
    return this.expensesService.getExpenseByYearAndMonth(userDetails.getUserId(), year, month);
  }

  /**
   * The service is to get previous expenses. That means that there's no list of expenses in current month in this list.
   * @param userDetails
   * @param name
   * @return list of expenses.
   */
  @Deprecated
  @GetMapping("/moneyflow/lastmonths")
  @PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
  public List<ItemDetailsPresenter> getPreviousExpenses(
      @AuthenticationPrincipal UserDetailsImpl userDetails,
      @RequestParam(value = "name", required = false) String name
  ) {
    return this.expensesService.getLastMonths(userDetails.getUserId(), name);
  }
}

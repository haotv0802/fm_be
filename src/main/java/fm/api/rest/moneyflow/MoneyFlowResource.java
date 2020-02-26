package fm.api.rest.moneyflow;

import fm.api.rest.BaseResource;
import fm.api.rest.moneyflow.interfaces.IMoneyFlowService;
import fm.api.rest.moneyflow.validators.MoneyFlowEditValidation;
import fm.auth.UserDetailsImpl;
import fm.common.Validator;
import fm.common.beans.HeaderLang;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by haho on 6/22/2017.
 */
@RestController
public class MoneyFlowResource extends BaseResource {

  private final IMoneyFlowService expensesService;
  private final Validator<MoneyFlowEditValidation> expenseEditValidator;

  @Autowired
  public MoneyFlowResource(
      @Qualifier("moneyFlowService") IMoneyFlowService expensesService,
      @Qualifier("moneyFlowEditValidator") Validator<MoneyFlowEditValidation> expenseEditValidator
  ) {
    Assert.notNull(expenseEditValidator);
    Assert.notNull(expensesService);

    this.expensesService = expensesService;
    this.expenseEditValidator = expenseEditValidator;
  }

  @PostMapping("/moneyflow")
  @PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
  public ResponseEntity addExpense(
      @AuthenticationPrincipal UserDetailsImpl userDetails,
      @HeaderLang String lang,
      @RequestBody Item itemCreation
  ) {
    Long id = this.expensesService.addExpense(itemCreation, userDetails.getUserId());
    return new ResponseEntity<>(new Object() {
      public final Long expenseId = id;
    }, HttpStatus.CREATED);
  }

  @PatchMapping("/moneyflow")
  @PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
  public ResponseEntity updateExpense(
      @AuthenticationPrincipal UserDetailsImpl userDetails,
      @HeaderLang String lang,
      @RequestBody ItemPresenter item
  ) {
    MoneyFlowEditValidation validation = new MoneyFlowEditValidation();
    validation.setUserId(userDetails.getUserId());
    validation.setExpenseId(item.getId());
    expenseEditValidator.validate(validation);

    this.expensesService.updateExpense(item);
    return new ResponseEntity(HttpStatus.NO_CONTENT);
  }

  @PatchMapping("/moneyflow/list")
  @PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
  public ResponseEntity updateItems(
      @AuthenticationPrincipal UserDetailsImpl userDetails,
      @HeaderLang String lang,
      @RequestBody List<ItemPresenter> items
  ) {
    for(ItemPresenter item : items) {
      MoneyFlowEditValidation validation = new MoneyFlowEditValidation();
      validation.setUserId(userDetails.getUserId());
      validation.setExpenseId(item.getId());
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
   * The service is to update amount of Expense which is actually an event.
   * Each time user perform an update or add action on EventExpenses, the total of event expenses will be updated to such expense.
   * @param userDetails
   * @param lang
   * @param expenseId
   * @param amount
   * @return ResponseEntity
   */
  @PatchMapping("/moneyflow/{expenseId}/{amount}/updateAmount")
  @PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
  public ResponseEntity updateAmount(
      @AuthenticationPrincipal UserDetailsImpl userDetails,
      @HeaderLang String lang,
      @PathVariable("expenseId") int expenseId,
      @PathVariable("amount") BigDecimal amount
  ) {
    this.expensesService.updateExpense(amount, userDetails.getUserId(), expenseId);
    return new ResponseEntity(HttpStatus.NO_CONTENT);
  }

  @PatchMapping("/moneyflow/{expenseId}/updateAmount")
  @PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
  public ResponseEntity updateAmount(
      @AuthenticationPrincipal UserDetailsImpl userDetails,
      @HeaderLang String lang,
      @PathVariable("expenseId") int expenseId
  ) {
    this.expensesService.updateAmount(expenseId);
    return new ResponseEntity(HttpStatus.NO_CONTENT);
  }

  @DeleteMapping("/moneyflow/{expenseId}/delete")
  @PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
  public ResponseEntity deleteExpense(
      @AuthenticationPrincipal UserDetailsImpl userDetails,
      @HeaderLang String lang,
      @PathVariable("expenseId") int expenseId
  ) {
    MoneyFlowEditValidation validation = new MoneyFlowEditValidation();
    validation.setUserId(userDetails.getUserId());
    validation.setExpenseId(expenseId);
    expenseEditValidator.validate(validation);

    this.expensesService.deleteExpense(expenseId);
    return new ResponseEntity(HttpStatus.NO_CONTENT);
  }

  /**
   * The service is to get all expenses in current month.
   * @param userDetails
   * @param lang
   * @return
   */
  @GetMapping("/moneyflow")
  @PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
  public ItemDetailsPresenter getExpenses(
      @AuthenticationPrincipal UserDetailsImpl userDetails,
      @RequestParam(value = "name", required = false) String name,
      @HeaderLang String lang) {
    return this.expensesService.getExpensesDetails(userDetails.getUserId(), name);
  }

  /**
   * Get list of years displayed on Money flow page. The purpose is to let user to decide which year details to be shown.
   * @param userDetails
   * @param lang
   * @return
   */
  @GetMapping("/moneyflowyearlist")
  @PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
  public List<Integer> getYearsList(
      @AuthenticationPrincipal UserDetailsImpl userDetails,
      @HeaderLang String lang) {
      return this.expensesService.getYearsList(userDetails.getUserId());
  }

  @GetMapping("/moneyflow/{year}")
  @PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
  public List<ItemDetailsPresenter> getExpensesByYear(
      @AuthenticationPrincipal UserDetailsImpl userDetails,
      @PathVariable("year") Integer year,
      @RequestParam(value = "name", required = false) String name,
      @HeaderLang String lang) {
    return this.expensesService.getExpensesByYear(userDetails.getUserId(), year, name);
  }

  @GetMapping("/moneyflow/{year}/{month}")
  @PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
  public ItemDetailsPresenter getExpensesByYearAndMonth(
      @AuthenticationPrincipal UserDetailsImpl userDetails,
      @PathVariable("year") Integer year,
      @PathVariable("month") Integer month,
      @HeaderLang String lang) {
    return this.expensesService.getExpenseByYearAndMonth(userDetails.getUserId(), year, month);
  }

  @GetMapping("/moneyflow/lastmonths")
  @PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
  public List<ItemDetailsPresenter> getPreviousExpenses(
      @AuthenticationPrincipal UserDetailsImpl userDetails,
      @RequestParam(value = "name", required = false) String name,
      @HeaderLang String lang) {
    return this.expensesService.getLastMonths(userDetails.getUserId(), name);
  }


}

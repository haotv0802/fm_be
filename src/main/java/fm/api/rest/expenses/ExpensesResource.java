package fm.api.rest.expenses;

import fm.api.rest.BaseResource;
import fm.api.rest.expenses.interfaces.IExpensesService;
import fm.api.rest.expenses.validators.ExpenseEditValidation;
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

import java.util.List;

/**
 * Created by haho on 6/22/2017.
 */
@RestController
public class ExpensesResource extends BaseResource {

  private final IExpensesService expensesService;
  private final Validator<ExpenseEditValidation> expenseEditValidator;

  @Autowired
  public ExpensesResource(
      @Qualifier("expensesService") IExpensesService expensesService,
      @Qualifier("expenseEditValidator") Validator<ExpenseEditValidation> expenseEditValidator
  ) {
    Assert.notNull(expenseEditValidator);
    Assert.notNull(expensesService);

    this.expensesService = expensesService;
    this.expenseEditValidator = expenseEditValidator;
  }

  @GetMapping("/expenses")
  @PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
  public List<ExpensePresenter> getExpenses(
      @AuthenticationPrincipal UserDetailsImpl userDetails,
      @HeaderLang String lang) {
    return this.expensesService.getExpenses(userDetails.getUserId());
  }

  @PostMapping("/expenses")
  @PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
  public ResponseEntity addExpense(
      @AuthenticationPrincipal UserDetailsImpl userDetails,
      @HeaderLang String lang,
      @RequestBody Expense expenseCreation
  ) {
    Long id = this.expensesService.addExpense(expenseCreation, userDetails.getUserId());
    return new ResponseEntity<>(new Object() {
      public final Long expenseId = id;
    }, HttpStatus.CREATED);
  }

  @PatchMapping("/expenses")
  @PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
  public ResponseEntity updateExpense(
      @AuthenticationPrincipal UserDetailsImpl userDetails,
      @HeaderLang String lang,
      @RequestBody Expense expense
  ) {
    ExpenseEditValidation validation = new ExpenseEditValidation();
    validation.setUserId(userDetails.getUserId());
    validation.setExpenseId(expense.getId());
    expenseEditValidator.validate(validation);

    this.expensesService.updateExpense(expense);
    return new ResponseEntity(HttpStatus.NO_CONTENT);
  }

  @DeleteMapping("/expenses/{expenseId}/delete")
  @PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
  public ResponseEntity deleteExpense(
      @AuthenticationPrincipal UserDetailsImpl userDetails,
      @HeaderLang String lang,
      @PathVariable("expenseId") int expenseId
  ) {
    ExpenseEditValidation validation = new ExpenseEditValidation();
    validation.setUserId(userDetails.getUserId());
    validation.setExpenseId(expenseId);
    expenseEditValidator.validate(validation);

    this.expensesService.deleteExpense(expenseId);
    return new ResponseEntity(HttpStatus.NO_CONTENT);
  }

  @GetMapping("/expensesDetails")
  @PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
  public ExpensesDetails getExpensesDetails(
      @AuthenticationPrincipal UserDetailsImpl userDetails,
      @HeaderLang String lang) {
    return this.expensesService.getExpensesDetails(userDetails.getUserId());
  }

  @GetMapping("/previousExpensesDetails")
  @PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
  public List<ExpensesDetails> getPreviousExpensesDetails(
      @AuthenticationPrincipal UserDetailsImpl userDetails,
      @HeaderLang String lang) {
    return this.expensesService.getPreviousExpensesDetails(userDetails.getUserId());
  }
}
package fm.api.rest.expenses;

import fm.api.rest.BaseResource;
import fm.api.rest.expenses.interfaces.IExpensesService;
import fm.auth.UserDetailsImpl;
import fm.common.beans.HeaderLang;
import io.jsonwebtoken.lang.Assert;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by haho on 6/22/2017.
 */
@RestController
public class ExpensesResource extends BaseResource {

  private final IExpensesService expensesService;

  public ExpensesResource(
      @Qualifier("expensesService") IExpensesService expensesService
  ) {
    Assert.notNull(expensesService);

    this.expensesService = expensesService;
  }

  @GetMapping("/expenses")
  @PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
  public List<Expense> getExpenses(
      @AuthenticationPrincipal UserDetailsImpl userDetails,
      @HeaderLang String lang) {
    return this.expensesService.getExpenses(userDetails.getUserId());
  }

  @GetMapping("/expenses2")
  @PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
  public ExpensesDetails getExpenses2(
      @AuthenticationPrincipal UserDetailsImpl userDetails,
      @HeaderLang String lang) {
    return this.expensesService.getExpensesDetails(userDetails.getUserId());
  }
}
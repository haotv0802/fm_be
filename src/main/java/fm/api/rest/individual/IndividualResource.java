package fm.api.rest.individual;

import fm.api.rest.BaseResource;
import fm.api.rest.individual.interfaces.IIndividualService;
import fm.api.rest.moneyflow.Item;
import fm.api.rest.moneyflow.ItemPresenter;
import fm.api.rest.moneyflow.validators.MoneyFlowEditValidation;
import fm.auth.UserDetailsImpl;
import fm.common.beans.HeaderLang;
import io.jsonwebtoken.lang.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

/**
 * Created by haoho on 2/28/20 13:46.
 */
@RestController
public class IndividualResource extends BaseResource {

  private IIndividualService individualService;

  @Autowired
  public IndividualResource(
      @Qualifier("individualService") IIndividualService individualService
  ) {
    Assert.notNull(individualService);

    this.individualService = individualService;
  }

  @GetMapping("/individual")
  @PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
  public IndividualPresenter getIndividual(
      @AuthenticationPrincipal UserDetailsImpl userDetails,
      @HeaderLang String lang
  ) {
    return this.individualService.getIndividual(userDetails.getUserId());
  }

  @PatchMapping("/individual")
  @PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
  public ResponseEntity updateIndividual(
      @AuthenticationPrincipal UserDetailsImpl userDetails,
      @HeaderLang String lang,
      @RequestBody IndividualPresenter item
  ) {
//    MoneyFlowEditValidation validation = new MoneyFlowEditValidation();
//    validation.setUserId(userDetails.getUserId());
//    validation.setExpenseId(item.getId());
//    expenseEditValidator.validate(validation);

//    this.expensesService.updateExpense(item);
    return new ResponseEntity(HttpStatus.NO_CONTENT);
  }

}

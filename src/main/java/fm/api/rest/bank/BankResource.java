package fm.api.rest.bank;

import fm.api.rest.BaseResource;
import fm.api.rest.bank.interfaces.IBankService;
import fm.auth.UserDetailsImpl;
import fm.common.beans.HeaderLang;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by haoho on 3/2/20 10:59.
 */
@RestController
public class BankResource extends BaseResource {

  private IBankService bankService;

  @Autowired
  public BankResource(
      @Qualifier("bankService") IBankService bankService
  ) {
    Assert.notNull(bankService);

    this.bankService = bankService;
  }

  @GetMapping("/bank")
  @PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
  public List<BankPresenter> getBanks(
      @AuthenticationPrincipal UserDetailsImpl userDetails,
      @HeaderLang String lang
  ) {
    return this.bankService.getBanks(userDetails.getUserId());
  }

}

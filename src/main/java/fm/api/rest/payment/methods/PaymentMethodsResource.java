package fm.api.rest.payment.methods;

import fm.api.rest.BaseResource;
import fm.api.rest.payment.methods.beans.CardInformation;
import fm.api.rest.payment.methods.interfaces.IPaymentMethodsService;
import fm.auth.UserDetailsImpl;
import fm.common.beans.HeaderLang;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by haho on 6/23/2017.
 */
@RestController
public class PaymentMethodsResource extends BaseResource {

  private final IPaymentMethodsService paymentMethodsService;

  public PaymentMethodsResource(
      @Qualifier("paymentMethodsService") IPaymentMethodsService paymentMethodsService
  ) {
    Assert.notNull(paymentMethodsService);

    this.paymentMethodsService = paymentMethodsService;
  }

  @GetMapping("/paymentMethods")
  @PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
  public List<CardInformation> getCardsInformation(
      @AuthenticationPrincipal UserDetailsImpl userDetails,
      @HeaderLang String lang) {

    return this.paymentMethodsService.getCardsInformation(userDetails.getUserId());
  }
}

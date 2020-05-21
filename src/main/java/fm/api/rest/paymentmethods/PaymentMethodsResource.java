package fm.api.rest.paymentmethods;

import fm.api.rest.BaseResource;
import fm.api.rest.paymentmethods.beans.PaymentMethodPresenter;
import fm.api.rest.paymentmethods.interfaces.IPaymentMethodsService;
import fm.auth.UserDetailsImpl;
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

    @GetMapping("/paymentMethod/list")
    @PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
    public List<PaymentMethodPresenter> getAllPaymentMethods(
            @AuthenticationPrincipal UserDetailsImpl userDetails) {

        return this.paymentMethodsService.getAllPaymentMethods();
    }
}

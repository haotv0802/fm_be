package fm.api.rest.paymentmethods;

import fm.api.rest.BaseResource;
import fm.api.rest.paymentmethods.beans.PaymentMethodPresenter;
import fm.api.rest.paymentmethods.interfaces.IPaymentMethodsService;
import fm.auth.UserDetailsImpl;
import fm.common.Validator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by haho on 6/23/2017.
 */
@RestController
public class PaymentMethodsResource extends BaseResource {

    private final IPaymentMethodsService paymentMethodsService;

    private final Validator<PaymentMethodPresenter> paymentMethodAddValidator;

    private final Validator<PaymentMethodPresenter> paymentMethodUpdateValidator;

    public PaymentMethodsResource(
            @Qualifier("paymentMethodsService") IPaymentMethodsService paymentMethodsService,
            @Qualifier("paymentMethodAddValidator") Validator<PaymentMethodPresenter> paymentMethodAddValidator,
            @Qualifier("paymentMethodUpdateValidator") Validator<PaymentMethodPresenter> paymentMethodUpdateValidator
    ) {
        Assert.notNull(paymentMethodsService);
        Assert.notNull(paymentMethodAddValidator);
        Assert.notNull(paymentMethodUpdateValidator);

        this.paymentMethodsService = paymentMethodsService;
        this.paymentMethodAddValidator = paymentMethodAddValidator;
        this.paymentMethodUpdateValidator = paymentMethodUpdateValidator;
    }

    @PostMapping("/paymentMethod")
    @PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
    public ResponseEntity addPaymentMethod(
            @AuthenticationPrincipal UserDetailsImpl userDetails,
            @Valid @RequestBody PaymentMethodPresenter paymentMethod
    ) {
        // validate if name is already existing.
        paymentMethodAddValidator.validate(paymentMethod);

        Integer id = this.paymentMethodsService.addPaymentMethod(paymentMethod);
        return new ResponseEntity<>(new Object() {
            public final Integer paymentMethodId = id;
        }, HttpStatus.CREATED);
    }

    @PatchMapping("/paymentMethod")
    @PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
    public ResponseEntity updatePaymentMethod(
            @AuthenticationPrincipal UserDetailsImpl userDetails,
            @Valid @RequestBody PaymentMethodPresenter paymentMethod
    ) {
        // validate if name is already existing.
        paymentMethodUpdateValidator.validate(paymentMethod);

        this.paymentMethodsService.updatePaymentMethod(paymentMethod);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/paymentMethod/list")
    @PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
    public List<PaymentMethodPresenter> getAllPaymentMethods(
            @AuthenticationPrincipal UserDetailsImpl userDetails) {

        return this.paymentMethodsService.getAllPaymentMethods();
    }
}

package fm.api.rest.bank;

import fm.api.rest.BaseResource;
import fm.api.rest.bank.interfaces.IBankService;
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

import javax.validation.Valid;
import java.util.List;

/**
 * Created by haoho on 3/2/20 10:59.
 */
@RestController
public class BankResource extends BaseResource {

    private final IBankService bankService;

    private final Validator<BankPresenter> bankAddValidator;

    private final Validator<BankPresenter> bankUpdateValidator;

    @Autowired
    public BankResource(
            @Qualifier("bankService") IBankService bankService,
            @Qualifier("bankAddValidator") Validator<BankPresenter> bankAddValidator,
            @Qualifier("bankUpdateValidator") Validator<BankPresenter> bankUpdateValidator
    ) {
        Assert.notNull(bankService);
        Assert.notNull(bankAddValidator);
        Assert.notNull(bankUpdateValidator);

        this.bankService = bankService;
        this.bankAddValidator = bankAddValidator;
        this.bankUpdateValidator = bankUpdateValidator;
    }

    @GetMapping("/bank")
    @PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
    public List<BankPresenter> getBanks(
            @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {
        return this.bankService.getBanks(userDetails.getUserId());
    }

    @PostMapping("/bank")
    @PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
    public ResponseEntity addBank(
            @AuthenticationPrincipal UserDetailsImpl userDetails,
            @Valid @RequestBody BankPresenter bank
    ) {

        this.bankAddValidator.validate(bank);

        Integer id = this.bankService.addBank(bank);
        return new ResponseEntity<>(new Object() {
            public final Integer bankId = id;
        }, HttpStatus.CREATED);
    }

    @PatchMapping("/bank")
    @PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
    public ResponseEntity updateBank(
            @AuthenticationPrincipal UserDetailsImpl userDetails,
            @Valid @RequestBody BankPresenter bank
    ) {
        this.bankUpdateValidator.validate(bank);

        this.bankService.updateBank(bank);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/bank/all")
    @PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
    public List<BankPresenter> getAllBanks(
            @AuthenticationPrincipal UserDetailsImpl userDetails,
            @HeaderLang String lang
    ) {
        return this.bankService.getAllBanks();
    }

}

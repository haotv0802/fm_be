package fm.api.rest.moneysource;

import fm.api.rest.BaseResource;
import fm.api.rest.moneysource.interfaces.IMoneySourceService;
import fm.auth.UserDetailsImpl;
import fm.common.Validator;
import io.jsonwebtoken.lang.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by haoho on 3/2/20 14:55.
 */
@RestController
public class MoneySourceResource extends BaseResource {

    private final IMoneySourceService moneySourceService;

    private final Validator<MoneySourcePresenter> moneySourceAddValidator;

    private final Validator<MoneySourcePresenter> moneySourceUpdateValidator;

    @Autowired
    public MoneySourceResource(
            @Qualifier("moneySourceService") IMoneySourceService moneySourceService,
            @Qualifier("moneySourceAddValidator") Validator<MoneySourcePresenter> moneySourceAddValidator,
            @Qualifier("moneySourceUpdateValidator") Validator<MoneySourcePresenter> moneySourceUpdateValidator) {
        Assert.notNull(moneySourceService);
        Assert.notNull(moneySourceAddValidator);
        Assert.notNull(moneySourceUpdateValidator);

        this.moneySourceService = moneySourceService;
        this.moneySourceAddValidator = moneySourceAddValidator;
        this.moneySourceUpdateValidator = moneySourceUpdateValidator;
    }

    @GetMapping("/moneysource")
    @PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
    public List<MoneySourcePresenter> getMoneySources(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        return moneySourceService.getMoneySources(userDetails.getUserId());
    }

    @PatchMapping("/moneysource")
    @PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
    public ResponseEntity updateMoneySource(
            @RequestBody MoneySourcePresenter moneySource
    ) {
        moneySourceUpdateValidator.validate(moneySource);

        this.moneySourceService.updateMoneySource(moneySource);

        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/moneysource")
    @PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
    public ResponseEntity addMoneySource(
            @AuthenticationPrincipal UserDetailsImpl userDetails,
            @RequestBody MoneySourcePresenter moneySource
    ) {
        moneySourceAddValidator.validate(moneySource);

        Integer id = this.moneySourceService.addMoneySource(moneySource, userDetails.getUserId());
        return new ResponseEntity<>(new Object() {
            public final Integer moneySourceId = id;
        }, HttpStatus.CREATED);
    }
}

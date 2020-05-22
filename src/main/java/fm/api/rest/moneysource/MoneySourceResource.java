package fm.api.rest.moneysource;

import fm.api.rest.BaseResource;
import fm.api.rest.moneysource.interfaces.IMoneySourceService;
import fm.auth.UserDetailsImpl;
import io.jsonwebtoken.lang.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by haoho on 3/2/20 14:55.
 */
@RestController
public class MoneySourceResource extends BaseResource {

    private IMoneySourceService moneySourceService;

    @Autowired
    public MoneySourceResource(
            @Qualifier("moneySourceService") IMoneySourceService moneySourceService
    ) {
        Assert.notNull(moneySourceService);

        this.moneySourceService = moneySourceService;
    }

    @PatchMapping("/moneysource")
    @PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
    public ResponseEntity updateMoneySource(
            @AuthenticationPrincipal UserDetailsImpl userDetails,
            @RequestBody MoneySourcePresenter moneySource
    ) {
        this.moneySourceService.updateMoneySource(moneySource);

        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/moneysource")
    @PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
    public ResponseEntity addMoneySource(
            @AuthenticationPrincipal UserDetailsImpl userDetails,
            @RequestBody MoneySourcePresenter moneySource
    ) {
        Integer id = this.moneySourceService.addMoneySource(moneySource, userDetails.getUserId());
        return new ResponseEntity<>(new Object() {
            public final Integer moneySourceId = id;
        }, HttpStatus.CREATED);
    }
}

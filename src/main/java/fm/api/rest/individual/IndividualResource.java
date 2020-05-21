package fm.api.rest.individual;

import fm.api.rest.BaseResource;
import fm.api.rest.individual.interfaces.IIndividualService;
import fm.auth.UserDetailsImpl;
import fm.common.Validator;
import fm.common.beans.HeaderLang;
import io.jsonwebtoken.lang.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * Created by haoho on 2/28/20 13:46.
 */
@RestController
public class IndividualResource extends BaseResource {

    private IIndividualService individualService;

    private Validator<IndividualPresenter> individualUpdateValidator;

    @Autowired
    public IndividualResource(
            @Qualifier("individualService") IIndividualService individualService,
            @Qualifier("individualUpdateValidator") Validator<IndividualPresenter> individualUpdateValidator
    ) {
        Assert.notNull(individualService);
        Assert.notNull(individualUpdateValidator);

        this.individualService = individualService;
        this.individualUpdateValidator = individualUpdateValidator;
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
            @Valid @RequestBody IndividualPresenter item
    ) {
        item.setUserId(userDetails.getUserId());

        individualUpdateValidator.validate(item);

        Long id = this.individualService.saveIndividual(item);
        return new ResponseEntity<>(new Object() {
            public final Long individualId = id;
        }, HttpStatus.CREATED);
    }

}

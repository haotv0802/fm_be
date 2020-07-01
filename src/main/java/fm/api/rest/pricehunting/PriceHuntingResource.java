package fm.api.rest.pricehunting;

import fm.api.rest.BaseResource;
import fm.api.rest.individual.IndividualPresenter;
import fm.api.rest.individual.interfaces.IIndividualService;
import fm.api.rest.pricehunting.interfaces.IPriceHuntingService;
import fm.auth.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by HaoHo on 6/8/2020
 */
@RestController
public class PriceHuntingResource extends BaseResource {

    private final IPriceHuntingService priceHuntingService;

    private final IIndividualService individualService;

    @Autowired
    public PriceHuntingResource(@Qualifier("priceHuntingService") IPriceHuntingService priceHuntingService,
                                @Qualifier("individualService") IIndividualService individualService) {
        Assert.notNull(priceHuntingService);
        Assert.notNull(individualService);

        this.individualService = individualService;
        this.priceHuntingService = priceHuntingService;
    }

    @PatchMapping("/price/hunt")
    @PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
    public ResponseEntity savePrice(
            @AuthenticationPrincipal UserDetailsImpl userDetails,
            @RequestBody Price price
    ) {
        this.priceHuntingService.savePrice(price);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PatchMapping("/price/list")
    @PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
    public List<Price> getPrices(
            @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {
        IndividualPresenter individualPresenter = this.individualService.getIndividual(userDetails.getUserId());

        return this.priceHuntingService.getPrices(individualPresenter.getEmail());
    }

    @GetMapping("/price/check")
    @PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
    public ResponseEntity checkPrice(
            @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {
        this.priceHuntingService.checkPriceAndNotify();

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

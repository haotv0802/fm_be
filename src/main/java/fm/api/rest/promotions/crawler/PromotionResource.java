package fm.api.rest.promotions.crawler;


import fm.api.rest.BaseResource;
import fm.api.rest.promotions.crawler.interfaces.IBankPromotion;
import fm.auth.UserDetailsImpl;
import fm.common.beans.HeaderLang;
import io.jsonwebtoken.lang.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * Quy created on 3/11/2020
 */
@RestController
public class PromotionResource extends BaseResource {

    private IBankPromotion bankPromotion;

    @Autowired
    public PromotionResource(@Qualifier("bankPromotion") IBankPromotion bankPromotion) {
        Assert.notNull(bankPromotion);
        this.bankPromotion = bankPromotion;
    }

    @GetMapping("/promotions/crawler/{bankID}")
    @PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
    public String crawlingPromotion(@AuthenticationPrincipal UserDetailsImpl userDetails,
                                    @PathVariable String bankID) {

        this.bankPromotion.crawl(bankID);
        String message = "Success";
        return message;
    }

}

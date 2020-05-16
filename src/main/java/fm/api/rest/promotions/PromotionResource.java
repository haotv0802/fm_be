package fm.api.rest.promotions;


import fm.api.rest.BaseResource;
import fm.api.rest.promotions.crawler.interfaces.IBankPromotion;
import fm.api.rest.promotions.crawler.interfaces.IBankPromotionCrawler;
import fm.auth.UserDetailsImpl;
import fm.common.beans.HeaderLang;
import io.jsonwebtoken.lang.Assert;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.ServiceLoader;

/**
 * Quy created on 3/11/2020
 */
@RestController
public class PromotionResource extends BaseResource {

    private static final Logger logger = LogManager.getLogger(PromotionResource.class);

    private IBankPromotion bankPromotion;

    @Autowired
    public PromotionResource(@Qualifier("bankPromotion") IBankPromotion bankPromotion) {
        Assert.notNull(bankPromotion);
        this.bankPromotion = bankPromotion;
    }

    @GetMapping("/promotions/crawler/{bankID}")
    @PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
    public void crawlingPromotion(@AuthenticationPrincipal UserDetailsImpl userDetails,
                                    @PathVariable String bankID) {

        this.bankPromotion.crawl(bankID);
    }

    @GetMapping("/promotions/crawlall")
    @PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
    public void crawlAll(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        this.bankPromotion.crawlAll();
    }

}

package fm.api.rest.promotions;


import fm.api.rest.BaseResource;
import fm.api.rest.promotions.crawler.interfaces.IBankPromotion;
import fm.api.rest.promotions.crawler.interfaces.IBankPromotionCrawler;
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

import java.util.ServiceLoader;

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
    public void crawlingPromotion(@AuthenticationPrincipal UserDetailsImpl userDetails,
                                    @PathVariable String bankID) {

        this.bankPromotion.crawl(bankID);
    }

    @GetMapping("/promotions/crawlall")
    @PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
    public void crawlAll(@AuthenticationPrincipal UserDetailsImpl userDetails) {

//        this.bankPromotion.crawl(bankID);

//        ServiceLoader<IBankPromotionCrawler> loader = ServiceLoader.load(IBankPromotionCrawler.class);
//        for (IBankPromotionCrawler implClass : loader) {
//            System.out.println(implClass.getClass().getSimpleName()); // prints Dog, Cat
//        }
//
//        Package[] packages = Package.getPackages();
//        for (Package p : packages) {
//            IBankPromotionCrawler annotation = p.getAnnotation(IBankPromotionCrawler.class);
//            if (annotation != null) {
//                Class<?>[]  implementations = annotation.();
//                for (Class<?> impl : implementations) {
//                    System.out.println(impl.getSimpleName());
//                }
//            }
//        }

    }
}

package fm.api.rest.promotions.crawler;
/* Quy created on 3/11/2020  */

import fm.api.rest.BaseResource;
import fm.api.rest.promotions.crawler.interfaces.IBankPromotionCrawler;
import fm.api.rest.promotions.crawler.interfaces.IBankPromotionService;
import fm.api.rest.promotions.crawler.interfaces.IPromotionCrawlerService;
import fm.auth.UserDetailsImpl;
import fm.common.beans.HeaderLang;
import io.jsonwebtoken.lang.Assert;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

@RestController
public class PromotionCrawlerResouce extends BaseResource {
    private static final Logger LOGGER = LogManager.getLogger(PromotionCrawlerResouce.class);
    private IBankPromotionCrawler bankPromotionCrawler;
    private IPromotionCrawlerService bankPromotionService;

    @Autowired
    public PromotionCrawlerResouce(
            @Qualifier("bankPromotionCrawler") IBankPromotionCrawler bankPromotionCrawler,
            @Qualifier("promotionCrawlerService") IPromotionCrawlerService bankPromotionService
    ) {
        Assert.notNull(bankPromotionCrawler);
        this.bankPromotionCrawler = bankPromotionCrawler;
        this.bankPromotionService = bankPromotionService;
    }

    @GetMapping("/promotions/crawler/{bankID}")
    @PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
    public String crawlingPromotion(@AuthenticationPrincipal UserDetailsImpl userDetails,
                                    @HeaderLang String lang,
                                    @PathVariable int bankID){
        String message = "FALSE";
        Map<String, List<PromotionCrawlerModel>> listMap = new TreeMap<>();
        switch (bankID){
            case 0:System.out.println("SCB");
                message="Success SCB";
                listMap= bankPromotionCrawler.SCBBankPromotion();
            break;
            case 1:System.out.println("VIB");
                message="Success VIB";
//                bankPromotionService.getPrmoTionByBankIdAndCate(2,1);
                PromotionCrawlerModel model = new PromotionCrawlerModel("Agoda","Giảm 10% đặt phòng","7%","","2020-04-10","1","2","AA","AA","AA","A","AA","A");
                bankPromotionService.insertBankPromotion(model);
//                listMap=bankPromotionCrawler.VIBBankPromotion();
//                for(String item : listMap.keySet()){
//                    for(PromotionCrawlerModel model : listMap.get(item)){
//                        bankPromotionService.insertBankPromotion(model);
//                    }
//                }


            break;
            case 2:System.out.println("ShinHan");
                message="Success SH";
                bankPromotionCrawler.SHBankPromotion();
                break;
        }


        return message;
    }

}

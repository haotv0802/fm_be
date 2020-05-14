 /* Quy created on 3/16/2020 */
 package fm.api.rest.promotions.crawler;

 import fm.api.rest.promotions.crawler.interfaces.IBankPromotion;
 import fm.api.rest.promotions.crawler.interfaces.IPromotionCrawlerService;
 import io.jsonwebtoken.lang.Assert;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.beans.factory.annotation.Qualifier;
 import org.springframework.stereotype.Service;

 import java.util.List;
 import java.util.Map;

 @Service("bankPromotion")
 public class BankPromotionImpl implements IBankPromotion {
   private BankCrawlerFactory bankCrawlerFactory;
   private IPromotionCrawlerService promotionCrawlerService;

   @Autowired
   public BankPromotionImpl(@Qualifier("bankCrawlerFactory") BankCrawlerFactory bankCrawlerFactory,
                            @Qualifier("promotionCrawlerService") IPromotionCrawlerService promotionCrawlerService) {
     Assert.notNull(bankCrawlerFactory);
     Assert.notNull(promotionCrawlerService);
     this.bankCrawlerFactory = bankCrawlerFactory;
     this.promotionCrawlerService = promotionCrawlerService;
   }

   @Override
   public void crawl(String bankName) {
     Map<Integer, List<PromotionCrawlerModel>> map = this.bankCrawlerFactory.getBankCrawler(bankName).crawl();
     /// DAO check, ínert, updte
     // Get Each Infomation of promotion insert into DB
     for (Integer category : map.keySet()) {
       for (PromotionCrawlerModel model : map.get(category)) {
         this.promotionCrawlerService.insertBankPromotion(model);
       }

     }
   }

 }
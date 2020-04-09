 /* Quy created on 3/22/2020 */
 package fm.api.rest.promotions.crawler;

 import fm.api.rest.promotions.PromotionPresenter;
 import fm.api.rest.promotions.crawler.interfaces.IPromotionCrawlerDAO;
 import fm.api.rest.promotions.crawler.interfaces.IPromotionCrawlerService;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.beans.factory.annotation.Qualifier;
 import org.springframework.stereotype.Service;
 import org.springframework.util.Assert;

 import java.util.List;

 @Service("promotionCrawlerService")
 public class PromotionCrawlerService implements IPromotionCrawlerService {
     private IPromotionCrawlerDAO promotionCrawlerDAO;

     @Autowired
     public PromotionCrawlerService(@Qualifier("promotionCrawlerDao") IPromotionCrawlerDAO promotionCrawlerDAO) {
         Assert.notNull(promotionCrawlerDAO);
         this.promotionCrawlerDAO = promotionCrawlerDAO;
     }


     @Override
     public String insertBankPromotion(PromotionCrawlerModel moddel) {
         String message = "FAILED";
         boolean check = promotionCrawlerDAO.savePromotion(moddel);
         if (check) {
             message = "SUCCESS";
         }
         return message;
     }

     @Override
     public List<PromotionPresenter> getPrmoTionByBankIdAndCate(int bankID) {
         return promotionCrawlerDAO.getPrmoTionByBankIdAndCate(bankID);
     }
 }

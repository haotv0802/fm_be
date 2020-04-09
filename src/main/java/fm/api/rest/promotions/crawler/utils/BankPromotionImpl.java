 /* Quy created on 3/16/2020 */
 package fm.api.rest.promotions.crawler.utils;

 import fm.api.rest.promotions.crawler.interfaces.IBankPromotion;
 import io.jsonwebtoken.lang.Assert;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.beans.factory.annotation.Qualifier;
 import org.springframework.stereotype.Service;

 import java.util.Map;

 @Service("bankPromotion")
 public class BankPromotionImpl implements IBankPromotion {
     private BankCrawlerFactory bankCrawlerFactory;

     @Autowired
     public BankPromotionImpl(@Qualifier("bankCrawlerFactory") BankCrawlerFactory bankCrawlerFactory) {
         Assert.notNull(bankCrawlerFactory);
         this.bankCrawlerFactory = bankCrawlerFactory;
     }

     @Override
     public void crawl(String bankName) {
         Map map = this.bankCrawlerFactory.getBankCrawler(bankName).crawl();


         /// DAO check, ínert, updte
     }

//     @Override
//     public Map<String, List<PromotionCrawlerModel>> SCBBankPromotion() {
//         SCBCrawler bank = new SCBCrawler();
//         return bank.getListPromotionInfo();
//     }
//
//     @Override
//     public Map<String, List<PromotionCrawlerModel>> VIBBankPromotion(List<PromotionPresenter> promotionPresenterList) {
//         return bankPromotionCrawler.VIBBankPromotion(promotionPresenterList);
//     }
//
//     @Override
//     public boolean SHBankPromotion() {
//         ShinHanBank bank = new ShinHanBank();
//         return bank.doPostRequest("https://shinhan.com.vn/get_shinhan_promotion");
//
//     }
 }

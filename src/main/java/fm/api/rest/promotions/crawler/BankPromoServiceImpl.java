 /* Quy created on 3/13/2020 */
 package fm.api.rest.promotions.crawler;

 import fm.api.rest.promotions.PromotionPresenter;
 import fm.api.rest.promotions.crawler.interfaces.IBankPromotion;
 import fm.api.rest.promotions.crawler.interfaces.IBankService;
 import fm.api.rest.promotions.crawler.utils.SCBCrawler;
 import fm.api.rest.promotions.crawler.utils.ShinHanBank;
 import io.jsonwebtoken.lang.Assert;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.beans.factory.annotation.Qualifier;

 import java.util.List;

 public class BankPromoServiceImpl implements IBankService {

     private IBankPromotion bankPromotionCrawler;

     @Autowired
     public BankPromoServiceImpl(@Qualifier("vibBank") IBankPromotion bankPromotionCrawler) {
         Assert.notNull(bankPromotionCrawler);
         this.bankPromotionCrawler = bankPromotionCrawler;
     }


     @Override
     public void SCBBankPromoCrawling() {
//         SCBCrawler bank = new SCBCrawler();
//         bank.getListPromotionInfo();
     }

     @Override
     public void VIBBankPromoCrawling(List<PromotionPresenter> promotionPresenterList) {
//         bankPromotionCrawler.VIBBankPromotion(promotionPresenterList);
     }

     @Override
     public void SHBankPromotionCrawling() {
//         ShinHanBank bank = new ShinHanBank();
//         bank.doPostRequest("");
     }
 }

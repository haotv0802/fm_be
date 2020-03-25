 /* Quy created on 3/16/2020 */
 package fm.api.rest.promotions.crawler.utils;

 import fm.api.rest.promotions.crawler.PromotionCrawlerModel;
 import fm.api.rest.promotions.crawler.interfaces.IBankPromotionCrawler;
 import org.springframework.stereotype.Service;

 import java.util.List;
 import java.util.Map;

 @Service("bankPromotionCrawler")
 public class BankPromotionCrawlerImpl implements IBankPromotionCrawler {
  @Override
  public Map<String, List<PromotionCrawlerModel>> SCBBankPromotion() {
   SCBBank bank = new SCBBank();
   return bank.getListPromotionInfo();
  }

  @Override
  public Map<String, List<PromotionCrawlerModel>> VIBBankPromotion() {
   VIBBank bank= new VIBBank();
   return bank.getListPromotionInfo();
  }

  @Override
  public boolean SHBankPromotion() {
   ShinHanBank bank = new ShinHanBank();
   return bank.doPostRequest("https://shinhan.com.vn/get_shinhan_promotion");

  }
 }

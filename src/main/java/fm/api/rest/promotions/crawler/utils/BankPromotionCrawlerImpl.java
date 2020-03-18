 /* Quy created on 3/16/2020 */
 package fm.api.rest.promotions.crawler.utils;

 import fm.api.rest.promotions.crawler.interfaces.IBankPromotionCrawler;
 import org.springframework.stereotype.Service;

 @Service("bankPromotionCrawler")
 public class BankPromotionCrawlerImpl implements IBankPromotionCrawler {
  @Override
  public boolean SCBBankPromotion() {
   SCBBank bank = new SCBBank();
   return bank.getListPromotionInfo();
  }

  @Override
  public boolean VIBBankPromotion() {
   VIBBank bank= new VIBBank();
   return bank.getListPromotionInfo();
  }

  @Override
  public boolean SHBankPromotion() {
   ShinHanBank bank = new ShinHanBank();
   return bank.doPostRequest("https://shinhan.com.vn/get_shinhan_promotion");

  }
 }

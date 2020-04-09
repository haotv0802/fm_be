 /* Quy created on 3/13/2020 */
 package fm.api.rest.promotions.crawler.interfaces;

 import fm.api.rest.promotions.PromotionPresenter;
 import fm.api.rest.promotions.crawler.PromotionCrawlerModel;

 import java.util.List;
 import java.util.Map;

 public interface IBankPromotion {
//  Map<String, List<PromotionCrawlerModel>> SCBBankPromotion();
//  Map<String,List<PromotionCrawlerModel>> VIBBankPromotion(List<PromotionPresenter> listBankPromoInfo);
//  boolean SHBankPromotion();



  void crawl(String bankName);
 }

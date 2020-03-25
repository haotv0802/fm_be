 /* Quy created on 3/13/2020 */
 package fm.api.rest.promotions.crawler.interfaces;

 import fm.api.rest.promotions.crawler.PromotionCrawlerModel;

 import java.util.List;
 import java.util.Map;

 public interface IBankPromotionCrawler {
  Map<String, List<PromotionCrawlerModel>> SCBBankPromotion();
  Map<String,List<PromotionCrawlerModel>> VIBBankPromotion();
  boolean SHBankPromotion();
 }

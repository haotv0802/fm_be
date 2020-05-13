// /* Quy created on 4/8/2020 */
 package fm.api.rest.promotions.crawler.interfaces;

 import fm.api.rest.promotions.crawler.PromotionCrawlerModel;

 import java.util.List;
 import java.util.Map;

 public interface IBankPromotionCrawler {

  Map<Integer, List<PromotionCrawlerModel>> crawl();

 }

 /* Quy created on 3/22/2020 */
 package fm.api.rest.promotions.crawler.interfaces;

 import fm.api.rest.promotions.crawler.PromotionCrawlerModel;
 import org.jsoup.select.Elements;

 import java.util.List;
 import java.util.Map;

 public interface IBankPromotionService {
  Map<String,List<PromotionCrawlerModel>> getListPromotionInfo();
  PromotionCrawlerModel getPromotionFromLink(String link, String categoryName);
  String getDetail(Elements container , String selector , String tagName);
  List<String> getLocations(Elements container);
 }

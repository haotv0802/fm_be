package fm.api.rest.promotions.crawler.interfaces;
/* Quy created on 3/11/2020  */

import java.util.List;
import java.util.Map;

public interface IPromotionCrawlerDAO {
 boolean savePromotion(Map<String, List> listMapPromotions);
}

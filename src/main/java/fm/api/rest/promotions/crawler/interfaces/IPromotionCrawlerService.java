package fm.api.rest.promotions.crawler.interfaces;
/* Quy created on 3/11/2020  */
import fm.api.rest.promotions.crawler.PromotionCrawlerModel;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.util.List;
@Service("promotionCrawlerService")
public interface IPromotionCrawlerService {

    boolean getListPromotionInfo();
    PromotionCrawlerModel getPromotionFromLink(String link, String categoryName);
    String getDetail(Elements container , String selector , String tagName);
    List<String> getLocations(Elements container);
}

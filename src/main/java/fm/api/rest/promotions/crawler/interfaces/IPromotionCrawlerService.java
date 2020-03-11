package fm.api.rest.promotions.crawler.interfaces;

import fm.api.rest.promotions.crawler.PromotionCrawlerModel;
import org.jsoup.select.Elements;

import java.util.List;

public interface IPromotionCrawlerService {

    void getListPromotionInfo();
    PromotionCrawlerModel getPromotionFromLink(String link, String categoryName);
    String getDetail(Elements container , String selector , String tagName);
    List<String> getLocations(Elements container);
}

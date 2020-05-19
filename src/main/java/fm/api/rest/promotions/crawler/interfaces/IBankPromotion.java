package fm.api.rest.promotions.crawler.interfaces;

import fm.api.rest.promotions.PromotionPresenter;
import fm.api.rest.promotions.crawler.PromotionCrawlerModel;

import java.util.List;
import java.util.Map;

/**
 * Quy created on 3/13/2020
 */
public interface IBankPromotion {
    void crawl(String bankName);

    void crawlAll();

    List<PromotionPresenter> getCrawledData();

    void saveCrawledData(Map<Integer, List<PromotionCrawlerModel>> data);
}

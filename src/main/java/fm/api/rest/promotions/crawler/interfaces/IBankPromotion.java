package fm.api.rest.promotions.crawler.interfaces;

import fm.api.rest.promotions.PromotionPresenter;

import java.util.List;

/**
 * Quy created on 3/13/2020
 */
public interface IBankPromotion {
    void crawl(String bankName);

    void crawlAll();

    void crawlAllByMultiThreads();

    List<PromotionPresenter> getCrawledData();
}

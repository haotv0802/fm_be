package fm.api.rest.promotions.crawler.interfaces;

/**
 * Quy created on 3/13/2020
 */
public interface IBankPromotion {
    void crawl(String bankName);

    void crawlAll();
}

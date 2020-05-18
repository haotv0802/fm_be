package fm.api.rest.promotions.crawler.interfaces;

import fm.api.rest.promotions.PromotionPresenter;
import fm.api.rest.promotions.crawler.PromotionCrawlerModel;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Quy created on 3/11/2020
 */
public interface IPromotionCrawlerDAO {
    void addPromotion(PromotionCrawlerModel model);

    void updatePromotion(PromotionPresenter model);

    List<PromotionPresenter> getPromotionByBankId(int bankID, int category_id);

    Map<String, Integer> getCategoryAndId();

    Boolean isPromotionExisting(String url, String title, Date endDate);

    PromotionPresenter getPromotion(String url, String title, Date endDate);
}

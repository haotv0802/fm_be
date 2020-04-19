package fm.api.rest.promotions.crawler.interfaces;
/* Quy created on 3/11/2020  */

import fm.api.rest.promotions.PromotionPresenter;
import fm.api.rest.promotions.crawler.PromotionCrawlerModel;

import java.util.List;
import java.util.Map;

public interface IPromotionCrawlerDAO {
    boolean savePromotion(PromotionCrawlerModel model);

    List<PromotionPresenter> getPrmoTionByBankId(int bankID);

    Map<String, Integer> getCategoryAndId();
}

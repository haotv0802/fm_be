package fm.api.rest.promotions.crawler.interfaces;
/* Quy created on 3/11/2020  */

import fm.api.rest.promotions.PromotionPresenter;
import fm.api.rest.promotions.crawler.PromotionCrawlerModel;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("promotionCrawlerService")
public interface IPromotionCrawlerService {

  String insertBankPromotion(PromotionCrawlerModel model);

  List<PromotionPresenter> getPrmoTionByBankId(int bankID, int cateID);

}

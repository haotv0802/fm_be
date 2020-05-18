package fm.api.rest.promotions.interfaces;

import fm.api.rest.promotions.PromotionPresenter;

import java.util.List;

/**
 * Created by haoho on 2/27/20 10:07.
 */
public interface IPromotionDao {

  List<PromotionPresenter> getAllPromotions(String title, String content, String start_date, String end_date, Integer bank_id, Integer category_id);
}

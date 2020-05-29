package fm.api.rest.promotions.interfaces;

import fm.api.rest.promotions.PromotionPresenter;

import java.time.LocalDate;
import java.util.List;

/**
 * Created by haoho on 2/27/20 10:07.
 */
public interface IPromotionService {
  List<PromotionPresenter> getAllPromotions (String title, String content, LocalDate start_date, LocalDate end_date, Integer bank_id, Integer category_id);
}

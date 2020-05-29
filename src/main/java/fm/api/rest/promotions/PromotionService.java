
package fm.api.rest.promotions;

import fm.api.rest.promotions.interfaces.IPromotionDao;
import fm.api.rest.promotions.interfaces.IPromotionService;
import fm.utils.FmDateUtils;
import io.jsonwebtoken.lang.Assert;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

/**
 * Created by Quy on 5/18/2020.
 */
@Service("promotionService")
public class PromotionService implements IPromotionService {

  private static final Logger logger = LogManager.getLogger(PromotionService.class);

  private IPromotionDao promotionDao;

  public PromotionService(@Qualifier("promotionDao") IPromotionDao promotionDao) {
    Assert.notNull(promotionDao);
    this.promotionDao = promotionDao;
  }

  @Override
  public List<PromotionPresenter> getAllPromotions(String title, String content, LocalDate start_date, LocalDate end_date, Integer bank_id, Integer category_id) {
    return this.promotionDao.getAllPromotions(title, content, start_date, end_date, bank_id, category_id);
  }
}

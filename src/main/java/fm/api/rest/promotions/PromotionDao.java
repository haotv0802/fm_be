package fm.api.rest.promotions;

import fm.api.rest.promotions.interfaces.IPromotionDao;
import fm.common.dao.DaoUtils;
import fm.utils.FmDateUtils;
import io.jsonwebtoken.lang.Assert;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by Quy on 5/18/2020.
 */
@Service("promotionDao")
public class PromotionDao implements IPromotionDao {
  private static final Logger logger = LogManager.getLogger(PromotionDao.class);

  private final NamedParameterJdbcTemplate namedTemplate;

  public PromotionDao(NamedParameterJdbcTemplate namedTemplate) {
    Assert.notNull(namedTemplate);
    this.namedTemplate = namedTemplate;
  }


  @Override
  public List<PromotionPresenter> getAllPromotions(String title, String content, String start_date, String end_date, Integer bank_id, Integer category_id) {
    final String sql =
        "  SELECT                                    "
            + "  id,                                 "
            + "  title,                              "
            + "  content,                            "
            + "  discount,                           "
            + "  installment,                        "
            + "  start_date,                         "
            + "  end_date,                           "
            + "  category_id,                        "
            + "  bank_id,                            "
            + "  category_id                         "
            + "FROM fm_promotions                    "
            + "WHERE title LIKE :title               "
            + (content != null || !StringUtils.isEmpty(content) ? "AND content LIKE :content" : "")
            + (start_date != null || !StringUtils.isEmpty(start_date) ? "AND  start_date >= :start_date" : "")
            + (end_date != null || !StringUtils.isEmpty(end_date) ?  "AND end_date <=:end_date" : "")
            + (bank_id != null ? " AND bank_id = :bank_id" : "")
            + (category_id != null ? " AND category_id = :category_id" : "");
    final MapSqlParameterSource paramsMap = new MapSqlParameterSource();
    paramsMap.addValue("title", "%" + title + "%");
    paramsMap.addValue("content", "%" + content + "%");
    if (!StringUtils.isEmpty(start_date)) {
      paramsMap.addValue("start_date", start_date.replaceAll("/", "-"));
    }
    if (!StringUtils.isEmpty(end_date)) {
      paramsMap.addValue("end_date", end_date.replaceAll("/", "-"));

    }
    paramsMap.addValue("bank_id", bank_id);
    paramsMap.addValue("category_id", category_id);

    DaoUtils.debugQuery(logger, sql, paramsMap.getValues());

    try {
      return namedTemplate.query(sql, paramsMap, (rs, rowNum) -> buildPromotionPresenter(rs));
    } catch (EmptyResultDataAccessException e) {
      e.printStackTrace();
    }
    return null;
  }


  private PromotionPresenter buildPromotionPresenter(ResultSet rs) throws SQLException {
    PromotionPresenter presenter = new PromotionPresenter();
    presenter.setId(rs.getLong("id"));
    presenter.setTitle(rs.getString("title"));
    presenter.setContent(rs.getString("content"));
    presenter.setDiscount(rs.getString("discount"));
    presenter.setInstallmentPeriod(rs.getString("installment"));
    presenter.setStartDate(rs.getString("start_date"));
    presenter.setEndDate(rs.getString("end_date"));
    presenter.setCategoryId(rs.getInt("category_Id"));
    presenter.setBankId(rs.getInt("bank_id"));
    return presenter;
  }
}

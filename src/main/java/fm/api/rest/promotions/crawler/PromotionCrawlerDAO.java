package fm.api.rest.promotions.crawler;

import fm.api.rest.promotions.PromotionPresenter;
import fm.api.rest.promotions.crawler.interfaces.IPromotionCrawlerDAO;
import fm.common.dao.DaoUtils;
import fm.utils.FmLocalDateUtils;
import io.jsonwebtoken.lang.Assert;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Quy created on 3/11/2020
 */
@Service("promotionCrawlerDao")
public class PromotionCrawlerDAO implements IPromotionCrawlerDAO {

    private static final Logger logger = LogManager.getLogger(PromotionCrawlerDAO.class);
    private final NamedParameterJdbcTemplate namedTemplate;

    @Autowired
    public PromotionCrawlerDAO(NamedParameterJdbcTemplate namedTemplate) {
        Assert.notNull(namedTemplate);
        this.namedTemplate = namedTemplate;
    }

    /**
     * @param promoModel
     * @return
     */
    @Override
    public void addPromotion(PromotionCrawlerModel promoModel) {
        final String sqlStatement = ""
                + "INSERT INTO      "
                + " fm_promotions   "
                + "    (title,      "
                + "    content,     "
                + "    discount,    "
                + "    installment, "
                + "    start_date,  "
                + "    end_date,    "
                + "    category_id, "
                + "    url ,        "
                + "    bank_id)     "
                + " VALUES          "
                + "   (:title,      "
                + "   :content,     "
                + "   :discount,    "
                + "   :installment ,"
                + "   :start_date,  "
                + "   :end_date,    "
                + "   :category_id, "
                + "   :url,         "
                + "   :bank_id)     ";
        final MapSqlParameterSource paramsMap = new MapSqlParameterSource();
        paramsMap.addValue("title", promoModel.getTitle());
        paramsMap.addValue("content", promoModel.getContent());
        paramsMap.addValue("discount", promoModel.getDiscount());
        paramsMap.addValue("installment", promoModel.getInstallmentPeriod());
        if (promoModel.getStartDate() == null) {
            paramsMap.addValue("start_date", Date.valueOf(LocalDate.now()));
        } else {
            paramsMap.addValue("start_date", Date.valueOf(promoModel.getStartDate()));
        }
        if (promoModel.getEndDate() == null) {
            paramsMap.addValue("end_date", Date.valueOf(FmLocalDateUtils.getLastDateOfNextYear()));
        } else {
            paramsMap.addValue("end_date", Date.valueOf(promoModel.getEndDate()));
        }
        paramsMap.addValue("url", promoModel.getUrl());
        paramsMap.addValue("category_id", promoModel.getCategoryId());
        paramsMap.addValue("bank_id", promoModel.getBankId());

        DaoUtils.debugQuery(logger, sqlStatement, paramsMap.getValues());

        namedTemplate.update(sqlStatement, paramsMap);
    }

    @Override
    public void updatePromotion(PromotionPresenter promotion) {
        final String sql = ""
                + "UPDATE                       "
                + " fm_promotions               "
                + "SET title = :title,          "
                + "  content = :content,        "
                + "  discount = :discount,      "
                + "  installment = :installment,"
                + "  start_date = :start_date,  "
                + "  end_date = :end_date,      "
                + "  category_id = :category_id,"
                + "  url = :url,                "
                + "  bank_id = :bank_id,        "
                + "  updated = :updated         "
                + "WHERE                        "
                + " id = :id                    ";
        final MapSqlParameterSource paramsMap = new MapSqlParameterSource();
        paramsMap.addValue("title", promotion.getTitle());
        paramsMap.addValue("content", promotion.getContent());
        paramsMap.addValue("discount", promotion.getDiscount());
        paramsMap.addValue("installment", promotion.getInstallmentPeriod());
        if (promotion.getStart_date().equals("") || promotion.getStart_date().equals("T? nay")) {
            paramsMap.addValue("start_date", Date.valueOf(LocalDate.now()));
        } else {
            paramsMap.addValue("start_date", Date.valueOf(promotion.getStart_date()));
        }
        if (promotion.getEnd_date().equals("")) {
            paramsMap.addValue("end_date", Date.valueOf(FmLocalDateUtils.getLastDateOfNextYear()));
        } else {
            paramsMap.addValue("end_date", Date.valueOf(promotion.getEnd_date()));
        }
        paramsMap.addValue("url", promotion.getUrl());
        paramsMap.addValue("category_id", promotion.getCategory_id());
        paramsMap.addValue("bank_id", promotion.getBank_id());
        paramsMap.addValue("updated", LocalDateTime.now());
        paramsMap.addValue("id", promotion.getId());

        DaoUtils.debugQuery(logger, sql, paramsMap.getValues());

        namedTemplate.update(sql, paramsMap);
    }

    @Override
    public List<PromotionPresenter> getPromotionByBankId(int bankID, int categoryId) {
        final String sqlQuery = ""
                + "SELECT                          "
                + "     id,                        "
                + "     title,                     "
                + "     content,                   "
                + "     discount,                  "
                + "     installment,               "
                + "     start_date,                "
                + "     end_date,                  "
                + "     category_id,               "
                + "     bank_id                    "
                + "FROM fm_promotions              "
                + "     WHERE bank_id=:bank_id     "
                + "AND category_id=:categoryId     ";
        try {
            final MapSqlParameterSource paramsMap = new MapSqlParameterSource();
            paramsMap.addValue("bank_id", bankID);
            paramsMap.addValue("categoryId", categoryId);
            DaoUtils.debugQuery(logger, sqlQuery, paramsMap.getValues());
            return namedTemplate.query(sqlQuery, paramsMap, (rs, rowNum) -> buildPromotionModel(rs));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Map<String, Integer> getCategoryAndId() {
        final String sqlQuery =
                "SELECT name, id FROM fm_promotion_categories";

        final MapSqlParameterSource paramsMap = new MapSqlParameterSource();
        DaoUtils.debugQuery(logger, sqlQuery, paramsMap.getValues());

        List<Map<String, Object>> results = namedTemplate.queryForList(sqlQuery, paramsMap);

        Map<String, Integer> categoriesAndIdlist = new HashMap<>();
        for (Map row : results) {
            categoriesAndIdlist.put((String) row.get("name"), Math.toIntExact((Long) row.get("id")));
        }
        return categoriesAndIdlist;
    }

    @Override
    public Boolean isPromotionExisting(String url, String title, LocalDate endDate) {
        final String sql = "SELECT COUNT(*) FROM fm_promotions WHERE url = :url AND title = :title AND end_date = :end_date";
        final MapSqlParameterSource paramsMap = new MapSqlParameterSource();
        paramsMap.addValue("url", url);
        paramsMap.addValue("title", title);
        paramsMap.addValue("end_date", endDate);

        DaoUtils.debugQuery(logger, sql, paramsMap.getValues());

        return namedTemplate.queryForObject(sql, paramsMap, Integer.class) > 0;
    }

    @Override
    public PromotionPresenter getPromotion(String url, String title, LocalDate endDate) {
        final String sql = ""
                + "SELECT                                "
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
                + "WHERE url = :url                      "
                + "AND title = :title                    "
                + "AND end_date = :endDate               ";

        final MapSqlParameterSource paramsMap = new MapSqlParameterSource();
        paramsMap.addValue("url", url);
        paramsMap.addValue("title", title);
        paramsMap.addValue("endDate", endDate);

        DaoUtils.debugQuery(logger, sql, paramsMap.getValues());

        try {
            return namedTemplate.queryForObject(sql, paramsMap, (rs, rowNum) -> buildPromotionModel(rs));
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    private PromotionPresenter buildPromotionModel(ResultSet rs) throws SQLException {
        PromotionPresenter presenter = new PromotionPresenter();
        presenter.setId(rs.getLong("id"));
        presenter.setTitle(rs.getString("title"));
        presenter.setContent(rs.getString("content"));
        presenter.setDiscount(rs.getString("discount"));
        presenter.setInstallmentPeriod(rs.getString("installment"));
        presenter.setStart_date(rs.getDate("start_date").toLocalDate());
        presenter.setEnd_date(rs.getDate("end_date").toLocalDate());
        presenter.setCategory_id(rs.getInt("category_Id"));
        presenter.setBank_id(rs.getInt("bank_id"));
        return presenter;
    }
}

package fm.api.rest.promotions.crawler;

import fm.api.rest.promotions.PromotionPresenter;
import fm.api.rest.promotions.crawler.interfaces.IPromotionCrawlerDAO;
import fm.common.dao.DaoUtils;
import fm.utils.FmDateUtils;
import io.jsonwebtoken.lang.Assert;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
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
     *
     * @param promoModel
     * @return
     */
    @Override
    public void addPromotion(PromotionCrawlerModel promoModel) {
        final String sqlStatement =
                          "INSERT INTO                           "
                        + "fm_promotions                         "
                        + "(title,                               "
                        + "content,                              "
                        + "discount,                             "
                        + "installment,                          "
                        + "start_date,                           "
                        + "end_date,                             "
                        + "category_id,                          "
                        + "url ,                                 "
                        + "bank_id)                              "
                        + " VALUES                               "
                        + "(:title,                              "
                        + ":content,                             "
                        + ":discount,                            "
                        + ":installment ,                        "
                        + ":start_date, "
                        + ":end_date,   "
                        + ":category_id,                         "
                        + ":url,                                 "
                        + ":bank_id)                             ";
        final MapSqlParameterSource paramsMap = new MapSqlParameterSource();
        paramsMap.addValue("title", promoModel.getTitle());
        paramsMap.addValue("content", promoModel.getContent());
        paramsMap.addValue("discount", promoModel.getDiscount());
        paramsMap.addValue("installment", promoModel.getInstallmentPeriod());
        if (promoModel.getStartDate().equals("") || promoModel.getStartDate().equals("T? nay")) {
            paramsMap.addValue("start_date", LocalDate.now());
        } else {
//            paramsMap.addValue("start_date", FmLocalDateUtils.parseDateTimeWithPattern(promoModel.getStartDate().replaceAll("/", "-"), "dd-MM-yyyy")); // TODO Work later
            paramsMap.addValue("start_date", FmDateUtils.parseDateWithPattern(promoModel.getStartDate().replaceAll("/", "-"), "dd-MM-yyyy"));
        }
        if (promoModel.getEndDate().equals("")) {
            LocalDate currentTime = LocalDate.now();
            paramsMap.addValue("end_date", LocalDate.of(currentTime.getYear() + 1, 12, 31));
        } else {
//            paramsMap.addValue("end_date", FmLocalDateUtils.parseDateTimeWithPattern(promoModel.getEndDate().replaceAll("/", "-"), "dd-MM-yyyy")); // TODO Work later
            paramsMap.addValue("end_date", FmDateUtils.parseDateWithPattern(promoModel.getEndDate().replaceAll("/", "-"), "dd-MM-yyyy"));
        }
        paramsMap.addValue("url", promoModel.getUrl());
        paramsMap.addValue("category_id", promoModel.getCategoryId());
        paramsMap.addValue("bank_id", promoModel.getBankId());

        DaoUtils.debugQuery(logger, sqlStatement, paramsMap.getValues());

        namedTemplate.update(sqlStatement, paramsMap);
    }

    @Override
    public void updatePromotion(PromotionPresenter promoModel) {
        final String sql =
                          "UPDATE                       "
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
                        + "bank_id = :bank_id           "
                ;
        final MapSqlParameterSource paramsMap = new MapSqlParameterSource();
        paramsMap.addValue("title", promoModel.getTitle());
        paramsMap.addValue("content", promoModel.getContent());
        paramsMap.addValue("discount", promoModel.getDiscount());
        paramsMap.addValue("installment", promoModel.getInstallmentPeriod());
        if (promoModel.getStartDate().equals("") || promoModel.getStartDate().equals("T? nay")) {
            paramsMap.addValue("start_date", LocalDate.now());
        } else {
//            paramsMap.addValue("start_date", FmLocalDateUtils.parseDateTimeWithPattern(promoModel.getStartDate().replaceAll("/", "-"), "dd-MM-yyyy")); // TODO Work later
            paramsMap.addValue("start_date", FmDateUtils.parseDateWithPattern(promoModel.getStartDate().replaceAll("/", "-"), "dd-MM-yyyy"));
        }
        if (promoModel.getEndDate().equals("")) {
            LocalDate currentTime = LocalDate.now();
            paramsMap.addValue("end_date", LocalDate.of(currentTime.getYear() + 1, 12, 31));
        } else {
//            paramsMap.addValue("end_date", FmLocalDateUtils.parseDateTimeWithPattern(promoModel.getEndDate().replaceAll("/", "-"), "dd-MM-yyyy")); // TODO Work later
            paramsMap.addValue("end_date", FmDateUtils.parseDateWithPattern(promoModel.getEndDate().replaceAll("/", "-"), "dd-MM-yyyy"));
        }
        paramsMap.addValue("url", promoModel.getUrl());
        paramsMap.addValue("category_id", promoModel.getCategoryId());
        paramsMap.addValue("bank_id", promoModel.getBankId());
        paramsMap.addValue("updated", LocalDateTime.now());

        DaoUtils.debugQuery(logger, sql, paramsMap.getValues());

        namedTemplate.update(sql, paramsMap);
    }

    @Override
    public List<PromotionPresenter> getPromotionByBankId(int bankID, int category_id) {
        final String sqlQuery =
                          "SELECT    "
                        + "id,                              "
                        + "title,                                  "
                        + "content,                                "
                        + "discount,                               "
                        + "installment,                            "
                        + "DATE_FORMAT(start_date, '%d-%m-%Y'),   "
                        + "DATE_FORMAT(end_date, '%d-%m-%Y'),      "
                        + "category_id,                            "
                        + "bank_id                                 "
                        + "FROM fm_promotions                      "
                        + "WHERE bank_id=:bank_id                  "
                        + "AND category_id=:category_id            ";
        try {
            final MapSqlParameterSource paramsMap = new MapSqlParameterSource();
            paramsMap.addValue("bank_id", bankID);
            paramsMap.addValue("category_id", category_id);
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

//    Iterator<String> iterator = results.iterator();

        Map<String, Integer> categoriesAndIdlist = new HashMap<>();
        for (Map row : results) {
            categoriesAndIdlist.put((String) row.get("name"), Math.toIntExact((Long) row.get("id")));
        }
//    categoriesAndIdlist.put((String) results.get("name"), Math.toIntExact((Long) results.get("id")));
//    while (iterator.hasNext()) {
//      String key = iterator.next();
//      categoriesAndIdlist.put(key, (Integer) results.get(key));
//    }

        return categoriesAndIdlist;
    }

    @Override
    public Boolean isPromotionExisting(String url, String title, Date endDate) {
        final String sql = "SELECT COUNT(*) FROM fm_promotions WHERE url = :url AND title = :title AND end_date = :end_date";
        final MapSqlParameterSource paramsMap = new MapSqlParameterSource();
        paramsMap.addValue("url", url);
        paramsMap.addValue("title", title);
        paramsMap.addValue("end_date", endDate);

        DaoUtils.debugQuery(logger, sql, paramsMap.getValues());

        return namedTemplate.queryForObject(sql, paramsMap, Integer.class) > 0;
    }

    @Override
    public PromotionPresenter getPromotion(String url, String title, Date endDate) {
//    public PromotionPresenter getPromotion(String url) {
        final String sql =
                          "SELECT    "
                        + "  id,                                 "
                        + "  title,                              "
                        + "  content,                            "
                        + "  discount,                           "
                        + "  installment,                        "
                        + "  DATE_FORMAT(start_date, '%d-%m-%Y'),"
                        + "  DATE_FORMAT(end_date, '%d-%m-%Y'),  "
                        + "  category_id,                        "
                        + "  bank_id                             "
                        + "FROM fm_promotions                    "
                        + "WHERE url = :url                      "
                        + "AND title = :title                    "
                        + "AND end_date = :end_date              "
                ;

        final MapSqlParameterSource paramsMap = new MapSqlParameterSource();
        paramsMap.addValue("url", url);
        paramsMap.addValue("title", title);
        paramsMap.addValue("end_date", endDate);

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
        presenter.setStartDate(rs.getString("DATE_FORMAT(start_date, '%d-%m-%Y')"));
        presenter.setEndDate(rs.getString("DATE_FORMAT(end_date, '%d-%m-%Y')"));
        presenter.setCategoryId(rs.getInt("category_id"));
        presenter.setBankId(rs.getInt("bank_id"));
        return presenter;
    }
}

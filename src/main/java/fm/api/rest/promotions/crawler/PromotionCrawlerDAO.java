package fm.api.rest.promotions.crawler;

import fm.api.rest.promotions.PromotionPresenter;
import fm.api.rest.promotions.crawler.interfaces.IPromotionCrawlerDAO;
import fm.common.dao.DaoUtils;
import fm.utils.FmDateUtils;
import fm.utils.FmLocalDateUtils;
import io.jsonwebtoken.lang.Assert;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

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

    // Save promotion Value into DB
    @Override
    public boolean savePromotion(PromotionCrawlerModel promoModel) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MMM-dd",
                Locale.ENGLISH);
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
//                        + "STR_TO_DATE(:start_date, '%d-%m-%Y'), "
                        + ":end_date,   "
//                        + "STR_TO_DATE(:end_date, '%d-%m-%Y'),   "
                        + ":category_id,                         "
                        + ":url,                                 "
                        + ":bank_id)                             ";
        final MapSqlParameterSource paramsMap = new MapSqlParameterSource();
        paramsMap.addValue("title", promoModel.getTitle());
        paramsMap.addValue("content", promoModel.getContent());
        paramsMap.addValue("discount", promoModel.getDiscount());
        paramsMap.addValue("installment", promoModel.getInstallmentPeriod());
        if (promoModel.getStartDate().equals("") || promoModel.getStartDate().equals("T? nay")) {
            paramsMap.addValue("start_date", LocalDateTime.now());
        } else {
//            paramsMap.addValue("start_date", FmLocalDateUtils.parseDateTimeWithPattern(promoModel.getStartDate().replaceAll("/", "-"), "dd-MM-yyyy")); // TODO Work later
            paramsMap.addValue("start_date", FmLocalDateUtils.parseDateTimeWithPattern(promoModel.getStartDate().replaceAll("/", "-"), "dd-MM-yyyy"));
        }
        if (promoModel.getEndDate().equals("")) {
            paramsMap.addValue("end_date", LocalDateTime.now());
        } else {
//            paramsMap.addValue("end_date", FmLocalDateUtils.parseDateTimeWithPattern(promoModel.getEndDate().replaceAll("/", "-"), "dd-MM-yyyy")); // TODO Work later
            paramsMap.addValue("end_date", FmDateUtils.parseDateWithPattern(promoModel.getEndDate().replaceAll("/", "-"), "dd-MM-yyyy"));
        }
        paramsMap.addValue("url", promoModel.getLinkDetail());
        paramsMap.addValue("category_id", promoModel.getCategoryId());
        paramsMap.addValue("bank_id", promoModel.getBankId());

        DaoUtils.debugQuery(logger, sqlStatement, paramsMap.getValues());

        namedTemplate.update(sqlStatement, paramsMap);
        return true;
    }

    @Override
    public List<PromotionPresenter> getPrmoTionByBankId(int bankID, int category_id) {
        List<PromotionPresenter> result = new ArrayList<>();
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
            result = namedTemplate.query(sqlQuery, paramsMap, (rs, rowNum) -> buildPromotionModel(rs));
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
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

    private PromotionPresenter buildPromotionModel(ResultSet rs) throws SQLException {
        PromotionPresenter presenter = new PromotionPresenter();
        presenter.setId(rs.getInt("id"));
        presenter.setTitle(rs.getString("title"));
        presenter.setContent(rs.getString("content"));
        presenter.setDiscount(rs.getString("discount"));
        presenter.setInstallmentPeriod(rs.getString("installment"));
        presenter.setStartDate(rs.getString("DATE_FORMAT(start_date, '%d-%m-%Y')"));
        presenter.setEndDate(rs.getString("DATE_FORMAT(end_date, '%d-%m-%Y')"));
        presenter.setCategoryID(rs.getInt("category_id"));
        presenter.setBankId(rs.getInt("bank_id"));
        return presenter;
    }
}

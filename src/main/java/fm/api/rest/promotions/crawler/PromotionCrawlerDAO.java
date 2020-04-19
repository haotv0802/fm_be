package fm.api.rest.promotions.crawler;
/**
 * Quy created on 3/11/2020
 */


import fm.api.rest.promotions.PromotionPresenter;
import fm.api.rest.promotions.crawler.interfaces.IPromotionCrawlerDAO;
import fm.common.dao.DaoUtils;
import io.jsonwebtoken.lang.Assert;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service("promotionCrawlerDao")
public class PromotionCrawlerDAO implements IPromotionCrawlerDAO {

    private static final Logger LOGGER = LogManager.getLogger(PromotionCrawlerDAO.class);
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
                "INSERT INTO "
                        + "fm_promotions "
                        + "(title,"
                        + "content,"
                        + "discount,"
                        + "start_date,"
                        + "end_date,"
                        + "category_id,"
                        + "bank_id)"
                        + " VALUES "
                        + "(:title,:content,:discount,DATE_FORMAT(:start_date, '%Y-%m-%d'),DATE_FORMAT(:end_date, '%Y-%m-%d'),:category_id,:bank_id)";
        try {
            final MapSqlParameterSource paramsMap = new MapSqlParameterSource();
            paramsMap.addValue("title", promoModel.getTitle());
            paramsMap.addValue("content", promoModel.getContent());
            paramsMap.addValue("discount", promoModel.getDiscount());
            if (promoModel.getStartDate().equals("") || promoModel.getStartDate().equals("T? nay")) {
                paramsMap.addValue("start_date", "2000-02-10");
            } else {
                Date parsedDate = sdf.parse(promoModel.getStartDate());
                SimpleDateFormat print = new SimpleDateFormat("yyyy-MM-dd");
                paramsMap.addValue("start_date", print.format(parsedDate));
            }
            if(promoModel.getEndDate().equals("")){
                SimpleDateFormat print = new SimpleDateFormat("yyyy-MM-dd");
                Date endDate = print.parse(promoModel.getEndDate());
                paramsMap.addValue("end_date", print.format(endDate));
            }else{
                paramsMap.addValue("end_date", "2000-02-10");
            }

            paramsMap.addValue("category_id", "1");
            paramsMap.addValue("bank_id", "2");
            DaoUtils.debugQuery(LOGGER, sqlStatement, paramsMap.getValues());
            namedTemplate.update(sqlStatement, paramsMap);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public List<PromotionPresenter> getPrmoTionByBankId(int bankID) {
        List<PromotionPresenter> result = new ArrayList<>();
        final String sqlQuery =
                        "SELECT *               " +
                        "FROM fm_promotions     " +
                        "WHERE bank_id=:bank_id ";
        try {
            final MapSqlParameterSource paramsMap = new MapSqlParameterSource();
            paramsMap.addValue("bank_id", bankID);
            DaoUtils.debugQuery(LOGGER, sqlQuery, paramsMap.getValues());
            result = namedTemplate.query(sqlQuery, paramsMap, (rs, rowNum) -> buildExpense(rs));
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public Map<String, Integer> getCategoryAndId() {

        final String sqlQuery =
                        "SELECT name, id FROM fm_promotion_categories               ";

        final MapSqlParameterSource paramsMap = new MapSqlParameterSource();

        DaoUtils.debugQuery(LOGGER, sqlQuery, paramsMap.getValues());

        Map<String, Object> results = namedTemplate.queryForMap(sqlQuery, paramsMap);
        Set keys = results.keySet();
        Iterator<String> iterator = keys.iterator();

        Map<String, Integer> categoriesAndIdlist = new HashMap<>();

        while(iterator.hasNext()) {
            String key = iterator.next();

            categoriesAndIdlist.put(key, (Integer) results.get(key));
        }

        return categoriesAndIdlist;
    }


    private PromotionPresenter buildExpense(ResultSet rs) throws SQLException {
        PromotionPresenter presenter = new PromotionPresenter();
        presenter.setId(rs.getInt("id"));
        presenter.setTitle(rs.getString("title"));
        presenter.setContent(rs.getString("content"));
        presenter.setDiscount(rs.getString("discount"));
        presenter.setStartDate(rs.getDate("start_date").toString());
        presenter.setEndDate(rs.getDate("end_date").toString());
        presenter.setCategoryID(rs.getInt("category_id"));
        presenter.setBankId(rs.getInt("bank_id"));
        return presenter;
    }
}

package fm.api.rest.pricehunting;

import fm.api.rest.individual.IndividualDao;
import fm.api.rest.pricehunting.interfaces.IPriceHuntingDao;
import fm.common.dao.DaoUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;

/**
 * Created by HaoHo on 6/8/2020
 */
@Service("priceHuntingDao")
public class PriceHuntingDao implements IPriceHuntingDao {
    private static final Logger logger = LogManager.getLogger(IndividualDao.class);

    private final NamedParameterJdbcTemplate namedTemplate;

    @Autowired
    public PriceHuntingDao(NamedParameterJdbcTemplate namedTemplate) {
        Assert.notNull(namedTemplate);

        this.namedTemplate = namedTemplate;
    }

    @Override
    public void addPrice(Price price) {
        final String sql = ""
                + "INSERT INTO             "
                + "   fm_sites_prices (    "
                + "        email,          "
                + "        url,            "
                + "        price,          "
                + "        expected_price, "
                + "        status          "
                + "        )               "
                + "  VALUES      (         "
                + "        :email,         "
                + "        :url,           "
                + "        :price,         "
                + "        :expected_price,"
                + "        :status         "
                + "        )               ";

        final MapSqlParameterSource paramsMap = new MapSqlParameterSource();
        paramsMap.addValue("email", price.getEmail());
        paramsMap.addValue("url", price.getUrl());
        paramsMap.addValue("price", price.getPrice());
        paramsMap.addValue("expected_price", price.getExpectedPrice());
        paramsMap.addValue("status", "RUNNING");

        DaoUtils.debugQuery(logger, sql, paramsMap.getValues());

        namedTemplate.update(sql, paramsMap);
    }

    @Override
    public void updatePrice(Price price) {
        final String sql = ""
                + "UPDATE                                   "
                + "  fm_sites_prices                        "
                + "   SET email = :email,                   "
                + "        url = :url,                      "
                + "        price = :price,                  "
                + "        expected_price = :expected_price,"
                + "        status = :status                 "
                + "        WHERE id = :id                 "
                ;

        final MapSqlParameterSource paramsMap = new MapSqlParameterSource();
        paramsMap.addValue("email", price.getEmail());
        paramsMap.addValue("url", price.getUrl());
        paramsMap.addValue("price", price.getPrice());
        paramsMap.addValue("expected_price", price.getExpectedPrice());
        paramsMap.addValue("status", "RUNNING");
        paramsMap.addValue("id", price.getId());

        DaoUtils.debugQuery(logger, sql, paramsMap.getValues());

        namedTemplate.update(sql, paramsMap);
    }

    @Override
    public Price getPriceByURL(String url) {
        final String sql = ""
                + " SELECT              "
                + "     id,             "
                + "     email,          "
                + "     url,            "
                + "     price,          "
                + "     expected_price  "
                + "    FROM             "
                + "     fm_sites_prices "
                + "    WHERE            "
                + "    url = :url       ";

        final MapSqlParameterSource paramsMap = new MapSqlParameterSource();
        paramsMap.addValue("url", url);

        DaoUtils.debugQuery(logger, sql, paramsMap.getValues());

        try {
            return namedTemplate.queryForObject(sql, paramsMap, (rs, rowNum) -> {
                        Price price = new Price();
                        price.setId(rs.getLong("id"));
                        price.setEmail(rs.getString("email"));
                        price.setUrl(rs.getString("url"));
                        price.setPrice(rs.getBigDecimal("price"));
                        price.setExpectedPrice(rs.getBigDecimal("expected_price"));

                        return price;
                    }
            );
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    @Override
    public List<Price> getPrices() {
        final String sql = ""
                + " SELECT              "
                + "     id,             "
                + "     email,          "
                + "     url,            "
                + "     price,          "
                + "     expected_price  "
                + "    FROM             "
                + "     fm_sites_prices "
                + "    WHERE            "
                + "    status = :status ";

        final MapSqlParameterSource paramsMap = new MapSqlParameterSource();
        paramsMap.addValue("status", "RUNNING");

        DaoUtils.debugQuery(logger, sql, paramsMap.getValues());

        try {
            return namedTemplate.query(sql, paramsMap, (rs, rowNum) -> {
                        Price price = new Price();
                        price.setId(rs.getLong("id"));
                        price.setEmail(rs.getString("email"));
                        price.setUrl(rs.getString("url"));
                        price.setPrice(rs.getBigDecimal("price"));
                        price.setExpectedPrice(rs.getBigDecimal("expected_price"));

                        return price;
                    }
            );
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }
}

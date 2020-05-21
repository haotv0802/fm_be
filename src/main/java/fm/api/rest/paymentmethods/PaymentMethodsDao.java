package fm.api.rest.paymentmethods;

import fm.api.rest.paymentmethods.beans.PaymentMethodPresenter;
import fm.api.rest.paymentmethods.interfaces.IPaymentMethodsDao;
import fm.common.dao.DaoUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import java.util.List;

/**
 * Created by haho on 6/26/2017.
 */
@Repository("paymentMethodsDao")
public class PaymentMethodsDao implements IPaymentMethodsDao {

    private static final Logger logger = LogManager.getLogger(PaymentMethodsDao.class);

    private final NamedParameterJdbcTemplate namedTemplate;

    @Autowired
    public PaymentMethodsDao(NamedParameterJdbcTemplate namedTemplate) {
        Assert.notNull(namedTemplate);

        this.namedTemplate = namedTemplate;
    }

    @Override
    public List<PaymentMethodPresenter> getAllPaymentMethods() {
        final String sql = ""
                + "SELECT              "
                + " id,                "
                + " name,              "
                + " logo               "
                + "FROM                "
                + " fm_payment_methods ";

        final MapSqlParameterSource paramsMap = new MapSqlParameterSource();

        DaoUtils.debugQuery(logger, sql, paramsMap.getValues());

        return namedTemplate.query(sql, paramsMap, (RowMapper<PaymentMethodPresenter>) (rs, rowNum) -> {
            PaymentMethodPresenter paymentMethodPresenter = new PaymentMethodPresenter();
            paymentMethodPresenter.setId(rs.getInt("id"));
            paymentMethodPresenter.setName(rs.getString("name"));
            paymentMethodPresenter.setLogo(rs.getString("logo"));
            return paymentMethodPresenter;
        });
    }

    @Override
    public Integer addPaymentMethod(PaymentMethodPresenter paymentMethod) {
        final String sql = ""
                + "INSERT INTO          "
                + " fm_payment_methods ("
                + "    name,            "
                + "    logo)            "
                + " VALUES (            "
                + "    :name,           "
                + "    :logo)           ";

        final MapSqlParameterSource paramsMap = new MapSqlParameterSource();
        paramsMap.addValue("name", paymentMethod.getName());
        paramsMap.addValue("logo", paymentMethod.getLogo());

        DaoUtils.debugQuery(logger, sql, paramsMap.getValues());

        KeyHolder keyHolder = new GeneratedKeyHolder();
        namedTemplate.update(sql, paramsMap, keyHolder);
        final Integer id = keyHolder.getKey().intValue();
        return id;
    }

    @Override
    public void updatePaymentMethod(PaymentMethodPresenter paymentMethod) {
        final String sql = ""
                + "UPDATE fm_payment_methods"
                + " SET name = :name,       "
                + "    logo = :logo         "
                + " WHERE id = :id          ";

        final MapSqlParameterSource paramsMap = new MapSqlParameterSource();
        paramsMap.addValue("name", paymentMethod.getName());
        paramsMap.addValue("logo", paymentMethod.getLogo());
        paramsMap.addValue("id", paymentMethod.getId());

        DaoUtils.debugQuery(logger, sql, paramsMap.getValues());
        namedTemplate.update(sql, paramsMap);
    }

    @Override
    public Boolean isPaymentNameExisting(String name) {
        final String sql = "SELECT COUNT(*) FROM fm_payment_methods WHERE name = :name";

        final MapSqlParameterSource paramsMap = new MapSqlParameterSource();
        paramsMap.addValue("name", name);

        DaoUtils.debugQuery(logger, sql, paramsMap.getValues());
        return namedTemplate.queryForObject(sql, paramsMap, Integer.class) > 0;
    }

    @Override
    public Boolean isPaymentNameExisting(Integer id, String name) {
        final String sql = "SELECT COUNT(*) FROM fm_payment_methods WHERE id != :id AND name = :name";

        final MapSqlParameterSource paramsMap = new MapSqlParameterSource();
        paramsMap.addValue("name", name);
        paramsMap.addValue("id", id);

        DaoUtils.debugQuery(logger, sql, paramsMap.getValues());
        return namedTemplate.queryForObject(sql, paramsMap, Integer.class) > 0;
    }
}

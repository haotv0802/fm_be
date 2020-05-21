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
            paymentMethodPresenter.setId(rs.getLong("id"));
            paymentMethodPresenter.setName(rs.getString("name"));
            paymentMethodPresenter.setLogo(rs.getString("logo"));
            return paymentMethodPresenter;
        });
    }
}

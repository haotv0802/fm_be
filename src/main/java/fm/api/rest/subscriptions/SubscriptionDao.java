package fm.api.rest.subscriptions;

import fm.common.dao.DaoUtils;
import io.jsonwebtoken.lang.Assert;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by HaoHo on 5/29/2020
 */
@Repository("subscriptionDao")
public class SubscriptionDao implements ISubscriptionDao {
    private static final Logger logger = LogManager.getLogger(SubscriptionDao.class);

    private final NamedParameterJdbcTemplate namedTemplate;

    @Autowired
    public SubscriptionDao(NamedParameterJdbcTemplate namedTemplate) {
        Assert.notNull(namedTemplate);
        this.namedTemplate = namedTemplate;
    }

    @Override
    public List<SubscriptionModel> getSubscribersByType(String type) {
        final String sql = ""
                + "SELECT            "
                + " id,              "
                + " user_id,         "
                + " email,           "
                + " type,            "
                + " status           "
                + "FROM              "
                + " fm_subscriptions "
                + "WHERE             "
                + " type = :type     ";

        final MapSqlParameterSource paramsMap = new MapSqlParameterSource();
        paramsMap.addValue("type", type);

        DaoUtils.debugQuery(logger, sql, paramsMap.getValues());

        List<SubscriptionModel> subscribers = namedTemplate.query(sql, paramsMap, (rs, rowNum) -> {
                    SubscriptionModel subscriber = new SubscriptionModel();
                    subscriber.setId(rs.getLong("id"));
                    subscriber.setUserId(rs.getInt("user_id"));
                    subscriber.setEmail(rs.getString("email"));
                    subscriber.setType(rs.getString("type"));
                    subscriber.setStatus(rs.getString("status"));
                    return subscriber;
                }
        );

        return subscribers;
    }

    @Override
    public void approveSubscriber(String verificationCode) {
        final String sql = ""
                + "UPDATE fm_subscriptions  "
                + " SET ,                   "
                + " user_id,                "
                + " email,                  "
                + " type,                   "
                + " status                  "
                + "FROM                     "
                + " fm_subscriptions        "
                + "WHERE                    "
                + " type = :type            ";

        final MapSqlParameterSource paramsMap = new MapSqlParameterSource();

        DaoUtils.debugQuery(logger, sql, paramsMap.getValues());
    }

    @Override
    public void subscribe(SubscriptionModel subscription) {

    }
}

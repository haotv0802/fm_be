package fm.api.rest.moneysource;

import fm.api.rest.bank.BankPresenter;
import fm.api.rest.moneysource.interfaces.IMoneySourceDao;
import fm.common.JdbcUtils;
import fm.common.dao.DaoUtils;
import io.jsonwebtoken.lang.Assert;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by haoho on 3/2/20 14:56.
 */
@Service("moneySourceDao")
public class MoneySourceDao implements IMoneySourceDao {

  private static final Logger LOGGER = LogManager.getLogger(MoneySourceDao.class);

  private final NamedParameterJdbcTemplate namedTemplate;

  @Autowired
  public MoneySourceDao(NamedParameterJdbcTemplate namedTemplate) {
    Assert.notNull(namedTemplate);
    this.namedTemplate = namedTemplate;
  }

  @Override
  public List<MoneySourcePresenter> getMoneySources(Integer userId) {
    final String sql =
              "SELECT                 "
            + " id,                   "
            + "    name,              "
            + "    start_date,        "
            + "    expiry_date,       "
            + "    card_number,       "
            + "    amount,            "
            + "    card_type_id,      "
            + "    user_id,           "
            + "    is_terminated,     "
            + "    bank_id            "
            + "FROM                   "
            + "    fm_money_source    "
            + "WHERE                  "
            + "    user_id = :userId  "
        ;

    final MapSqlParameterSource paramsMap = new MapSqlParameterSource();
    paramsMap.addValue("userId", userId);

    DaoUtils.debugQuery(LOGGER, sql, paramsMap.getValues());

    List<MoneySourcePresenter> moneyPresenters = namedTemplate.query(sql, paramsMap, (rs, rowNum) -> {
          MoneySourcePresenter bankPresenter = new MoneySourcePresenter();
          bankPresenter.setId(rs.getLong("id"));
          bankPresenter.setName(rs.getString("name"));
          bankPresenter.setStartDate(JdbcUtils.toUtilDate(rs.getDate("start_date")));
          bankPresenter.setExpiryDate(JdbcUtils.toUtilDate(rs.getDate("expiry_date")));
          bankPresenter.setCardNumber(rs.getString("card_number"));
          bankPresenter.setCreditLimit(rs.getBigDecimal("amount"));
          bankPresenter.setTerminated(rs.getBoolean("is_terminated"));
          bankPresenter.setBankId(rs.getLong("bank_id"));
          return bankPresenter;
        }
    );

    return moneyPresenters;
  }
}
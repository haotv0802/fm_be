package fm.api.rest.individual;

import fm.api.rest.individual.interfaces.IIndividualDao;
import fm.common.JdbcUtils;
import fm.common.dao.DaoUtils;
import io.jsonwebtoken.lang.Assert;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;

/**
 * Created by haoho on 2/28/20 13:51.
 */
@Service("individualDao")
public class IndividualDao implements IIndividualDao {

  private static final Logger LOGGER = LogManager.getLogger(IndividualDao.class);

  private final NamedParameterJdbcTemplate namedTemplate;

  @Autowired
  public IndividualDao(NamedParameterJdbcTemplate namedTemplate) {
    Assert.notNull(namedTemplate);
    this.namedTemplate = namedTemplate;
  }


  @Override
  public IndividualPresenter getIndividual(int userId) {
    final String sql =
          " SELECT                                                "
        + "     i.id,                                             "
        + "     i.first_name,                                     "
        + "     i.last_name,                                      "
        + "     i.middle_name,                                    "
        + "     i.birthday,                                       "
        + "     i.gender,                                         "
        + "     i.email,                                          "
        + "     i.phone_number,                                   "
        + "     i.income,                                         "
        + "     i.user_id,                                        "
        + "     m.id money_source_id,                             "
        + "     m.name money_source_name,                         "
        + "     m.start_date money_source_start_date,             "
        + "     m.expiry_date money_source_expiry_date,           "
        + "     m.card_number money_source_card_number,           "
        + "     m.amount money_source_credit_limit,               "
        + "     m.card_type_id money_source_card_type_id,         "
        + "     m.user_id,                                        "
        + "     m.is_terminated money_source_is_terminated,       "
        + "     m.bank_id money_source_bank_id                    "
        + "    FROM                                               "
        + "        (                                              "
        + "            fm_individuals i                           "
        + "            inner join fm_users u on i.user_id = u.id  "
        + "        )                                              "
        + "    inner join fm_money_source m on u.id = m.user_id   "
        + "    WHERE                                              "
        + "    i.user_id = :userId                                "
    ;

    final MapSqlParameterSource paramsMap = new MapSqlParameterSource();
    paramsMap.addValue("userId", userId);

    DaoUtils.debugQuery(LOGGER, sql, paramsMap.getValues());

    IndividualPresenter expensesList = namedTemplate.queryForObject(sql, paramsMap, (rs, rowNum) -> {
          IndividualPresenter individualPresenter = new IndividualPresenter();
          individualPresenter.setId(rs.getLong("id"));
          individualPresenter.setFirstName(rs.getString("first_name"));
          individualPresenter.setLastName(rs.getString("last_name"));
          individualPresenter.setMiddleName(rs.getString("middle_name"));
          individualPresenter.setBirthday(JdbcUtils.toUtilDate(rs.getDate("birthday")));
          individualPresenter.setGender(rs.getString("gender"));
          individualPresenter.setEmail(rs.getString("email"));
          individualPresenter.setPhoneNumber(rs.getString("phone_number"));
          individualPresenter.setIncome(rs.getBigDecimal("income"));
          individualPresenter.setMoneySourceId(rs.getLong("money_source_id"));
          individualPresenter.setMoneySourceName(rs.getString("money_source_name"));
          individualPresenter.setStartDate(JdbcUtils.toUtilDate(rs.getDate("money_source_start_date")));
          individualPresenter.setExpiryDate(JdbcUtils.toUtilDate(rs.getDate("money_source_expiry_date")));
          individualPresenter.setCardNumber(rs.getString("money_source_card_number"));
          individualPresenter.setCreditLimit(rs.getBigDecimal("money_source_credit_limit"));
          individualPresenter.setTerminated(rs.getBoolean("money_source_is_terminated"));
          individualPresenter.setBankId(rs.getLong("money_source_bank_id"));

          return individualPresenter;
        }
    );

    return expensesList;
  }
}

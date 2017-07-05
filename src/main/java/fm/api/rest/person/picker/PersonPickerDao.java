package fm.api.rest.person.picker;

import fm.api.rest.expenses.ExpensesDao;
import fm.api.rest.person.picker.beans.PersonPresenter;
import fm.api.rest.person.picker.interfaces.IPersonPickerDao;
import fm.common.JdbcUtils;
import fm.common.dao.DaoUtils;
import io.jsonwebtoken.lang.Assert;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by haho on 7/5/2017.
 */
@Repository("personPickerDao")
public class PersonPickerDao implements IPersonPickerDao {

  private static final Logger LOGGER = LogManager.getLogger(ExpensesDao.class);

  private final NamedParameterJdbcTemplate namedTemplate;

  public PersonPickerDao(NamedParameterJdbcTemplate namedTemplate) {
    Assert.notNull(namedTemplate);

    this.namedTemplate = namedTemplate;
  }

  @Override
  public List<PersonPresenter> getPersonsList(int userId) {
    final String sql =
          "SELECT                 "
        + "	id,                   "
        + "	first_name,           "
        + "	last_name,            "
        + "	middle_name,          "
        + "	birthday,             "
        + "	gender,               "
        + "	email,                "
        + "	phone_number,         "
        + "	official_income,      "
        + "	payment_date_of_month,"
        + "	user_id               "
        + "FROM                   "
        + "	fm_person_picker      "
        + "WHERE                  "
        + "	user_id = :userId     "
        ;

    final MapSqlParameterSource paramsMap = new MapSqlParameterSource();
    paramsMap.addValue("userId", userId);

    DaoUtils.debugQuery(LOGGER, sql, paramsMap.getValues());

    return namedTemplate.query(sql, paramsMap, (rs, rowNum) -> {
      PersonPresenter presenter = new PersonPresenter();
      presenter.setId(rs.getInt("id"));
      presenter.setFirstName(rs.getString("first_name"));
      presenter.setLastName(rs.getString("last_name"));
      presenter.setMiddleName(rs.getString("middle_name"));
      presenter.setBirthday(JdbcUtils.toUtilDate(rs.getDate("birthday")));
      presenter.setGender(rs.getBoolean("gender"));
      presenter.setEmail(rs.getString("email"));
      presenter.setPhoneNumber(rs.getString("phone_number"));
      presenter.setOfficialIncome(rs.getBigDecimal("official_income"));
      presenter.setMonthlyPaymentDate(rs.getInt("payment_date_of_month"));
      presenter.setUserId(rs.getInt("user_id"));

      return presenter;
    });
  }
}

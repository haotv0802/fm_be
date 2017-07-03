package fm.api.rest.expenses;

import fm.api.rest.expenses.interfaces.IExpensesDao;
import fm.common.JdbcUtils;
import fm.common.ValidationException;
import fm.common.dao.DaoUtils;
import io.jsonwebtoken.lang.Assert;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by haho on 6/22/2017.
 */
@Repository("expensesDao")
public class ExpensesDao implements IExpensesDao {

  private static final Logger LOGGER = LogManager.getLogger(ExpensesDao.class);

  private final NamedParameterJdbcTemplate namedTemplate;

  @Autowired
  public ExpensesDao(NamedParameterJdbcTemplate namedTemplate) {
    Assert.notNull(namedTemplate);
    this.namedTemplate = namedTemplate;
  }

  @Override
  @Deprecated
  public List<Expense> getExpenses(int userId) {
    final String sql = "SELECT                                                 "
                     + "	e.id,                                                "
                     + "	e.user_id,                                           "
                     + "	e.amount,                                            "
                     + "	e.date,                                              "
                     + "	e.place,                                             "
                     + "	e.for_person,                                        "
                     + "	e.is_an_event,                                       "
                     + "	e.card_id,                                           "
                     + "	e.pay_in_cash,                                       "
                     + "	c.card_number,                                       "
                     + "	c.name card_info,                                    "
                     + "	p.name payment_method                                "
                     + "FROM                                                   "
                     + "	(fm_expenses e                                       "
                     + "	LEFT JOIN fm_cards_information c ON e.card_id = c.id)"
                     + "		LEFT JOIN                                          "
                     + "	fm_payment_methods p ON c.card_type_id = p.id        "
                     + "WHERE                                                  "
                     + "	e.user_id = :userId                                  "
                     + "ORDER BY id ASC                                        "
        ;

    final MapSqlParameterSource paramsMap = new MapSqlParameterSource();
    paramsMap.addValue("userId", userId);

    DaoUtils.debugQuery(LOGGER, sql, paramsMap.getValues());

    List<Expense> expensesList = namedTemplate.query(sql, paramsMap, (rs, rowNum) -> buildExpense(rs));

    return expensesList;
  }

  private List<String> getMonths(int userId) {
    final String sql = "SELECT DISTINCT                    "
                     + "	DATE_FORMAT(date, '%Y-%m') month "
                     + "FROM                               "
                     + "	fm_expenses                      "
                     + "WHERE                              "
                     + "	user_id = :userId                "
                     + "ORDER BY date DESC                 "
        ;

    final MapSqlParameterSource paramsMap = new MapSqlParameterSource();
    paramsMap.addValue("userId", userId);

    DaoUtils.debugQuery(LOGGER, sql, paramsMap.getValues());

    return namedTemplate.queryForList(sql, paramsMap, String.class);
  }

  private Expense buildExpense(ResultSet rs) throws SQLException {
      Expense expense = new Expense();
      expense.setId(rs.getInt("id"));
      expense.setUserId(rs.getInt("user_id"));
      expense.setAmount(rs.getBigDecimal("amount"));
      expense.setDate(JdbcUtils.toUtilDate(rs.getDate("date")));
      expense.setPlace(rs.getString("place"));
      expense.setForPerson(rs.getString("for_person"));
      expense.setAnEvent(rs.getBoolean("is_an_event"));
      expense.setCardId(rs.getInt("card_id"));
      boolean payInCash = rs.getBoolean("pay_in_cash");
      if (payInCash) {
        expense.setPaymentMethod("CASH");
      } else {
        expense.setPaymentMethod(rs.getString("payment_method"));
        expense.setCardInfo(rs.getString("card_info"));
        expense.setCardNumber(rs.getString("card_number"));
      }
      return expense;
  }

  @Override
  public ExpensesDetails getExpenesDetails(int userId) {
    List<String> months = this.getMonths(userId);
    if (CollectionUtils.isEmpty(months)) {
      throw new ValidationException("Data not found");
    }

    return this.getExpenesDetailsByMonth(userId, months.get(0));
  }

  private ExpensesDetails getExpenesDetailsByMonth(int userId, String month) {
    final String sql =
          "SELECT                                                "
        + "	e.id,                                                "
        + "	e.user_id,                                           "
        + "	e.amount,                                            "
        + "	e.date,                                              "
        + "	e.place,                                             "
        + "	e.for_person,                                        "
        + "	e.is_an_event,                                       "
        + "	e.card_id,                                           "
        + "	e.pay_in_cash,                                       "
        + "	c.card_number,                                       "
        + "	c.name card_info,                                    "
        + "	p.name payment_method                                "
        + "FROM                                                  "
        + "	(fm_expenses e                                       "
        + "	LEFT JOIN fm_cards_information c ON e.card_id = c.id)"
        + "		LEFT JOIN                                          "
        + "	fm_payment_methods p ON c.card_type_id = p.id        "
        + "WHERE                                                 "
        + "	e.user_id = :userId                                  "
        + "        AND DATE_FORMAT(date, '%Y-%m') = :month       "
        + "ORDER BY e.date DESC                                  "
        ;

    final MapSqlParameterSource paramsMap = new MapSqlParameterSource();
    paramsMap.addValue("userId", userId);
    paramsMap.addValue("month", month);

    DaoUtils.debugQuery(LOGGER, sql, paramsMap.getValues());

    ExpensesDetails expensesDetails = new ExpensesDetails();

    List<Expense> expensesList = namedTemplate.query(sql, paramsMap, (rs, rowNum) -> buildExpense(rs));

    BigDecimal totalSpendings = BigDecimal.ZERO;
    for (int i = 0; i < expensesList.size(); i++) {
      totalSpendings = totalSpendings.add(expensesList.get(i).getAmount());
    }

    expensesDetails.setExpenses(expensesList);
    expensesDetails.setTotal(totalSpendings);
    return expensesDetails;
  }

  @Override
  public List<ExpensesDetails> getPreviousExpenesDetails(int userId) {
    List<String> months = this.getMonths(userId);
    if (CollectionUtils.isEmpty(months)) {
      throw new ValidationException("Data not found");
    }

    List<ExpensesDetails> expensesDetailsList = new ArrayList<>();
    for (int i = 1; i < months.size(); i++) {
      ExpensesDetails expensesDetails = this.getExpenesDetailsByMonth(userId, months.get(i));
      expensesDetailsList.add(expensesDetails);
    }

    return expensesDetailsList;
  }

  @Override
  public void addExpense(ExpenseCreation expenseCreation) {
//    final String sql = "SELECT DISTINCT                    "
//        + "	DATE_FORMAT(date, '%Y-%m') month "
//        + "FROM                               "
//        + "	fm_expenses                      "
//        + "WHERE                              "
//        + "	user_id = :userId                "
//        + "ORDER BY date DESC                 "
//        ;
//
//    final MapSqlParameterSource paramsMap = new MapSqlParameterSource();
//    paramsMap.addValue("userId", userId);
//
//    DaoUtils.debugQuery(LOGGER, sql, paramsMap.getValues());
//
//    return namedTemplate.queryForList(sql, paramsMap, String.class);
  }
}

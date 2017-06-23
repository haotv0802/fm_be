package fm.api.rest.expenses;

import fm.api.rest.expenses.interfaces.IExpensesDao;
import fm.common.JdbcUtils;
import fm.common.dao.DaoUtils;
import io.jsonwebtoken.lang.Assert;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by haho on 6/22/2017.
 */
@Repository("expensesDao")
public class ExpensesDao implements IExpensesDao {

  private static final Logger LOGGER = LogManager.getLogger(ExpensesDao.class);

  private final NamedParameterJdbcTemplate namedTemplate;

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

    List<Expense> expenses = namedTemplate.query(sql, paramsMap, (rs, rowNum) -> {
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
    });

    return expenses;
  }

  @Override
  public ExpensesDetails getExpenesDetails(int userId) {
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

    ExpensesDetails expensesDetails = new ExpensesDetails();


    List<Expense> expensesList = namedTemplate.query(sql, paramsMap, (rs, rowNum) -> {
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
    });

    BigDecimal totalSpendings = BigDecimal.ZERO;
    for (int i = 0; i < expensesList.size(); i++) {
      totalSpendings = totalSpendings.add(expensesList.get(i).getAmount());
    }

    expensesDetails.setExpenses(expensesList);
    expensesDetails.setTotal(totalSpendings);
    return expensesDetails;
  }
}

package fm.api.rest.moneyflow;

import fm.api.rest.moneyflow.interfaces.IMoneyFlowDao;
import fm.common.JdbcUtils;
import fm.common.ValidationException;
import fm.common.dao.DaoUtils;
import io.jsonwebtoken.lang.Assert;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
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
@Repository("moneyFlowDao")
public class MoneyFlowDao implements IMoneyFlowDao {

  private static final Logger LOGGER = LogManager.getLogger(MoneyFlowDao.class);

  private final NamedParameterJdbcTemplate namedTemplate;

  @Autowired
  public MoneyFlowDao(NamedParameterJdbcTemplate namedTemplate) {
    Assert.notNull(namedTemplate);
    this.namedTemplate = namedTemplate;
  }

  @Override
  @Deprecated
  public List<ItemPresenter> getExpenses(int userId) {
    final String sql = "SELECT                                                         "
                     + "	e.id,                                                        "
                     + "	e.user_id,                                                   "
                     + "	e.amount,                                                    "
                     + "	e.date,                                                      "
                     + "	e.name,                                                      "
                     + "	e.money_source_id,                                           "
                     + "	CASE                                                         "
                     + "	  WHEN c.name IS NULL THEN 'CASH'                            "
                     + "	  ELSE c.name                                                "
                     + "	END money_source_name,                                       "
                     + "	e.is_spending,                                               "
                     + "	c.card_number,                                               "
                     + "	c.name card_info,                                            "
                     + "	p.name payment_method                                        "
                     + "FROM                                                           "
                     + "	(fm_money_flow e                                             "
                     + "	LEFT JOIN fm_money_source c ON e.money_source_id = c.id)     "
                     + "		LEFT JOIN                                                  "
                     + "	fm_payment_methods p ON c.card_type_id = p.id                "
                     + "WHERE                                                          "
                     + "	e.user_id = :userId                                          "
                     + "        AND is_deleted = FALSE                                 "
                     + "ORDER BY id ASC                                                "
        ;

    final MapSqlParameterSource paramsMap = new MapSqlParameterSource();
    paramsMap.addValue("userId", userId);

    DaoUtils.debugQuery(LOGGER, sql, paramsMap.getValues());

    List<ItemPresenter> expensesList = namedTemplate.query(sql, paramsMap, (rs, rowNum) -> buildExpense(rs));

    return expensesList;
  }

  private List<String> getMonths(int userId) {
    final String sql = "SELECT DISTINCT                    "
                     + "	DATE_FORMAT(date, '%Y-%m') month "
                     + "FROM                               "
                     + "	fm_money_flow                    "
                     + "WHERE                              "
                     + "	user_id = :userId                "
                     + "	AND is_deleted = FALSE           "
                     + "ORDER BY month DESC                "
        ;

    final MapSqlParameterSource paramsMap = new MapSqlParameterSource();
    paramsMap.addValue("userId", userId);

    DaoUtils.debugQuery(LOGGER, sql, paramsMap.getValues());

    return namedTemplate.queryForList(sql, paramsMap, String.class);
  }

  private ItemPresenter buildExpense(ResultSet rs) throws SQLException {
      ItemPresenter expense = new ItemPresenter();
      expense.setId(rs.getInt("id"));
      expense.setUserId(rs.getInt("user_id"));
      expense.setAmount(rs.getBigDecimal("amount"));
      expense.setDate(JdbcUtils.toUtilDate(rs.getDate("date")));
      expense.setName(rs.getString("name"));
      expense.setMoneySourceId(rs.getInt("money_source_id"));
      expense.setMoneySourceName(rs.getString("money_source_name"));
      expense.setSpending(rs.getBoolean("is_spending"));

      if (null == expense.getMoneySourceId() || expense.getMoneySourceId() == 0) {
        expense.setPaymentMethod("CASH");
      } else {
        expense.setPaymentMethod(rs.getString("payment_method"));
        expense.setCardInfo(rs.getString("card_info"));
        expense.setCardNumber(rs.getString("card_number"));
      }
      return expense;
  }

  @Override
  public ItemDetailsPresenter getExpenesDetails(int userId) {
    List<String> months = this.getMonths(userId);
    if (CollectionUtils.isEmpty(months)) {
      throw new ValidationException("Data not found");
    }

    return this.getExpenesDetailsByMonth(userId, months.get(0));
  }

  private ItemDetailsPresenter getExpenesDetailsByMonth(int userId, String month) {
    final String sql =
          "SELECT                                                   "
        + "	e.id,                                                   "
        + "	e.user_id,                                              "
        + "	e.amount,                                               "
        + "	e.date,                                                 "
        + "	e.name,                                                 "
        + "	e.is_spending,                                          "
        + "	c.card_number,                                          "
        + "	e.money_source_id,                                      "
        + "	CASE                                                    "
        + "	  WHEN c.name IS NULL THEN 'CASH'                       "
        + "	  ELSE c.name                                           "
        + "	END money_source_name,                                  "
        + "	c.name card_info,                                       "
        + "	p.name payment_method                                   "
        + "FROM                                                     "
        + "	(fm_money_flow e                                        "
        + "	LEFT JOIN fm_money_source c ON e.money_source_id = c.id)"
        + "		LEFT JOIN                                             "
        + "	fm_payment_methods p ON c.card_type_id = p.id           "
        + "WHERE                                                    "
        + "	e.user_id = :userId                                     "
        + "        AND DATE_FORMAT(date, '%Y-%m') = :month          "
        + "        AND is_deleted = FALSE                           "
        + "ORDER BY e.date DESC                                     "
        ;

    final MapSqlParameterSource paramsMap = new MapSqlParameterSource();
    paramsMap.addValue("userId", userId);
    paramsMap.addValue("month", month);

    DaoUtils.debugQuery(LOGGER, sql, paramsMap.getValues());

    ItemDetailsPresenter itemDetailsPresenter = new ItemDetailsPresenter();

    List<ItemPresenter> expensesList = namedTemplate.query(sql, paramsMap, (rs, rowNum) -> buildExpense(rs));

    BigDecimal totalSpendings = BigDecimal.ZERO;
    for (int i = 0; i < expensesList.size(); i++) {
      if (null == expensesList.get(i).getAmount()) {
        continue;
      }
      totalSpendings = totalSpendings.add(expensesList.get(i).getAmount());
    }

    itemDetailsPresenter.setExpenses(expensesList);
    itemDetailsPresenter.setTotal(totalSpendings);
    return itemDetailsPresenter;
  }

  @Override
  public List<ItemDetailsPresenter> getPreviousExpensesDetails(int userId) {
    List<String> months = this.getMonths(userId);
    if (CollectionUtils.isEmpty(months)) {
      throw new ValidationException("Data not found");
    }

    List<ItemDetailsPresenter> itemDetailsPresenterList = new ArrayList<>();
    for (int i = 1; i < months.size(); i++) {
      ItemDetailsPresenter itemDetailsPresenter = this.getExpenesDetailsByMonth(userId, months.get(i));
      itemDetailsPresenterList.add(itemDetailsPresenter);
    }

    return itemDetailsPresenterList;
  }

  @Override
  public Long addExpense(Item item, int userId) {
    final String sql =
          "INSERT INTO fm_money_flow (user_id, amount, date, name, money_source_id, is_spending) "
        + "VALUES (:userId, :amount, :date, :name, :money_source_id, :is_spending)               "
        ;

    final MapSqlParameterSource paramsMap = new MapSqlParameterSource();
    paramsMap.addValue("userId", userId);
    paramsMap.addValue("amount", item.getAmount());
    paramsMap.addValue("date", item.getDate());
    paramsMap.addValue("name", item.getName());
    paramsMap.addValue("is_spending", item.getSpending());
    Integer cardId = null;
    if (null != item.getMoneySourceId()) {
      cardId = item.getMoneySourceId() < 0 ? null : item.getMoneySourceId();
    }
    paramsMap.addValue("money_source_id", cardId);

    DaoUtils.debugQuery(LOGGER, sql, paramsMap.getValues());

    KeyHolder keyHolder = new GeneratedKeyHolder();
    namedTemplate.update(sql, paramsMap, keyHolder);
    final Long id = keyHolder.getKey().longValue();
    return id;
  }

  @Override
  public void updateExpense(Item item) {
    final String sql =
        "UPDATE fm_money_flow              "
      + "SET                               "
      + "	amount = :amount,                "
      + "	date = DATE(:date),              "
      + "	name = :name,                    "
      + "	is_spending = :isSpending,       "
      + "	money_source_id = :moneySourceId "
      + "WHERE                             "
      + "	id = :id                         "
        ;

    final MapSqlParameterSource paramsMap = new MapSqlParameterSource();
    paramsMap.addValue("id", item.getId());
    paramsMap.addValue("amount", item.getAmount());
    paramsMap.addValue("date", item.getDate());
    paramsMap.addValue("name", item.getName());
    paramsMap.addValue("isSpending", item.getSpending());
    Integer moneySourceId = null;
    if (null != item.getMoneySourceId()) {
      moneySourceId = item.getMoneySourceId() <= 0 ? null : item.getMoneySourceId();
    }
    paramsMap.addValue("moneySourceId", moneySourceId);

    DaoUtils.debugQuery(LOGGER, sql, paramsMap.getValues());

    namedTemplate.update(sql, paramsMap);
  }

  @Override
  public void updateExpense(BigDecimal amount, int userId, int expenseId) {
    final String sql =
        "UPDATE fm_money_flow                   "
      + "SET                                    "
      + "	amount = :amount                      "
      + "WHERE                                  "
      + "	user_id = :userId AND id = :expenseId "
        ;

    final MapSqlParameterSource paramsMap = new MapSqlParameterSource();
    paramsMap.addValue("amount", amount);
    paramsMap.addValue("userId", userId);
    paramsMap.addValue("expenseId", expenseId);

    DaoUtils.debugQuery(LOGGER, sql, paramsMap.getValues());

    namedTemplate.update(sql, paramsMap);
  }

  @Override
  public void updateAmount(int expenseId) {
    final String sql =
              "UPDATE fm_money_flow e       "
            + "SET                          "
            + "	amount = (SELECT            "
            + "			SUM(ee.amount)          "
            + "		FROM                      "
            + "			fm_event_expenses ee    "
            + "		WHERE                     "
            + "			ee.expense_id = :id     "
            + "			AND is_deleted = FALSE) "
            + "WHERE                        "
            + "	e.id = :id                  "
        ;

    final MapSqlParameterSource paramsMap = new MapSqlParameterSource();
    paramsMap.addValue("id", expenseId);

    DaoUtils.debugQuery(LOGGER, sql, paramsMap.getValues());

    namedTemplate.update(sql, paramsMap);
  }

  @Override
  public void deleteExpense(int expenseId) {
    final String sql =
        "UPDATE fm_money_flow        "
      + "SET                         "
      + "	is_deleted = TRUE          "
      + "WHERE                       "
      + "	id = :id                   "
        ;

    final MapSqlParameterSource paramsMap = new MapSqlParameterSource();
    paramsMap.addValue("id", expenseId);

    DaoUtils.debugQuery(LOGGER, sql, paramsMap.getValues());

    namedTemplate.update(sql, paramsMap);
  }

  @Override
  public boolean checkIfLoginUserOwner(int expenseId, int userId) {
    final String sql = "SELECT COUNT(*) FROM fm_money_flow WHERE id = :id AND user_id = :userId"
        ;

    final MapSqlParameterSource paramsMap = new MapSqlParameterSource();
    paramsMap.addValue("id", expenseId);
    paramsMap.addValue("userId", userId);

    DaoUtils.debugQuery(LOGGER, sql, paramsMap.getValues());

    return namedTemplate.queryForObject(sql, paramsMap, Integer.class) > 0;
  }
}

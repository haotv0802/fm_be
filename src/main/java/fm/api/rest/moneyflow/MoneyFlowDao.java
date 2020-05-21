package fm.api.rest.moneyflow;

import fm.api.rest.moneyflow.interfaces.IMoneyFlowDao;
import fm.common.ValidationException;
import fm.common.dao.DaoUtils;
import fm.utils.FmDateUtils;
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
import org.springframework.util.StringUtils;

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

    private static final Logger logger = LogManager.getLogger(MoneyFlowDao.class);

    private final NamedParameterJdbcTemplate namedTemplate;

    @Autowired
    public MoneyFlowDao(NamedParameterJdbcTemplate namedTemplate) {
        Assert.notNull(namedTemplate);
        this.namedTemplate = namedTemplate;
    }

    @Override
    @Deprecated
    public List<ItemPresenter> getExpenses(int userId) {
        final String sql = ""
                + "SELECT                                                        "
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
                + "FROM                                                          "
                + "	(fm_money_flow e                                             "
                + "	LEFT JOIN fm_money_source c ON e.money_source_id = c.id)     "
                + "		LEFT JOIN                                                "
                + "	fm_payment_methods p ON c.card_type_id = p.id                "
                + "WHERE                                                         "
                + "	e.user_id = :userId                                          "
                + "        AND is_deleted = FALSE                                "
                + "ORDER BY id ASC                                               ";

        final MapSqlParameterSource paramsMap = new MapSqlParameterSource();
        paramsMap.addValue("userId", userId);

        DaoUtils.debugQuery(logger, sql, paramsMap.getValues());

        List<ItemPresenter> expensesList = namedTemplate.query(sql, paramsMap, (rs, rowNum) -> buildExpense(rs));

        return expensesList;
    }

    /**
     * Get list of years in number. Ex: 2020, 2019.
     *
     * @param userId
     * @return list of years.
     */
    @Override
    public List<Integer> getYearsList(int userId) {
        final String sql = ""
                + "SELECT DISTINCT                "
                + "	DATE_FORMAT(date, '%Y') year  "
                + "FROM                           "
                + "	fm_money_flow                 "
                + "WHERE                          "
                + "	user_id = :userId             "
                + "	AND is_deleted = FALSE        "
                + "ORDER BY year DESC             ";

        final MapSqlParameterSource paramsMap = new MapSqlParameterSource();
        paramsMap.addValue("userId", userId);

        DaoUtils.debugQuery(logger, sql, paramsMap.getValues());

        return namedTemplate.queryForList(sql, paramsMap, Integer.class);
    }

    /**
     * Get list of months with format yyyy-mm, ex: 2020-04, 2020-03
     *
     * @param userId
     * @return list of months.
     */
    private List<String> getMonthsWithYear(int userId) {
        final String sql = ""
                + "SELECT DISTINCT                   "
                + "	DATE_FORMAT(date, '%Y-%m') month "
                + "FROM                              "
                + "	fm_money_flow                    "
                + "WHERE                             "
                + "	user_id = :userId                "
                + "	AND is_deleted = FALSE           "
                + "ORDER BY month DESC               ";

        final MapSqlParameterSource paramsMap = new MapSqlParameterSource();
        paramsMap.addValue("userId", userId);

        DaoUtils.debugQuery(logger, sql, paramsMap.getValues());

        return namedTemplate.queryForList(sql, paramsMap, String.class);
    }

    /**
     * Get list of months of current year with format yyyy-mm, ex: 2020-04, 2020-03
     *
     * @param userId
     * @return list of months.
     */
    private List<String> getMonthsInCurrentYear(int userId) {
        final String sql = ""
                + "SELECT DISTINCT                                                    "
                + "	DATE_FORMAT(date, '%Y-%m') month                                  "
                + "FROM                                                               "
                + "	fm_money_flow                                                     "
                + "WHERE                                                              "
                + "	user_id = :userId                                                 "
                + "	AND is_deleted = FALSE                                            "
                + "	AND DATE_FORMAT(CURDATE(), '%Y-%m') != DATE_FORMAT(date, '%Y-%m') "
                + "	AND YEAR(date) = YEAR(CURDATE())                                  "
                + "ORDER BY month DESC                                                ";

        final MapSqlParameterSource paramsMap = new MapSqlParameterSource();
        paramsMap.addValue("userId", userId);

        DaoUtils.debugQuery(logger, sql, paramsMap.getValues());

        return namedTemplate.queryForList(sql, paramsMap, String.class);
    }

    /**
     * Get list of months with year, ex: 2020-02, 2020-01
     *
     * @param userId
     * @param year
     * @return list of months and years.
     */
    private List<String> getMonthsWithYear(int userId, int year) {
        final String sql = ""
                + "SELECT DISTINCT                                                    "
                + "	DATE_FORMAT(date, '%Y-%m') month                                  "
                + "FROM                                                               "
                + "	fm_money_flow                                                     "
                + "WHERE                                                              "
                + "	user_id = :userId                                                 "
                + "	AND is_deleted = FALSE                                            "
                + "	AND DATE_FORMAT(date, '%Y') = :year                               "
                + "	AND DATE_FORMAT(CURDATE(), '%Y-%m') != DATE_FORMAT(date, '%Y-%m') "
                + "ORDER BY month DESC                                                ";

        final MapSqlParameterSource paramsMap = new MapSqlParameterSource();
        paramsMap.addValue("userId", userId);
        paramsMap.addValue("year", year);

        DaoUtils.debugQuery(logger, sql, paramsMap.getValues());

        return namedTemplate.queryForList(sql, paramsMap, String.class);
    }

    private ItemPresenter buildExpense(ResultSet rs) throws SQLException {
        ItemPresenter expense = new ItemPresenter();
        expense.setId(rs.getInt("id"));
        expense.setUserId(rs.getInt("user_id"));
        expense.setAmount(rs.getBigDecimal("amount"));
        expense.setDate(FmDateUtils.toUtilDate(rs.getDate("date")));
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

    /**
     * Get list of expenses belonging to specific user.
     *
     * @param userId
     * @param name
     * @return list of expenses.
     */
    @Override
    public ItemDetailsPresenter getExpensesDetails(int userId, String name) {
        List<String> months = this.getMonthsWithYear(userId);
        if (CollectionUtils.isEmpty(months)) {
            throw new ValidationException("Data not found");
        }

        return this.getExpensesDetailsByMonth(userId, months.get(0), name);
    }

    /**
     * Get list of expenses including total of all expenses by year & month.
     *
     * @param userId
     * @param year
     * @param month
     * @return list of expenses.
     */
    @Override
    public ItemDetailsPresenter getExpensesDetailsByYearAndMonth(int userId, int year, int month) {
        final String sql = ""
                + "SELECT                                                   "
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
                + "		LEFT JOIN                                           "
                + "	fm_payment_methods p ON c.card_type_id = p.id           "
                + "WHERE                                                    "
                + "	e.user_id = :userId                                     "
                + "        AND DATE_FORMAT(date, '%Y') = :year              "
                + "        AND DATE_FORMAT(date, '%m') = :month             "
                + "        AND is_deleted = FALSE                           "
                + "ORDER BY e.date DESC                                     ";

        final MapSqlParameterSource paramsMap = new MapSqlParameterSource();
        paramsMap.addValue("userId", userId);
        paramsMap.addValue("year", year);
        paramsMap.addValue("month", month);

        DaoUtils.debugQuery(logger, sql, paramsMap.getValues());

        return buildItemDetailsPresenter(sql, paramsMap);
    }

    /**
     * Get list of expenses including total of all expenses by month.
     *
     * @param userId
     * @param month
     * @param name
     * @return list of expenses.
     */
    private ItemDetailsPresenter getExpensesDetailsByMonth(int userId, String month, String name) {
        final String sql = ""
                + "SELECT                                                   "
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
                + "		LEFT JOIN                                           "
                + "	fm_payment_methods p ON c.card_type_id = p.id           "
                + "WHERE                                                    "
                + "	e.user_id = :userId                                     "
                + "        AND DATE_FORMAT(date, '%Y-%m') = :month          "
                + "        AND is_deleted = FALSE                           "
                + ((null != name && !StringUtils.isEmpty(name)) ? "AND e.name LIKE :name " : "")
                + "ORDER BY e.date DESC                                     ";

        final MapSqlParameterSource paramsMap = new MapSqlParameterSource();
        paramsMap.addValue("userId", userId);
        paramsMap.addValue("month", month);
        paramsMap.addValue("name", "%" + name + "%");

        DaoUtils.debugQuery(logger, sql, paramsMap.getValues());

        return buildItemDetailsPresenter(sql, paramsMap);
    }

    /**
     * Build expense item details to be returned to Front-end.
     *
     * @param sql
     * @param paramsMap
     * @return expense item.
     */
    private ItemDetailsPresenter buildItemDetailsPresenter(String sql, MapSqlParameterSource paramsMap) {
        ItemDetailsPresenter itemDetailsPresenter = new ItemDetailsPresenter();

        List<ItemPresenter> expensesList = namedTemplate.query(sql, paramsMap, (rs, rowNum) -> buildExpense(rs));

        // Calculate Total of all expenses and put it in Detail object.
        BigDecimal totalSending = BigDecimal.ZERO;
        for (ItemPresenter itemPresenter : expensesList) {
            if (null == itemPresenter.getAmount()) {
                continue;
            }

            if (itemPresenter.getSpending()) {
                totalSending = totalSending.subtract(itemPresenter.getAmount());
            } else {
                totalSending = totalSending.add(itemPresenter.getAmount());
            }
        }
        itemDetailsPresenter.setExpenses(expensesList);
        itemDetailsPresenter.setTotal(totalSending);

        return itemDetailsPresenter;
    }

    /**
     * Get list expenses by year.
     *
     * @param userId
     * @param year
     * @param name
     * @return list of expenses.
     */
    @Override
    public List<ItemDetailsPresenter> getExpensesByYear(int userId, int year, String name) {
        List<String> months = this.getMonthsWithYear(userId, year);
        if (CollectionUtils.isEmpty(months)) {
            throw new ValidationException("Data not found");
        }

        List<ItemDetailsPresenter> itemDetailsPresenterList = new ArrayList<>();
        for (int i = 0; i < months.size(); i++) {
            ItemDetailsPresenter itemDetailsPresenter = this.getExpensesDetailsByMonth(userId, months.get(i), name);
            String[] yearAndMonth = months.get(i).split("-");
            itemDetailsPresenter.setYear(Integer.valueOf(yearAndMonth[0]));
            itemDetailsPresenter.setMonth(Integer.valueOf(yearAndMonth[1]));
            itemDetailsPresenterList.add(itemDetailsPresenter);
        }

        return itemDetailsPresenterList;
    }

    @Deprecated
    @Override
    public List<ItemDetailsPresenter> getLastMonths(int userId, String name) {
        List<String> months = this.getMonthsInCurrentYear(userId);
        if (CollectionUtils.isEmpty(months)) {
            throw new ValidationException("Data not found");
        }

        List<ItemDetailsPresenter> itemDetailsPresenterList = new ArrayList<>();
        for (int i = 0; i < months.size(); i++) {
            ItemDetailsPresenter itemDetailsPresenter = this.getExpensesDetailsByMonth(userId, months.get(i), name);
            itemDetailsPresenterList.add(itemDetailsPresenter);
        }

        return itemDetailsPresenterList;
    }

    /**
     * Add new expense.
     *
     * @param item
     * @param userId
     * @return Id of new expense as a number.
     */
    @Override
    public Long addExpense(Item item, int userId) {
        final String sql = ""
                + "INSERT INTO fm_money_flow (user_id, amount, date, name, money_source_id, is_spending) "
                + "VALUES (:userId, :amount, :date, :name, :money_source_id, :is_spending)               ";

        final MapSqlParameterSource paramsMap = new MapSqlParameterSource();
        paramsMap.addValue("userId", userId);
        paramsMap.addValue("amount", item.getAmount());
        paramsMap.addValue("date", FmDateUtils.toSqlDate(item.getDate()));
        paramsMap.addValue("name", item.getName());
        paramsMap.addValue("is_spending", item.getSpending());
        Integer cardId = null;
        if (null != item.getMoneySourceId()) {
            cardId = item.getMoneySourceId() < 0 ? null : item.getMoneySourceId();
        }
        paramsMap.addValue("money_source_id", cardId);

        DaoUtils.debugQuery(logger, sql, paramsMap.getValues());

        KeyHolder keyHolder = new GeneratedKeyHolder();
        namedTemplate.update(sql, paramsMap, keyHolder);
        final Long id = keyHolder.getKey().longValue();
        return id;
    }

    /**
     * Update expense.
     *
     * @param item
     */
    @Override
    public void updateExpense(Item item) {
        final String sql = ""
                + "UPDATE fm_money_flow              "
                + "SET                               "
                + "	amount = :amount,                "
                + "	date = DATE(:date),              "
                + "	name = :name,                    "
                + "	is_spending = :isSpending,       "
                + "	money_source_id = :moneySourceId "
                + "WHERE                             "
                + "	id = :id                         ";

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

        DaoUtils.debugQuery(logger, sql, paramsMap.getValues());

        namedTemplate.update(sql, paramsMap);
    }

    @Deprecated
    @Override
    public void updateExpense(BigDecimal amount, int userId, int expenseId) {
        final String sql = ""
                + "UPDATE fm_money_flow                   "
                + "SET                                    "
                + "	amount = :amount                      "
                + "WHERE                                  "
                + "	user_id = :userId AND id = :expenseId ";

        final MapSqlParameterSource paramsMap = new MapSqlParameterSource();
        paramsMap.addValue("amount", amount);
        paramsMap.addValue("userId", userId);
        paramsMap.addValue("expenseId", expenseId);

        DaoUtils.debugQuery(logger, sql, paramsMap.getValues());

        namedTemplate.update(sql, paramsMap);
    }

    /**
     * Delete specific expense.
     *
     * @param expenseId
     */
    @Override
    public void deleteExpense(int expenseId) {
        final String sql = ""
                + "UPDATE fm_money_flow "
                + "SET                  "
                + "	is_deleted = TRUE   "
                + "WHERE                "
                + "	id = :id            ";

        final MapSqlParameterSource paramsMap = new MapSqlParameterSource();
        paramsMap.addValue("id", expenseId);

        DaoUtils.debugQuery(logger, sql, paramsMap.getValues());

        namedTemplate.update(sql, paramsMap);
    }

    @Override
    public boolean checkIfLoginUserOwner(int expenseId, int userId) {
        final String sql = "SELECT COUNT(*) FROM fm_money_flow WHERE id = :id AND user_id = :userId";
        final MapSqlParameterSource paramsMap = new MapSqlParameterSource();
        paramsMap.addValue("id", expenseId);
        paramsMap.addValue("userId", userId);

        DaoUtils.debugQuery(logger, sql, paramsMap.getValues());

        return namedTemplate.queryForObject(sql, paramsMap, Integer.class) > 0;
    }
}

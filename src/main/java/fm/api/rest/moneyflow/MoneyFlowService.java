package fm.api.rest.moneyflow;

import fm.api.rest.moneyflow.interfaces.IMoneyFlowDao;
import fm.api.rest.moneyflow.interfaces.IMoneyFlowService;
import io.jsonwebtoken.lang.Assert;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by haho on 6/22/2017.
 */
@Service("moneyFlowService")
public class MoneyFlowService implements IMoneyFlowService {

  private static final Logger LOGGER = LogManager.getLogger(MoneyFlowService.class);

  private final IMoneyFlowDao expensesDao;

  @Autowired
  public MoneyFlowService(
      @Qualifier("moneyFlowDao") IMoneyFlowDao expensesDao
  ) {
    Assert.notNull(expensesDao);

    this.expensesDao = expensesDao;
  }

  /**
   * Get list of expenses including total of all expenses.
   * @param userId
   * @param name
   * @return list of expenses.
   */
  @Override
  public ItemDetailsPresenter getExpensesDetails(int userId, String name) {
    return this.expensesDao.getExpensesDetails(userId, name);
  }

  /**
   * Get list of expenses by year.
   * @param userId
   * @param year
   * @param name
   * @return list of expenses.
   */
  @Override
  public List<ItemDetailsPresenter> getExpensesByYear(int userId, int year, String name) {
    return this.expensesDao.getExpensesByYear(userId, year, name);
  }

  /**
   * Get list of expenses by year and month.
   * @param userId
   * @param year
   * @param month
   * @return list of expenses.
   */
  @Override
  public ItemDetailsPresenter getExpenseByYearAndMonth(int userId, int year, int month) {
    return this.expensesDao.getExpensesDetailsByYearAndMonth(userId, year, month);
  }

  @Deprecated
  @Override
  public List<ItemDetailsPresenter> getLastMonths(int userId, String name) {
    return this.expensesDao.getLastMonths(userId, name);
  }

  /**
   * Get list of years in number.
   * @param userId
   * @return list of years.
   */
  @Override
  public List<Integer> getYearsList(int userId) {
    return this.expensesDao.getYearsList(userId);
  }

  /**
   * Add new expense.
   * @param item
   * @param userId
   * @return Id of new expense as a number.
   */
  @Override
  public Long addExpense(Item item, int userId) {
    return this.expensesDao.addExpense(item, userId);
  }

  /**
   * Update specific expense.
   * @param presenter
   */
  @Override
  public void updateExpense(ItemPresenter presenter) {
    this.expensesDao.updateExpense(new Item(presenter));
  }

  /**
   * Delete specific expense.
   * @param expenseId
   */
  @Override
  public void deleteExpense(int expenseId) {
    this.expensesDao.deleteExpense(expenseId);
  }
}

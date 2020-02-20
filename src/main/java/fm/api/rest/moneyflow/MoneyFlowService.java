package fm.api.rest.moneyflow;

import fm.api.rest.moneyflow.interfaces.IMoneyFlowDao;
import fm.api.rest.moneyflow.interfaces.IMoneyFlowService;
import io.jsonwebtoken.lang.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by haho on 6/22/2017.
 */
@Service("moneyFlowService")
public class MoneyFlowService implements IMoneyFlowService {

  private final IMoneyFlowDao expensesDao;

  @Autowired
  public MoneyFlowService(
      @Qualifier("moneyFlowDao") IMoneyFlowDao expensesDao
  ) {
    Assert.notNull(expensesDao);

    this.expensesDao = expensesDao;
  }

  @Override
  public ItemDetailsPresenter getExpensesDetails(int userId) {
    return this.expensesDao.getExpenesDetails(userId);
  }

  @Override
  public List<ItemDetailsPresenter> getPreviousExpensesDetails(int userId) {
    return this.expensesDao.getPreviousExpensesDetails(userId);
  }

  @Override
  public List<Integer> getYearsList(int userId) {
    return this.expensesDao.getYearsList(userId);
  }

  @Override
  public List<ItemPresenter> getExpenses(int userId) {
    return this.expensesDao.getExpenses(userId);
  }

  @Override
  public Long addExpense(Item item, int userId) {
    return this.expensesDao.addExpense(item, userId);
  }

  @Override
  public void updateExpense(ItemPresenter presenter) {
    this.expensesDao.updateExpense(new Item(presenter));
  }

  @Override
  public void updateExpense(BigDecimal amount, int userId, int expenseId) {
    this.expensesDao.updateExpense(amount, userId, expenseId);
  }

  @Override
  public void updateAmount(int expenseId) {
    this.expensesDao.updateAmount(expenseId);
  }

  @Override
  public void deleteExpense(int expenseId) {
    this.expensesDao.deleteExpense(expenseId);
  }
}

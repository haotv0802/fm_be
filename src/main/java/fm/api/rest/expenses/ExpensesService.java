package fm.api.rest.expenses;

import fm.api.rest.expenses.interfaces.IExpensesDao;
import fm.api.rest.expenses.interfaces.IExpensesService;
import io.jsonwebtoken.lang.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by haho on 6/22/2017.
 */
@Service("expensesService")
public class ExpensesService implements IExpensesService {

  private final IExpensesDao expensesDao;

  @Autowired
  public ExpensesService(
      @Qualifier("expensesDao") IExpensesDao expensesDao
  ) {
    Assert.notNull(expensesDao);

    this.expensesDao = expensesDao;
  }

  @Override
  public ExpensesDetailsPresenter getExpensesDetails(int userId) {
    return this.expensesDao.getExpenesDetails(userId);
  }

  @Override
  public List<ExpensesDetailsPresenter> getPreviousExpensesDetails(int userId) {
    return this.expensesDao.getPreviousExpensesDetails(userId);
  }

  @Override
  public List<ExpensePresenter> getExpenses(int userId) {
    return this.expensesDao.getExpenses(userId);
  }

  @Override
  public Long addExpense(Expense expense, int userId) {
    return this.expensesDao.addExpense(expense, userId);
  }

  @Override
  public void updateExpense(Expense expense) {
    this.expensesDao.updateExpense(expense);
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

package fm.api.rest.expenses;

import fm.api.rest.expenses.interfaces.IExpensesDao;
import fm.api.rest.expenses.interfaces.IExpensesService;
import io.jsonwebtoken.lang.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

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
  public ExpensesDetails getExpensesDetails(int userId) {
    return this.expensesDao.getExpenesDetails(userId);
  }

  @Override
  public List<ExpensesDetails> getPreviousExpenesDetails(int userId) {
    return this.expensesDao.getPreviousExpenesDetails(userId);
  }

  @Override
  public List<Expense> getExpenses(int userId) {
    return this.expensesDao.getExpenses(userId);
  }

  @Override
  public void addExpense(ExpenseCreation expenseCreation) {
//    return this.expensesDao.getExpenses(userId);
  }
}

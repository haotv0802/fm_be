package fm.api.rest.moneyflow.interfaces;

import fm.api.rest.moneyflow.ExpensesDetailsPresenter;
import fm.api.rest.moneyflow.Item;
import fm.api.rest.moneyflow.ExpensePresenter;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by haho on 6/22/2017.
 */
public interface IMoneyFlowService {
  List<ExpensePresenter> getExpenses(int userId);

  ExpensesDetailsPresenter getExpensesDetails(int userId);

  List<ExpensesDetailsPresenter> getPreviousExpensesDetails(int userId);

  Long addExpense(Item item, int userId);

  void updateExpense(Item item);

  void updateExpense(BigDecimal amount, int userId, int expenseId);

  void updateAmount(int expenseId);

  void deleteExpense(int expenseId);
}

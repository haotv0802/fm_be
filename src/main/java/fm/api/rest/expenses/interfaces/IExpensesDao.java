package fm.api.rest.expenses.interfaces;

import fm.api.rest.expenses.ExpensePresenter;
import fm.api.rest.expenses.Expense;
import fm.api.rest.expenses.ExpensesDetailsPresenter;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by haho on 6/22/2017.
 */
public interface IExpensesDao {
  List<ExpensePresenter> getExpenses(int userId);

  ExpensesDetailsPresenter getExpenesDetails(int userId);

  List<ExpensesDetailsPresenter> getPreviousExpensesDetails(int userId);

  Long addExpense(Expense expense, int userId);

  void updateExpense(Expense expense);

  void updateExpense(BigDecimal amount, int userId, int expenseId);

  void updateAmount(int expenseId);

  void deleteExpense(int expenseId);

  boolean checkIfLoginUserOwner(int expenseId, int userId);
}
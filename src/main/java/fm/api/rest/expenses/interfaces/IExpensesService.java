package fm.api.rest.expenses.interfaces;

import fm.api.rest.expenses.ExpensePresenter;
import fm.api.rest.expenses.Expense;
import fm.api.rest.expenses.ExpensesDetails;

import java.util.List;

/**
 * Created by haho on 6/22/2017.
 */
public interface IExpensesService {
  List<ExpensePresenter> getExpenses(int userId);

  ExpensesDetails getExpensesDetails(int userId);

  List<ExpensesDetails> getPreviousExpensesDetails(int userId);

  Long addExpense(Expense expense, int userId);

  void updateExpense(Expense expense);

  void deleteExpense(int expenseId);
}

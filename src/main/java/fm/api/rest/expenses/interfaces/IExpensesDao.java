package fm.api.rest.expenses.interfaces;

import fm.api.rest.expenses.Expense;
import fm.api.rest.expenses.ExpenseCreation;
import fm.api.rest.expenses.ExpensesDetails;

import java.util.List;

/**
 * Created by haho on 6/22/2017.
 */
public interface IExpensesDao {
  List<Expense> getExpenses(int userId);

  ExpensesDetails getExpenesDetails(int userId);

  List<ExpensesDetails> getPreviousExpenesDetails(int userId);

  void addExpense(ExpenseCreation expenseCreation);
}
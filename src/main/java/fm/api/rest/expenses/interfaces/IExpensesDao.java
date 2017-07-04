package fm.api.rest.expenses.interfaces;

import fm.api.rest.expenses.ExpensePresenter;
import fm.api.rest.expenses.Expense;
import fm.api.rest.expenses.ExpensesDetails;

import java.util.List;

/**
 * Created by haho on 6/22/2017.
 */
public interface IExpensesDao {
  List<ExpensePresenter> getExpenses(int userId);

  ExpensesDetails getExpenesDetails(int userId);

  List<ExpensesDetails> getPreviousExpensesDetails(int userId);

  Long addExpense(Expense expenseCreation, int userId);

}
package fm.api.rest.expenses.events.interfaces;

import fm.api.rest.expenses.events.beans.EventPresenter;
import fm.api.rest.expenses.events.beans.Expense;

/**
 * Property of CODIX Bulgaria EAD
 * Date: 7/19/2017 Time: 11:02 AM
 * <p>
 * TODO: WRITE THE DESCRIPTION HERE
 *
 * @author haho
 */
public interface IEventExpensesDao {
  Boolean isEventExisting(int expenseId);

  EventPresenter getEvent(int userId, int expenseId);

  Long addExpense(Expense expense, int expenseId);
}

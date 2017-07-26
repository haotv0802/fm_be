package fm.api.rest.expenses.events.interfaces;

import fm.api.rest.expenses.events.beans.EventPresenter;
import fm.api.rest.expenses.events.beans.Expense;

/**
 * Date: 7/19/2017 Time: 10:59 AM
 * <p>
 * TODO: WRITE THE DESCRIPTION HERE
 *
 * @author haho
 */
public interface IEventExpensesService {
  Boolean isEventExisting(int expenseId);

  EventPresenter getEvent(int userId, int expenseId);

  Long addExpense(Expense expense, int expenseId);

  void updateExpense(Expense expense, int eventId);

  void deleteExpense(int expenseId, int eventId);
}

package fm.api.rest.expenses.events.interfaces;

import fm.api.rest.expenses.events.beans.EventPresenter;

/**
 * Property of CODIX Bulgaria EAD
 * Date: 7/19/2017 Time: 10:59 AM
 * <p>
 * TODO: WRITE THE DESCRIPTION HERE
 *
 * @author haho
 */
public interface IEventExpensesService {
  Boolean isEventExisting(int expenseId);

  EventPresenter getEvent(int userId, int expenseId);
}

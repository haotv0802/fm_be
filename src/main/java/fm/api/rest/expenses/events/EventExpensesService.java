package fm.api.rest.expenses.events;

import fm.api.rest.expenses.events.interfaces.IEventExpensesDao;
import fm.api.rest.expenses.events.interfaces.IEventExpensesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

/**
 * Date: 7/19/2017 Time: 10:56 AM
 * <p>
 * TODO: WRITE THE DESCRIPTION HERE
 *
 * @author haho
 */
@Service("eventExpensesService")
public class EventExpensesService implements IEventExpensesService {

  private final IEventExpensesDao eventExpensesDao;

  @Autowired
  public EventExpensesService(
      @Qualifier("eventExpensesDao") IEventExpensesDao eventExpensesDao
  ) {
    Assert.notNull(eventExpensesDao);

    this.eventExpensesDao = eventExpensesDao;
  }

  @Override
  public Boolean isEventExisting(int expenseId) {
    return eventExpensesDao.isEventExisting(expenseId);
  }
}

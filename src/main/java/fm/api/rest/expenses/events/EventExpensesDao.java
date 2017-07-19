package fm.api.rest.expenses.events;

import fm.api.rest.expenses.events.interfaces.IEventExpensesDao;
import fm.common.dao.DaoUtils;
import io.jsonwebtoken.lang.Assert;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 * Date: 7/19/2017 Time: 11:00 AM
 * <p>
 * TODO: WRITE THE DESCRIPTION HERE
 *
 * @author haho
 */
@Repository("eventExpensesDao")
public class EventExpensesDao implements IEventExpensesDao {

  private static final Logger LOGGER = LogManager.getLogger(EventExpensesDao.class);

  private final NamedParameterJdbcOperations namedTemplate;

  @Autowired
  public EventExpensesDao(NamedParameterJdbcTemplate namedTemplate) {
    Assert.notNull(namedTemplate);
    this.namedTemplate = namedTemplate;
  }

  @Override
  public Boolean isEventExisting(int expenseId) {
    final String sql = "SELECT COUNT(*) FROM fm_event_expenses WHERE expense_id = :expenseId"
        ;

    final MapSqlParameterSource paramsMap = new MapSqlParameterSource();
    paramsMap.addValue("expenseId", expenseId);

    DaoUtils.debugQuery(LOGGER, sql, paramsMap.getValues());

    return namedTemplate.queryForObject(sql, paramsMap, Integer.class) > 0;
  }
}

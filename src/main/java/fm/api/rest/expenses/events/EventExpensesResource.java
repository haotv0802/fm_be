package fm.api.rest.expenses.events;

import fm.api.rest.BaseResource;
import fm.api.rest.expenses.events.interfaces.IEventExpensesService;
import fm.auth.UserDetailsImpl;
import fm.common.beans.HeaderLang;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * Date: 7/18/2017 Time: 6:00 PM
 * <p>
 *
 * @author haho
 */
@RestController
public class EventExpensesResource extends BaseResource {

  private final IEventExpensesService eventExpensesService;

  private static final Logger LOGGER = LogManager.getLogger(EventExpensesResource.class);

  @Autowired
  public EventExpensesResource(@Qualifier("eventExpensesService") IEventExpensesService eventExpensesService) {
    Assert.notNull(eventExpensesService);

    this.eventExpensesService = eventExpensesService;
  }

  @GetMapping("/eventExpenses/{expenseId}/check")
  @PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
  public ResponseEntity isEventExisting(
      @PathVariable("expenseId") int expenseId,
      @AuthenticationPrincipal UserDetailsImpl userDetails,
      @HeaderLang String lang) {
    boolean value = eventExpensesService.isEventExisting(expenseId);
    LOGGER.info("isEventExisting: " + value);
    return new ResponseEntity(new Object() {
      public final Boolean isEventExisting = value;
    }, HttpStatus.OK);
  }
}

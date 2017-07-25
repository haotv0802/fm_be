package fm.api.rest.expenses.events;

import fm.api.rest.BaseResource;
import fm.api.rest.expenses.events.beans.EventPresenter;
import fm.api.rest.expenses.events.beans.Expense;
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
import org.springframework.web.bind.annotation.*;

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

  @GetMapping("/eventExpenses/{expenseId}")
  @PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
  public EventPresenter getEvent(
      @PathVariable("expenseId") int expenseId,
      @AuthenticationPrincipal UserDetailsImpl userDetails,
      @HeaderLang String lang) {
    return eventExpensesService.getEvent(userDetails.getUserId(), expenseId);
  }

  @PostMapping("/eventExpenses/{expenseId}")
  @PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
  public ResponseEntity addExpense(
      @AuthenticationPrincipal UserDetailsImpl userDetails,
      @HeaderLang String lang,
      @PathVariable("expenseId") int expenseId,
      @RequestBody Expense expenseCreation
  ) {
    Long id = this.eventExpensesService.addExpense(expenseCreation, expenseId);
    return new ResponseEntity<>(new Object() {
      public final Long expenseId = id;
    }, HttpStatus.CREATED);
  }

  @PatchMapping("/eventExpenses/{eventId}")
  @PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
  public ResponseEntity updateExpense(
      @AuthenticationPrincipal UserDetailsImpl userDetails,
      @HeaderLang String lang,
      @PathVariable("eventId") int eventId,
      @RequestBody Expense expenseCreation
  ) {
    this.eventExpensesService.updateExpense(expenseCreation, eventId);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

  @DeleteMapping("/eventExpenses/{eventId}")
  @PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
  public ResponseEntity deleteExpense(
      @AuthenticationPrincipal UserDetailsImpl userDetails,
      @HeaderLang String lang,
      @PathVariable("eventId") int eventId
  ) {
    this.eventExpensesService.deleteExpense(eventId);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }
}

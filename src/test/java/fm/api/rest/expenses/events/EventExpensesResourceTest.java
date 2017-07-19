package fm.api.rest.expenses.events;

import fm.api.rest.BaseDocumentation;
import fm.api.rest.expenses.Expense;
import fm.api.rest.expenses.ExpensePresenter;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.testng.annotations.Test;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import static com.fasterxml.jackson.databind.type.TypeFactory.defaultInstance;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by haho on 3/22/2017.
 */
public class EventExpensesResourceTest extends BaseDocumentation {

  @Test
  public void testIsEventExisting() throws Exception {
    mockMvc
        .perform(get("/svc/eventExpenses/{expenseId}/check", 89)
            .header("Accept-Language", "en")
            .header("X-AUTH-TOKEN", authTokenService.getAuthToken())
        )
        .andExpect(status().is(200))
    ;
  }

}

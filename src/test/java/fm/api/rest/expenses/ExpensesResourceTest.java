package fm.api.rest.expenses;

import fm.api.rest.BaseDocumentation;
import org.springframework.http.MediaType;
import org.testng.annotations.Test;

import java.math.BigDecimal;
import java.util.Date;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by haho on 3/22/2017.
 */
public class ExpensesResourceTest extends BaseDocumentation {

  @Test
  public void testGetExpenses() throws Exception {
    mockMvc
        .perform(get("/svc/expenses")
            .header("Accept-Language", "en")
            .header("X-AUTH-TOKEN", authTokenService.getAuthToken())
        )
        .andExpect(status().is(200))
    ;
  }

  @Test
  public void testAddExpense() throws Exception {
    Expense creation = new Expense();
    creation.setAmount(new BigDecimal(1234));
    creation.setAnEvent(false);
    creation.setCardId(2);
    creation.setDate(new Date());
    creation.setForPerson(null);
    creation.setPlace("ILA");

    mockMvc
        .perform(post("/svc/expenses")
            .header("Accept-Language", "en")
            .header("X-AUTH-TOKEN", authTokenService.getAuthToken())
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(creation))
        )
        .andExpect(status().is(201))
    ;
  }

  @Test
  public void testGetExpensesDetails() throws Exception {
    mockMvc
        .perform(get("/svc/expensesDetails")
            .header("Accept-Language", "en")
            .header("X-AUTH-TOKEN", authTokenService.getAuthToken())
        )
        .andExpect(status().is(200))
    ;
  }

  @Test
  public void getPreviousExpensesDetails() throws Exception {
    mockMvc
        .perform(get("/svc/previousExpensesDetails")
            .header("Accept-Language", "en")
            .header("X-AUTH-TOKEN", authTokenService.getAuthToken())
        )
        .andExpect(status().is(200))
    ;
  }
}

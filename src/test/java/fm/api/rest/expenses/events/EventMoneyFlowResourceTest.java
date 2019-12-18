package fm.api.rest.expenses.events;

import fm.api.rest.BaseDocumentation;
import fm.api.rest.expenses.events.beans.EventPresenter;
import fm.api.rest.expenses.events.beans.Expense;
import org.json.JSONObject;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.testng.annotations.Test;

import java.math.BigDecimal;
import java.util.Date;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by haho on 3/22/2017.
 */
public class EventMoneyFlowResourceTest extends BaseDocumentation {

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

  @Test
  public void testGetEvent() throws Exception {
    mockMvc
        .perform(get("/svc/eventExpenses/{expenseId}", 21)
            .header("Accept-Language", "en")
            .header("X-AUTH-TOKEN", authTokenService.getAuthToken())
        )
        .andExpect(status().is(200))
    ;
  }

  @Test
  public void testAddEventExpense() throws Exception {
    MvcResult mvcResult = mockMvc
        .perform(get("/svc/eventExpenses/{expenseId}", 1)
            .header("Accept-Language", "en")
            .header("X-AUTH-TOKEN", authTokenService.getAuthToken())
        )
        .andExpect(status().is(200))
        .andReturn()
    ;

    EventPresenter eventPresenter = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), EventPresenter.class);

    Expense creation = new Expense();
    creation.setAmount(new BigDecimal(1234));
    creation.setAnEvent(false);
    creation.setCardId(1);
    creation.setDate(new Date());
    creation.setForPerson(null);
    creation.setPlace("ILA");

    mockMvc
        .perform(post("/svc/eventExpenses/{expenseId}", eventPresenter.getId())
            .header("Accept-Language", "en")
            .header("X-AUTH-TOKEN", authTokenService.getAuthToken())
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(creation))
        )
        .andExpect(status().is(201))
    ;

    mockMvc
        .perform(get("/svc/eventExpenses/{expenseId}", 1)
            .header("Accept-Language", "en")
            .header("X-AUTH-TOKEN", authTokenService.getAuthToken())
        )
        .andExpect(status().is(200))
    ;
  }

  @Test
  public void testUpdateEventExpense() throws Exception {
    MvcResult mvcResult = mockMvc
        .perform(get("/svc/eventExpenses/{expenseId}", 1)
            .header("Accept-Language", "en")
            .header("X-AUTH-TOKEN", authTokenService.getAuthToken())
        )
        .andExpect(status().is(200))
        .andReturn()
        ;

    EventPresenter eventPresenter = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), EventPresenter.class);

    Expense creation = new Expense();
    creation.setAmount(new BigDecimal(1234));
    creation.setAnEvent(false);
    creation.setCardId(1);
    creation.setDate(new Date());
    creation.setForPerson(null);
    creation.setPlace("ILA");

     MvcResult result = mockMvc
        .perform(post("/svc/eventExpenses/{expenseId}", eventPresenter.getId())
            .header("Accept-Language", "en")
            .header("X-AUTH-TOKEN", authTokenService.getAuthToken())
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(creation))
        )
        .andExpect(status().is(201))
        .andReturn()
    ;
    JSONObject jsonObject = new JSONObject(result.getResponse().getContentAsString());
    Long id = Long.valueOf(jsonObject.get("expenseId").toString());

    Expense update = new Expense();
    update.setAmount(new BigDecimal(1234));
    update.setAnEvent(false);
    update.setCardId(1);
    update.setDate(new Date());
    update.setForPerson(null);
    update.setPlace("ILA ILA");

    mockMvc
        .perform(patch("/svc/eventExpenses/{eventId}", id)
            .header("Accept-Language", "en")
            .header("X-AUTH-TOKEN", authTokenService.getAuthToken())
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(update))
        )
        .andExpect(status().is(204))
    ;

    mockMvc
        .perform(get("/svc/eventExpenses/{expenseId}", 1)
            .header("Accept-Language", "en")
            .header("X-AUTH-TOKEN", authTokenService.getAuthToken())
        )
        .andExpect(status().is(200))
        ;
  }

  @Test
  public void testDeleteEventExpense() throws Exception {
    MvcResult mvcResult = mockMvc
        .perform(get("/svc/eventExpenses/{expenseId}", 1)
            .header("Accept-Language", "en")
            .header("X-AUTH-TOKEN", authTokenService.getAuthToken())
        )
        .andExpect(status().is(200))
        .andReturn()
        ;

    EventPresenter eventPresenter = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), EventPresenter.class);

    Expense creation = new Expense();
    creation.setAmount(new BigDecimal(1234));
    creation.setAnEvent(false);
    creation.setCardId(1);
    creation.setDate(new Date());
    creation.setForPerson(null);
    creation.setPlace("ILA");

    MvcResult result = mockMvc
        .perform(post("/svc/eventExpenses/{expenseId}", eventPresenter.getId())
            .header("Accept-Language", "en")
            .header("X-AUTH-TOKEN", authTokenService.getAuthToken())
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(creation))
        )
        .andExpect(status().is(201))
        .andReturn()
        ;
    JSONObject jsonObject = new JSONObject(result.getResponse().getContentAsString());
    Long id = Long.valueOf(jsonObject.get("expenseId").toString());

    mockMvc
        .perform(get("/svc/eventExpenses/{expenseId}", 1)
            .header("Accept-Language", "en")
            .header("X-AUTH-TOKEN", authTokenService.getAuthToken())
        )
        .andExpect(status().is(200))
        .andReturn()
    ;

    mockMvc
        .perform(delete("/svc/eventExpenses/{eventId}", id)
            .header("Accept-Language", "en")
            .header("X-AUTH-TOKEN", authTokenService.getAuthToken())
            .contentType(MediaType.APPLICATION_JSON)
        )
        .andExpect(status().is(204))
    ;

    mockMvc
        .perform(get("/svc/eventExpenses/{expenseId}", 1)
            .header("Accept-Language", "en")
            .header("X-AUTH-TOKEN", authTokenService.getAuthToken())
        )
        .andExpect(status().is(200))
    ;
  }
}

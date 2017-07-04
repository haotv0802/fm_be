package fm.api.rest.expenses;

import fm.api.rest.BaseDocumentation;
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
  public void testUpdateExpense() throws Exception {
    MvcResult result = mockMvc
        .perform(get("/svc/expenses")
            .header("Accept-Language", "en")
            .header("X-AUTH-TOKEN", authTokenService.getAuthToken())
        )
        .andExpect(status().is(200))
        .andReturn()
    ;

    List<ExpensePresenter> expenses = objectMapper.readValue(
        result.getResponse().getContentAsString(),
        defaultInstance().constructCollectionType(List.class, ExpensePresenter.class)
    );
    ExpensePresenter expensePresenter = expenses.get(0);
    Expense expense = new Expense();
    expense.setAmount(expensePresenter.getAmount());
    expense.setAnEvent(expensePresenter.getAnEvent());
    expense.setCardId(expensePresenter.getCardId());
    expense.setDate(new Date());
    expense.setForPerson(null);
    expense.setPlace("VUS");
    expense.setId(expensePresenter.getId());

    mockMvc
        .perform(patch("/svc/expenses")
            .header("Accept-Language", "en")
            .header("X-AUTH-TOKEN", authTokenService.getAuthToken())
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(expense))
        )
        .andExpect(status().is(204))
    ;

    mockMvc
        .perform(get("/svc/expenses")
            .header("Accept-Language", "en")
            .header("X-AUTH-TOKEN", authTokenService.getAuthToken())
        )
        .andExpect(status().is(200))
        ;
  }


  @Test
  public void testDeleteExpense() throws Exception {
    MvcResult result = mockMvc
        .perform(get("/svc/expenses")
            .header("Accept-Language", "en")
            .header("X-AUTH-TOKEN", authTokenService.getAuthToken())
        )
        .andExpect(status().is(200))
        .andReturn()
        ;

    List<ExpensePresenter> expenses = objectMapper.readValue(
        result.getResponse().getContentAsString(),
        defaultInstance().constructCollectionType(List.class, ExpensePresenter.class)
    );
    ExpensePresenter expensePresenter = expenses.get(0);
    mockMvc
        .perform(delete("/svc/expenses/{expenseId}/delete", expensePresenter.getId())
            .header("Accept-Language", "en")
            .header("X-AUTH-TOKEN", authTokenService.getAuthToken())
            .contentType(MediaType.APPLICATION_JSON)
        )
        .andExpect(status().is(204))
    ;

    mockMvc
        .perform(get("/svc/expenses")
            .header("Accept-Language", "en")
            .header("X-AUTH-TOKEN", authTokenService.getAuthToken())
        )
        .andExpect(status().is(200))
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

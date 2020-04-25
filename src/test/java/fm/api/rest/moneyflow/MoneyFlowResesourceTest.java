package fm.api.rest.moneyflow;

import fm.api.rest.BaseDocumentation;
import org.testng.annotations.Test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by haoho on 4/25/20.
 */
public class MoneyFlowResesourceTest extends BaseDocumentation {

  @Test
  public void testGetExpenses() throws Exception {
    mockMvc
        .perform(get("/svc/moneyflow")
            .header("Accept-Language", "en")
            .header("X-AUTH-TOKEN", authTokenService.getAuthToken())
        )
        .andExpect(status().is(200))
    ;
  }

}

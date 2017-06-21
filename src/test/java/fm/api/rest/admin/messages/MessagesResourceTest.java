package fm.api.rest.admin.messages;

import fm.api.rest.BaseAdminDocumentation;
import org.testng.annotations.Test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by haho on 6/7/2017.
 */
public class MessagesResourceTest extends BaseAdminDocumentation {

  @Test
  public void testGetMessages() throws Exception {
    mockMvc
        .perform(get("/svc/admin/messages")
            .header("Accept-Language", "")
            .header("X-AUTH-TOKEN", authTokenService.getAuthToken())
        )
        .andExpect(status().is(200))
    ;
  }
}
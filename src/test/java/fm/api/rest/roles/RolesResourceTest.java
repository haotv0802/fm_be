package fm.api.rest.roles;

import fm.api.rest.BaseAdminDocumentation;
import org.testng.annotations.Test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by haho on 3/22/2017.
 */
public class RolesResourceTest extends BaseAdminDocumentation {

  @Test
  public void testGetRoles() throws Exception {
    mockMvc
        .perform(get("/svc/admin/roles")
            .header("Accept-Language", "en")
            .header("X-AUTH-TOKEN", authTokenService.getAuthToken())
        )
        .andExpect(status().is(200))
    ;
  }

  @Test
  public void testGetRolesInfo() throws Exception {
    mockMvc
        .perform(get("/svc/admin/roles/keyValuePair")
            .header("Accept-Language", "en")
            .header("X-AUTH-TOKEN", authTokenService.getAuthToken())
        )
        .andExpect(status().is(200))
    ;
  }
}

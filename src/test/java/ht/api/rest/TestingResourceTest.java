package ht.api.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.testng.annotations.Test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by haho on 3/3/2017.
 */
@WebAppConfiguration
@ContextConfiguration(
    locations = {
         "/config/spring-mvc.xml"
        ,"/config/spring-mvc-test.xml"
    })
public class TestingResourceTest extends BaseAdminDocumentation {

  @Autowired
  @Qualifier("authTokenService")
  private IAuthTokenService authTokenService;

  public TestingResourceTest() {
  }

  @Test
  public void testGetAuthorities() throws Exception {
    mockMvc
        .perform(get("/svc/staff/testing")
            .header("Accept-Language", "en")
            .header("X-AUTH-TOKEN", authTokenService.getAuthToken())
        )
        .andExpect(status().is(200))
    ;
  }
}

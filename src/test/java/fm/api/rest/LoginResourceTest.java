package fm.api.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import fm.auth.Credentials;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.testng.annotations.Test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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
public class LoginResourceTest extends BaseDocumentation {

  @Autowired
  @Qualifier("authTokenService")
  private IAuthTokenService authTokenService;

  public LoginResourceTest() {
  }

  @Test
  public void testLogin() throws Exception {
    Credentials c = new Credentials();
    c.setUserName("admin");
    c.setUserPass("admin");
    c.setUserLang("AN");

    String auth = mockMvc
        .perform(post("/svc/login")
            .header("Accept-Language", "en")
            .contentType(MediaType.APPLICATION_JSON)
            .content(new ObjectMapper().writeValueAsString(c))
        )
        .andExpect(status().is(200))
        .andReturn()
        .getResponse()
        .getHeader("X-AUTH-TOKEN")
        ;
    logger.info("X-AUTH: " + auth);
  }

  @Test
  public void testHello() throws Exception {
    mockMvc
        .perform(post("/svc/hello")
            .header("Accept-Language", "fr")
            .header("X-AUTH-TOKEN", authTokenService.getAuthToken())
        )
        .andExpect(status().is(200))
    ;
  }
}

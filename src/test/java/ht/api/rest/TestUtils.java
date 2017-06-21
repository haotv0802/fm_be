package ht.api.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import ht.auth.AuthConstants;
import ht.auth.Credentials;
import org.apache.logging.log4j.Logger;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public final class TestUtils {

  private TestUtils() {
  }

  public static void beginSubTestSection(Logger log, String sectionName) {
    log.info("****** BEGIN SECTION " + sectionName + " **************************************************************");
  }

  public static void endSubTestSection(Logger log, String sectionName) {
    log.info("******   END SECTION " + sectionName + " ***************************************************************");
  }

  /**
   * Perform login
   *
   * @param mock     to be used
   * @param username the user name
   * @param password the user password
   * @return authentication header to be included to sequential calls
   */
  public static String performLogin(MockMvc mock, String username, String password) throws Exception {
    Credentials c = new Credentials();
    c.setUserName(username);
    c.setUserPass(password);

    return mock
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

  }

  public static void performLogout(MockMvc mock, String sessionId) throws Exception {
    mock.perform(
        post("/logout")
            .header("Accept-Language", "en")
            .header(AuthConstants.AUTH_HEADER_NAME, sessionId)
    ).andExpect(status().is(200));
  }
}

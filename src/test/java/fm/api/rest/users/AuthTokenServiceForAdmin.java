package fm.api.rest.users;

import fm.api.rest.IAuthTokenService;
import fm.api.rest.TestUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.io.IoBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.Filter;
import java.io.PrintWriter;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@Service("authTokenServiceForAdmin")
public class AuthTokenServiceForAdmin implements IAuthTokenService {

  private static final Logger logger = LogManager.getLogger(AuthTokenServiceForAdmin.class);

  private String authToken;

  @Autowired
  public AuthTokenServiceForAdmin(
      WebApplicationContext wac,
      @Qualifier("springSessionRepositoryFilter") Filter sessionRepositoryFilter,
      @Qualifier("txFilter") Filter txFilter
  ) throws Exception {
    final PrintWriter printWriter = IoBuilder.forLogger(logger).buildPrintWriter();

    MockMvc mockMvc
        = MockMvcBuilders
        .webAppContextSetup(wac)
        .addFilter(sessionRepositoryFilter)
        .addFilter(txFilter)
        .apply(springSecurity())
        .alwaysDo(print(printWriter))
        .build();

    authToken = TestUtils.performLogin(mockMvc, "admin", "admin");
  }

  @Override
  public String getAuthToken() {
    return authToken;
  }
}

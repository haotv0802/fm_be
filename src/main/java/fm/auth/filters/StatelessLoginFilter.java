package fm.auth.filters;

import com.fasterxml.jackson.databind.ObjectMapper;
import fm.auth.Credentials;
import fm.auth.UserTokenDetails;
import fm.auth.exceptions.BadLoginPayloadException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class StatelessLoginFilter extends AbstractAuthenticationProcessingFilter {

  public StatelessLoginFilter(
       String urlMapping
      ,AuthenticationManager authManager
      ,AuthenticationFailureHandler failureHandler
      ,AuthenticationSuccessHandler successHandler
  ) {
    super(new AntPathRequestMatcher(urlMapping));

    setAuthenticationManager(authManager);
    setAuthenticationFailureHandler(failureHandler);
    setAuthenticationSuccessHandler(successHandler);
  }

  @Override
  public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
      throws AuthenticationException, IOException, ServletException {
    final Credentials user;
    try {
      user = new ObjectMapper().readValue(request.getInputStream(), Credentials.class);
    } catch (IOException e) {
      throw new BadLoginPayloadException("Invalid login payload");
    }

    final UsernamePasswordAuthenticationToken loginToken =
        new UsernamePasswordAuthenticationToken(user.getUserName(), user.getUserPass());
    loginToken.setDetails(new UserTokenDetails(request));

    return getAuthenticationManager().authenticate(loginToken);
  }

}
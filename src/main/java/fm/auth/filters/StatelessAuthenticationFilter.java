package fm.auth.filters;

import fm.auth.TokenAuthenticationService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.ThreadContext;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;
import org.springframework.web.util.UrlPathHelper;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class StatelessAuthenticationFilter extends GenericFilterBean {
  private static final Logger logger = LogManager.getLogger(StatelessAuthenticationFilter.class);

  private final TokenAuthenticationService authenticationService;
  private final UrlPathHelper urlPathHelper = new UrlPathHelper();

  public StatelessAuthenticationFilter(TokenAuthenticationService authenticationService) {
    this.authenticationService = authenticationService;
  }

  private String getRelativeRequestURI(ServletRequest request) {
    if (request instanceof HttpServletRequest) {
      return urlPathHelper.getPathWithinApplication((HttpServletRequest) request);
    }

    return null;
  }

  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
      throws IOException, ServletException {

    HttpServletRequest httpRequest = (HttpServletRequest) request;
    Authentication authentication = authenticationService.getAuthentication(httpRequest);

    if (null != authentication) {
      ThreadContext.put("username", authentication.getName());
    }
    ThreadContext.put("URI", getRelativeRequestURI(request));
    SecurityContextHolder.getContext().setAuthentication(authentication);

    chain.doFilter(request, response);

    SecurityContextHolder.getContext().setAuthentication(null);
    ThreadContext.clearAll();
  }
}

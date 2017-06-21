package fm.auth;

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
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LoggingEnhancingFilter extends GenericFilterBean
{

  private final UrlPathHelper urlPathHelper = new UrlPathHelper();

  private void addUid(){
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

    if (null != authentication) {
      ThreadContext.put("uid", authentication.getName() );
    }
  }

  private void addSid(ServletRequest request){
    if ( request instanceof HttpServletRequest) {
      final HttpSession session = ((HttpServletRequest) request).getSession(false);
      if ( null != session){
        String sid = session.getId();
        ThreadContext.put("sid", "xxx" + sid.substring(Math.max(0, sid.length() - 8)));
      }
    }
  }

  private void addRelURI(ServletRequest request) {
    if ( request instanceof HttpServletRequest) {
      ThreadContext.put("URI", urlPathHelper.getPathWithinApplication((HttpServletRequest)request));
    }
  }

  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException
  {
    addUid();
    addSid(request);
    addRelURI(request);

    try {
      chain.doFilter(request, response);
    }
    finally {
      ThreadContext.clearAll();
    }
  }
}

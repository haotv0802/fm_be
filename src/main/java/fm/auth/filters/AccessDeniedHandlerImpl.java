package fm.auth.filters;

import fm.common.ServiceFault;
import org.springframework.context.MessageSource;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Locale;

public class AccessDeniedHandlerImpl implements AccessDeniedHandler {

  private MessageSource messageSource;

  public AccessDeniedHandlerImpl(MessageSource messageSource) {
    this.messageSource = messageSource;
  }

  @Override
  public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
    Locale locale = new Locale("en");

    response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
    response.sendError(HttpServletResponse.SC_FORBIDDEN, "Forbidden");

    ServiceFault serviceFault = new ServiceFault("authorization.not.enough.privileges", messageSource.getMessage("login.blocked.hardlimit", null, locale));

  }
}

package fm.auth.filters;

import com.fasterxml.jackson.databind.ObjectMapper;
import fm.auth.exceptions.BadLoginPayloadException;
import fm.auth.exceptions.HardLimitReachedException;
import fm.auth.exceptions.SoftLimitReachedException;
import fm.common.ServiceFault;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Locale;

public class  AuthenticationFailureHandlerImpl implements AuthenticationFailureHandler {

  private MessageSource messageSource;

  public AuthenticationFailureHandlerImpl(MessageSource messageSource) {
    this.messageSource = messageSource;
  }

  @Override
  public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
    Locale locale = new Locale("en");
    if ("fr".equalsIgnoreCase(request.getHeader(HttpHeaders.ACCEPT_LANGUAGE))) {
      locale = new Locale("fr");
    }

    response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
    response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
    response.setHeader(HttpHeaders.WWW_AUTHENTICATE, "ImxAuth realm=\"imx\"");
    final ServletOutputStream outputStream = response.getOutputStream();

    if (exception instanceof BadLoginPayloadException) {
      ServiceFault sf = new ServiceFault("login.bad.payload", messageSource.getMessage("login.bad.payload", null, locale));
      new ObjectMapper().writeValue(outputStream, sf);
      return;
    }

    if (exception instanceof UsernameNotFoundException) {
      ServiceFault sf = new ServiceFault("login.user.not.found", messageSource.getMessage("login.user.not.found", null, locale));
      new ObjectMapper().writeValue(outputStream, sf);
      return;
    }

    // Either: bad password or wrong credentials when provider.setHideUserNotFoundExceptions(true);
    if (exception instanceof BadCredentialsException) {

      if (exception instanceof SoftLimitReachedException) {
        ServiceFault sf = new ServiceFault("login.blocked.softlimit", messageSource.getMessage("login.blocked.softlimit", null, locale));
        new ObjectMapper().writeValue(outputStream, sf);
        return;
      }

      if (exception instanceof HardLimitReachedException) {
        ServiceFault sf = new ServiceFault("login.blocked.hardlimit", messageSource.getMessage("login.blocked.hardlimit", null, locale));
        new ObjectMapper().writeValue(outputStream, sf);
        return;
      }

      ServiceFault sf = new ServiceFault("login.invalid", messageSource.getMessage("login.invalid", null, locale));
      new ObjectMapper().writeValue(outputStream, sf);
      return;
    }

    if (exception instanceof AccountExpiredException) {
      ServiceFault sf = new ServiceFault("login.expired", messageSource.getMessage("login.expired", null, locale));
      new ObjectMapper().writeValue(outputStream, sf);
      return;
    }

    //Default message
    ServiceFault sf = new ServiceFault("login.invalid", messageSource.getMessage("login.invalid", null, locale));
    new ObjectMapper().writeValue(outputStream, sf);
    return;
  }
}

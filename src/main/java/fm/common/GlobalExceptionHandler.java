package fm.common;

import fm.common.error.IErrorService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.dao.CannotAcquireLockException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.regex.Pattern;

/**
 * Will make sure to translate some exceptions to a meaningful response to the client.
 */
@ControllerAdvice
public class GlobalExceptionHandler {
  private Logger log = LogManager.getLogger(getClass());
  private static final int PL_SQL_USER_DEFINED_ERR_CODE_RANGE_END = 20999;
  private static final int PL_SQL_USER_DEFINED_ERR_CODE_RANGE_START = 20000;
  private static final Pattern NEW_LINE = Pattern.compile("\\R");

  private final ResourceBundleMessageSource messageSource;

  // TODO log error code into database
  private final IErrorService errorService;

  @Autowired
  public GlobalExceptionHandler(
      ResourceBundleMessageSource messageSource,
      IErrorService errorService
  ) {
    Assert.notNull(messageSource);
    this.messageSource = messageSource;

    Assert.notNull(errorService);
    this.errorService = errorService;
  }


  @ExceptionHandler(ValidationException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST) //400
  @ResponseBody
  public ServiceFault handleConflict(ValidationException e) {
    log.error(e.getMessage(), e);
    String faultCode = e.getFaultCode();
    Object[] context = e.getContext();

    ServiceFault fault = new ServiceFault(faultCode, messageSource.getMessage(faultCode, context, LocaleContextHolder.getLocale()));
    return errorService.registerBackEndFault(fault, e.getStackTrace());
  }


  @ExceptionHandler(EmptyResultDataAccessException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND) // 404
  public ServiceFault handleConflict(EmptyResultDataAccessException e) {
    log.info("Info: ", e);
    return errorService.registerBackEndFault(new ServiceFault(HttpStatus.NOT_FOUND.toString(), e.getMessage()), e.getStackTrace());
  }

  @ExceptionHandler(CannotAcquireLockException.class)
  @ResponseStatus(HttpStatus.LOCKED) // 423
  public ServiceFault handleConflict(CannotAcquireLockException e) {
    log.info("Info:", e);
    return errorService.registerBackEndFault(new ServiceFault(HttpStatus.LOCKED.toString(), e.getMessage()), e.getStackTrace());
  }

}
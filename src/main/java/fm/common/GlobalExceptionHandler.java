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
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Will make sure to translate some exceptions to a meaningful response to the client.
 */
@ControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger logger = LogManager.getLogger(GlobalExceptionHandler.class);

    private final ResourceBundleMessageSource messageSource;

    // log error code into database
    private final IErrorService errorService;

    @Autowired
    public GlobalExceptionHandler(
            ResourceBundleMessageSource messageSource,
            IErrorService errorService
    ) {
        Assert.notNull(messageSource);
        Assert.notNull(errorService);

        this.messageSource = messageSource;
        this.errorService = errorService;
    }

    private String getCurrentUserName() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();

        return username;
    }

    @ExceptionHandler(ValidationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST) //400
    @ResponseBody
    public ServiceFault handleConflict(ValidationException e) {
        logger.error(e.getMessage(), e);
        String faultCode = e.getFaultCode();
        Object[] context = e.getContext();

        ServiceFault fault = new ServiceFault(faultCode, messageSource.getMessage(faultCode, context, LocaleContextHolder.getLocale()));
        return errorService.registerBackEndFault(fault, e.getStackTrace(), e, getCurrentUserName());
    }

    @ExceptionHandler(EmptyResultDataAccessException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND) // 404
    public ServiceFault handleConflict(EmptyResultDataAccessException e) {
        logger.error(e.getMessage(), e);
        return errorService.registerBackEndFault(new ServiceFault(HttpStatus.NOT_FOUND.toString(), e.getMessage()), e.getStackTrace(), e, getCurrentUserName());
    }

    @ExceptionHandler(CannotAcquireLockException.class)
    @ResponseStatus(HttpStatus.LOCKED) // 423
    public ServiceFault handleConflict(CannotAcquireLockException e) {
        logger.error(e.getMessage(), e);
        return errorService.registerBackEndFault(new ServiceFault(HttpStatus.LOCKED.toString(), e.getMessage()), e.getStackTrace(), e, getCurrentUserName());
    }
}

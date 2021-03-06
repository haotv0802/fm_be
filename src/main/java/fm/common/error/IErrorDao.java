package fm.common.error;

import fm.common.FrontEndFault;
import fm.common.ServiceFault;

/**
 * Created by haoho
 * Date:  25/01/2017 Time: 3:06 PM
 * The contract for registering BE/FE errors to RDBMS
 */
public interface IErrorDao {
  String registerBackEndFault(ServiceFault fault);

  String registerBackEndFault(ServiceFault fault, StackTraceElement[] stack, Exception ex, String username);

  String registerBackEndFault(ServiceFault fault, StackTraceElement[] stack, Exception ex, String username, String dump);

  ErrorReference registerFrontEndError(FrontEndFault errorMessage);
}


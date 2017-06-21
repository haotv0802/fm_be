package fm.common.error;

import fm.common.FrontEndFault;
import fm.common.ServiceFault;

/**
 * Property of CODIX Bulgaria EAD
 * Created by vtodorov
 * Date:  25/01/2017 Time: 3:06 PM
 * The contract for registering BE/FE errors to RDBMS
 *
 * @author vtodorov
 */
public interface IErrorDao {
  String registerBackEndFault(ServiceFault fault);

  String registerBackEndFault(ServiceFault fault, StackTraceElement[] stack);

  String registerBackEndFault(ServiceFault fault, StackTraceElement[] stack, String dump);

  ErrorReference registerFrontEndError(FrontEndFault errorMessage);
}


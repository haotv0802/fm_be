package fm.common.error;

import fm.common.ServiceFault;

/**
 * Property of CODIX Bulgaria EAD
 * Created by haho
 * Date:  10/05/2017 Time: 3:08 PM
 * Interface defining the contract for registering BE errors
 */
public interface IErrorService {
  ServiceFault registerBackEndFault(ServiceFault sf, StackTraceElement[] stack);
}


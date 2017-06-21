package ht.common.error;

import ht.common.ServiceFault;

/**
 * Property of CODIX Bulgaria EAD
 * Created by haho
 * Date:  10/05/2017 Time: 3:08 PM
 * Interface defining the contract for registering BE errors
 */
public interface IErrorService {
  ServiceFault registerBackEndFault(ServiceFault sf, StackTraceElement[] stack);
}


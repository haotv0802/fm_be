package fm.common.error;

import fm.common.ServiceFault;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;
import org.springframework.util.Assert;

/**
 * Created by haoho
 * Date:  24/01/2017 Time: 2:08 PM
 * Service for handling errors
 */
@Service
public class ErrorService implements IErrorService {

  private final IErrorDao errorDao;

  private final TransactionTemplate transactionTemplate;

  @Autowired
  public ErrorService(
      IErrorDao errorDao,
      PlatformTransactionManager transactionManager
  ) {
    Assert.notNull(errorDao);
    this.errorDao = errorDao;

    Assert.notNull(transactionManager);
    transactionTemplate = new TransactionTemplate(transactionManager);
    transactionTemplate.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
  }

  /**
   * Register and assigns incident id for a fault
   *
   * @param sf the service fault, never {@code null}
   * @return a service fault with incidend id
   */
  @Override
  public ServiceFault registerBackEndFault(ServiceFault sf, StackTraceElement[] stack, Exception ex) {
    Assert.notNull(sf);
    return transactionTemplate.execute(new TransactionCallback<ServiceFault>() {
      @Override
      public ServiceFault doInTransaction(TransactionStatus status) {
        sf.setIncidentId(errorDao.registerBackEndFault(sf, stack, ex));
        return sf;
      }
    });
  }
}

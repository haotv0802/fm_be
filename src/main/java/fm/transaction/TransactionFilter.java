package fm.transaction;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.apache.commons.lang3.StringUtils.isNotBlank;

public class TransactionFilter implements Filter {
  private Logger log = LogManager.getLogger(getClass());

  private ServletContext ctx;
  private TransactionsList transactions = TransactionsList.getInstance();

  @Autowired
  private ImxTransactionCommit imxTransactionCommit;

  public TransactionFilter() {
  }

  @Override
  public void destroy() {
  }

  @Override
  public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
    HttpServletRequest request = (HttpServletRequest) req;
    HttpServletResponse response = (HttpServletResponse) resp;
    String transactionId = request.getHeader("txId");
    log.debug("txId=={}", transactionId);

    if (isNotBlank(transactionId)) {

      attachTransaction(transactionId, request);

      if (!"/svc/transactions".contains(request.getRequestURI())) {
        imxTransactionCommit.forbidCommit(request.getSession());
      }
    } else {
      detachTransaction();
    }

    // TransactionSynchronizationManager.bindResource(, connection);

    chain.doFilter(request, response);
  }

  private void detachTransaction() {
    ManagedDataSourceProxy.bindCurrentConnection(null);
  }

  /**
   * Make sure that spring will use the transaction with the requested id
   *
   * @param transactionId
   */
  private void attachTransaction(String transactionId, HttpServletRequest request) {

    TrackingConnectionWrapper transaction = transactions.findTransaction(transactionId);
    if (transaction == null) {
      log.warn("Received request about transaction {}, but no such transaction was found!", transactionId);
      return;
    }
    ManagedDataSourceProxy.bindCurrentConnection(transaction);

  }

  @Override
  public void init(FilterConfig cfg) throws ServletException {
    ctx = cfg.getServletContext();
  }

}

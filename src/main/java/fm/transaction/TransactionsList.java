package fm.transaction;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.dao.DuplicateKeyException;

import java.util.ArrayList;
import java.util.List;

/**
 * Provides methods for working with the list of the transactions
 *
 * In general all methods are synchronizing to the access to the collection
 *
 * @author rpaskalev
 *
 */
public class TransactionsList {
  private final static Logger log = LogManager.getLogger(TransactionsList.class);

  private final List<TrackingConnectionWrapper> transactions = new ArrayList<TrackingConnectionWrapper>();

  private static final TransactionsList instance = new TransactionsList();

  /**
   * For now make it a real singleton, until i find a better solution
   */
  private TransactionsList() {
  }

  public static TransactionsList getInstance() {
    return instance;
  }

  public TrackingConnectionWrapper findTransaction(String id) {
    synchronized (transactions) {
      for (int i = 0; i < transactions.size(); i++) {
        TrackingConnectionWrapper conn = transactions.get(i);

        if (log.isTraceEnabled()) {
          log.trace(conn.getTransactionId());
        }

        if (StringUtils.equals(conn.getTransactionId(), id)) {
          // mark in order to prevent the meanhile expire of the connection
          conn.markAccessTime();
          return conn;
        }
      }
    }
    return null;
  }

  public void add(TrackingConnectionWrapper connection, String transactionId) {
    synchronized (transactions) {
      // check that there is not already a connection with that transaction id
      if (findTransaction(transactionId) != null) {
        log.warn("There is already existing transaction with id '" + transactionId + "'");
        throw new DuplicateKeyException("There is already existing transaction with id '" + transactionId + "'");
      }
      connection.setTransactionId(transactionId);
      transactions.add(connection);
    }
  }

  public long checkConnections(ConnectionsWatchdog watcher) {
    synchronized (transactions) {
      return watcher.checkConnections(transactions.iterator());
    }
  }

  public void remove(String transactionId) {
    synchronized (transactions) {
      for (int i = 0; i < transactions.size(); i++) {
        TrackingConnectionWrapper conn = transactions.get(i);
        if (StringUtils.equals(conn.getTransactionId(), transactionId)) {
          // mark in order to prevent the meanhile expire of the connection
          transactions.remove(i);
          break;
        }
      }
    }
  }

  /**
   * Forcibly close all connections in order to avoid connections leakage
   */
  public void forceCloseAllConnections() {
    try {
      synchronized (transactions) {
        for (int i = transactions.size() - 1; i >= 0; i--) {
          try { // i don't want the loop to break in any condition, so thats is why is in separate try
            TrackingConnectionWrapper holder = transactions.remove(i);
            tryRollback(holder);
            tryClose(holder);
          }
          catch (Exception e) {
            log.warn("Error: ", e);
          }
        }
      }
    }
    catch (Exception e) {
      log.warn("Error: ", e);
    }
  }

  private void tryRollback(TrackingConnectionWrapper conn) {
    try {
      conn.getConnection().rollback();
    }
    catch (Exception e) {
      log.warn("Error: ", e);
    }
  }

  private void tryClose(TrackingConnectionWrapper conn) {
    try {
      conn.getConnection().close();
    }
    catch (Exception e) {
      log.warn("Error: ", e);
    }
  }

  public boolean isEmpty() {
    return transactions.isEmpty();
  }
}

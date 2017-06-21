package fm.transaction;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Iterator;
import java.util.concurrent.TimeUnit;

/**
 * Periodically check each connection that it is not abandoned<br>
 * If needed will close it.<br>
 * <p>
 * NOTE: When the thread is terminated, then will close all connections
 */
public class ConnectionsWatchdog implements Runnable {
  private Logger log = LogManager.getLogger(getClass());

  private volatile boolean stop = false;
  private volatile Thread runningThread;

  /**
   * The maximum timeout interval after which the connection will be rollback and closed
   */
  private long maxTimeoutInterval = TimeUnit.MINUTES.toMillis(30);

  private final TransactionsList connections;

  public ConnectionsWatchdog(long maxTimeoutInterval, TransactionsList transactions) {
    super();
    this.maxTimeoutInterval = maxTimeoutInterval;
    this.connections = transactions;
  }

  @Override
  public void run() {
    log.debug("Starting");

    runningThread = Thread.currentThread();
    try {
      while (!stop) {
        try {
          long nextSleepInterval = connections.checkConnections(this);
          // sleep untill the next connection in the pool is about to expire
          Thread.sleep(nextSleepInterval + 10);

        } catch (InterruptedException ie) {
          log.info("Interruped");
          stop = true;
        } catch (Exception e) {
          log.warn("Error:", e);
          stop = true;
        }
      }
    } finally {
      // make sure all connections are closed and released
      connections.forceCloseAllConnections();
    }
    log.debug("Stopped");
  }

  //
  // /**
  // * Check and remove expired connections
  // *
  // * @return the time to sleep until the next check
  // */
  // private long checkConnections() {
  // // scan all connections in the pool
  // long nextSleepInterval = maxTimeoutInterval;
  // // TODO: optimize - implement so you don't lock the collection untill have to modify it
  // synchronized (connections) {
  // for (int i = connections.size() - 1; i >= 0; i--) {
  // TrackingConnectionWrapper conn = connections.get(i);
  // long lastAccessTime = conn.getLastAccessTime();
  // long timePassed = System.currentTimeMillis() - lastAccessTime;
  // // check if is closed and must be removed from the pool
  // // check its timout state - if timedout close it and rollback
  // if (timePassed > maxTimeoutInterval) {
  // log.info("removing expired connection");
  // conn = connections.remove(i);
  // tryRollback(conn);
  // tryClose(conn);
  // // TODO: release the connection (return to the datasource)
  // }
  // else {
  // // find the next time that we need to wakeup the checker
  // if (timePassed < nextSleepInterval) {
  // nextSleepInterval = timePassed;
  // }
  // }
  // }
  // }
  // return nextSleepInterval;
  // }

  /**
   * Check, remove and close the expired connections.<br>
   * Will be called from inside the transactions list, because the list is responsive to synchronize its access
   *
   * @return the time to sleep untill the next check
   */
  public long checkConnections(Iterator<TrackingConnectionWrapper> it) {
    // scan all connections in the pool
    long nextSleepInterval = maxTimeoutInterval;

    while (it.hasNext()) {
      TrackingConnectionWrapper conn = it.next();

      long lastAccessTime = conn.getLastAccessTime();
      long timePassed = System.currentTimeMillis() - lastAccessTime;
      // check if is closed and must be removed from the pool
      // check its timout state - if timedout close it and rollback

      log.info("lastAccessTime " + lastAccessTime);
      log.info("timePassed " + timePassed);
      log.info("maxTimeoutInterval " + maxTimeoutInterval);

      if (timePassed > maxTimeoutInterval) {
        log.info("removing expired connection " + conn.getTransactionId());
        it.remove();
        tryRollback(conn);
        tryClose(conn);
        // TODO: release the connection (return to the datasource)
      }
      // else {
      // // find the next time that we need to wakeup the checker
      // if (timePassed < nextSleepInterval) {
      // nextSleepInterval = timePassed;
      // }
      // }
    }

    return nextSleepInterval;
  }

  private void tryRollback(TrackingConnectionWrapper conn) {
    try {
      conn.getConnection().rollback();
    } catch (Exception e) {
      log.warn("Error: ", e);
    }
  }

  private void tryClose(TrackingConnectionWrapper conn) {
    try {
      conn.getConnection().close();
    } catch (Exception e) {
      log.warn("Error: ", e);
    }
  }

  public void stop() {
    stop = true;
    if (null != runningThread) {
      runningThread.interrupt();
    }
  }

}
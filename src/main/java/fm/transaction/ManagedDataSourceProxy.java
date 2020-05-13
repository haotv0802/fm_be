package fm.transaction;

import fm.auth.UserDetailsImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import javax.sql.DataSource;
import java.io.PrintWriter;
import java.sql.*;

/**
 * Overrides the standard datasource so to produce the wrapped connections
 */
public class ManagedDataSourceProxy implements DataSource {
  private final DataSource ds;
  private Logger log = LogManager.getLogger(getClass());

  /**
   * The connection bound to the current thread
   */
  private static final ThreadLocal<TrackingConnectionWrapper> currentConnection = new ThreadLocal<TrackingConnectionWrapper>();

  private Connection getBoundConnection() {
    TrackingConnectionWrapper connection = currentConnection.get();
    return connection;
  }

  /**
   * Bind the connection to the current thread, so it will be used
   *
   * @param connection
   */
  public static void bindCurrentConnection(TrackingConnectionWrapper connection) {
    currentConnection.set(connection);
  }

  public ManagedDataSourceProxy(DataSource ds) {
    super();
    this.ds = ds;
  }

  @Override
  public PrintWriter getLogWriter() throws SQLException {
    return ds.getLogWriter();
  }

  @Override
  public <T> T unwrap(Class<T> iface) throws SQLException {
    return ds.unwrap(iface);
  }

  @Override
  public void setLogWriter(PrintWriter out) throws SQLException {
    ds.setLogWriter(out);
  }

  @Override
  public boolean isWrapperFor(Class<?> iface) throws SQLException {
    return ds.isWrapperFor(iface);
  }

  @Override
  public Connection getConnection() throws SQLException {
    Connection conn = getBoundConnection();
    if (conn != null) {
      log.debug("Providing bound connection: " + conn);
    } else {
      conn = ds.getConnection();
      log.debug("Providing unbound connection: " + conn);
    }

    UserDetails principal = null;
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    if (auth != null) {
      principal = (UserDetails) auth.getPrincipal();
    }

    setDbmsSessionParams(conn, principal);

    return conn;

  }

  private void setDbmsSessionParams(Connection conn, UserDetails principal) {
    UserDetailsImpl p = (UserDetailsImpl) principal;
  }

  @Override
  public void setLoginTimeout(int seconds) throws SQLException {
    ds.setLoginTimeout(seconds);
  }

  @Override
  public Connection getConnection(String username, String password) throws SQLException {
    Connection conn = getBoundConnection();
    if (conn != null) {
      return conn;
    }
    conn = ds.getConnection(username, password);
    conn.setAutoCommit(false);
    return conn;
  }

  @Override
  public int getLoginTimeout() throws SQLException {
    return ds.getLoginTimeout();
  }

  @Override
  public java.util.logging.Logger getParentLogger() throws SQLFeatureNotSupportedException {
    return ds.getParentLogger();
  }

  public TrackingConnectionWrapper getWrappedConnection() throws SQLException {
    return new TrackingConnectionWrapper(getConnection());
  }
}

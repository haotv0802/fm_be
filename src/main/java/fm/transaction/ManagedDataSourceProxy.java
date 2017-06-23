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
 *
 * @author rpaskalev
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
    setClientInfo(conn, p);
//    setImxSessionRefPerson(conn, p);
//    setImxSessionCurrentUser(conn, p);
//    setImxCurrentUserLang(conn,p);
    setImxSessionDetails(conn, p);
  }

  private void setImxSessionDetails(Connection conn, UserDetailsImpl p) {
    CallableStatement cs = null;
    try {
      String sql = "{ call imx_session_params.setSessionDetails(?, ?, ?, ?)}";
      cs = conn.prepareCall(sql);

      final String currentUserLogin = null != p ? p.getUsername() : null;
//      final Integer currentUserRefperso = null != p ? p.getRefPerson() : null;
      final String currentLanguage = null != p ? p.getLang() : null;

      cs.setString(1, currentUserLogin);

//      if (currentUserRefperso != null)
//        cs.setInt(2, currentUserRefperso);
//      else
//        cs.setObject(2, null);

      cs.setString(3, currentLanguage);

//      log.debug(sql + "; bv{" + currentUserLogin + ", " + currentUserRefperso + ", " + currentLanguage + ", }");
      cs.execute();
    } catch (Exception e) {
      log.warn("Can't set imx_session_params.setSessionDetails(): ", e);
    } finally {
      if (cs != null) {
        try {
          cs.close();
        } catch (Exception e) {
        }
      }
    }
  }

  private void setImxCurrentUserLang(Connection conn, UserDetailsImpl p) {
    CallableStatement cs = null;
    try {
      String sql = "{ call imx_session_params.setCurrentLanguage(?)}";
      cs = conn.prepareCall(sql);

      final String userLang = null != p ? p.getLang() : null;
      cs.setString(1, userLang);

      log.debug(sql + "; bv{" + userLang + "}");
      cs.execute();
    } catch (Exception e) {
      log.warn("Can't set imx_session_params.setCurrentLanguage(): ", e);
    } finally {
      try {
        cs.close();
      } catch (Exception e) {
      }
    }
  }

  private void startXa(Connection conn, UserDetailsImpl p) {
    if (p == null) {
      //todo: replace when the package is fixed
      return;
    }
    CallableStatement pstm = null;
    try {
      String sql = "{ call service_bus.xa_start() }";
      pstm = conn.prepareCall(sql);

      log.debug(sql + "; bv{}");
      pstm.execute();
    } catch (Exception e) {
      log.warn("startXa failed due to: ", e);
    } finally {
      if (null != pstm) {
        try {
          pstm.close();
        } catch (Exception e) {
          //NOP
        }
      }
    }
  }

  private void setImxSessionCurrentUser(Connection conn, UserDetailsImpl p) {
    if (p == null) {
      //todo: replace when the package is fixed
      return;
    }
    CallableStatement pstm = null;
    try {
      String sql = "{ call imx_session_params.setCurrentUserLogin(?)}";
      pstm = conn.prepareCall(sql);

      final String refperson = null != p ? p.getUsername() : null;
      pstm.setString(1, refperson);

      log.debug(sql + "; bv{" + refperson + "}");
      pstm.execute();

    } catch (Exception e) {
      log.warn("Can't set imx_session_params.setCurrentUserLogin(): ", e);
    } finally {
      try {
        pstm.close();
      } catch (Exception e) {
      }
    }
  }

  private void setImxSessionRefPerson(Connection conn, UserDetailsImpl p) {
    if (p == null) {
      //todo: replace when the package is fixed
      return;
    }
    CallableStatement pstm = null;

    try {
      String sql = "{ call imx_session_params.setCurrentUserRefperso(?)}";
      pstm = conn.prepareCall(sql);

//      final Integer refperson = null != p ? p.getRefPerson() : null;

//      if (null != refperson) {
//        pstm.setInt(1, refperson);
//      } else {
//        pstm.setNull(1, Types.NUMERIC);
//      }
//
//      log.debug(sql + "; bv{" + refperson + "}");
      pstm.execute();

    } catch (Exception e) {
      log.warn("Can't set imx_session_params.setCurrentUserRefperso(): ", e);
    } finally {
      try {
        pstm.close();
      } catch (Exception e) {
      }
    }
  }

  private void setClientInfo(Connection conn, UserDetailsImpl principal) {

    CallableStatement pstm = null;
    final String sql = "{ call dbms_application_info.set_client_info(?)}";
    try {
      pstm = conn.prepareCall(sql);
      final String username = null != principal ? principal.getUsername() : null;
      pstm.setString(1, username);

      log.debug(sql + "; bv{" + username + "}");
      pstm.execute();
    } catch (Exception e) {
      log.warn("Can't set connection's user info: ", e);
    } finally {
      try {
        pstm.close();
      } catch (Exception e) {
      }
    }

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

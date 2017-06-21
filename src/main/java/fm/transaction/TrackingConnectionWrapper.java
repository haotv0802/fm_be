package fm.transaction;

import java.io.Serializable;
import java.sql.*;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.Executor;

/**
 * A simple class that keeps a connection and a record of when it was last accessed
 *
 */
public class TrackingConnectionWrapper extends ConnectionWrapper implements Serializable {

  private static final long serialVersionUID = 1L;
  // FIXME: update it with AOP on every call of the wrapped object methods
  private long lastAccessTime;
  private String transactionId;

  public TrackingConnectionWrapper(Connection connection) {
    super(connection);
    try {
      super.setAutoCommit(false);
      markAccessTime();
    }
    catch (SQLException e) {
      log.warn("Error: ", e);
    }
  }

  public long getLastAccessTime() {
    return lastAccessTime;
  }

  public void setLastAccessTime(long lastAccessTime) {
    this.lastAccessTime = lastAccessTime;
  }

  public void markAccessTime() {
    this.lastAccessTime = System.currentTimeMillis();
  }

  public Connection getConnection() {
    return connection;
  }

  public String getTransactionId() {
    return transactionId;
  }

  public void setTransactionId(String transactionId) {
    this.transactionId = transactionId;
  }

  @Override
  public void commit() throws SQLException {
    markAccessTime();
    log.debug("fake commit()");
    // do nothing it will be called by transaction manager on the wrapped object
  }

  @Override
  public void close() throws SQLException {
    markAccessTime();
    log.debug("fake close()");
    // do nothing it will be called by transaction manager on the wrapped object
  }

  @Override
  public void rollback() throws SQLException {
    markAccessTime();
    log.debug("fake rollback()");
    // super.rollback();
  }

  @Override
  public PreparedStatement prepareStatement(String sql) throws SQLException {
    markAccessTime();
    return super.prepareStatement(sql);
  }

  @Override
  public <T> T unwrap(Class<T> iface) throws SQLException {
    markAccessTime();
    return super.unwrap(iface);
  }

  @Override
  public boolean isWrapperFor(Class<?> iface) throws SQLException {
    markAccessTime();
    return super.isWrapperFor(iface);
  }

  @Override
  public Statement createStatement() throws SQLException {
    markAccessTime();
    return super.createStatement();
  }

  @Override
  public CallableStatement prepareCall(String sql) throws SQLException {
    markAccessTime();
    return super.prepareCall(sql);
  }

  @Override
  public String nativeSQL(String sql) throws SQLException {
    markAccessTime();
    return super.nativeSQL(sql);
  }

  @Override
  public void setAutoCommit(boolean autoCommit) throws SQLException {
    markAccessTime();
    // don't allow changing the autocomit - for managed connections it must always be false
    // super.setAutoCommit(autoCommit);
  }

  @Override
  public boolean getAutoCommit() throws SQLException {
    markAccessTime();
    return super.getAutoCommit();
  }

  @Override
  public boolean isClosed() throws SQLException {
    markAccessTime();
    return super.isClosed();
  }

  @Override
  public DatabaseMetaData getMetaData() throws SQLException {
    markAccessTime();
    return super.getMetaData();
  }

  @Override
  public void setReadOnly(boolean readOnly) throws SQLException {
    markAccessTime();
    super.setReadOnly(readOnly);
  }

  @Override
  public boolean isReadOnly() throws SQLException {
    markAccessTime();
    return super.isReadOnly();
  }

  @Override
  public void setCatalog(String catalog) throws SQLException {
    markAccessTime();
    super.setCatalog(catalog);
  }

  @Override
  public String getCatalog() throws SQLException {
    markAccessTime();
    return super.getCatalog();
  }

  @Override
  public void setTransactionIsolation(int level) throws SQLException {
    markAccessTime();
    super.setTransactionIsolation(level);
  }

  @Override
  public int getTransactionIsolation() throws SQLException {
    markAccessTime();
    return super.getTransactionIsolation();
  }

  @Override
  public SQLWarning getWarnings() throws SQLException {
    markAccessTime();
    return super.getWarnings();
  }

  @Override
  public void clearWarnings() throws SQLException {
    markAccessTime();
    super.clearWarnings();
  }

  @Override
  public Statement createStatement(int resultSetType, int resultSetConcurrency) throws SQLException {
    markAccessTime();
    return super.createStatement(resultSetType, resultSetConcurrency);
  }

  @Override
  public PreparedStatement prepareStatement(String sql, int resultSetType, int resultSetConcurrency) throws SQLException {
    markAccessTime();
    return super.prepareStatement(sql, resultSetType, resultSetConcurrency);
  }

  @Override
  public CallableStatement prepareCall(String sql, int resultSetType, int resultSetConcurrency) throws SQLException {
    markAccessTime();
    return super.prepareCall(sql, resultSetType, resultSetConcurrency);
  }

  @Override
  public Map<String, Class<?>> getTypeMap() throws SQLException {
    markAccessTime();
    return super.getTypeMap();
  }

  @Override
  public void setTypeMap(Map<String, Class<?>> map) throws SQLException {
    markAccessTime();
    super.setTypeMap(map);
  }

  @Override
  public void setHoldability(int holdability) throws SQLException {
    markAccessTime();
    super.setHoldability(holdability);
  }

  @Override
  public int getHoldability() throws SQLException {
    markAccessTime();
    return super.getHoldability();
  }

  @Override
  public Savepoint setSavepoint() throws SQLException {
    markAccessTime();
    return super.setSavepoint();
  }

  @Override
  public Savepoint setSavepoint(String name) throws SQLException {
    markAccessTime();
    return super.setSavepoint(name);
  }

  @Override
  public void rollback(Savepoint savepoint) throws SQLException {
    markAccessTime();
    log.debug("fake rollback with savepoint");
    // super.rollback(savepoint);
  }

  @Override
  public void releaseSavepoint(Savepoint savepoint) throws SQLException {
    markAccessTime();
    super.releaseSavepoint(savepoint);
  }

  @Override
  public Statement createStatement(int resultSetType, int resultSetConcurrency, int resultSetHoldability) throws SQLException {
    markAccessTime();
    return super.createStatement(resultSetType, resultSetConcurrency, resultSetHoldability);
  }

  @Override
  public PreparedStatement prepareStatement(String sql, int resultSetType, int resultSetConcurrency, int resultSetHoldability) throws SQLException {
    markAccessTime();
    return super.prepareStatement(sql, resultSetType, resultSetConcurrency, resultSetHoldability);
  }

  @Override
  public CallableStatement prepareCall(String sql, int resultSetType, int resultSetConcurrency, int resultSetHoldability) throws SQLException {
    markAccessTime();
    return super.prepareCall(sql, resultSetType, resultSetConcurrency, resultSetHoldability);
  }

  @Override
  public PreparedStatement prepareStatement(String sql, int autoGeneratedKeys) throws SQLException {
    markAccessTime();
    return super.prepareStatement(sql, autoGeneratedKeys);
  }

  @Override
  public PreparedStatement prepareStatement(String sql, int[] columnIndexes) throws SQLException {
    markAccessTime();
    return super.prepareStatement(sql, columnIndexes);
  }

  @Override
  public PreparedStatement prepareStatement(String sql, String[] columnNames) throws SQLException {
    markAccessTime();
    return super.prepareStatement(sql, columnNames);
  }

  @Override
  public Clob createClob() throws SQLException {
    markAccessTime();
    return super.createClob();
  }

  @Override
  public Blob createBlob() throws SQLException {
    markAccessTime();
    return super.createBlob();
  }

  @Override
  public NClob createNClob() throws SQLException {
    markAccessTime();
    return super.createNClob();
  }

  @Override
  public SQLXML createSQLXML() throws SQLException {
    markAccessTime();
    return super.createSQLXML();
  }

  @Override
  public boolean isValid(int timeout) throws SQLException {
    markAccessTime();
    return super.isValid(timeout);
  }

  @Override
  public void setClientInfo(String name, String value) throws SQLClientInfoException {
    markAccessTime();
    super.setClientInfo(name, value);
  }

  @Override
  public void setClientInfo(Properties properties) throws SQLClientInfoException {
    markAccessTime();
    super.setClientInfo(properties);
  }

  @Override
  public String getClientInfo(String name) throws SQLException {
    markAccessTime();
    return super.getClientInfo(name);
  }

  @Override
  public Properties getClientInfo() throws SQLException {
    markAccessTime();
    return super.getClientInfo();
  }

  @Override
  public Array createArrayOf(String typeName, Object[] elements) throws SQLException {
    markAccessTime();
    return super.createArrayOf(typeName, elements);
  }

  @Override
  public Struct createStruct(String typeName, Object[] attributes) throws SQLException {
    markAccessTime();
    return super.createStruct(typeName, attributes);
  }

  @Override
  public void setSchema(String schema) throws SQLException {
    markAccessTime();
    super.setSchema(schema);
  }

  @Override
  public String getSchema() throws SQLException {
    markAccessTime();
    return super.getSchema();
  }

  @Override
  public void abort(Executor executor) throws SQLException {
    markAccessTime();
    super.abort(executor);
  }

  @Override
  public void setNetworkTimeout(Executor executor, int milliseconds) throws SQLException {
    markAccessTime();
    super.setNetworkTimeout(executor, milliseconds);
  }

  @Override
  public int getNetworkTimeout() throws SQLException {
    markAccessTime();
    return super.getNetworkTimeout();
  }

}

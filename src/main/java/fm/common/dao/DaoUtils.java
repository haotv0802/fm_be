package fm.common.dao;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.PropertyAccessorFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.util.Assert;

import java.beans.FeatureDescriptor;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Arrays.asList;

public final class DaoUtils {

  private DaoUtils() {
  }

  public static final int INVALID_ROWID = 1410;

  private static final List<Class<?>> CACHED_COMMON_TYPES = asList(
      boolean.class, Boolean.class, byte.class, Byte.class, char.class, Character.class,
      double.class, Double.class, int.class, Integer.class, long.class, Long.class,
      float.class, Float.class, short.class, Short.class, String.class, BigDecimal.class,
      Date.class
  );

  /**
   * Helper class for holding the calculated paging arguments
   */
  public static class PagingIndex {
    int startIdx;
    int endIdx;
    int pageSize;


    public int getStartIdx() {
      return startIdx;
    }

    public void setStartIdx(int startIdx) {
      this.startIdx = startIdx;
    }

    public int getEndIdx() {
      return endIdx;
    }

    public void setEndIdx(int endIdx) {
      this.endIdx = endIdx;
    }

    public int getPageSize() {
      return pageSize;
    }

    public void setPageSize(int pageSize) {
      this.pageSize = pageSize;
    }
  }


  /**
   * Calculating indexes for page.
   *
   * @param pageable
   * @return
   */
  public static PagingIndex pagingIdxForPage(Pageable pageable) {
    if (pageable == null) {
      //todo is it going to be null;
      return null;
    }

    final int pageSize = pageable.getPageSize();
    final int base = pageSize * pageable.getPageNumber();

    PagingIndex pi = new PagingIndex();
    pi.setStartIdx(base + 1);
    pi.setEndIdx(base + pageSize);
    pi.setPageSize(pageSize);

    return pi;
  }


  /**
   * Calculating indexes so the fetched result might be with one more then the the size of the page
   *
   * @param pageRequest
   * @return a object holding the calculated indexes
   */
  public static PagingIndex pagingIdxForSlice(Pageable pageRequest) {
    Assert.notNull(pageRequest, "pageRequest must not be null!");

    final int pageSize = pageRequest.getPageSize();
    final int base = pageSize * pageRequest.getPageNumber();

    PagingIndex pi = new PagingIndex();
//    pi.setStartIdx(base + 1);
//    pi.setEndIdx(base + pageSize + 1);
    pi.setPageSize(pageSize + 1);
    pi.setStartIdx(base);
    pi.setEndIdx(base + pageSize);
//    pi.setPageSize(pageSize);

    return pi;
  }

  /**
   * Removes the extra record form a list which is a product of data fetch limited by indexes product of pagingIdxForSlice
   *
   * @param theList     the list to be processed
   * @param pageRequest the page request
   * @return a slice of data
   */
  public static <T> Slice<T> calcSlice(List<T> theList, Pageable pageRequest) {
    Assert.notNull(theList, "theList must not be null!");
    Assert.notNull(pageRequest, "pageRequest must not be null!");

    boolean hasNext = false;
    final int size = theList.size();
    final int pageSize = pageRequest.getPageSize();

    if (pageSize < size) {
      //TODO: if the difference is more than one
      theList.remove(size - 1);
      hasNext = true;
    }

    return new SliceImpl<T>(theList, pageRequest, hasNext);
  }

  /**
   * Logs a query in DEBUG level.
   * For the logging purposes removes multiple spaces in the query string, so, the result might not be correct if the
   * query contains inline parameter literals ( name = 'ABABABA   AAAAAA' ) =>  ( name = 'ABABABA AAAAAA')
   *
   * @param sql    query
   * @param logger to be used
   */
  public static void debugQuery(Logger logger, String sql) {
    if (null != logger && logger.isDebugEnabled()) {
      logger.debug(StringUtils.normalizeSpace(sql) + "; bv:{}");
    }
  }

  /**
   * Logs a query in DEBUG level.
   * For the logging purposes removes multiple spaces in the query string, so, the result might not be correct if the
   * query contains inline parameter literals ( name = 'ABABABA   AAAAAA' ) =>  ( name = 'ABABABA AAAAAA')
   *
   * @param sql      query
   * @param bindVars binded vars
   * @param logger   to be used
   */
  public static void debugQuery(Logger logger, String sql, Map<String, Object> bindVars) {
    if (null != logger && logger.isDebugEnabled()) {
      logger.debug(StringUtils.normalizeSpace(sql) + "; bv:" + bindVars);
    }
  }

  /**
   * Logs a query in DEBUG level.
   * For the logging purposes removes multiple spaces in the query string, so, the result might not be correct if the
   * query contains inline parameter literals ( name = 'ABABABA   AAAAAA' ) =>  ( name = 'ABABABA AAAAAA')
   *
   * @param sql      query
   * @param bindVars binded vars
   * @param logger   to be used
   */
  public static void debugQuery(Logger logger, String sql, Object[] bindVars) {
    if (null != logger && logger.isDebugEnabled()) {
      logger.debug(StringUtils.normalizeSpace(sql) + "; bv:" + Arrays.toString(bindVars));
    }
  }

  public static void debugQuery(Logger logger, String sql, SqlParameterSource sqlParameterSource) {

    if (null != logger && logger.isDebugEnabled()) {
      if (sqlParameterSource instanceof BeanPropertySqlParameterSource)
        debugQuery(logger, sql, (BeanPropertySqlParameterSource) sqlParameterSource);
      else if (sqlParameterSource instanceof MapSqlParameterSource)
        debugQuery(logger, sql, (MapSqlParameterSource) sqlParameterSource);
    }
  }

  public static void debugQuery(Logger logger, String sql, MapSqlParameterSource mapSqlParameterSource) {
    if (null != logger && logger.isDebugEnabled()) {
      debugQuery(logger, sql, mapSqlParameterSource.getValues());
    }
  }

  /**
   * Logs a query in DEBUG level.
   * For the logging purposes removes multiple spaces in the query string, so, the result might not be correct if the
   * query contains inline parameter literals ( name = 'ABABABA   AAAAAA' ) =>  ( name = 'ABABABA AAAAAA')
   *
   * @param logger    to be used
   * @param sql       query
   * @param batchVars the batch bind values
   */
  public static void debugQuery(Logger logger, String sql, MapSqlParameterSource[] batchVars) {
    if (null != logger && logger.isDebugEnabled()) {
      logger.debug(sql.trim().replaceAll(" +", " "));

      final int length = batchVars.length;
      if (null != batchVars && length > 0) {
        for (int i = 0; i < length; i++) {
          logger.debug("bv[" + i + "]: " + batchVars[i].getValues());
        }
      }
    }
  }


  public static void debugQuery(Logger logger, String sql, final BeanPropertySqlParameterSource beanPropertySqlParameterSource) {

    if (null != logger && logger.isDebugEnabled()) {
      //works with one level of nested bean
      String bindVars =
          Stream.of(beanPropertySqlParameterSource.getReadablePropertyNames())
              .filter(beanPropertySqlParameterSource::hasValue)
              .filter(bindProperty -> !"class".equals(bindProperty))
              .map(bindProperty -> {
                Object propertyValue = beanPropertySqlParameterSource.getValue(bindProperty);

                if (propertyValue == null)
                  return bindProperty + '=' + "null";
                else if (CACHED_COMMON_TYPES.contains(propertyValue.getClass()))
                  return bindProperty + '=' + propertyValue;
                else { //Nested bean
                  BeanWrapper beanWrapper = PropertyAccessorFactory.forBeanPropertyAccess(propertyValue);

                  return Stream.of(beanWrapper.getPropertyDescriptors())
                      .filter(nestedProp -> !"class".equals(nestedProp.getName()))
                      .map(FeatureDescriptor::getName)
                      .filter(beanWrapper::isReadableProperty)
                      .map(nestedProp -> bindProperty + '.' + nestedProp + '=' + beanWrapper.getPropertyValue(nestedProp))
                      .collect(Collectors.joining(", "));
                }
              })
              .collect(Collectors.joining(", "));

      logger.debug(StringUtils.normalizeSpace(sql) + "; bv: " + bindVars);
    }
  }
}

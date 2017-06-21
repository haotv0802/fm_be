package fm.common;

import org.springframework.data.domain.Sort;
import org.springframework.util.Assert;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by haho
 * Date:  17/05/2017
 */
public class HTStringUtils {

  private static final int CAPACITY = 128;

  public static final String SUCCESS_STATUS = "success";

  public static final String FAILURE_STATUS = "failure";

  public static final String ERROR_STATUS = "error";

  private HTStringUtils() {
  }

  public static String nvl(String toTest, String fallBack) {
    if (null != toTest && !toTest.isEmpty()) {
      return toTest;
    }
    return fallBack;
  }

  public static String orderByType(String orderBy) {

    if (orderBy != null && ("DESC".equalsIgnoreCase(orderBy) || "ASC".equalsIgnoreCase(orderBy))) {
      return orderBy.toUpperCase();
    }
    return "";
  }

  @Deprecated
  public static String populateOrderBy(Sort sort, String defautOrderByClause) {

    StringBuilder orderClause = new StringBuilder("");

    if (sort != null) {

      int count = 0;
      for (Sort.Order order : sort) {
        orderClause.append(count++ > 0 ? ", " : "");
        orderClause.append(String.format("%1$s %2$s", order.getProperty(), order.getDirection()));
      }

      return orderClause.toString();
    }
    return defautOrderByClause;
  }

  /**
   *
   * @param sort
   * @param validOrders
   * @param defaultOrderByClause if NULL, " id ASC" will be added
   * @return
   */
  public static String populateOrderBy(Sort sort, String validOrders, String defaultOrderByClause) {
    Assert.hasText(validOrders);
//    Assert.hasText(defaultOrderByClause);
    if (null == defaultOrderByClause || defaultOrderByClause.isEmpty()) {
      defaultOrderByClause = " id ASC";
    }

    Set<String> processedOrdersList = new HashSet<>(Arrays.asList(validOrders.split(",")));

    return populateOrderBy(sort, processedOrdersList, defaultOrderByClause);
  }


  /**
   * Constructs order by clause from Sort object set of valid columns
   *
   * @param sort                - sort object, may be null
   * @param ordersList              - set of valid order cols, never null and never containing null element
   * @param defaultOrderByClause the default order by, never blank
   * @return String with order by columns
   * @throws IllegalArgumentException when valids is null or contains null element, or defautOrderByClause is empty
   */
  public static String populateOrderBy(Sort sort, Set<String> ordersList, String defaultOrderByClause) {
    Assert.isTrue(!ordersList.contains(null));
    Assert.hasText(defaultOrderByClause);

    if (null != sort && !ordersList.isEmpty()) {
      StringBuilder orderClause = new StringBuilder(CAPACITY);

      for (Sort.Order order : sort) {
        String orderProperty = order.getProperty().trim();
        Sort.Direction orderDirection = order.getDirection();

        if (ordersList.contains(orderProperty)) {
          orderClause
              .append(orderClause.length() == 0 ? "" : ", ")
              .append(orderProperty)
              .append(' ')
              .append(orderDirection);
        }
      }

      if (orderClause.length() > 0) {
        return orderClause.toString();
      }
    }

    return defaultOrderByClause;
  }

  public static String populateSelectColumns(Map<String, String> mapOfColumns) {
    Assert.notNull(mapOfColumns);
    Assert.isTrue(!mapOfColumns.isEmpty());

    StringBuilder columnClause = new StringBuilder(CAPACITY);

    for (Map.Entry<String, String> entry : mapOfColumns.entrySet()) {
      columnClause
          .append(columnClause.length() == 0 ? "" : ", ")
          .append(entry.getValue())
          .append(' ')
          .append(entry.getKey());
    }

    return columnClause.toString();
  }

  /**
   * Converts what is considered true in various iMX places to boolean
   *
   * @param imxBool imx boolean representation
   * @return {@code true}, when the imxBool is one of: (O, TRUE, Y), {@code false} otherwise
   */
  public static boolean toBoolean(String imxBool) {
    return "O".equalsIgnoreCase(imxBool) || "TRUE".equalsIgnoreCase(imxBool) || "Y".equalsIgnoreCase(imxBool);
  }
}
package fm.common.error;

import fm.common.FrontEndFault;
import fm.common.ServiceFault;
import fm.common.dao.DaoUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

/**
 * Created by haho on 10/05/2017.
 * Class for registering errors
 */
@Repository
public class ErrorDao implements IErrorDao {
  private static final Logger logger = LogManager.getLogger(ErrorDao.class);

  private static final String FE_ERROR_PROC = "FE_ERROR";
  private static final String BE_ERROR_PROC = "BE_ERROR";

  private final JdbcTemplate jdbcTemplate;

  private final NamedParameterJdbcTemplate namedTemplate;

  @Autowired
  public ErrorDao(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedTemplate) {
    Assert.notNull(jdbcTemplate);
    this.jdbcTemplate = jdbcTemplate;

    Assert.notNull(namedTemplate);
    this.namedTemplate = namedTemplate;
  }

  /**
   * limits the lenght of an error message
   *
   * @param orig the original message as string
   * @return the truncated message
   */
  private String prepErrMsg(String orig) {
    if (null == orig) {
      return "";
    }

    return orig.substring(0, orig.length() > 99 ? 99 : orig.length());
  }

  /**
   * Converts a java stack to string
   *
   * @param stack the stack
   * @return a string builder containing the stack
   */
  private StringBuilder prepareStack(StackTraceElement[] stack) {
    StringBuilder stackStr = new StringBuilder();

    if (stack != null) {
      for (StackTraceElement el : stack) {
        stackStr.append(el.toString()).append("\n");
      }
    }
    return stackStr;
  }

  /**
   * Prepares a dump from a front end errorMessage
   *
   * @param errorMessage the error message
   * @return a dump as string
   */
  private String prepareDump(FrontEndFault errorMessage) {
    //@formatter:off
    String dump = new StringBuilder(512)
        .append("{")                      // open json
        .append(" \"dump\" : [")          // open dump
        .append(errorMessage.getDump())   // dump is added as array
        .append("\"],")                   // close dump
        .append(" \"context\": [")        // open context
        .append(errorMessage.getContext())// context is added as array
        .append("\"]")                    // close context
        .append("}")                      // close json
        .toString();
    ;
    //@formatter:on
    return dump;
  }

  @Override
  public String registerBackEndFault(ServiceFault fault) {
    return registerBackEndFault(fault, null, null);
  }

  @Override
  public String registerBackEndFault(ServiceFault fault, StackTraceElement[] stack) {
    return registerBackEndFault(fault, stack, null);
  }

  @Override
  public String registerBackEndFault(ServiceFault fault, StackTraceElement[] stack, String dump) {
    StringBuilder stackStr = prepareStack(stack);
    String errorMsg = prepErrMsg(fault.getFaultCode() + " : " + fault.getFaultMessage());

    return insertError(errorMsg, dump, BE_ERROR_PROC, stackStr.toString());
  }

  /**
   * Registers front end error
   *
   * @param errorMessage the front end error message
   * @return wrapper object for the incident id
   */
  @Override
  public ErrorReference registerFrontEndError(FrontEndFault errorMessage) {
    String dump = prepareDump(errorMessage);
    String errorMsg = prepErrMsg(errorMessage.getFaultCode() + " : " + errorMessage.getFaultMessage());

    return new ErrorReference(insertError(errorMsg, dump, FE_ERROR_PROC, errorMessage.getStack()));
  }


  /**
   * Getting a new unique indication reference
   *
   * @return new referece
   */
  private String getErrorRefID() {
    final String sql = "SELECT newref.f_get_new_reference('texte') FROM dual";

    DaoUtils.debugQuery(logger, sql);
    return jdbcTemplate.queryForObject(sql, String.class);
  }

  /**
   * Insert error and return the id
   * @param errMsg
   * @param dump
   * @param process
   * @param stack
   * @return
   */
  private String insertError(String errMsg, String dump, String process, String stack) {
    final String sql =
          "INSERT INTO fm_error_tracking (error_message, stack_trace, user)"
        + "  VALUES (:errorMessage, :stackTrace, :user)                    ";

    MapSqlParameterSource paramsMap = new MapSqlParameterSource()
        .addValue("errorMessage", errMsg)
        .addValue("stackTrace", stack)
        .addValue("user", "haho");

    KeyHolder keyHolder = new GeneratedKeyHolder();
    namedTemplate.update(sql, paramsMap, keyHolder, new String[]{"user"});
    final String errorId = String.valueOf(keyHolder.getKey().longValue());
    return errorId;
  }

}
package ht.api.rest.customer.messages;

import ht.api.rest.customer.messages.interfaces.IMessagesDao;
import ht.common.dao.DaoUtils;
import io.jsonwebtoken.lang.Assert;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by haho on 6/12/2017.
 */
@Repository("customerMessagesDao")
public class MessagesDao implements IMessagesDao {

  private static final Logger LOGGER = LogManager.getLogger(MessagesDao.class);

  private final NamedParameterJdbcTemplate namedTemplate;

  @Autowired
  public MessagesDao(NamedParameterJdbcTemplate namedTemplate) {
    Assert.notNull(namedTemplate);
    this.namedTemplate = namedTemplate;
  }

  @Override
  public Map<String, Map<String, String>> getMessages(String language) {
    final String sql = "SELECT                            "
                     + "	m.id,                           "
                     + "	m.role_id,                      "
                     + "	r.role_name,                    "
                     + "	m.component_name,               "
                     + "	m.message_key,                  "
                     + "	m.message_en,                   "
                     + "	m.message_fr                    "
                     + "FROM                              "
                     + "	ht_db.messages m                "
                     + "		INNER JOIN                    "
                     + "	user_role r ON m.role_id = r.id "
                     + "WHERE                             "
                     + "	r.role_name = UPPER(:role)      "
                     + "AND m.component_name = :name      "
        ;
    List<String> componentsList = this.getComponentsList("CUSTOMER");

    Map<String, Map<String, String>> messages = new TreeMap<>();
    for (String component : componentsList) {
      final MapSqlParameterSource paramsMap = new MapSqlParameterSource();
      paramsMap.addValue("role", "customer");
      paramsMap.addValue("name", component);


      DaoUtils.debugQuery(LOGGER, sql, paramsMap.getValues());

      Map<String, String> keysValuesMap = namedTemplate.query(sql, paramsMap, new ResultSetExtractor<Map<String, String>>() {
        @Override
        public Map<String, String> extractData(ResultSet rs) throws SQLException, DataAccessException {
          Map<String, String> keysValues = new TreeMap<>();

          while (rs.next()) {
            keysValues.put(
                rs.getString("message_key"),
                rs.getString("message_" + language.toLowerCase())
            );
          }

          return keysValues;
        }
      });

      messages.put(component, keysValuesMap);
    }

    return messages;
  }

  private List<String> getComponentsList(String role) {
    final String sql = "SELECT DISTINCT                   "
                     + "	m.component_name                "
                     + "FROM                              "
                     + "	ht_db.messages m                "
                     + "		INNER JOIN                    "
                     + "	user_role r ON m.role_id = r.id "
                     + "WHERE                             "
                     + "	r.role_name = UPPER(:role)    "
        ;

    final MapSqlParameterSource paramsMap = new MapSqlParameterSource();
    paramsMap.addValue("role", role);

    DaoUtils.debugQuery(LOGGER, sql, paramsMap.getValues());

    return namedTemplate.queryForList(sql, paramsMap, String.class);
  }
}

package fm.api.rest.htBK.roles;

import fm.api.rest.htBK.roles.interfaces.IRoleDao;
import fm.common.dao.DaoUtils;
import io.jsonwebtoken.lang.Assert;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.util.KeyValuePair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by haho on 4/5/2017.
 */
@Repository("adminRoleDao")
public class RoleDao implements IRoleDao {
  private static final Logger LOGGER = LogManager.getLogger(RoleDao.class);

  private final NamedParameterJdbcTemplate namedTemplate;

  @Autowired
  public RoleDao(NamedParameterJdbcTemplate namedTemplate) {
    Assert.notNull(namedTemplate);
    this.namedTemplate = namedTemplate;
  }

  @Override
  public List<String> getRoles() {
    final String sql = "SELECT role_name FROM user_role";

    final MapSqlParameterSource paramsMap = new MapSqlParameterSource();

    DaoUtils.debugQuery(LOGGER, sql, paramsMap.getValues());

    return namedTemplate.query(sql, paramsMap, (rs, i) -> rs.getString("role_name"));
  }

  @Override
  public List<KeyValuePair> getRolesInfo() {
    final String sql = "SELECT id, role_name FROM ht_db.user_role ORDER BY id asc";

    final MapSqlParameterSource paramsMap = new MapSqlParameterSource();

    DaoUtils.debugQuery(LOGGER, sql, paramsMap.getValues());

    return namedTemplate.query(sql, paramsMap, new RowMapper<KeyValuePair>() {
      @Override
      public KeyValuePair mapRow(ResultSet rs, int rowNum) throws SQLException {
        KeyValuePair pair =
            new KeyValuePair(rs.getString("id"), rs.getString("role_name"));
        return pair;
      }
    });
  }

}
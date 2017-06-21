package fm.api.rest.testing;

import fm.common.dao.DaoUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by haho on 3/13/2017.
 */
@Repository
public class TestingDao {
  private static final Logger log = LogManager.getLogger(TestingDao.class);

  @Autowired
  NamedParameterJdbcTemplate namedTemplate;


  public List<AuthorityBean> getAuthorities() {

    final String sql = "SELECT ID, ROLE_NAME FROM user_role";

    final MapSqlParameterSource paramsMap = new MapSqlParameterSource();

    DaoUtils.debugQuery(log, sql, paramsMap.getValues());

    return namedTemplate.query(sql, paramsMap, (resultSet, i) -> {
      AuthorityBean bean = new AuthorityBean();
      bean.setId(resultSet.getInt("ID"));
      bean.setName(resultSet.getString("ROLE_NAME"));

      return bean;
    });
  }
}

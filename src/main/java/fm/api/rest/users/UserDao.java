package fm.api.rest.users;

import fm.api.rest.users.intefaces.IUserDao;
import fm.common.dao.DaoUtils;
import io.jsonwebtoken.lang.Assert;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by haho on 3/22/2017.
 */
@Repository("userDao")
public class UserDao implements IUserDao {
  private static final Logger LOGGER = LogManager.getLogger(UserDao.class);

  private final NamedParameterJdbcTemplate namedTemplate;

  @Autowired
  public UserDao(NamedParameterJdbcTemplate namedTemplate) {
    Assert.notNull(namedTemplate);
    this.namedTemplate = namedTemplate;
  }

  @Override
  public List<UserBean> getUsers() {
    final String sql = "SELECT                                                  "
                     + "	u.id, u.user_name, r.role_name, r.id role_id          "
                     + "FROM                                                    "
                     + "	(fm_user_roles r                                      "
                     + "	INNER JOIN fm_user_role_details d ON r.id = d.role_id)"
                     + "		INNER JOIN                                          "
                     + "	fm_users u ON u.id = d.user_id                        "
                     + "ORDER BY u.id                                           "
        ;

    final MapSqlParameterSource paramsMap = new MapSqlParameterSource();

    DaoUtils.debugQuery(LOGGER, sql, paramsMap.getValues());

    List<UserBean> users = namedTemplate.query(sql, paramsMap, (rs, rowNum) -> {
      UserBean user = new UserBean();
      user.setId(rs.getInt("id"));
      user.setName(rs.getString("user_name"));
      user.setRole(rs.getString("role_name"));
      user.setRoleId(rs.getString("role_id"));
      return user;
    });

    return users;
  }

  @Override
  public void updateUserRole(UserBean user) {
    final String sql = "UPDATE fm_user_role_details "
                     + "SET                         "
                     + "	role_id = :roleId         "
                     + "WHERE                       "
                     + "	user_id = :userId         "
        ;
    final MapSqlParameterSource paramsMap = new MapSqlParameterSource();
    paramsMap.addValue("userId", user.getId());
    paramsMap.addValue("roleId", user.getRoleId());

    DaoUtils.debugQuery(LOGGER, sql, paramsMap.getValues());

    namedTemplate.update(sql, paramsMap);
  }
}

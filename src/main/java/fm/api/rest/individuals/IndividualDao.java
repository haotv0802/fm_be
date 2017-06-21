package fm.api.rest.individuals;

import fm.api.rest.individuals.interfaces.IIndividualDao;
import fm.common.HTStringUtils;
import fm.common.dao.DaoUtils;
import io.jsonwebtoken.lang.Assert;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by haho on 3/22/2017.
 */
@Repository("adminIndividualDao")
public class IndividualDao implements IIndividualDao {
  private static final Logger LOGGER = LogManager.getLogger(IndividualDao.class);

  private final NamedParameterJdbcTemplate namedTemplate;

  @Autowired
  public IndividualDao(NamedParameterJdbcTemplate namedTemplate) {
    Assert.notNull(namedTemplate);
    this.namedTemplate = namedTemplate;
  }

  @Override
  public List<IndividualModel> getIndividuals() {
    String sql = "SELECT                                                "
               + "	i.id,                                               "
               + "	i.first_name,                                       "
               + "	i.last_name,                                        "
               + "	i.middle_name,                                      "
               + "	i.birthday,                                         "
               + "	i.gender,                                           "
               + "	i.email,                                            "
               + "	i.phone_number,                                     "
               + "	i.image_id,                                         "
               + "	i.user_id,                                          "
               + "	u.user_name,                                        "
               + "	m.image_url,                                        "
               + "	m.image_info,                                       "
               + "	r.role_name                                         "
               + "FROM                                                  "
               + "	(((individual i                                     "
               + "	LEFT JOIN user_table u ON i.user_id = u.id)         "
               + "	LEFT JOIN image m ON m.id = i.image_id)             "
               + "	LEFT JOIN user_role_details d ON d.user_id = u.id)  "
               + "		LEFT JOIN                                         "
               + "	user_role r ON r.id = d.role_id                     "
        ;
    return queryForIndividuals(sql);
  }

  private List<IndividualModel> queryForIndividuals(String sql) {

    final MapSqlParameterSource paramsMap = new MapSqlParameterSource();

    DaoUtils.debugQuery(LOGGER, sql, paramsMap.getValues());

    List<IndividualModel> users = namedTemplate.query(sql, paramsMap, (rs, rowNum) -> {
      IndividualModel bean = new IndividualModel();
      bean.setId(rs.getInt("id"));
      bean.setFirstName(rs.getString("first_name"));
      bean.setLastName(rs.getString("last_name"));
      bean.setMiddleName(rs.getString("middle_name"));
      bean.setBirthday(rs.getDate("birthday"));
      bean.setGender(rs.getString("gender"));
      bean.setEmail(rs.getString("email"));
      bean.setPhoneNumber(rs.getString("phone_number"));
      bean.setImageURL(rs.getString("image_url"));
      bean.setImageInfo(rs.getString("image_info"));
      bean.setUserName(rs.getString("user_name"));
      bean.setUserId(rs.getInt("user_id"));
      bean.setRole(rs.getString("role_name"));
      return bean;
    });

    return users;
  }

  @Override
  public Slice<IndividualPresenter> getIndividuals(Pageable pageable) {
    String sql = "SELECT                                                "
               + "	i.id,                                               "
               + "	i.first_name,                                       "
               + "	i.last_name,                                        "
               + "	i.middle_name,                                      "
               + "	i.birthday,                                         "
               + "	i.gender,                                           "
               + "	i.email,                                            "
               + "	i.phone_number,                                     "
               + "	i.image_id,                                         "
               + "	i.user_id,                                          "
               + "	u.user_name,                                        "
               + "	m.image_url,                                        "
               + "	m.image_info,                                       "
               + "	r.role_name                                         "
               + "FROM                                                  "
               + "	(((individual i                                     "
               + "	LEFT JOIN user_table u ON i.user_id = u.id)         "
               + "	LEFT JOIN image m ON m.id = i.image_id)             "
               + "	LEFT JOIN user_role_details d ON d.user_id = u.id)  "
               + "		LEFT JOIN                                         "
               + "	user_role r ON r.id = d.role_id                     "
        ;
    String fooSQL = buildSQLWithPaging(sql, pageable);

    List<IndividualModel> individuals = queryForIndividuals(fooSQL);
    List<IndividualPresenter> individualList = new ArrayList<>();
    for (IndividualModel model : individuals) {
      IndividualPresenter presenter = new IndividualPresenter(model);
      individualList.add(presenter);
    }
    return DaoUtils.calcSlice(individualList, pageable);
  }

  @Override
  public Integer getNumberOfIndividuals() {
    final String sql = "SELECT COUNT(*) FROM individual"
        ;
    final MapSqlParameterSource paramsMap = new MapSqlParameterSource();

    DaoUtils.debugQuery(LOGGER, sql, paramsMap.getValues());

    return namedTemplate.queryForObject(sql, paramsMap, Integer.class);
  }

  private String buildSQLWithPaging(String sql, Pageable pageable) {
    final DaoUtils.PagingIndex pi = DaoUtils.pagingIdxForSlice(pageable);
    String fooSql = String.format(
                 "SELECT foo.* FROM   "
               + "(                   "
               + " %s                 "
               + "ORDER BY %s         "
               + ") foo               "
               + "                    "
               + "LIMIT %d, %d        ",
        sql,
        getLimitDetailsSearchOrder(pageable.getSort()),
        pi.getStartIdx(),
        pi.getPageSize()
    );

    return fooSql;
  }

  private String getLimitDetailsSearchOrder(Sort sort) {

    String validOrders = "first_name, last_name, middle_name, birthday, gender, email, phone_number, image_id, user_id";
    String defaultOrderClause = " id ASC";

    return HTStringUtils.populateOrderBy(sort, validOrders, defaultOrderClause);
  }

  @Override
  public Boolean isUserNameExisting(String username) {
    final String sql = "SELECT COUNT(*) FROM user_table WHERE user_name = :username"
        ;
    final MapSqlParameterSource paramsMap = new MapSqlParameterSource();
    paramsMap.addValue("username", username);

    DaoUtils.debugQuery(LOGGER, sql, paramsMap.getValues());

    return namedTemplate.queryForObject(sql, paramsMap, Integer.class) > 0;
  }

  @Override
  public Boolean isUserNameExisting(String oldUserName, String userName) {
    return null;
  }

  private List<String> getRoles(int userId) {
    final String sql = "SELECT                                    "
                     + "	r.role_name                             "
                     + "FROM                                      "
                     + "	user_role r                             "
                     + "		INNER JOIN                            "
                     + "	user_role_details d ON d.role_id = r.id "
                     + "WHERE                                     "
                     + "	d.user_id = :userId                     "
        ;
    final MapSqlParameterSource paramsMap = new MapSqlParameterSource();
    paramsMap.addValue("userId", userId);

    DaoUtils.debugQuery(LOGGER, sql, paramsMap.getValues());

    return namedTemplate.queryForList(sql, paramsMap, String.class);
  }
}

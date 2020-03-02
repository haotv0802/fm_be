package fm.api.rest.bank;

import fm.api.rest.bank.interfaces.IBankDao;
import fm.api.rest.individual.IndividualDao;
import fm.common.dao.DaoUtils;
import io.jsonwebtoken.lang.Assert;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by haoho on 3/2/20 10:58.
 */
@Service("bankDao")
public class BankDao implements IBankDao {

  private static final Logger LOGGER = LogManager.getLogger(IndividualDao.class);

  private final NamedParameterJdbcTemplate namedTemplate;

  @Autowired
  public BankDao(NamedParameterJdbcTemplate namedTemplate) {
    Assert.notNull(namedTemplate);
    this.namedTemplate = namedTemplate;
  }

  @Override
  public List<BankPresenter> getBanks(Integer userId) {
    final String sql =
          "SELECT                                            "
        + " b.id,                                            "
        + " b.name,                                          "
        + " b.address,                                       "
        + " b.website                                        "
        + "FROM                                              "
        + " fm_banks b                                       "
        + " inner join fm_money_source m on m.bank_id = b.id "
        + "WHERE                                             "
        + " m.user_id = :userId                              "
        ;

    final MapSqlParameterSource paramsMap = new MapSqlParameterSource();
    paramsMap.addValue("userId", userId);

    DaoUtils.debugQuery(LOGGER, sql, paramsMap.getValues());

    List<BankPresenter> bankPresenters = namedTemplate.query(sql, paramsMap, (rs, rowNum) -> {
          BankPresenter bankPresenter = new BankPresenter();
          bankPresenter.setId(rs.getLong("id"));
          bankPresenter.setName(rs.getString("name"));
          bankPresenter.setAddress(rs.getString("address"));
          bankPresenter.setWebsite(rs.getString("website"));
          return bankPresenter;
        }
    );

    return bankPresenters;
  }
}

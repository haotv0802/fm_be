package fm.api.rest.individual;

import fm.api.rest.individual.interfaces.IIndividualDao;
import fm.api.rest.moneysource.interfaces.IMoneySourceDao;
import fm.common.dao.DaoUtils;
import fm.utils.FmDateUtils;
import io.jsonwebtoken.lang.Assert;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;

/**
 * Created by haoho on 2/28/20 13:51.
 */
@Service("individualDao")
public class IndividualDao implements IIndividualDao {

    private static final Logger logger = LogManager.getLogger(IndividualDao.class);

    private final NamedParameterJdbcTemplate namedTemplate;

    private IMoneySourceDao moneySourceDao;

    @Autowired
    public IndividualDao(
            NamedParameterJdbcTemplate namedTemplate,
            @Qualifier("moneySourceDao") IMoneySourceDao moneySourceDao
    ) {
        Assert.notNull(namedTemplate);
        Assert.notNull(moneySourceDao);

        this.namedTemplate = namedTemplate;
        this.moneySourceDao = moneySourceDao;
    }

    @Override
    public IndividualPresenter getIndividual(int userId) {
        final String sql = ""
                + " SELECT                "
                + "     i.id,             "
                + "     i.first_name,     "
                + "     i.last_name,      "
                + "     i.middle_name,    "
                + "     i.birthday,       "
                + "     i.gender,         "
                + "     i.email,          "
                + "     i.phone_number,   "
                + "     i.income,         "
                + "     i.user_id         "
                + "    FROM               "
                + "     fm_individuals i  "
                + "    WHERE              "
                + "    i.user_id = :userId";

        final MapSqlParameterSource paramsMap = new MapSqlParameterSource();
        paramsMap.addValue("userId", userId);

        DaoUtils.debugQuery(logger, sql, paramsMap.getValues());

        IndividualPresenter expensesList = namedTemplate.queryForObject(sql, paramsMap, (rs, rowNum) -> {
                    IndividualPresenter individualPresenter = new IndividualPresenter();
                    individualPresenter.setId(rs.getLong("id"));
                    individualPresenter.setFirstName(rs.getString("first_name"));
                    individualPresenter.setLastName(rs.getString("last_name"));
                    individualPresenter.setMiddleName(rs.getString("middle_name"));
                    individualPresenter.setBirthday(FmDateUtils.toUtilDate(rs.getDate("birthday")));
                    individualPresenter.setGender(rs.getString("gender"));
                    individualPresenter.setEmail(rs.getString("email"));
                    individualPresenter.setPhoneNumber(rs.getString("phone_number"));
                    individualPresenter.setIncome(rs.getBigDecimal("income"));

                    individualPresenter.setMoneySourcePresenters(this.moneySourceDao.getMoneySources(userId));

                    return individualPresenter;
                }
        );

        return expensesList;
    }
}

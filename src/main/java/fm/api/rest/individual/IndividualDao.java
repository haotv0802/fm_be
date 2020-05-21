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
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
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

        try {
            return namedTemplate.queryForObject(sql, paramsMap, (rs, rowNum) -> {
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
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    @Override
    public Long addIndividual(IndividualPresenter model) {
        final String sql = ""
                + "INSERT INTO fm_individuals   "
                + "        (                    "
                + "        first_name,          "
                + "        last_name,           "
                + "        middle_name,         "
                + "        birthday,            "
                + "        gender,              "
                + "        email,               "
                + "        phone_number,        "
                + "        income,              "
                + "        user_id)             "
                + "  VALUES      (              "
                + "        :firstName,          "
                + "        :lastName,           "
                + "        :middleName,         "
                + "        :birthday,           "
                + "        :gender,             "
                + "        :email,              "
                + "        :phoneNumber,        "
                + "        :income,             "
                + "        :userId)             ";

        final MapSqlParameterSource paramsMap = new MapSqlParameterSource();
        paramsMap.addValue("firstName", model.getFirstName());
        paramsMap.addValue("lastName", model.getLastName());
        paramsMap.addValue("middleName", model.getMiddleName());
        paramsMap.addValue("birthday", FmDateUtils.toSqlDate(model.getBirthday()));
        paramsMap.addValue("gender", model.getGender());
        paramsMap.addValue("email", model.getEmail());
        paramsMap.addValue("phoneNumber", model.getPhoneNumber());
        paramsMap.addValue("income", model.getIncome());
        paramsMap.addValue("userId", model.getUserId());

        DaoUtils.debugQuery(logger, sql, paramsMap.getValues());

        namedTemplate.update(sql, paramsMap);
        KeyHolder keyHolder = new GeneratedKeyHolder();
        namedTemplate.update(sql, paramsMap, keyHolder);
        final Long id = keyHolder.getKey().longValue();
        return id;
    }

    @Override
    public void updateIndividual(IndividualPresenter model) {
        final String sql = ""
                + "UPDATE fm_individuals                    "
                + "   SET first_name = :firstName,          "
                + "        last_name = :lastName,           "
                + "        middle_name = :middleName,       "
                + "        birthday = :birthday,            "
                + "        gender = :gender,                "
                + "        email = :email,                  "
                + "        phone_number = :phoneNumber,     "
                + "        income = :income,                "
                + "        user_id = :userId                "
                + "WHERE id = :id                           ";

        final MapSqlParameterSource paramsMap = new MapSqlParameterSource();
        paramsMap.addValue("firstName", model.getFirstName());
        paramsMap.addValue("lastName", model.getLastName());
        paramsMap.addValue("middleName", model.getMiddleName());
        paramsMap.addValue("birthday", FmDateUtils.toSqlDate(model.getBirthday()));
        paramsMap.addValue("gender", model.getGender());
        paramsMap.addValue("email", model.getEmail());
        paramsMap.addValue("phoneNumber", model.getPhoneNumber());
        paramsMap.addValue("income", model.getIncome());
        paramsMap.addValue("userId", model.getUserId());
        paramsMap.addValue("id", model.getId());

        DaoUtils.debugQuery(logger, sql, paramsMap.getValues());

        namedTemplate.update(sql, paramsMap);
    }

    @Override
    public Boolean isIndividualExisting(Integer userId) {
        final String sql = "SELECT COUNT(*) FROM fm_individuals WHERE user_id = :userId";

        final MapSqlParameterSource paramsMap = new MapSqlParameterSource();
        paramsMap.addValue("userId", userId);

        DaoUtils.debugQuery(logger, sql, paramsMap.getValues());

        return namedTemplate.queryForObject(sql, paramsMap, Integer.class) > 0;
    }
}

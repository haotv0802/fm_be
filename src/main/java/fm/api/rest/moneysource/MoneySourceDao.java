package fm.api.rest.moneysource;

import fm.api.rest.bank.BankPresenter;
import fm.api.rest.bank.interfaces.IBankDao;
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

import java.util.List;

/**
 * Created by haoho on 3/2/20 14:56.
 */
@Service("moneySourceDao")
public class MoneySourceDao implements IMoneySourceDao {

    private static final Logger logger = LogManager.getLogger(MoneySourceDao.class);

    private final NamedParameterJdbcTemplate namedTemplate;

    private IBankDao bankDao;

    @Autowired
    public MoneySourceDao(
            NamedParameterJdbcTemplate namedTemplate,
            @Qualifier("bankDao") IBankDao bankDao
    ) {
        Assert.notNull(namedTemplate);
        Assert.notNull(bankDao);

        this.namedTemplate = namedTemplate;
        this.bankDao = bankDao;
    }

    @Override
    public List<MoneySourcePresenter> getMoneySources(Integer userId) {
        final String sql =
                "SELECT                 "
                        + " id,                   "
                        + "    name,              "
                        + "    start_date,        "
                        + "    expiry_date,       "
                        + "    card_number,       "
                        + "    amount,            "
                        + "    card_type_id,      "
                        + "    user_id,           "
                        + "    is_terminated,     "
                        + "    bank_id            "
                        + "FROM                   "
                        + "    fm_money_source    "
                        + "WHERE                  "
                        + "    user_id = :userId  ";

        final MapSqlParameterSource paramsMap = new MapSqlParameterSource();
        paramsMap.addValue("userId", userId);

        DaoUtils.debugQuery(logger, sql, paramsMap.getValues());

        List<MoneySourcePresenter> moneyPresenters = namedTemplate.query(sql, paramsMap, (rs, rowNum) -> {
                    MoneySourcePresenter moneyPresenter = new MoneySourcePresenter();
                    moneyPresenter.setId(rs.getLong("id"));
                    moneyPresenter.setName(rs.getString("name"));
                    moneyPresenter.setStartDate(FmDateUtils.toUtilDate(rs.getDate("start_date")));
                    moneyPresenter.setExpiryDate(FmDateUtils.toUtilDate(rs.getDate("expiry_date")));
                    moneyPresenter.setCardNumber(rs.getString("card_number"));
                    moneyPresenter.setCreditLimit(rs.getBigDecimal("amount"));
                    moneyPresenter.setTerminated(rs.getBoolean("is_terminated"));
                    moneyPresenter.setBankId(rs.getLong("bank_id"));
                    moneyPresenter.setPaymentMethodId(rs.getString("card_type_id"));

                    BankPresenter bank = this.bankDao.getBankById(rs.getLong("bank_id"));
                    moneyPresenter.setBank(bank);

                    return moneyPresenter;
                }
        );

        return moneyPresenters;
    }

    @Override
    public void updateMoneySource(MoneySourcePresenter moneySource) {
        final String sql =
                "UPDATE                              "
                        + "   fm_money_source                  "
                        + "SET                                 "
                        + "   name = :name,                    "
                        + "   start_date = DATE(:start_date),  "
                        + "   expiry_date = DATE(:expiry_date),"
                        + "   card_number = :card_number,      "
                        + "   amount = :amount,                "
                        + "   is_terminated = :terminated,     "
                        + "   bank_id = :bank_id               "
                        + "WHERE                               "
                        + "   id = :id                         ";

        final MapSqlParameterSource paramsMap = new MapSqlParameterSource();
        paramsMap.addValue("name", moneySource.getName());
        paramsMap.addValue("start_date", moneySource.getStartDate());
        paramsMap.addValue("expiry_date", moneySource.getExpiryDate());
        paramsMap.addValue("card_number", moneySource.getCardNumber());
        paramsMap.addValue("amount", moneySource.getCreditLimit());
        paramsMap.addValue("terminated", moneySource.getTerminated());
        paramsMap.addValue("bank_id", moneySource.getBankId());
        paramsMap.addValue("id", moneySource.getId());

        DaoUtils.debugQuery(logger, sql, paramsMap.getValues());

        namedTemplate.update(sql, paramsMap);
    }
}

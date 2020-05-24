package fm.api.rest.moneysource.validators;

import fm.api.rest.moneysource.MoneySourcePresenter;
import fm.api.rest.moneysource.interfaces.IMoneySourceDao;
import fm.common.ValidationException;
import fm.common.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

/**
 * Created by haoho on 5/24/20.
 */
@Service("moneySourceAddValidator")
public class MoneySourceAddValidator implements Validator<MoneySourcePresenter> {

    private final IMoneySourceDao moneySourceDao;

    @Autowired
    public MoneySourceAddValidator(@Qualifier("moneySourceDao") IMoneySourceDao moneySourceDao) {
        Assert.notNull(moneySourceDao);

        this.moneySourceDao = moneySourceDao;
    }

    @Override
    public String defaultFaultCode() {
        return "money.source.invalid"; // TODO make it more sense
    }

    @Override
    public void validate(MoneySourcePresenter moneySource, String faultCode, Object... args) {
        Assert.notNull(moneySource);
        Assert.notNull(moneySource.getCardNumber());

        if (moneySourceDao.isMoneySourceExisting(moneySource.getCardNumber())) {
            throw new ValidationException("money.source.card.number.existing");
        }
    }
}

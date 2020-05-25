package fm.api.rest.bank.validators;

import fm.api.rest.bank.BankPresenter;
import fm.api.rest.bank.interfaces.IBankDao;
import fm.common.ValidationException;
import fm.common.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

/**
 * Created by HaoHo on 5/21/2020
 */
@Service("bankUpdateValidator")
public class BankUpdateValidator implements Validator<BankPresenter> {

    private final IBankDao bankDao;

    @Autowired
    public BankUpdateValidator(@Qualifier("bankDao") IBankDao bankDao) {
        Assert.notNull(bankDao);

        this.bankDao = bankDao;
    }

    @Override
    public String defaultFaultCode() {
        return "payment.method.invalid"; // TODO make it more sense
    }

    @Override
    public void validate(BankPresenter bank, String faultCode, Object... args) {
        Assert.notNull(bank);
        Assert.notNull(bank.getId());
        Assert.notNull(bank.getName());

        if (!bankDao.isBankExisting(bank.getId())) {
            throw new ValidationException("bank.id.not.existing");
        }

        if (bankDao.isBankExisting(bank.getId(), bank.getName())) {
            throw new ValidationException("bank.name.existing");
        }
    }
}

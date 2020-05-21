package fm.api.rest.individual.validators;

import fm.api.rest.bank.interfaces.IBankDao;
import fm.api.rest.individual.IndividualPresenter;
import fm.api.rest.individual.interfaces.IIndividualDao;
import fm.api.rest.moneysource.MoneySourcePresenter;
import fm.common.ValidationException;
import fm.common.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

/**
 * Created by HaoHo on 5/20/2020
 */
@Service("individualUpdateValidator")
public class IndividualUpdateValidator implements Validator<IndividualPresenter> {

    private final IIndividualDao individualDao;

    private final IBankDao bankDao;

    @Autowired
    public IndividualUpdateValidator(
            @Qualifier("individualDao") IIndividualDao individualDao,
            @Qualifier("bankDao") IBankDao bankDao
    ) {
        Assert.notNull(individualDao);
        Assert.notNull(bankDao);

        this.individualDao = individualDao;
        this.bankDao = bankDao;
    }

    @Override
    public String defaultFaultCode() {
        return "individual.invalid"; // TODO make it more sense
    }

    @Override
    public void validate(IndividualPresenter individual, String faultCode, Object... args) {
        Assert.notNull(individual);
        Assert.notNull(individual.getUserId());

        Integer userId = individual.getUserId();

//        if (!individualDao.isIndividualExisting(userId)) {
//            throw new ValidationException("individual.user.id.notnull");
//        }

        // Check if money source is not belong to this user.
        if (!CollectionUtils.isEmpty(individual.getMoneySourcePresenters())) {
            for (MoneySourcePresenter moneySource : individual.getMoneySourcePresenters()) {
                if (!userId.equals(moneySource.getUserId())) {
                    throw new ValidationException("individual.money.source.not.belong.to.user");
                }

                if (!bankDao.isBankExisting(moneySource.getBankId())) {
                    throw new ValidationException("individual.bank.not.existing");
                } else { // In case bankId is existing, process 2nd step, in case user cheats code
                    if (!moneySource.getBankId().equals(moneySource.getBank().getId())) {
                        throw new ValidationException("individual.bank.conflict");
                    }
                }
            }
        }
    }
}

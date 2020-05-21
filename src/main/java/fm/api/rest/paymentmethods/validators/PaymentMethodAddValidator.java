package fm.api.rest.paymentmethods.validators;

import fm.api.rest.paymentmethods.beans.PaymentMethodPresenter;
import fm.api.rest.paymentmethods.interfaces.IPaymentMethodsDao;
import fm.common.ValidationException;
import fm.common.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

/**
 * Created by HaoHo on 5/21/2020
 */
@Service("paymentMethodAddValidator")
public class PaymentMethodAddValidator implements Validator<PaymentMethodPresenter> {

    private final IPaymentMethodsDao paymentMethodsDao;

    @Autowired
    public PaymentMethodAddValidator(@Qualifier("paymentMethodsDao") IPaymentMethodsDao paymentMethodsDao) {
        Assert.notNull(paymentMethodsDao);

        this.paymentMethodsDao = paymentMethodsDao;
    }

    @Override
    public String defaultFaultCode() {
        return "payment.method.invalid"; // TODO make it more sense
    }

    @Override
    public void validate(PaymentMethodPresenter paymentMethod, String faultCode, Object... args) {
        Assert.notNull(paymentMethod);
        Assert.notNull(paymentMethod.getName());

        if (paymentMethodsDao.isPaymentNameExisting(paymentMethod.getName())) {
            throw new ValidationException("payment.method.name.existing");
        }
    }
}

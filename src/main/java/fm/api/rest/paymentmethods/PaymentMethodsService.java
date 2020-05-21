package fm.api.rest.paymentmethods;

import fm.api.rest.paymentmethods.beans.PaymentMethodPresenter;
import fm.api.rest.paymentmethods.interfaces.IPaymentMethodsDao;
import fm.api.rest.paymentmethods.interfaces.IPaymentMethodsService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;

/**
 * Created by haho on 6/25/2017.
 */
@Service("paymentMethodsService")
public class PaymentMethodsService implements IPaymentMethodsService {

    private final IPaymentMethodsDao paymentMethodsDao;

    public PaymentMethodsService(@Qualifier("paymentMethodsDao") IPaymentMethodsDao paymentMethodsDao) {
        Assert.notNull(paymentMethodsDao);

        this.paymentMethodsDao = paymentMethodsDao;
    }

    @Override
    public List<PaymentMethodPresenter> getAllPaymentMethods() {
        return this.paymentMethodsDao.getAllPaymentMethods();
    }
}

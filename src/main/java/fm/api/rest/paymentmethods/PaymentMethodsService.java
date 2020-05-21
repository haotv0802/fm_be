package fm.api.rest.paymentmethods;

import fm.api.rest.paymentmethods.beans.PaymentMethodPresenter;
import fm.api.rest.paymentmethods.interfaces.IPaymentMethodsDao;
import fm.api.rest.paymentmethods.interfaces.IPaymentMethodsService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    public PaymentMethodsService(@Qualifier("paymentMethodsDao") IPaymentMethodsDao paymentMethodsDao) {
        Assert.notNull(paymentMethodsDao);

        this.paymentMethodsDao = paymentMethodsDao;
    }

    @Override
    public List<PaymentMethodPresenter> getAllPaymentMethods() {
        return this.paymentMethodsDao.getAllPaymentMethods();
    }

    @Override
    public Integer addPaymentMethod(PaymentMethodPresenter paymentMethod) {
        return this.paymentMethodsDao.addPaymentMethod(paymentMethod);
    }

    @Override
    public void updatePaymentMethod(PaymentMethodPresenter paymentMethod) {
        this.paymentMethodsDao.updatePaymentMethod(paymentMethod);
    }
}

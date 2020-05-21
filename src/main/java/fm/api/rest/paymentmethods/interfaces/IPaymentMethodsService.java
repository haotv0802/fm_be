package fm.api.rest.paymentmethods.interfaces;

import fm.api.rest.paymentmethods.beans.PaymentMethodPresenter;

import java.util.List;

/**
 * Created by haho on 6/26/2017.
 */
public interface IPaymentMethodsService {
    List<PaymentMethodPresenter> getAllPaymentMethods();
}

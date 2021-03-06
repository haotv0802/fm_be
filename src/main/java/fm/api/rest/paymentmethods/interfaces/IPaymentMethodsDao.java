package fm.api.rest.paymentmethods.interfaces;

import fm.api.rest.paymentmethods.beans.PaymentMethodPresenter;

import java.util.List;

/**
 * Created by haho on 6/26/2017.
 */
public interface IPaymentMethodsDao {
    List<PaymentMethodPresenter> getAllPaymentMethods();

    Integer addPaymentMethod(PaymentMethodPresenter paymentMethod);

    void updatePaymentMethod(PaymentMethodPresenter paymentMethod);

    Boolean isPaymentNameExisting(String name);

    Boolean isPaymentNameExisting(Integer id, String name);
}

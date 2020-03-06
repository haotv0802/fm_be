package fm.api.rest.paymentmethods.interfaces;

import fm.api.rest.paymentmethods.beans.CardInformation;
import fm.api.rest.paymentmethods.beans.PaymentMethod;

import java.util.List;

/**
 * Created by haho on 6/26/2017.
 */
public interface IPaymentMethodsService {
  List<CardInformation> getCardsInformation(int userId);

  List<PaymentMethod> getAllPaymentMethods();
}

package fm.api.rest.payment.methods.interfaces;

import fm.api.rest.payment.methods.beans.CardInformation;

import java.util.List;

/**
 * Created by haho on 6/26/2017.
 */
public interface IPaymentMethodsService {
  List<CardInformation> getCardsInformation(int userId);
}

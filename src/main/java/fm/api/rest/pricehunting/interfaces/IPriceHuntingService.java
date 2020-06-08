package fm.api.rest.pricehunting.interfaces;

import fm.api.rest.pricehunting.Price;

import java.util.List;

public interface IPriceHuntingService {
    void savePrice(Price price);

    List<Price> getPrices();

    void checkPriceAndNotify();
}

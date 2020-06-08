package fm.api.rest.pricehunting.interfaces;

import fm.api.rest.pricehunting.Price;

import java.util.List;

/**
 * Created by HaoHo on 6/8/2020
 */
public interface IPriceHuntingDao {
    void addPrice(Price price);

    void updatePrice(Price price);

    Price getPriceByURL(String url);

    List<Price> getPrices();
}

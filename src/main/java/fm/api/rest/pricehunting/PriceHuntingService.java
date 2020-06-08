package fm.api.rest.pricehunting;

import fm.api.rest.pricehunting.interfaces.IPriceHuntingService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by HaoHo on 6/8/2020
 */
@Service("priceHuntingService")
public class PriceHuntingService implements IPriceHuntingService {
    @Override
    public void addPrice(Price price) {

    }

    @Override
    public List<Price> getPrices() {
        return null;
    }
}

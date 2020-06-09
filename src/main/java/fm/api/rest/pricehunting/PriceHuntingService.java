package fm.api.rest.pricehunting;

import fm.api.rest.email.IEmailService;
import fm.api.rest.pricehunting.interfaces.IPriceHuntingDao;
import fm.api.rest.pricehunting.interfaces.IPriceHuntingService;
import fm.api.rest.promotions.crawler.SCBCrawler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.List;

/**
 * Created by HaoHo on 6/8/2020
 */
@Service("priceHuntingService")
public class PriceHuntingService implements IPriceHuntingService {

    private static final Logger logger = LogManager.getLogger(SCBCrawler.class);

    private final IPriceHuntingDao priceHuntingDao;

    private final IEmailService emailService;

    private final DecimalFormat df2 = new DecimalFormat("#.##");

    @Autowired
    public PriceHuntingService(@Qualifier("priceHuntingDao") IPriceHuntingDao priceHuntingDao,
                               @Qualifier("emailService") IEmailService emailService) {
        Assert.notNull(priceHuntingDao);
        Assert.notNull(emailService);

        this.emailService = emailService;
        this.priceHuntingDao = priceHuntingDao;
    }

    @Override
    public void savePrice(Price price) {
        BigDecimal priceCrawled = getPriceFromURL(price.getUrl());
        price.setPrice(priceCrawled);

        Price existingPrice = priceHuntingDao.getPriceByURL(price.getUrl());
        if (existingPrice != null) {
            existingPrice.setEmail(price.getEmail());
            existingPrice.setPrice(price.getPrice());
            existingPrice.setExpectedPrice(price.getExpectedPrice());
            priceHuntingDao.updatePrice(existingPrice);
        } else {
            this.priceHuntingDao.addPrice(price);
        }
    }

    @Override
    public List<Price> getPrices() {
        return this.priceHuntingDao.getPrices();
    }

    @Override
    public void checkPriceAndNotify() {
        List<Price> prices = this.priceHuntingDao.getPrices();
        for (Price price : prices) {
            BigDecimal priceValue = getPriceFromURL(price.getUrl());
            BigDecimal priceCompare = price.getExpectedPrice() != null ? price.getExpectedPrice() : price.getPrice();
            if (priceValue.compareTo(priceCompare) == -1) { // -1 means current value is lower than crawled value.
                // notify
                emailService.sendEmail(String.format("Price of URL %s changed", price.getUrl()),
                        String.format("%s \n Price: %s changed to: %s", price.getUrl(), df2.format(priceCompare), df2.format(priceValue)));
            }
        }
    }

    // https://tiki.vn/thung-20-chai-nuoc-gao-woongjin-500ml-x20-chai-p20720569.html
    private BigDecimal getPriceFromURL(String url) {
        try {
            Document page = Jsoup.connect(url).timeout(4 * 1000).get();

            Elements body = page.getElementsByClass("body");

            Elements scripts = page.getElementsByTag("script");
            Element correctScript = null;

            for (Element script : scripts) {
                if (script.html().contains("_NEXT_")) {
                    correctScript = script; // find out script contains price.
                }
            }

            String text = correctScript.html();
            text = text.substring("_NEXT_DATA_=".length() + 3);

            JSONObject json = new JSONObject(text);
            String priceAsString = json.getJSONObject("props").
                    getJSONObject("initialState").
                    getJSONObject("mobile").
                    getJSONObject("product").
                    getJSONObject("productDetail").
                    get("price").toString();

            return new BigDecimal(priceAsString);
        } catch (IOException e) {
            logger.info("error on link: {}", url, e);
        }
        return null;
    }
}

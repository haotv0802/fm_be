package fm.api.rest.pricehunting;

import fm.api.rest.email.IEmailService;
import fm.api.rest.pricehunting.interfaces.IPriceHuntingDao;
import fm.api.rest.pricehunting.interfaces.IPriceHuntingService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.SocketTimeoutException;
import java.text.DecimalFormat;
import java.util.List;

/**
 * Created by HaoHo on 6/8/2020
 */
@Service("priceHuntingService")
public class PriceHuntingService implements IPriceHuntingService {

    private static final Logger logger = LogManager.getLogger(PriceHuntingService.class);

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
        addInfoToPrice(price);
        if (price.getPrice() == null) {
            return; // notify to front-end user that "there's problem getting price on this URL"
        }

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

    @Scheduled(fixedDelay = 10000, initialDelay = 1000)
    @Override
    public void checkPriceAndNotify() {
        List<Price> prices = this.priceHuntingDao.getPrices();
        for (Price price : prices) {
            BigDecimal priceValue = getPriceFromURL(price.getUrl());
            BigDecimal priceCompare = price.getExpectedPrice() != null ? price.getExpectedPrice() : price.getPrice();
            if (priceValue == null) {
                continue;
            }
            if (priceValue.compareTo(priceCompare) == -1) { // -1 means current value is lower than crawled value.
                logger.info("Notify to email " + price.getEmail());
                // notify
                emailService.sendEmail(String.format("Price changed [%s]", price.getTitle()),
                        String.format("%s \n\r Price: %s changed to: %s", price.getUrl(), df2.format(priceCompare), df2.format(priceValue)));
            }
        }
    }

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
        } catch (SocketTimeoutException e) { // sometimes, connection to the page is down.
            logger.info("timeout reading on link: {}", url, e);
        } catch (IOException e) {
            logger.info("error on link: {}", url, e);
        } catch (JSONException e) { // sometimes, jsoup gets document, but hardly reads content.\
                                    // ex: JSONObject["price"] not found. if this kind of problem happens, notify to admin to correct it. Since, this function will be for preimum user.
            logger.info("error on link: {}", url, e);
        }
        return null;
    }

    private void addInfoToPrice(Price price) {
        try {
            Document page = Jsoup.connect(price.getUrl()).timeout(4 * 1000).get();


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

            String title = json.getJSONObject("props").
                    getJSONObject("initialState").
                    getJSONObject("mobile").
                    getJSONObject("product").
                    getJSONObject("productDetail").
                    get("name").toString();

            price.setPrice(new BigDecimal(priceAsString));
            price.setTitle(title);
        } catch (SocketTimeoutException e) { // sometimes, connection to the page is down.
            logger.info("timeout reading on link: {}", price.getUrl(), e);
        } catch (IOException e) {
            logger.info("error on link: {}", price.getUrl(), e);
        } catch (JSONException e) { // sometimes, jsoup gets document, but hardly reads content.
                                    // ex: JSONObject["price"] not found. if this kind of problem happens, notify to admin to correct it. Since, this function will be for preimum user.
            logger.info("error on link: {}", price.getUrl(), e);
        }
    }
}

/**
 * Created by Quy on 4/30/2020.
 */
package fm.api.rest.promotions.crawler;

import fm.api.rest.promotions.PromotionPresenter;
import fm.api.rest.promotions.crawler.interfaces.IBankPromotionCrawler;
import fm.api.rest.promotions.crawler.interfaces.IPromotionCrawlerDAO;
import fm.api.rest.promotions.crawler.interfaces.BankLinkPromotion;
import fm.api.rest.promotions.crawler.utils.PromotionUtils;
import fm.common.FmConstants;
import io.jsonwebtoken.lang.Assert;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;

@Service("scbCrawler")
public class SCBCrawler implements IBankPromotionCrawler {
    private static final Logger logger = LogManager.getLogger(SCBCrawler.class);
    private final Integer sleepTime = 50;

    private final String mainLink = "htstps://www.scb.com.vn/";
    private PromotionUtils promotionUtils;
    private Set<String> listDetailPromoLinks = new HashSet<>();
    private IPromotionCrawlerDAO iPromotionCrawlerDAO;
    private Map<String, Integer> categoriesDB = new HashMap<>();

    @Autowired
    public SCBCrawler(@Qualifier("promotionCrawlerDao") IPromotionCrawlerDAO iPromotionCrawlerDAO,
                      @Qualifier("promoUtils") PromotionUtils promotionUtils) {
        Assert.notNull(iPromotionCrawlerDAO);
        Assert.notNull(promotionUtils);
        this.iPromotionCrawlerDAO = iPromotionCrawlerDAO;
        this.promotionUtils = promotionUtils;
    }

    @Override
    public Map<Integer, List<PromotionCrawlerModel>> crawl() {
        Map<Integer, List<PromotionCrawlerModel>> ressult = new TreeMap<>();
        categoriesDB = this.iPromotionCrawlerDAO.getCategoryAndId();
        try {
            // Test all function
            ressult = promotionUtils.addPromotionDataIntoMap(ressult, getTravelPromotion(), categoriesDB.get(FmConstants.PROMOTION_CATEGORY_TRAVEL));

            ressult = promotionUtils.addPromotionDataIntoMap(ressult, getFoodPromotion(), categoriesDB.get(FmConstants.PROMOTION_CATEGORY_FOOD));

            ressult = promotionUtils.addPromotionDataIntoMap(ressult, getHealthPromotion(), categoriesDB.get(FmConstants.PROMOTION_CATEGORY_HEALTH));

            ressult = promotionUtils.addPromotionDataIntoMap(ressult, getPrivilegeGoodwillPromotion(), categoriesDB.get(FmConstants.PROMOTION_CATEGORY_OTHER));

            ressult = promotionUtils.addPromotionDataIntoMap(ressult, getOtherPromotion(), categoriesDB.get(FmConstants.PROMOTION_CATEGORY_OTHER));

            ressult = promotionUtils.addPromotionDataIntoMap(ressult, getShoppingInstalment(), categoriesDB.get(FmConstants.PROMOTION_CATEGORY_SHOPPING));

            ressult = promotionUtils.addPromotionDataIntoMap(ressult, getEducationInstalment(), categoriesDB.get(FmConstants.PROMOTION_CATEGORY_EDUCATION));

            ressult = promotionUtils.addPromotionDataIntoMap(ressult, getJewelryInstalment(), categoriesDB.get(FmConstants.PROMOTION_CATEGORY_SHOPPING));

            ressult = promotionUtils.addPromotionDataIntoMap(ressult, getOtherInstalment(), categoriesDB.get(FmConstants.PROMOTION_CATEGORY_OTHER));

        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return ressult;
    }

    /**
     * This service os to get Promtion detail data .
     *
     * @param link
     * @return
     * @throws IOException
     */
    private PromotionCrawlerModel getPromotionFromLink(String link, int cateId) throws IOException {
        String end_Date = "";
        String start_Date = "";
        Document docPromotionDetailInfo = Jsoup.connect(link).timeout(3 * 1000).get();
        Elements elPromoDetailInfo = docPromotionDetailInfo.getElementsByClass("content-1");
        String tilte = getTitle(docPromotionDetailInfo.select(".sale-detail-wrap"), ".title-d-1") != null ? getTitle(docPromotionDetailInfo.select(".sale-detail-wrap"), ".title-d-1") : "";
        String content = getDetail(elPromoDetailInfo, "p", "Ưu đãi");
        String date = getDetail(elPromoDetailInfo, "p", "Thời gian");
        String cardType = getDetail(elPromoDetailInfo, "p", "Áp dụng");
        String condition = getDetail(elPromoDetailInfo, "p", "Điều kiện điều khoản");
        if (condition == null) {
            condition = getDetail(elPromoDetailInfo, "p", "Điều kiện và điều khoản");
        }

        String htmlText = elPromoDetailInfo.text();
        if (date != null) {
            if (date.split("đến").length > 1) {
                start_Date = date.split("đến")[0].replaceAll("/", "-");
                end_Date = date.split("đến")[1].replaceAll("/", "-");
            } else {
                start_Date = date;
            }
        }
        if (content != null) {
            PromotionCrawlerModel model = new PromotionCrawlerModel(tilte, content, promotionUtils.getProvision(content) != null ? promotionUtils.getProvision(content) : "0", promotionUtils.getPeriod(content), promotionUtils.getDateSCBData(start_Date), promotionUtils.getDateSCBData(end_Date), cateId, 3, htmlText, link, "", cardType, condition, "");
            return model;
        } else {
            logger.info("ERROR LINK PROMOTION : " + link);
            return null;
        }


    }

    /**
     * This service is to get infomation base on tag name.
     *
     * @param container
     * @param selector
     * @param tagName
     * @return
     */
    private String getDetail(Elements container, String selector, String tagName) {
        Elements promotionDetailEls = container.select(selector);
        if (promotionDetailEls.size() <= 2) {
            promotionDetailEls = container.select("li");
        }
        for (Element promoDetail : promotionDetailEls) {
            if (promoDetail.text().contains(tagName) || promoDetail.text().contains(tagName.toLowerCase())) {
                if (promoDetail.nextElementSibling() != null) {
                    if (promoDetail.nextElementSibling().outerHtml().contains("<ul>")) {
                        return promoDetail.nextElementSibling().text();
                    }
                }
                return promoDetail.text();
            }

        }
        return "";
    }

    /**
     * This service is to get max number of cataegoriees page.
     *
     * @param els
     * @return
     */
    private List<String> getLimitPagePromoCate(Elements els) {
        Elements numbPage = els.select("li");
        List<String> listAllPage = new ArrayList<>();
        for (Element item : numbPage) {
            String link = item.select("a").first().attr("href");
            listAllPage.add(link);
        }
        return listAllPage;
    }

    /**
     * This service is to get title of Promotion
     *
     * @param container
     * @param selector
     * @return
     */
    private String getTitle(Elements container, String selector) {
        Element promoDetailEl = container.select(selector).first();
        if (promoDetailEl != null) {
            return promoDetailEl.text();
        }
        return null;
    }

    /**
     * This service is to get all link promotion in from category
     *
     * @param url
     * @return
     * @throws IOException
     */
    private List<String> getAllPromotionLinks(String url) throws IOException {
        List<String> listLinkInCate = new ArrayList<>();
        Document promoDoc = Jsoup.connect(url).get();
        List<String> listAllPage = getLimitPagePromoCate(promoDoc.getElementsByClass("page-num"));
        for (String link : listAllPage) {
            promoDoc = Jsoup.connect(link).timeout(3 * 1000).get();
            Elements promoDivEls = promoDoc.select(".small-plus-item > a");
            for (Element promoDivEl : promoDivEls) {
                String linkDetail = promoDivEl.attr("href").toString();
                if (listDetailPromoLinks.add(linkDetail)) {
                    listLinkInCate.add(linkDetail);
                } else {
                    logger.error("The link promotion SCB is already Exists in List " + linkDetail);
                }
            }
        }
        return listLinkInCate;
    }

    /**
     * This service is to get Travel promotion
     *
     * @return
     * @throws IOException
     * @throws InterruptedException
     */
    private List<PromotionCrawlerModel> getTravelPromotion() throws IOException, InterruptedException {
        int cateId = categoriesDB.get("Travel");

        List<PromotionPresenter> listPromoBankData = this.promotionUtils.initBankData(3, cateId);

        List<PromotionCrawlerModel> travelPromotionData = doCrawlingPromotionDetaiData(cateId, BankLinkPromotion.SCB_PROMOTION_TRAVEL, listPromoBankData);


        return travelPromotionData;
    }

    /**
     * This service is to get Food promotion
     *
     * @return
     * @throws IOException
     * @throws InterruptedException
     */
    private List<PromotionCrawlerModel> getFoodPromotion() throws IOException, InterruptedException {
        int cateId = categoriesDB.get("Food");
        List<PromotionPresenter> listPromoBankData = this.promotionUtils.initBankData(3, cateId);

        List<PromotionCrawlerModel> foodPromotionData = doCrawlingPromotionDetaiData(cateId, BankLinkPromotion.SCB_PROMOTION_FOOD, listPromoBankData);


        return foodPromotionData;
    }

    /**
     * This service is to get Health promotion
     *
     * @return
     * @throws IOException
     * @throws InterruptedException
     */
    private List<PromotionCrawlerModel> getHealthPromotion() throws IOException, InterruptedException {
        int cateId = categoriesDB.get("Health");
        List<PromotionPresenter> listPromoBankData = this.promotionUtils.initBankData(3, cateId);

        List<PromotionCrawlerModel> healthPromotionData = doCrawlingPromotionDetaiData(cateId, BankLinkPromotion.SCB_PROMOTION_HEALTH, listPromoBankData);


        return healthPromotionData;
    }

    /**
     * This service is to get Privilge Goodwill promotion
     *
     * @return
     * @throws IOException
     * @throws InterruptedException
     */
    private List<PromotionCrawlerModel> getPrivilegeGoodwillPromotion() throws IOException, InterruptedException {
        int cateId = categoriesDB.get("Other");
        List<PromotionPresenter> listPromoBankData = this.promotionUtils.initBankData(3, cateId);
        List<PromotionCrawlerModel> privilegeGoodwillPromotionData = doCrawlingPromotionDetaiData(cateId, BankLinkPromotion.SCB_PROMOTION_PRIVILEGEGOODWILL, listPromoBankData);


        return privilegeGoodwillPromotionData;
    }

    /**
     * This service is to get Other promotion
     *
     * @return
     * @throws IOException
     * @throws InterruptedException
     */
    private List<PromotionCrawlerModel> getOtherPromotion() throws IOException, InterruptedException {
        int cateId = categoriesDB.get("Other");
        List<PromotionPresenter> listPromoBankData = this.promotionUtils.initBankData(3, cateId);
        List<PromotionCrawlerModel> otherPromotionData = doCrawlingPromotionDetaiData(cateId, BankLinkPromotion.SCB_PROMOTION_OTHER, listPromoBankData);


        return otherPromotionData;
    }

    /**
     * This service is to get Shopping Instalment
     *
     * @return
     * @throws IOException
     * @throws InterruptedException
     */
    private List<PromotionCrawlerModel> getShoppingInstalment() throws IOException, InterruptedException {
        int cateId = categoriesDB.get("Shopping");
        List<PromotionPresenter> listPromoBankData = this.promotionUtils.initBankData(3, cateId);
        List<PromotionCrawlerModel> shoppingInstalmnetData = doCrawlingInstalment(cateId, BankLinkPromotion.SCB_INSTALLMENT_SHOPPING_ELECTRIC, listPromoBankData);


        return shoppingInstalmnetData;
    }

    /**
     * This service is to get Education Instalment
     *
     * @return
     * @throws IOException
     * @throws InterruptedException
     */
    private List<PromotionCrawlerModel> getEducationInstalment() throws IOException, InterruptedException {
        int cateId = categoriesDB.get("Education");
        List<PromotionPresenter> listPromoBankData = this.promotionUtils.initBankData(3, cateId);
        List<PromotionCrawlerModel> educationInstalmnetData = doCrawlingInstalment(cateId, BankLinkPromotion.SCB_INSTALLMENT_HEALTH_EDUCATION, listPromoBankData);


        return educationInstalmnetData;
    }

    /**
     * This service is to get Jewelry Instalment
     *
     * @return
     * @throws IOException
     * @throws InterruptedException
     */
    private List<PromotionCrawlerModel> getJewelryInstalment() throws IOException, InterruptedException {
        int cateId = categoriesDB.get("Jewelry");
        List<PromotionPresenter> listPromoBankData = this.promotionUtils.initBankData(3, cateId);

        List<PromotionCrawlerModel> jewelryInstalmnetData = doCrawlingInstalment(cateId, BankLinkPromotion.SCB_INSTALLMENT_JEWELRY, listPromoBankData);


        return jewelryInstalmnetData;
    }

    /**
     * This service is to get Other Instalment
     *
     * @return
     * @throws IOException
     * @throws InterruptedException
     */
    private List<PromotionCrawlerModel> getOtherInstalment() throws IOException, InterruptedException {
        int cateId = categoriesDB.get("Other");
        List<PromotionPresenter> listPromoBankData = this.promotionUtils.initBankData(3, cateId);

        List<PromotionCrawlerModel> otherInstalmnetData = doCrawlingInstalment(cateId, BankLinkPromotion.SCB_INSTALLMENT_OTHER, listPromoBankData);


        return otherInstalmnetData;
    }

    /**
     * This service is to do crawling promtion detail data from link
     *
     * @param cateID
     * @param url
     * @return
     * @throws IOException
     * @throws InterruptedException
     */
    private List<PromotionCrawlerModel> doCrawlingPromotionDetaiData(int cateID, String url, List<PromotionPresenter> listPromoBankData) throws IOException, InterruptedException {

        List<PromotionCrawlerModel> listPromotionCrawling = new ArrayList<>();


        List<String> listPromotionLinks = getAllPromotionLinks(url);

        for (String link : listPromotionLinks) {
            Thread.sleep(sleepTime);
            PromotionCrawlerModel model = getPromotionFromLink(link, cateID);
            if (model != null) {
                if (promotionUtils.checkInfoExit(model, listPromoBankData)) {
                    logger.info("SCB Bank Promotion Date is Existed");
                } else {
                    listPromotionCrawling.add(model);
                }
            }
        }
        listPromotionLinks.clear();
        return listPromotionCrawling;
    }

    /**
     * This service is to get Instalment
     *
     * @param cateID
     * @param url
     * @param listPromoBankData
     * @return
     * @throws IOException
     */
    private List<PromotionCrawlerModel> doCrawlingInstalment(int cateID, String url, List<PromotionPresenter> listPromoBankData) throws IOException {
        List<PromotionCrawlerModel> listPromotionCrawling = new ArrayList<>();
        Document promoDoc = Jsoup.connect(url).get();

        List<String> listPageLimit = getLimitPagePromoCate(promoDoc.getElementsByClass("page-num"));

        for (String link : listPageLimit) {
            if (listPromotionCrawling.isEmpty()) {
                listPromotionCrawling = doCrwalingInstallmentData(link, cateID);
            } else {
                listPromotionCrawling.addAll(doCrwalingInstallmentData(link, cateID));
            }

        }
        return listPromotionCrawling;
    }

    /**
     * this service is to get Instalment Promtion detail
     *
     * @param link
     * @param cateID
     * @return
     * @throws IOException
     */
    private List<PromotionCrawlerModel> doCrwalingInstallmentData(String link, int cateID) throws IOException {

        List<PromotionCrawlerModel> listResult = new ArrayList<>();

        Document docInstalmentPage = Jsoup.connect(link).timeout(3 * 1000).get();

        Elements elsInstalmentContainer = docInstalmentPage.select(".item-partner");

        for (Element elInstalment : elsInstalmentContainer) {
            String startDate = "";
            String endDatae = "";
            Elements elinstalmentTxtPartner = elInstalment.select(".txt-partner");
            String title = elinstalmentTxtPartner.first().getElementsByTag("h3").text() != null ? elinstalmentTxtPartner.first().getElementsByTag("h3").text() : "";
            String content = getDetail(elinstalmentTxtPartner, "p", "Trả góp");
            String codition = getDetail(elinstalmentTxtPartner, "p", "Kênh áp dụng");
            String location = getDetail(elinstalmentTxtPartner, "p", "Địa chỉ");
            String date = getDetail(elinstalmentTxtPartner, "p", "Thời gian");
            String detailLink = getLinkDetail(elinstalmentTxtPartner, link);
            String htmlText = elinstalmentTxtPartner.outerHtml();
            if (date != null) {
                startDate = promotionUtils.getDateSCBData(date.split("đến")[0]);
                endDatae = promotionUtils.getDateSCBData(date.split("đến")[1]);
            }

            PromotionCrawlerModel model = new PromotionCrawlerModel(title, content, "", getPeriod(content) + " tháng", startDate, endDatae, cateID, 3, htmlText, link, detailLink, "", codition, location);
            listResult.add(model);
        }

        return listResult;
    }

    /**
     * This service is to get period data fro Instalment
     *
     * @param text
     * @return
     */
    private String getPeriod(String text) {
        if (text.contains("trong")) {
            int beginPosition = text.indexOf("trong") + 5;
            int endPosstion = text.indexOf("tháng");

            String period = text.substring(beginPosition, endPosstion).trim();

            return period;
        }
        return null;
    }

    /**
     * This service is to get link detail of Instalment from each element
     *
     * @param els
     * @param link
     * @return
     */
    private String getLinkDetail(Elements els, String link) {
        Elements elsContent = els.select("p");
        for (Element el : elsContent) {
            if (el.text().contains("Website:")) {
                return el.select("a").first().attr("href");
            }
        }
        return link;
    }

}

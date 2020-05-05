package fm.api.rest.promotions.crawler;

import fm.api.rest.promotions.PromotionPresenter;
import fm.api.rest.promotions.crawler.interfaces.BankLinkPromotion;
import fm.api.rest.promotions.crawler.interfaces.IBankPromotionCrawler;
import fm.api.rest.promotions.crawler.interfaces.IPromotionCrawlerDAO;
import fm.api.rest.promotions.crawler.utils.PromotionUtils;
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

@Service("vibCrawler")
public class VIBCrawler implements IBankPromotionCrawler {

  private static final Logger LOGGER = LogManager.getLogger(VIBCrawler.class);
  private PromotionUtils promotionUtils;
  private Set<String> listDetailPromoLink = new HashSet<>();
  private IPromotionCrawlerDAO iPromotionCrawlerDAO;
  private final String mainLink = "https://www.vib.com.vn/";
  private Map<String, Integer> categoriesDB = new HashMap<>();

  @Autowired
  public VIBCrawler(@Qualifier("promotionCrawlerDao") IPromotionCrawlerDAO iPromotionCrawlerDAO,
                    @Qualifier("promoUtils") PromotionUtils promotionUtils) {
    Assert.notNull(iPromotionCrawlerDAO);
    Assert.notNull(promotionUtils);
    this.iPromotionCrawlerDAO = iPromotionCrawlerDAO;
    this.promotionUtils = promotionUtils;
  }

  /**
   * This service is to do crawling  bank promotion data by each Categories
   *
   * @return Map<categoriesID, List < PromotionCrawlingData> </>></>
   */
  @Override
  public Map<Integer, List<PromotionCrawlerModel>> crawl() {
    Map<Integer, List<PromotionCrawlerModel>> ressult = new HashMap<>();
    categoriesDB = this.iPromotionCrawlerDAO.getCategoryAndId();
    try {
      ressult = promotionUtils.addPromotionDataIntoMap(ressult, getTravelPromotion(), categoriesDB.get("Travel"));

      ressult = promotionUtils.addPromotionDataIntoMap(ressult, getEducationPromotion(), categoriesDB.get("Education"));

      ressult = promotionUtils.addPromotionDataIntoMap(ressult, getShoppingPromotion(), categoriesDB.get("Shopping"));

      ressult = promotionUtils.addPromotionDataIntoMap(ressult, getFoodPromotion(), categoriesDB.get("Food"));

      ressult = promotionUtils.addPromotionDataIntoMap(ressult, getHealthPromotion(), categoriesDB.get("Health"));

      ressult = promotionUtils.addPromotionDataIntoMap(ressult, getTravelInstallment(), categoriesDB.get("Travel"));

      ressult = promotionUtils.addPromotionDataIntoMap(ressult, getEducationInstallment(), categoriesDB.get("Education"));

      ressult = promotionUtils.addPromotionDataIntoMap(ressult, getHealthInstallment(), categoriesDB.get("Health"));

      ressult = promotionUtils.addPromotionDataIntoMap(ressult, getElectricateInstallment(), categoriesDB.get("Electronics"));

      ressult = promotionUtils.addPromotionDataIntoMap(ressult, getShoppingInstallment(), categoriesDB.get("Shopping"));
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    listDetailPromoLink.clear();
    return ressult;
  }



  /**
   * This service is to get neccessary infomation from detail link
   *
   * @param link       : detail link
   * @param categoryId : categories id
   * @return PromotionCrawlerModel
   */
  private PromotionCrawlerModel getPromotionFromLink(String link, int categoryId) {
    try {
      Document promotionPage = Jsoup.connect(link).timeout(1000 * 3).get();
      Elements promotionPageEls = promotionPage.getElementsByClass("vib-v2-wrapper_new");
      String title = getDetail(promotionPageEls, ".vib-v2-title-promotion-detail", "Title");
      String endDate = getDetail(promotionPageEls, ".vib-v2-date-promotion", "End Date").replaceAll("/", "-");
      String content = getDetail(promotionPageEls, ".vib-v2-left-body-world-detail", "Content");
      List<String> location = getLocations(promotionPageEls);
      String htmlText = getDetail(promotionPageEls, ".vib-v2-left-body-world-detail", "HTML");
      PromotionCrawlerModel model = new PromotionCrawlerModel(title, content, promotionUtils.getProvision(content) != null ? promotionUtils.getProvision(content) : "0", promotionUtils.getPeriod(content), "", endDate, categoryId, 2, htmlText, link, "IMG", "CARD TYPE", "CONDITION", "LOCATION");
      return model;
    } catch (Exception e) {
//            e.printStackTrace();
      LOGGER.info("Bank Promotion Read time out - Link  : " + link);
    }
    return null;
  }


  /**
   * This service is to get detail info from HTML
   *
   * @param container : is the content which include neccessary data
   * @param selector  : is the key to get data
   * @param tagName
   * @return
   */
  private String getDetail(Elements container, String selector, String tagName) {
    try {
      for (Element item : container) {
        String result = item.select(selector).text();
        if (!result.equals("")) {
          if (tagName.equals("End Date")) {
            return promotionUtils.getDateVIBData(result);
          } else if (tagName.equals("HTML")) {
            return item.select(selector).outerHtml();
          } else {
            return result;
          }

        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }

  private List<String> getLocations(Elements container) {
    List<String> locationLists = new ArrayList<>();
    for (Element item : container) {
      Elements locationContainer = item.select(".vib-v2-box-place-map");
      for (Element locaationEl : locationContainer) {
        String location = locaationEl.text();
        locationLists.add(location);
      }
    }
    return locationLists;
  }

  /**
   * This service is to get promotion by education category
   *
   * @return List<PromotionCrawlerModel>
   * @throws InterruptedException
   */

  private List<PromotionCrawlerModel> getEducationPromotion() throws InterruptedException {
    int cateID = categoriesDB.get("Education");

    List<PromotionCrawlerModel> educationPromotionCrawlinData = doCrawling(cateID, BankLinkPromotion.VIB_PROMOTION_EDUCATION);

    return educationPromotionCrawlinData;

  }

  /**
   * This service is to get promotion by travel category
   *
   * @return List<PromotionCrawlerModel>
   * @throws InterruptedException
   */
  private List<PromotionCrawlerModel> getTravelPromotion() throws InterruptedException {
    int cateID = categoriesDB.get("Travel");

    List<PromotionCrawlerModel> travelPromotionCrawlinData = doCrawling(cateID, BankLinkPromotion.VIB_PROMOTION_TRAVEL);

    return travelPromotionCrawlinData;


  }

  /**
   * This service is to get promotion by food category
   *
   * @return List<PromotionCrawlerModel>
   * @throws InterruptedException
   */
  private List<PromotionCrawlerModel> getFoodPromotion() throws InterruptedException {
    int cateID = categoriesDB.get("Food");

    List<PromotionCrawlerModel> foodPromotionCrawlinData = doCrawling(cateID, BankLinkPromotion.VIB_PROMOTION_FOOD);

    return foodPromotionCrawlinData;


  }

  /**
   * This service is to get promotion by health category
   *
   * @return List<PromotionCrawlerModel>
   * @throws InterruptedException
   */
  private List<PromotionCrawlerModel> getHealthPromotion() throws InterruptedException {
    int cateID = categoriesDB.get("Health");

    List<PromotionCrawlerModel> healthPromotionCrawlinData = doCrawling(cateID, BankLinkPromotion.VIB_PROMOTION_HEALTHCARE);

    return healthPromotionCrawlinData;


  }

  /**
   * This service is to get promotion by shopping category
   *
   * @return List<PromotionCrawlerModel>
   * @throws InterruptedException
   */
  private List<PromotionCrawlerModel> getShoppingPromotion() throws InterruptedException {

    int cateID = categoriesDB.get("Shopping");

    List<PromotionCrawlerModel> shoppingPromotionCrawlinData = doCrawling(cateID, BankLinkPromotion.VIB_PROMOTION_SHOPPING);

    return shoppingPromotionCrawlinData;


  }

  /**
   * This service is to get installment by shopping category
   *
   * @return List<PromotionCrawlerModel>
   * @throws InterruptedException
   */
  private List<PromotionCrawlerModel> getShoppingInstallment() throws InterruptedException {


    int cateID = categoriesDB.get("Shopping");

    List<PromotionCrawlerModel> shoppingInstallmentCrawlinData = doCrawling(cateID, BankLinkPromotion.VIB_INSTALLMENT_SHOPPING);

    return shoppingInstallmentCrawlinData;


  }

  /**
   * This service is to get installment by health category
   *
   * @return List<PromotionCrawlerModel>
   * @throws InterruptedException
   */
  private List<PromotionCrawlerModel> getHealthInstallment() throws InterruptedException {


    int cateID = categoriesDB.get("Health");

    List<PromotionCrawlerModel> heanlthInstallmentCrawlinData = doCrawling(cateID, BankLinkPromotion.VIB_INSTALLMENT_HEALTHCARE);

    return heanlthInstallmentCrawlinData;


  }

  /**
   * This service is to get installment by electric category
   *
   * @return List<PromotionCrawlerModel>
   * @throws InterruptedException
   */
  private List<PromotionCrawlerModel> getElectricateInstallment() throws InterruptedException {


    int cateID = categoriesDB.get("Electronics");

    List<PromotionCrawlerModel> electricateInstallmentCrawlinData = doCrawling(cateID, BankLinkPromotion.VIB_INSTALLMENT_ELECTRICATE);

    return electricateInstallmentCrawlinData;


  }

  /**
   * This service is to get installment by education category
   *
   * @return List<PromotionCrawlerModel>
   * @throws InterruptedException
   */
  private List<PromotionCrawlerModel> getEducationInstallment() throws InterruptedException {

    int cateID = categoriesDB.get("Education");

    List<PromotionCrawlerModel> educationInstallmentCrawlinData = doCrawling(cateID, BankLinkPromotion.VIB_INSTALLMENT_EDUCATION);

    return educationInstallmentCrawlinData;


  }

  /**
   * This service is to get installment by travek category
   *
   * @return List<PromotionCrawlerModel>
   * @throws InterruptedException
   */
  private List<PromotionCrawlerModel> getTravelInstallment() throws InterruptedException {

    int cateID = categoriesDB.get("Travel");

    List<PromotionCrawlerModel> travelInstallmentCrawlinData = doCrawling(cateID, BankLinkPromotion.VIB_INSTALLMENT_TRAVEL);

    return travelInstallmentCrawlinData;
  }


  /**
   * This service is to get all links which is belong to category;
   *
   * @param url: main link categories
   * @return list of linbks.
   */
  private List<String> getAllListFromCateMainLink(String url) {
    List<String> listPromotionLinks = new ArrayList<>();
    Document pagePromoByCate = null;
    try {
      pagePromoByCate = Jsoup.connect(url).get();
      Element promotionContainer = pagePromoByCate.getElementById("promotionList");
      Elements promotionbox = promotionContainer.select(".vib-v2-world-box");
      for (Element el : promotionbox) {
        String linkPromoDetail = mainLink + el.select("a").attr("href");
        if (!listDetailPromoLink.add(linkPromoDetail)) {
          LOGGER.error("THe Link detail is Existed : " + linkPromoDetail);
        } else {
          listPromotionLinks.add(linkPromoDetail);
        }
      }
    } catch (IOException e) {
      e.printStackTrace();
    }

    return listPromotionLinks;
  }

  /**
   * This service is to get detai infomation from detail Link
   *
   * @param cateID
   * @param url:   detail Link
   * @return list of promotion/installment data
   * @throws InterruptedException
   */
  private List<PromotionCrawlerModel> doCrawling(int cateID, String url) throws InterruptedException {
    List<PromotionCrawlerModel> promotionCrawlinData = new ArrayList<>();

    List<PromotionPresenter> listPromoBankData = this.promotionUtils.initBankData(2, cateID);

    List<String> linkPromotionsLinks = getAllListFromCateMainLink(url);


    for (String link : linkPromotionsLinks) {
      Thread.sleep(2000);
      PromotionCrawlerModel model = getPromotionFromLink(link, cateID);
      if (model != null) {
        if (this.promotionUtils.checkInfoExit(model, listPromoBankData)) {
          LOGGER.info("VIB Bank Promotion Data is Existed");
        } else {
          promotionCrawlinData.add(model);
        }
      }
    }
    listDetailPromoLink.clear();
    return promotionCrawlinData;
  }

}

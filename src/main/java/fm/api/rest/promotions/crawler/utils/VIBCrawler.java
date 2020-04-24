package fm.api.rest.promotions.crawler.utils;

import fm.api.rest.promotions.PromotionPresenter;
import fm.api.rest.promotions.crawler.PromotionCrawlerModel;
import fm.api.rest.promotions.crawler.interfaces.IBankPromotionCrawler;
import fm.api.rest.promotions.crawler.interfaces.IPromotionCrawlerDAO;
import fm.api.rest.promotions.crawler.interfaces.VIBLink;
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

  @Autowired
  public VIBCrawler(@Qualifier("promotionCrawlerDao") IPromotionCrawlerDAO iPromotionCrawlerDAO,
                    @Qualifier("promoUtils") PromotionUtils promotionUtils) {
    Assert.notNull(iPromotionCrawlerDAO);
    Assert.notNull(promotionUtils);
    this.iPromotionCrawlerDAO = iPromotionCrawlerDAO;
    this.promotionUtils = promotionUtils;
  }

  @Override
  public Map<Integer, List<PromotionCrawlerModel>> crawl() {
    Map<Integer, List<PromotionCrawlerModel>> ressult = new HashMap<>();
    try {
      ressult = travelInstallmentCrawler();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    listDetailPromoLink.clear();
    return ressult;
  }

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


  private String getDetail(Elements container, String selector, String tagName) {
    try {
      for (Element item : container) {
        String result = item.select(selector).text();
        if (!result.equals("")) {
          if (tagName.equals("End Date")) {
            return promotionUtils.getDate(result);
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


  private Map<Integer, List<PromotionCrawlerModel>> educationPromotionCrawler() throws InterruptedException {
    String url = VIBLink.PROMOTION_EDUCATION;

    Map<String, Integer> categoriesDB = this.iPromotionCrawlerDAO.getCategoryAndId("Education");

    Map<Integer, List<PromotionCrawlerModel>> promotionCrawlinData = new HashMap<>();

    int cateID = categoriesDB.get("Education");

    List<PromotionPresenter> listPromoBankData = this.promotionUtils.initBankData(2, cateID);

    List<String> linkPromotions = getAllListFromCateMainLink(url);

    List<PromotionCrawlerModel> listModel = new ArrayList<>();

    for (String link : linkPromotions) {
      Thread.sleep(2000);
      PromotionCrawlerModel model = getPromotionFromLink(link, cateID);
      if (model != null) {
        if (this.promotionUtils.checkInfoExit(model, listPromoBankData)) {
          LOGGER.info("VIB Bank Promotion Data is Existed");
        } else {
          listModel.add(model);
        }
      }
    }
    promotionCrawlinData.put(cateID, listModel);
    return promotionCrawlinData;

  }

  private Map<Integer, List<PromotionCrawlerModel>> travelPromotionCrawler() throws InterruptedException {
    Map<String, Integer> categoriesDB = this.iPromotionCrawlerDAO.getCategoryAndId("Travel");


    int cateID = categoriesDB.get("Travel");

    Map<Integer, List<PromotionCrawlerModel>> travelPromotionCrawlinData = doCrawling(cateID, VIBLink.PROMOTION_TRAVEL);

    return travelPromotionCrawlinData;


  }

  private Map<Integer, List<PromotionCrawlerModel>> foodPromotionCrawler() throws InterruptedException {
    Map<String, Integer> categoriesDB = this.iPromotionCrawlerDAO.getCategoryAndId("Food");


    int cateID = categoriesDB.get("Food");

    Map<Integer, List<PromotionCrawlerModel>> foodPromotionCrawlinData = doCrawling(cateID, VIBLink.PROMOTION_FOOD);

    return foodPromotionCrawlinData;


  }

  private Map<Integer, List<PromotionCrawlerModel>> healthPromotionCrawler() throws InterruptedException {
    Map<String, Integer> categoriesDB = this.iPromotionCrawlerDAO.getCategoryAndId("Health");


    int cateID = categoriesDB.get("Health");

    Map<Integer, List<PromotionCrawlerModel>> healthPromotionCrawlinData = doCrawling(cateID, VIBLink.PROMOTION_HEALTHCARE);

    return healthPromotionCrawlinData;


  }

  private Map<Integer, List<PromotionCrawlerModel>> shoppingPromotionCrawler() throws InterruptedException {
    Map<String, Integer> categoriesDB = this.iPromotionCrawlerDAO.getCategoryAndId("Shopping");


    int cateID = categoriesDB.get("Shopping");

    Map<Integer, List<PromotionCrawlerModel>> shoppingPromotionCrawlinData = doCrawling(cateID, VIBLink.PROMOTION_SHOPPING);

    return shoppingPromotionCrawlinData;


  }


  private Map<Integer, List<PromotionCrawlerModel>> shoppingInstallmentCrawler() throws InterruptedException {
    Map<String, Integer> categoriesDB = this.iPromotionCrawlerDAO.getCategoryAndId("Shopping");


    int cateID = categoriesDB.get("Shopping");

    Map<Integer, List<PromotionCrawlerModel>> shoppingInstallmentCrawlinData = doCrawling(cateID, VIBLink.INSTALLMENT_SHOPPING);

    return shoppingInstallmentCrawlinData;


  }

  private Map<Integer, List<PromotionCrawlerModel>> healthInstallmentCrawler() throws InterruptedException {
    Map<String, Integer> categoriesDB = this.iPromotionCrawlerDAO.getCategoryAndId("Health");


    int cateID = categoriesDB.get("Health");

    Map<Integer, List<PromotionCrawlerModel>> heanlthInstallmentCrawlinData = doCrawling(cateID, VIBLink.INSTALLMENT_HEALTHCARE);

    return heanlthInstallmentCrawlinData;


  }

  private Map<Integer, List<PromotionCrawlerModel>> electricateInstallmentCrawler() throws InterruptedException {
    Map<String, Integer> categoriesDB = this.iPromotionCrawlerDAO.getCategoryAndId("Electronics");


    int cateID = categoriesDB.get("Electronics");

    Map<Integer, List<PromotionCrawlerModel>> electricateInstallmentCrawlinData = doCrawling(cateID, VIBLink.INSTALLMENT_ELECTRICATE);

    return electricateInstallmentCrawlinData;


  }

  private Map<Integer, List<PromotionCrawlerModel>> educationInstallmentCrawler() throws InterruptedException {
    Map<String, Integer> categoriesDB = this.iPromotionCrawlerDAO.getCategoryAndId("Education");


    int cateID = categoriesDB.get("Education");

    Map<Integer, List<PromotionCrawlerModel>> educationInstallmentCrawlinData = doCrawling(cateID, VIBLink.INSTALLMENT_EDUCATION);

    return educationInstallmentCrawlinData;


  }

  private Map<Integer, List<PromotionCrawlerModel>> travelInstallmentCrawler() throws InterruptedException {
    Map<String, Integer> categoriesDB = this.iPromotionCrawlerDAO.getCategoryAndId("Travel");


    int cateID = categoriesDB.get("Travel");

    Map<Integer, List<PromotionCrawlerModel>> travelInstallmentCrawlinData = doCrawling(cateID, VIBLink.INSTALLMENT_TRAVEL);

    return travelInstallmentCrawlinData;
  }


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

  private Map<Integer, List<PromotionCrawlerModel>> doCrawling(int cateID, String url) throws InterruptedException {
    Map<Integer, List<PromotionCrawlerModel>> promotionCrawlinData = new HashMap<>();

    List<PromotionPresenter> listPromoBankData = this.promotionUtils.initBankData(2, cateID);

    List<String> linkPromotions = getAllListFromCateMainLink(url);

    List<PromotionCrawlerModel> listModel = new ArrayList<>();

    for (String link : linkPromotions) {
      Thread.sleep(2000);
      PromotionCrawlerModel model = getPromotionFromLink(link, cateID);
      if (model != null) {
        if (this.promotionUtils.checkInfoExit(model, listPromoBankData)) {
          LOGGER.info("VIB Bank Promotion Data is Existed");
        } else {
          listModel.add(model);
        }
      }
    }
    promotionCrawlinData.put(cateID, listModel);
    return promotionCrawlinData;
  }

}

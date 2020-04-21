package fm.api.rest.promotions.crawler.utils;

import fm.api.rest.promotions.PromotionPresenter;
import fm.api.rest.promotions.crawler.PromotionCrawlerModel;
import fm.api.rest.promotions.crawler.interfaces.IBankPromotionCrawler;
import fm.api.rest.promotions.crawler.interfaces.IPromotionCrawlerDAO;
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
//    List<String> listLinks = promotionUtils.getBankPromotionLinks("./properties/VIB.properties", "VIB");
//    if (!listLinks.isEmpty()) {
//      try {
//        List<PromotionPresenter> listPromoBankData = promotionUtils.initBankData(2);
//        Map<String, List<PromotionCrawlerModel>> listPromotion = new TreeMap<>();
//        for (int i = 0; i < listLinks.size(); i++) {
//          List<PromotionCrawlerModel> listModel = new ArrayList<>();
//          Document pagePromoByCate = Jsoup.connect(listLinks.get(i).toString()).timeout(1000 * 5).get();
//          Element promotionContainer = pagePromoByCate.getElementById("promotionList");
//          Elements promotionbox = promotionContainer.select(".vib-v2-world-box");
//          for (Element el : promotionbox) {
//            String linkPromoDetail = mainLink + el.select("a").attr("href");
//            if (!listDetailPromoLink.add(linkPromoDetail)) {
//              LOGGER.error("THe LLink detail is Existed");
//            }
//          }
//          for (String link : listDetailPromoLink) {
//            Thread.sleep(2000);
//
//            PromotionCrawlerModel model = getPromotionFromLink(link, i);
//            if (model != null) {
//              if (promotionUtils.checkInfoExit(model, listPromoBankData)) {
//                LOGGER.info("VIB Bank Promotion Data is Existed");
//              } else {
//                listModel.add(model);
//              }
//            }
//
//          }
//          String cateName = listLinks.get(i).split("\\?cate=")[1].split("&name=")[1];
//          listPromotion.put(cateName, listModel);
//
//        }
//        String[] headers = {"Bank", "TiTle", "Contain", "Discount", "Category", "Start Beign", "Date Expire", "Html", "Link"};
//        promotionUtils.exportProvisionExcelFile(listPromotion, "VIBBank", headers);
//        listDetailPromoLink.clear();
//        return listPromotion;
//      } catch (IOException e) {
////                e.printStackTrace();
//      } catch (InterruptedException ex) {
////                ex.printStackTrace();
//
//      }
//
//
//    }
//    return null;
    try {
      ressult = travelPromotionCrawler();
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
      PromotionCrawlerModel model = new PromotionCrawlerModel(title, content, promotionUtils.getProvision(content) != null ? promotionUtils.getProvision(content) : "0", "", endDate, categoryId, 2, htmlText, link, "IMG", "CARD TYPE", "CONDITION", "LOCATION");
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

  private Map<Integer, List<PromotionCrawlerModel>> travelPromotionCrawler() throws InterruptedException {

    String url = "https://www.vib.com.vn/wps/portal/vn/VIBWorld/landing?cate=3&name=Du-lich";

    Map<String, Integer> categoriesDB = this.iPromotionCrawlerDAO.getCategoryAndId("Travel");

    Map<Integer, List<PromotionCrawlerModel>> travelPromotionCrawlinData = new HashMap<>();

    int cateID = categoriesDB.get("Travel");

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
    for (PromotionCrawlerModel model : listModel) {
      this.iPromotionCrawlerDAO.savePromotion(model);
    }
    travelPromotionCrawlinData.put(cateID, listModel);
    return travelPromotionCrawlinData;
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


}

/**
 * Created by Quy on 4/30/2020.
 */
package fm.api.rest.promotions.crawler;

import fm.api.rest.promotions.PromotionPresenter;
import fm.api.rest.promotions.crawler.interfaces.IBankPromotionCrawler;
import fm.api.rest.promotions.crawler.interfaces.IPromotionCrawlerDAO;
import fm.api.rest.promotions.crawler.interfaces.BankLinkPromotion;
import fm.api.rest.promotions.crawler.utils.PromotionUtils;
import io.jsonwebtoken.lang.Assert;
import org.apache.commons.lang3.StringUtils;
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

@Service("scbCrawler2")
public class SCBCrawler implements IBankPromotionCrawler {
  private static final Logger LOGGER = LogManager.getLogger(PromotionCrawlerDAO.class);
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
    Map<Integer, List<PromotionCrawlerModel>> ressult = new HashMap<>();
    categoriesDB = this.iPromotionCrawlerDAO.getCategoryAndId();
    try {
      // Test all function
      ressult = promotionUtils.addPromotionDataIntoMap(ressult, getTravelPromotion(), categoriesDB.get("Travel"));

      ressult = promotionUtils.addPromotionDataIntoMap(ressult, getFoodPromotion(), categoriesDB.get("Food"));

      ressult = promotionUtils.addPromotionDataIntoMap(ressult, getHealthPromotion(), categoriesDB.get("Health"));

      ressult = promotionUtils.addPromotionDataIntoMap(ressult, getPrivilegeGoodwillPromotion(), categoriesDB.get("Privilege Goodwill"));

      ressult = promotionUtils.addPromotionDataIntoMap(ressult, getOtherPromotion(), categoriesDB.get("Other"));

      ressult = promotionUtils.addPromotionDataIntoMap(ressult, getShoppingInstalment(), categoriesDB.get("Shopping"));

      ressult = promotionUtils.addPromotionDataIntoMap(ressult, getEducationInstalment(), categoriesDB.get("Education"));

      ressult = promotionUtils.addPromotionDataIntoMap(ressult, getJewelryInstalment(), categoriesDB.get("Jewelry"));

      ressult = promotionUtils.addPromotionDataIntoMap(ressult, getOtherInstalment(), categoriesDB.get("Other"));

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
  private PromotionCrawlerModel getPromotionFromLink(String link) throws IOException {
    String end_Date = "";
    String start_Date = "";
    Document docPromotionDetailInfo = Jsoup.connect(link).timeout(3 * 1000).get();
    Elements elPromoDetailInfo = docPromotionDetailInfo.getElementsByClass("content-1");
    String tilte = getTitle(docPromotionDetailInfo.select(".sale-detail-wrap"), ".title-d-1");
    String content = getDetail(elPromoDetailInfo, "p", "Ưu đãi");
    String date = getDetail(elPromoDetailInfo, "p", "Thời gian");
    String cardType = getDetail(elPromoDetailInfo, "p", "Áp dụng");
    String condition = getDetail(elPromoDetailInfo, "p", "Điều kiện điều khoản");
    String htmlText = elPromoDetailInfo.text();
    if (date != null) {
      if (date.split("đến").length > 1) {
        start_Date = date.split("đến")[0].replaceAll("/", "-");
        end_Date = date.split("đến")[1].replaceAll("/", "-");
      } else {
        start_Date = date;
      }
    }
    PromotionCrawlerModel model = new PromotionCrawlerModel(tilte, content, promotionUtils.getProvision(content) != null ? promotionUtils.getProvision(content) : "0", promotionUtils.getPeriod(content), promotionUtils.getDateSCBData(start_Date), promotionUtils.getDateSCBData(end_Date), 0, 3, htmlText, link, "", cardType, condition, "");
    return model;

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

    for (Element promoDetail : promotionDetailEls) {
      if (promoDetail.text().contains(tagName)) {
        if (promoDetail.nextElementSibling().outerHtml().contains("<ul>")) {
          return promoDetail.nextElementSibling().text();
        }
        return promoDetail.text();
      }
    }
    return null;
  }

  /**
   * This service is to get max number of cataegoriees page.
   *
   * @param els
   * @return
   */
  private int getLimitPagePromoCate(Elements els) {
    Elements numbPage = els.select("li");
    return numbPage.size();
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
    int pageLimit = getLimitPagePromoCate(promoDoc.getElementsByClass("page-num"));
    for (int i = 0; i < pageLimit; i++) {
      String pageLinkPromo = StringUtils.substring(url, 0, url.length() - 1) + i;
      promoDoc = Jsoup.connect(pageLinkPromo).get();
      Elements promoDivEls = promoDoc.select(".small-plus-item > a");
      for (Element promoDivEl : promoDivEls) {
        String linkDetail = promoDivEl.attr("href").toString();
        if (listDetailPromoLinks.add(linkDetail)) {
          listLinkInCate.add(linkDetail);
        } else {
          LOGGER.error("The link promotion SCB is already Exists in List " + linkDetail);
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

    List<PromotionCrawlerModel> travelPromotionData = doCrawlingDetaiData(cateId, BankLinkPromotion.SCB_PROMOTION_TRAVEL);


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

    List<PromotionCrawlerModel> foodPromotionData = doCrawlingDetaiData(cateId, BankLinkPromotion.SCB_PROMOTION_FOOD);


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

    List<PromotionCrawlerModel> healthPromotionData = doCrawlingDetaiData(cateId, BankLinkPromotion.SCB_PROMOTION_HEALTH);


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
    int cateId = categoriesDB.get("Privilege Goodwill");

    List<PromotionCrawlerModel> privilegeGoodwillPromotionData = doCrawlingDetaiData(cateId, BankLinkPromotion.SCB_PROMOTION_PRIVILEGEGOODWILL);


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

    List<PromotionCrawlerModel> otherPromotionData = doCrawlingDetaiData(cateId, BankLinkPromotion.SCB_PROMOTION_OTHER);


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

    List<PromotionCrawlerModel> shoppingInstalmnetData = doCrawlingDetaiData(cateId, BankLinkPromotion.SCB_INSTALLMENT_SHOPPING_ELECTRIC);


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

    List<PromotionCrawlerModel> educationInstalmnetData = doCrawlingDetaiData(cateId, BankLinkPromotion.SCB_INSTALLMENT_HEALTH_EDUCATION);


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

    List<PromotionCrawlerModel> jewelryInstalmnetData = doCrawlingDetaiData(cateId, BankLinkPromotion.SCB_INSTALLMENT_HEALTH_EDUCATION);


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

    List<PromotionCrawlerModel> otherInstalmnetData = doCrawlingDetaiData(cateId, BankLinkPromotion.SCB_INSTALLMENT_OTHER);


    return otherInstalmnetData;
  }


  /**
   * This service is to do crwaling detail data from link
   *
   * @param cateID
   * @param url
   * @return
   * @throws IOException
   * @throws InterruptedException
   */
  private List<PromotionCrawlerModel> doCrawlingDetaiData(int cateID, String url) throws IOException, InterruptedException {

    List<PromotionCrawlerModel> listPromotionCrawling = new ArrayList<>();

    List<PromotionPresenter> listPromoBankData = this.promotionUtils.initBankData(3, cateID);

    List<String> listPromotionLinks = getAllPromotionLinks(url);

    for (String link : listPromotionLinks) {
      Thread.sleep(2000);
      PromotionCrawlerModel model = getPromotionFromLink(link);
      if (model != null) {
        if (promotionUtils.checkInfoExit(model, listPromoBankData)) {
          LOGGER.info("SCB Bank Promotion Date is Existed");
        } else {
          listPromotionCrawling.add(model);
        }
      }
    }
    listPromotionLinks.clear();
    return listPromotionCrawling;
  }

}

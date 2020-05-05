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
    categoriesDB = iPromotionCrawlerDAO.getCategoryAndId();
    try {
      getTravelPromotion();
    } catch (IOException e) {
      e.printStackTrace();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    return null;
  }

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

  private String getDetail(Elements container, String selector, String tagName) {
    Elements promotionDetailEls = container.select(selector);

    for (Element promoDetail : promotionDetailEls) {
      if (promoDetail.text().contains(tagName)) {
        if(promoDetail.nextElementSibling().outerHtml().contains("<ul>")){
          return promoDetail.nextElementSibling().text();
        }
        return promoDetail.text();
      }
    }
    return null;
  }

  private int getLimitPagePromoCate(Elements els) {
    Elements numbPage = els.select("li");
    return numbPage.size();
  }

  private String getTitle(Elements container, String selector) {
    Element promoDetailEl = container.select(selector).first();
    if (promoDetailEl != null) {
      return promoDetailEl.text();
    }
    return null;
  }

  private List<String> getAllPromotionLinks(String url) throws IOException {
    List<String> listLinkInCate = new ArrayList<>();
    Document promoDoc = Jsoup.connect(url).get();
    int pageLimit = getLimitPagePromoCate(promoDoc.getElementsByClass("page-num"));
    for (int i = 0; i < pageLimit; i++) {
      String pageLinkPromo = StringUtils.substring(url, 0, url.length() - 1) + i;
      promoDoc = Jsoup.connect(pageLinkPromo).get();
      Elements promoDivEls = promoDoc.select(".small-plus-item > a");
      for(Element promoDivEl : promoDivEls){
        String linkDetail = promoDivEl.attr("href").toString();
        if(listDetailPromoLinks.add(linkDetail)){
          listLinkInCate.add(linkDetail);
        }else{
          LOGGER.error("The link promotion SCB is already Exists in List " + linkDetail);
        }
      }
    }
    return listLinkInCate;
  }
  private List<PromotionCrawlerModel> getTravelPromotion () throws IOException, InterruptedException {
    int cateId = categoriesDB.get("Travel");

    List <PromotionCrawlerModel> travelPromotionData = doCrawlingDetaiData(cateId, BankLinkPromotion.SCB_PROMOTION_TRAVEL);

    System.out.println(travelPromotionData);
    return null;
  }

  private List<PromotionCrawlerModel> doCrawlingDetaiData (int cateID , String url) throws IOException, InterruptedException {

    List<PromotionCrawlerModel> listPromotionCrawling = new ArrayList<>();

    List<PromotionPresenter> listPromoBankData = this.promotionUtils.initBankData(3,cateID);

    List<String> listPromotionLinks = getAllPromotionLinks(url);

    for(String link : listPromotionLinks){
      Thread.sleep(2000);
      PromotionCrawlerModel model = getPromotionFromLink(link);
      if(model!=null){
        if(promotionUtils.checkInfoExit(model,listPromoBankData)){
          LOGGER.info("SCB Bank Promotion Date is Existed");
        }else{
          listPromotionCrawling.add(model);
        }
      }
    }
    listPromotionLinks.clear();
    return listPromotionCrawling;
  }

}

package fm.api.rest.promotions.crawler.utils;
/* Quy created on 3/11/2020  */

import fm.api.rest.promotions.PromotionModel;
import fm.api.rest.promotions.crawler.PromotionCrawlerDAO;
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
import java.nio.charset.Charset;
import java.util.*;

@Service("scbCrawler")
public class SCBCrawler implements IBankPromotionCrawler {
    private static final Logger LOGGER = LogManager.getLogger(PromotionCrawlerDAO.class);
    private final String mainLink = "https://www.scb.com.vn/";
    private PromotionUtils promotionUtils;
    private Set<String> listDetailPromoLinks = new HashSet<>();
    private IPromotionCrawlerDAO iPromotionCrawlerDAO;

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
//        Map<String, List<PromotionCrawlerModel>> listPromotion = new TreeMap<>();
//        List<String> listLinks = promotionUtils.getBankPromotionLinks("./properties/SCB.properties", "SCB");
//        if (!listLinks.isEmpty()) {
//            try {
//                List<PromotionPresenter> listBankPromoData = promotionUtils.initBankData(3);
//                int tagNum =1 ;
//                for (String link : listLinks) {
//                    List<PromotionCrawlerModel> bankProvisionModels = new ArrayList<>();
//                    Document promoDoc = Jsoup.connect(link).get();
//                    int pageLimit = getLimitPagePromotionCate(promoDoc.getElementsByClass("page-num"));
//                    for (int i = 0; i < pageLimit; i++) {
//                        String pageLinkPromo = StringUtils.substring(link, 0, link.length() - 1) + i;
//                        promoDoc = Jsoup.connect(pageLinkPromo).get();
//                        Elements promoDivEls = promoDoc.select(".small-plus-item > a");
//                        for (Element promoDivEl : promoDivEls) {
//                            String linkDetail = promoDivEl.attr("href").toString();
//                            System.out.println(linkDetail);
//                            if (listDetailPromoLinks.add(linkDetail)) {
//                                System.out.println("Link failed: " + linkDetail);
//                            } else {
//                                LOGGER.error("The link promotion SCB is already existed in List " + linkDetail);
//                            }
//                        }
//                    }
//                    for (String linkPromo : listDetailPromoLinks) {
//                        Thread.sleep(2000);
//                        System.out.println("Linke nè : "+ linkPromo);
//                        PromotionCrawlerModel model = getPromotionFromLink(linkPromo);
//                        if(listBankPromoData.isEmpty()){
//                            bankProvisionModels.add(model);
//                        }else{
//                            if (promotionUtils.checkInfoExit(model, listBankPromoData)) {
//                                bankProvisionModels.add(model);
//                            }
//                        }
//
//                    }
//
//                    listPromotion.put(tagNum+"", bankProvisionModels);
//                    String[] headers = {"Bank", "TiTle", "Contain", "Discount", "Category", "Start Beign", "Date Expire", "Html", "Link", "imgURL", "cardType", "condition", "location"};
//                    promotionUtils.exportProvisionExcelFile(listPromotion, "SCBCrawler", headers);
//                    tagNum++;
//                }
//                listDetailPromoLinks.clear();
//                return listPromotion;
//            } catch (Exception e) {
//                LOGGER.error(e.getMessage());
//            }
//        }
        return null;
    }

    public PromotionCrawlerModel getPromotionFromLink(String link) {
        try {
            String startDate = "";
            String endDate = "";
            Document docPromoDetailInfo = Jsoup.connect(link).timeout(5 * 1000).get();
            docPromoDetailInfo.outputSettings().charset(Charset.forName("UTF-8"));
            Elements elPromoDetailInfo = docPromoDetailInfo.getElementsByClass("content-1");
            String title = getTitle(docPromoDetailInfo.select(".sale-detail-wrap"), ".title-d-1");
            String content = getDetail(elPromoDetailInfo, "p", "Ưu đãi");
            String date = getDetail(elPromoDetailInfo, "p", "Thời gian");
            String cardType = getDetail(elPromoDetailInfo, "p", "Áp dụng");
            String condition = getDetail(elPromoDetailInfo, "p", "Điều kiện điều khoản");
            String location = getDetail(elPromoDetailInfo, "p", "Địa chỉ");
            String htmlText = getDetail(elPromoDetailInfo, "content-1", "HTML");
            String img = getImg(elPromoDetailInfo, "p > img");
            if (date != null) {
                if(date.split("đến").length>1) {
                    startDate = date.split("đến")[0].replaceAll("/","-");
                    endDate = date.split("đến")[1].replaceAll("/","-");
                }else{
                    startDate = date;
                }
            }
            PromotionCrawlerModel model = new PromotionCrawlerModel(title, content, promotionUtils.getProvision(content) != null ? promotionUtils.getProvision(content) : "0",null, promotionUtils.getDate(startDate), promotionUtils.getDate(endDate), 0, 3, htmlText, link, img, cardType, condition, location);
            return model;
        } catch (IOException e) {
            LOGGER.error(e.getMessage());
        }
        return null;
    }

    public String getDetail(Elements container, String selector, String tagName) {
        Elements promoDetailEls = container.select(selector);
        if (tagName.equals("HTML")) {
            System.out.println("HTLM");
            return container.outerHtml();
        }
        for (Element promoDetail : promoDetailEls) {
            if (promoDetail.text().contains(tagName)) {
                if (promoDetail.text().length() <= tagName.length() + 8) {
                    if (promoDetail.nextElementSibling().select("li").size() > 0) {
                        StringBuilder strb = new StringBuilder();
                        for (Element el : promoDetail.nextElementSibling().select("li")) {
                            strb.append(el.text());
                            strb.append("-");
                        }
                        return strb.toString();
                    } else {
                        return promoDetail.nextElementSibling().text();
                    }
                } else {
                    return promoDetail.text();
                }
            }
        }
        return null;
    }

    public List<String> getLocations(Elements container) {
        return null;
    }


    public int getLimitPagePromotionCate(Elements elements) {
        Elements numberPage = elements.select("li");
        return numberPage.size();
    }

    public String getTitle(Elements container, String selector) {
        Element promoDetailEl = container.select(selector).first();
        if (promoDetailEl != null) {
            return promoDetailEl.text();
        }
        return null;
    }

    public String getImg(Elements container, String selector) {
        Element promoDetailEl = container.select(selector).first();
        if (promoDetailEl != null) {
            return promoDetailEl.attr("src");
        }
        return null;
    }

    private Map<String, List<PromotionModel>> getEntertainment() {
        // get category Name by id
        // INT ID = map.getValue(PromotionConstants.ENTERTAINMENT)
        // /// CARWLING

        /// put map.put(ID, LIST);
        //return MAP
//        this.iPromotionCrawlerDAO.getCategoryAndId().get(PropertyFile.Entertainment);

        return null;
    }

}

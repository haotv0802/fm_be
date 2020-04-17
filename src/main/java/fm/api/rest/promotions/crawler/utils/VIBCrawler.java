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
    private final PromotionUtils utils = new PromotionUtils();
    private Set<String> listDetailPromoLink = new HashSet<>();
    private IPromotionCrawlerDAO iPromotionCrawlerDAO;


    @Autowired
    public VIBCrawler(@Qualifier("promotionCrawlerDao") IPromotionCrawlerDAO iPromotionCrawlerDAO) {
        Assert.notNull(iPromotionCrawlerDAO);
        this.iPromotionCrawlerDAO = iPromotionCrawlerDAO;
    }

    @Override
    public Map<String, List<PromotionCrawlerModel>> crawl() {
        final String mainLink = "https://www.vib.com.vn/";
        List<String> listLinks = utils.getBankPromotionLinks("./properties/VIB.properties", "VIB");
        if (!listLinks.isEmpty()) {
            try {
                List<PromotionPresenter> listPromoBankData = initBankData(2);
                Map<String, List<PromotionCrawlerModel>> listPromotion = new TreeMap<>();
                for (int i = 0; i < listLinks.size(); i++) {
                    List<PromotionCrawlerModel> listModel = new ArrayList<>();
                    Document pagePromoByCate = Jsoup.connect(listLinks.get(i).toString()).timeout(1000 * 5).get();
                    Element promotionContainer = pagePromoByCate.getElementById("promotionList");
                    Elements promotionbox = promotionContainer.select(".vib-v2-world-box");
                    for (Element el : promotionbox) {
                        String linkPromoDetail = mainLink + el.select("a").attr("href");
                        if (!listDetailPromoLink.add(linkPromoDetail)) {
                            LOGGER.error("THe LLink detail is Existed");
                        }
                    }
                    for (String link : listDetailPromoLink) {
                        Thread.sleep(2000);

                        PromotionCrawlerModel model = getPromotionFromLink(link, i);
                        if(model !=null) {
                            if (checkInfoExit(model, listPromoBankData)) {
                                LOGGER.info("VIB Bank Promotion Data is Existed");
                            } else {
                                listModel.add(model);
                            }
                        }

                    }
                    String cateName = listLinks.get(i).split("\\?cate=")[1].split("&name=")[1];
                    listPromotion.put(cateName, listModel);

                }
                String[] headers = {"Bank", "TiTle", "Contain", "Discount", "Category", "Start Beign", "Date Expire", "Html", "Link"};
                utils.exportProvisionExcelFile(listPromotion, "VIBBank", headers);
                listDetailPromoLink.clear();
                return listPromotion;
            } catch (IOException e) {
//                e.printStackTrace();
            } catch (InterruptedException ex) {
//                ex.printStackTrace();

            }


        }
        return null;
    }

    public PromotionCrawlerModel getPromotionFromLink(String link, int categoryId) {
        try {
            Document promotionPage = Jsoup.connect(link).get();
            Elements promotionPageEls = promotionPage.getElementsByClass("vib-v2-wrapper_new");
            String title = getDetail(promotionPageEls, ".vib-v2-title-promotion-detail", "Title");
            String endDate = getDetail(promotionPageEls, ".vib-v2-date-promotion", "End Date").replaceAll("/", "-");
            String content = getDetail(promotionPageEls, ".vib-v2-left-body-world-detail", "Content");
            List<String> location = getLocations(promotionPageEls);
            String htmlText = getDetail(promotionPageEls, ".vib-v2-left-body-world-detail", "HTML");
            PromotionCrawlerModel model = new PromotionCrawlerModel(title, content, utils.getProvision(content) != null ? utils.getProvision(content) : "0", "", endDate, categoryId, 2, htmlText, link, "IMG", "CARD TYPE", "CONDITION", "LOCATION");
            return model;
        } catch (Exception e) {
//            e.printStackTrace();
            LOGGER.info("Bank Promotion Read time out - Link  : " + link );
        }
        return null;
    }

    public String getDetail(Elements container, String selector, String tagName) {
        try {
            for (Element item : container) {
                String result = item.select(selector).text();
                if (!result.equals("")) {
                    if (tagName.equals("End Date")) {
                        return utils.getDate(result);
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

    public List<String> getLocations(Elements container) {
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

    private boolean checkInfoExit(PromotionCrawlerModel model, List<PromotionPresenter> list) {
        for (PromotionPresenter item : list) {
            if (model.getTitle().equals(item.getTitle())) {
                if (model.getContent().equals(item.getContent())) {
                    if (!model.getDiscount().equals(item.getDiscount()) || !model.getEndDate().equals(item.getEndDate())) {
                        //do update
                        LOGGER.info("Update VIB Promotion - Promotion link  :  "+ model.getLinkDetail());
                        return true;
                    } else {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private List<PromotionPresenter> initBankData(int bankId) {
        List<PromotionPresenter> listBankDataInfo = new ArrayList<>();
        listBankDataInfo = iPromotionCrawlerDAO.getPrmoTionByBankIdAndCate(bankId);
        return listBankDataInfo;
    }
}

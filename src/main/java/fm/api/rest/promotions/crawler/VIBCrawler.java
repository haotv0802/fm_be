package fm.api.rest.promotions.crawler;

import fm.api.rest.promotions.PromotionPresenter;
import fm.api.rest.promotions.crawler.interfaces.BankLinkPromotion;
import fm.api.rest.promotions.crawler.interfaces.IBankPromotionCrawler;
import fm.api.rest.promotions.crawler.interfaces.IPromotionCrawlerDAO;
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
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;

@Service("vibCrawler")
public class VIBCrawler implements IBankPromotionCrawler {

    private static final Logger logger = LogManager.getLogger(VIBCrawler.class);
    private PromotionUtils promotionUtils;
    private Set<String> listDetailPromoLink = new HashSet<>();
    private IPromotionCrawlerDAO iPromotionCrawlerDAO;
    private final String mainLink = "https://www.vib.com.vn/";
    private Map<String, Integer> categoriesDB = new HashMap<>();

    @Value("${crawler.sleeptime}")
    private Integer sleepTime; // @Value sets variable final by default, even is is forced with other value in constructor.

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
        Map<Integer, List<PromotionCrawlerModel>> results = new HashMap<>();
        categoriesDB = this.iPromotionCrawlerDAO.getCategoryAndId();
        promotionUtils.addPromotionDataIntoMap(results, getTravelPromotion(), categoriesDB.get(FmConstants.PROMOTION_CATEGORY_TRAVEL));

        promotionUtils.addPromotionDataIntoMap(results, getEducationPromotion(), categoriesDB.get(FmConstants.PROMOTION_CATEGORY_EDUCATION));

        promotionUtils.addPromotionDataIntoMap(results, getShoppingPromotion(), categoriesDB.get(FmConstants.PROMOTION_CATEGORY_SHOPPING));

        promotionUtils.addPromotionDataIntoMap(results, getFoodPromotion(), categoriesDB.get(FmConstants.PROMOTION_CATEGORY_FOOD));

        promotionUtils.addPromotionDataIntoMap(results, getHealthPromotion(), categoriesDB.get(FmConstants.PROMOTION_CATEGORY_HEALTH));

        promotionUtils.addPromotionDataIntoMap(results, getTravelInstallment(), categoriesDB.get(FmConstants.PROMOTION_CATEGORY_TRAVEL));

        promotionUtils.addPromotionDataIntoMap(results, getEducationInstallment(), categoriesDB.get(FmConstants.PROMOTION_CATEGORY_EDUCATION));

        promotionUtils.addPromotionDataIntoMap(results, getHealthInstallment(), categoriesDB.get(FmConstants.PROMOTION_CATEGORY_HEALTH));

        promotionUtils.addPromotionDataIntoMap(results, getElectronicsInstallment(), categoriesDB.get(FmConstants.PROMOTION_CATEGORY_ELECTRONICS));

        promotionUtils.addPromotionDataIntoMap(results, getShoppingInstallment(), categoriesDB.get(FmConstants.PROMOTION_CATEGORY_SHOPPING));
        listDetailPromoLink.clear();
        return results;
    }


  /**
   * This service is to get neccessary infomation from detail link
   *
   * @param link       : detail link
   * @param categoryId : categories id
   * @return PromotionCrawlerModel
   */
  private PromotionCrawlerModel getPromotionFromLink(String link, int categoryId, int crawling_option) {
    try {
      Document promotionPage = Jsoup.connect(link).timeout(1000 * 3).get();
      Elements promotionPageEls = promotionPage.getElementsByClass("vib-v2-wrapper_new");
      String title = getDetail(promotionPageEls, ".vib-v2-title-promotion-detail", "Title");
      String endDate = getDetail(promotionPageEls, ".vib-v2-date-promotion", "End Date").replaceAll("/", "-");
      String content = getDetail(promotionPageEls, ".vib-v2-left-body-world-detail", "Content");
      List<String> location = getLocations(promotionPageEls);
      String htmlText = getDetail(promotionPageEls, ".vib-v2-left-body-world-detail", "HTML");
      PromotionCrawlerModel model = new PromotionCrawlerModel(title, content, promotionUtils.getProvision(content) != null ? promotionUtils.getProvision(content) : "0", promotionUtils.getPeriod(content) + " tháng", "", endDate, categoryId, 2, htmlText, link, "IMG", "CARD TYPE", "CONDITION", "LOCATION");
      if (crawling_option == 1) {
        model.setInstallmentPeriod(null);
      } else if (crawling_option == 2) {
        model.setInstallmentPeriod(null);
      }
      if (content != null) {
        return model;
      } else {
        logger.info("ERROR LINK PROMTION : " + link);
      }
    } catch (Exception e) {
//            e.printStackTrace();
            logger.info("Bank Promotion Read time out - Link  : " + link);
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

  private List<PromotionCrawlerModel> getEducationPromotion() {
    try {
      int cateID = categoriesDB.get(FmConstants.PROMOTION_CATEGORY_EDUCATION);
      List<PromotionCrawlerModel> educationPromotionCrawlinData = doCrawling(cateID, BankLinkPromotion.VIB_PROMOTION_EDUCATION, PROMOTION_CRAWLING_OPTION);
      return educationPromotionCrawlinData;
    } catch (Exception ex) {
      logger.error(ex.getMessage(), ex);
    }
    return null;
  }

    /**
     * This service is to get promotion by travel category
     *
     * @return List<PromotionCrawlerModel>
     * @throws InterruptedException
     */
    private List<PromotionCrawlerModel> getTravelPromotion() {
        try {
            int cateID = categoriesDB.get(FmConstants.PROMOTION_CATEGORY_TRAVEL);

      List<PromotionCrawlerModel> travelPromotionCrawlinData = doCrawling(cateID, BankLinkPromotion.VIB_PROMOTION_TRAVEL, PROMOTION_CRAWLING_OPTION);

            return travelPromotionCrawlinData;
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
        }
        return null;
    }

    /**
     * This service is to get promotion by food category
     *
     * @return List<PromotionCrawlerModel>
     * @throws InterruptedException
     */
    private List<PromotionCrawlerModel> getFoodPromotion() {
        try {
            int cateID = categoriesDB.get(FmConstants.PROMOTION_CATEGORY_FOOD);

      List<PromotionCrawlerModel> foodPromotionCrawlinData = doCrawling(cateID, BankLinkPromotion.VIB_PROMOTION_FOOD, PROMOTION_CRAWLING_OPTION);

            return foodPromotionCrawlinData;
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
        }
        return null;
    }

    /**
     * This service is to get promotion by health category
     *
     * @return List<PromotionCrawlerModel>
     * @throws InterruptedException
     */
    private List<PromotionCrawlerModel> getHealthPromotion() {
        try {
            int cateID = categoriesDB.get(FmConstants.PROMOTION_CATEGORY_HEALTH);

      List<PromotionCrawlerModel> healthPromotionCrawlinData = doCrawling(cateID, BankLinkPromotion.VIB_PROMOTION_HEALTHCARE, PROMOTION_CRAWLING_OPTION);

            return healthPromotionCrawlinData;
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
        }
        return null;
    }

    /**
     * This service is to get promotion by shopping category
     *
     * @return List<PromotionCrawlerModel>
     * @throws InterruptedException
     */
    private List<PromotionCrawlerModel> getShoppingPromotion() {
        try {

            int cateID = categoriesDB.get(FmConstants.PROMOTION_CATEGORY_SHOPPING);

      List<PromotionCrawlerModel> shoppingPromotionCrawlinData = doCrawling(cateID, BankLinkPromotion.VIB_PROMOTION_SHOPPING, PROMOTION_CRAWLING_OPTION);

            return shoppingPromotionCrawlinData;
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
        }
        return null;
    }

    /**
     * This service is to get installment by shopping category
     *
     * @return List<PromotionCrawlerModel>
     * @throws InterruptedException
     */
    private List<PromotionCrawlerModel> getShoppingInstallment() {
        try {

            int cateID = categoriesDB.get(FmConstants.PROMOTION_CATEGORY_SHOPPING);

      List<PromotionCrawlerModel> shoppingInstallmentCrawlinData = doCrawling(cateID, BankLinkPromotion.VIB_INSTALLMENT_SHOPPING, INSTALLMENT_CRAWLING_OPTION);

            return shoppingInstallmentCrawlinData;
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
        }
        return null;
    }

    /**
     * This service is to get installment by health category
     *
     * @return List<PromotionCrawlerModel>
     * @throws InterruptedException
     */
    private List<PromotionCrawlerModel> getHealthInstallment() {
        try {

            int cateID = categoriesDB.get(FmConstants.PROMOTION_CATEGORY_HEALTH);

      List<PromotionCrawlerModel> heanlthInstallmentCrawlinData = doCrawling(cateID, BankLinkPromotion.VIB_INSTALLMENT_HEALTHCARE, INSTALLMENT_CRAWLING_OPTION);

            return heanlthInstallmentCrawlinData;
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
        }
        return null;
    }

    /**
     * This service is to get installment by electric category
     *
     * @return List<PromotionCrawlerModel>
     * @throws InterruptedException
     */
    private List<PromotionCrawlerModel> getElectronicsInstallment() {
        try {
            int cateID = categoriesDB.get(FmConstants.PROMOTION_CATEGORY_ELECTRONICS);

      List<PromotionCrawlerModel> data = doCrawling(cateID, BankLinkPromotion.VIB_INSTALLMENT_ELECTRICATE, INSTALLMENT_CRAWLING_OPTION);

            return data;
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
        }
        return null;
    }

    /**
     * This service is to get installment by education category
     *
     * @return List<PromotionCrawlerModel>
     * @throws InterruptedException
     */
    private List<PromotionCrawlerModel> getEducationInstallment() {
        try {
            int cateID = categoriesDB.get(FmConstants.PROMOTION_CATEGORY_EDUCATION);

      List<PromotionCrawlerModel> data = doCrawling(cateID, BankLinkPromotion.VIB_INSTALLMENT_EDUCATION, INSTALLMENT_CRAWLING_OPTION);

            return data;
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
        }
        return null;
    }

  /**
   * This service is to get installment by travek category
   *
   * @return List<PromotionCrawlerModel>
   * @throws InterruptedException
   */
  private List<PromotionCrawlerModel> getTravelInstallment() {
    try {
      int cateID = categoriesDB.get(FmConstants.PROMOTION_CATEGORY_TRAVEL);
      List<PromotionCrawlerModel> data = doCrawling(cateID, BankLinkPromotion.VIB_INSTALLMENT_TRAVEL, INSTALLMENT_CRAWLING_OPTION);
      return data;
    } catch (Exception ex) {
      logger.error(ex.getMessage(), ex);
    }
    return null;
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
                    logger.error("Promotion is Existed, {}", linkPromoDetail);
                } else {
                    listPromotionLinks.add(linkPromoDetail);
                }
            }
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
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
        Thread.sleep(sleepTime);
        PromotionCrawlerModel model = getPromotionFromLink(link, cateID, craling_option);
        if (model != null) {
          if (this.promotionUtils.checkIfPromotionExisting(model, listPromoBankData)) {
            logger.info("VIB Bank Promotion Data is Existed");
          } else {
            promotionCrawlinData.add(model);
          }
        }
      }
        listDetailPromoLink.clear();
        return promotionCrawlinData;
    }

    @Override
    public String toString() {
        return "VIBCrawler{" +
                ", mainLink='" + mainLink + '\'' +
                '}';
    }
  }
}

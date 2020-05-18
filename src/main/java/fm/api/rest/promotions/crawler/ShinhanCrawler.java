 /* Quy created on 3/13/2020 */
 package fm.api.rest.promotions.crawler;

 import fm.api.rest.promotions.PromotionPresenter;
 import fm.api.rest.promotions.crawler.interfaces.BankLinkPromotion;
 import fm.api.rest.promotions.crawler.interfaces.IBankPromotionCrawler;
 import fm.api.rest.promotions.crawler.interfaces.IPromotionCrawlerDAO;
 import fm.api.rest.promotions.crawler.utils.PromotionUtils;
 import io.jsonwebtoken.lang.Assert;
 import org.apache.http.NameValuePair;
 import org.apache.http.client.entity.UrlEncodedFormEntity;
 import org.apache.http.client.methods.CloseableHttpResponse;
 import org.apache.http.client.methods.HttpPost;
 import org.apache.http.impl.client.CloseableHttpClient;
 import org.apache.http.impl.client.HttpClients;
 import org.apache.http.message.BasicNameValuePair;
 import org.apache.http.util.EntityUtils;
 import org.apache.logging.log4j.LogManager;
 import org.apache.logging.log4j.Logger;
 import org.json.JSONArray;
 import org.json.JSONObject;
 import org.jsoup.Jsoup;
 import org.jsoup.nodes.Document;
 import org.jsoup.nodes.Element;
 import org.jsoup.select.Elements;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.beans.factory.annotation.Qualifier;
 import org.springframework.beans.factory.annotation.Value;
 import org.springframework.stereotype.Service;

 import java.io.IOException;
 import java.util.ArrayList;
 import java.util.List;
 import java.util.Map;
 import java.util.TreeMap;

 @Service("shinhanCrawler")
 public class ShinhanCrawler implements IBankPromotionCrawler {
     private static final Logger logger = LogManager.getLogger(ShinhanCrawler.class);
     private PromotionUtils promotionUtils;
     private IPromotionCrawlerDAO iPromotionCrawlerDAO;

     @Value("${bank.shinhan.id}")
     private Integer bankId;

     @Autowired
     public ShinhanCrawler(@Qualifier("promotionCrawlerDao") IPromotionCrawlerDAO iPromotionCrawlerDAO,
                           @Qualifier("promoUtils") PromotionUtils promotionUtils) {
         Assert.notNull(iPromotionCrawlerDAO);
         Assert.notNull(promotionUtils);
         this.iPromotionCrawlerDAO = iPromotionCrawlerDAO;
         this.promotionUtils = promotionUtils;
     }


     private Map<String, Integer> categoriesDB = new TreeMap<>();

     @Override
     public Map<Integer, List<PromotionCrawlerModel>> crawl() {
         categoriesDB = this.iPromotionCrawlerDAO.getCategoryAndId();
         int cateID = categoriesDB.get("Other");
         Map<Integer, List<PromotionCrawlerModel>> ressult = new TreeMap<>();

         try {
             int limitPageNum = getLimitPagePromoCate();

             ressult = promotionUtils.addPromotionDataIntoMap(ressult, getBankPromotion(cateID, BankLinkPromotion.SHINHAN_PROMOTION, limitPageNum), cateID);


         } catch (IOException e) {
             e.printStackTrace();
         }
         return ressult;
     }

     private List<PromotionCrawlerModel> getBankPromotion(int cateId, String link, int limitPageNum) {
         try {
             List<PromotionCrawlerModel> list = new ArrayList<>();
             String crwalingData = "";
             HttpPost postConnection = new HttpPost(link);
             List<PromotionPresenter> listBankDataPromo = iPromotionCrawlerDAO.getPromotionByBankId(bankId, cateId);

             List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
             urlParameters.add(new BasicNameValuePair("_token", "iEWghzwz8FGa0yCkILYxWtwJx1A8Ya1avRJyPyay"));
             urlParameters.add(new BasicNameValuePair("province", "all"));
             urlParameters.add(new BasicNameValuePair("district", "all"));
             urlParameters.add(new BasicNameValuePair("key_word", "all"));
             urlParameters.add(new BasicNameValuePair("filter", "all"));
             urlParameters.add(new BasicNameValuePair("type", "all"));
             urlParameters.add(new BasicNameValuePair("cate", "all"));
             urlParameters.add(new BasicNameValuePair("lang", "vi"));
             urlParameters.add(new BasicNameValuePair("order_by", "0"));
             for (int i = 1; i <= limitPageNum; i++) {
                 urlParameters.add(new BasicNameValuePair("page", i + ""));
                 postConnection.setEntity(new UrlEncodedFormEntity(urlParameters));

                 try (CloseableHttpClient httpClient = HttpClients.createDefault();
                      CloseableHttpResponse response = httpClient.execute(postConnection)) {

                     crwalingData = EntityUtils.toString(response.getEntity());
                 }
                 JSONObject obj = new JSONObject(crwalingData);
                 JSONArray subJsonArray = obj.getJSONArray("list_result");
                 for (int y = 0; y < subJsonArray.length(); y++) {
                     String title = subJsonArray.getJSONObject(y).getString("title");
                     String description = subJsonArray.getJSONObject(y).getString("description");
                     String contentHTML = subJsonArray.getJSONObject(y).getString("content");
                     String date_start = !subJsonArray.getJSONObject(y).getString("date_start").equals("") ? promotionUtils.formatDateText((String) subJsonArray.getJSONObject(y).getString("date_start")) : "";
                     String date_end = !subJsonArray.getJSONObject(y).getString("date_end").equals("") ? promotionUtils.formatDateText((String) subJsonArray.getJSONObject(y).getString("date_end")) : "";
                     String type = subJsonArray.getJSONObject(y).getString("type");
                     String image_future = subJsonArray.getJSONObject(y).getString("image_future");
                     String get_permalinks = subJsonArray.getJSONObject(y).getString("get_permalinks");
                     PromotionCrawlerModel model = new PromotionCrawlerModel(title, description, promotionUtils.getProvision(description), null, date_start, date_end, cateId, bankId, contentHTML, get_permalinks, null, null, null, null);
                     if (promotionUtils.checkIfPromotionExisting(model, listBankDataPromo)) {
                         logger.info("Shinhan Bank Promotion is Existed, {}", model.getUrl());
                     } else {
                         list.add(model);
                     }
                 }
             }
             return list;

         } catch (IOException e) {
             logger.error(e.getMessage(), e);
         }

         return null;
     }

     /**
      * This service is to get max number of cataegoriees page.
      *
      * @return
      */
     private int getLimitPagePromoCate() throws IOException {
         String url = "https://shinhan.com.vn/vi/promotion";
         Document pageDoc = Jsoup.connect(url).get();
         Elements numbPage = pageDoc.select("#paginate li");
         int temp = 0;
         for (Element item : numbPage) {
             String pageNum = item.text().trim();
             if (!pageNum.equals("")) {
                 try {
                     if (temp < Integer.parseInt(pageNum)) {
                         temp = Integer.parseInt(pageNum);
                     }
                 } catch (NumberFormatException e) {
                 }
             }
         }
         return temp;
     }

     @Override
     public String toString() {
         return "ShinhanCrawler";
     }
 }

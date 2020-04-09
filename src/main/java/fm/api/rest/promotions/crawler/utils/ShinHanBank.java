 /* Quy created on 3/13/2020 */
 package fm.api.rest.promotions.crawler.utils;

 import fm.api.rest.promotions.crawler.PromotionCrawlerModel;
 import fm.api.rest.promotions.crawler.interfaces.IBankPromotionCrawler;
 import org.apache.http.NameValuePair;
 import org.apache.http.client.entity.UrlEncodedFormEntity;
 import org.apache.http.client.methods.CloseableHttpResponse;
 import org.apache.http.client.methods.HttpPost;
 import org.apache.http.impl.client.CloseableHttpClient;
 import org.apache.http.impl.client.HttpClients;
 import org.apache.http.message.BasicNameValuePair;
 import org.apache.http.util.EntityUtils;
 import org.json.JSONArray;
 import org.json.JSONObject;
 import org.springframework.stereotype.Service;

 import java.io.IOException;
 import java.net.URL;
 import java.util.ArrayList;
 import java.util.List;
 import java.util.Map;
 import java.util.TreeMap;

 @Service("shinhanCrawler")
 public class ShinHanBank implements IBankPromotionCrawler {

  @Override
  public Map<String, List<PromotionCrawlerModel>> crawl() {
   final String mainLink="https://shinhan.com.vn/get_shinhan_promotion";
   Map<String,List<PromotionCrawlerModel>> listPromotions = new TreeMap<>();
   try {
    List<PromotionCrawlerModel> list = new ArrayList<>();
    String result="";
    HttpPost postConnection = new HttpPost(mainLink);
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
    for(int i = 0 ; i<=10 ; i++){
     urlParameters.add(new BasicNameValuePair("page", i+""));
     postConnection.setEntity(new UrlEncodedFormEntity(urlParameters));

     try (CloseableHttpClient httpClient = HttpClients.createDefault();
          CloseableHttpResponse response = httpClient.execute(postConnection)){

      result = EntityUtils.toString(response.getEntity());
     }
     System.out.println(result);
     JSONObject obj = new JSONObject(result);
     JSONArray subJsonArray = obj.getJSONArray("list_result");
     for(int y = 0 ; y< subJsonArray.length();y++){
      String title=subJsonArray.getJSONObject(y).getString("title");
      String description=subJsonArray.getJSONObject(y).getString("description");
      String date_start=subJsonArray.getJSONObject(y).getString("date_start");
      String date_end=subJsonArray.getJSONObject(y).getString("date_end");
      String type=subJsonArray.getJSONObject(y).getString("type");
      String image_future=subJsonArray.getJSONObject(y).getString("image_future");
      String get_permalinks=subJsonArray.getJSONObject(y).getString("get_permalinks");
      Object category=subJsonArray.getJSONObject(y).get("category");
      System.out.println("title : " + title);
      System.out.println("description : " + description);
      System.out.println("date_start : " + date_start);
      System.out.println("date_end : " + date_end);
      System.out.println("type : " + type);
      System.out.println("image_future : " + image_future);
      System.out.println("get_permalinks : " + get_permalinks);
      System.out.println("category : " + category.toString());
      System.out.println("*************************");
//      list.add(new PromotionCrawlerModel(title,description,date_start,date_end,type,image_future,4,get_permalinks,"","","","","")) ;
     }
    }


   }  catch (IOException e) {
    System.out.println(e.getMessage());
   }
   return null;
  }




 }

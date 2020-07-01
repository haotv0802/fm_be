package fm.api.rest.promotions;

import com.fasterxml.jackson.databind.ObjectMapper;
import fm.api.rest.BaseDocumentation;
import fm.api.rest.bankinterests.crawlers.BankInterestsModel;
import jdk.nashorn.internal.parser.JSONParser;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.testng.annotations.Test;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by haho on 3/20/2020.
 */
public class PromotionsResourceTest extends BaseDocumentation {

  @Test
  @Rollback(false)
  public void testPromotionsCrawlerForShinhan() throws Exception {
    mockMvc
        .perform(get("/svc/promotions/crawler/shinhan")
            .header("Accept-Language", "en")
            .header("X-AUTH-TOKEN", authTokenService.getAuthToken())
            .contentType(MediaType.APPLICATION_JSON)
        )
        .andExpect(status().is(200))
    ;
  }

  @Test
  @Rollback(false)
  public void testPromotionsCrawlerForVIB() throws Exception {
    mockMvc
        .perform(get("/svc/promotions/crawler/vib")
            .header("Accept-Language", "en")
            .header("X-AUTH-TOKEN", authTokenService.getAuthToken())
            .contentType(MediaType.APPLICATION_JSON)
        )
        .andExpect(status().is(200))
    ;
  }

  @Test
  @Rollback(false)
  public void testPromotionsCrawlerForSCB() throws Exception {
    mockMvc
        .perform(get("/svc/promotions/crawler/scb")
            .header("Accept-Language", "en")
            .header("X-AUTH-TOKEN", authTokenService.getAuthToken())
            .contentType(MediaType.APPLICATION_JSON)
        )
        .andExpect(status().is(200))
    ;
  }

  @Test
  @Rollback(false)
  public void testCrawlAll() throws Exception {

    mockMvc
        .perform(get("/svc/promotions/crawlall")
            .header("Accept-Language", "en")
            .header("X-AUTH-TOKEN", authTokenService.getAuthToken())
            .contentType(MediaType.APPLICATION_JSON)
        )
        .andExpect(status().is(200))
    ;

  }

  @Test
  @Rollback(false)
  public void testCrawlAllByMultiThreads() throws Exception {

    mockMvc
        .perform(get("/svc/promotions/crawlall/threads")
            .header("Accept-Language", "en")
            .header("X-AUTH-TOKEN", authTokenService.getAuthToken())
            .contentType(MediaType.APPLICATION_JSON)
        )
        .andExpect(status().is(200))
    ;

  }

  @Test
  public void testGetAllPromotions() throws Exception {

//        mockMvc
//            .perform(get("/svc/promotions/list?title=GRAND")
//                .header("Accept-Language", "en")
//                .header("X-AUTH-TOKEN", authTokenService.getAuthToken())
//                .contentType(MediaType.APPLICATION_JSON_UTF8)
//            )
//            .andExpect(status().is(200))
//        ;
//        mockMvc
//            .perform(get("/svc/promotions/list?title=GRAND&content=Giảm ngay")
//                .header("Accept-Language", "en")
//                .header("X-AUTH-TOKEN", authTokenService.getAuthToken())
//                .contentType(MediaType.APPLICATION_JSON_UTF8)
//            )
//            .andExpect(status().is(200))
//        ;

//        mockMvc
//            .perform(get("/svc/promotions/list?title=GRAND&start_date=2020-03-20")
//                .header("Accept-Language", "en")
//                .header("X-AUTH-TOKEN", authTokenService.getAuthToken())
//                .contentType(MediaType.APPLICATION_JSON)
//            )
//            .andExpect(status().is(200))
//        ;
    mockMvc
        .perform(get("/svc/promotions/list?&start_date=2020-03-10")
            .header("Accept-Language", "vi")
            .header("X-AUTH-TOKEN", authTokenService.getAuthToken())
            .contentType(MediaType.APPLICATION_JSON_UTF8)
        )
        .andExpect(status().is(200))
    ;

//        mockMvc
//            .perform(get("/svc/promotions/list?title=GRAND&bank_id=3&category_id=1")
//                .header("Accept-Language", "vi")
//                .header("X-AUTH-TOKEN", authTokenService.getAuthToken())
//                .contentType(MediaType.APPLICATION_JSON_UTF8)
//            )
//            .andExpect(status().is(200))
//        ;
  }

  @org.junit.Test
  public void test() {
    String link = "https://bankergroup.vn/wp-json/api/banks";
    List<BankInterestsModel> list = new ArrayList<>();
    try {
      URL url = new URL(link);
      String crwalingData = "";
      String readLine = null;
      HttpGet getConnection = new HttpGet(link);
      try (CloseableHttpClient httpClient = HttpClients.createDefault();
           CloseableHttpResponse response = httpClient.execute(getConnection)) {
        crwalingData = EntityUtils.toString(response.getEntity());
      } catch (IOException e) {
        e.printStackTrace();
      }
      JSONObject obj = new JSONObject(crwalingData);
      JSONObject obj2 = obj.getJSONObject("data");
      JSONObject obj3 = obj2.getJSONObject("banks");
      Map<String, Object> bankValues = toMap(obj3);
      Set<String> keysSet = bankValues.keySet();
      for (String key : keysSet) {
        Map objVal = (Map) bankValues.get(key);
        String bankName = objVal.get("name").toString();
        String bankType = objVal.get("type").toString();
        System.out.println("=====================================================");
        System.out.println("Title       : " + bankName);
        System.out.println("Type        : " + bankType);
        List<Map> loans = (ArrayList) objVal.get("loans");
        if (loans.size() > 0) {
          for (int i = 0; i < loans.size(); i++) {
            Map<String, String> loanDetails = (Map) loans.get(i);
            String fixed_period = loanDetails.get("fixed_period");
            String fixed_rate = loanDetails.get("fixed_rate");
            String rate_after_fixed_period = loanDetails.get("rate_after_fixed_period");
            String loan_goal_id = loanDetails.get("loan_goal_id");
            String loan_goal_name =(String)loanDetails.get("loan_goal_name");
            if (!loanDetails.get("loan_goal_name").isEmpty()) {
//              loan_goal_name = loanDetails.get("loan_goal_name");
            }
            String min_amount = loanDetails.get("min_amount");
            String max_amount = loanDetails.get("max_amount");
            String min_wage_tranfer = loanDetails.get("min_wage_tranfer");
            String min_wage_cash = loanDetails.get("min_wage_cash");

            System.out.println("fixed_period        : " + fixed_period);
            System.out.println("fixed_rate        : " + fixed_rate);
            System.out.println("rate_after_fixed_period        : " + rate_after_fixed_period);
            System.out.println("loan_goal_id        : " + loan_goal_id);
            System.out.println("loan_goal_name        : " + loan_goal_name);
            System.out.println("min_amount        : " + min_amount);
            System.out.println("max_amount        : " + max_amount);
            System.out.println("min_wage_tranfer        : " + min_wage_tranfer);
            System.out.println("min_wage_cash        : " + min_wage_cash);
          }

        }
        System.out.println("=====================================================");
      }

    } catch (MalformedURLException e) {
      e.printStackTrace();
    }

  }

  public static Map<String, Object> toMap(JSONObject object) throws JSONException {
    Map<String, Object> map = new HashMap<String, Object>();

    Iterator<String> keysItr = object.keys();
    while (keysItr.hasNext()) {
      String key = keysItr.next();
      Object value = object.get(key);

      if (value instanceof JSONArray) {
        value = toList((JSONArray) value);
      } else if (value instanceof JSONObject) {
        value = toMap((JSONObject) value);
      }
      map.put(key, value);
    }
    return map;
  }

  public static List<Object> toList(JSONArray array) throws JSONException {
    List<Object> list = new ArrayList<Object>();
    for (int i = 0; i < array.length(); i++) {
      Object value = array.get(i);
      if (value instanceof JSONArray) {
        value = toList((JSONArray) value);
      } else if (value instanceof JSONObject) {
        value = toMap((JSONObject) value);
      }
      list.add(value);
    }
    return list;
  }


}

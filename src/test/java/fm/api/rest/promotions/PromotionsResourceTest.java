package fm.api.rest.promotions;

import fm.api.rest.BaseDocumentation;
import org.springframework.http.MediaType;
import org.testng.annotations.Test;

import java.io.UnsupportedEncodingException;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by haho on 3/20/2020.
 */
public class PromotionsResourceTest extends BaseDocumentation {

//    @Test
    public void testPromotionsCrawler() throws Exception {

        mockMvc
                .perform(get("/svc/promotions/crawler/scb")
                        .header("Accept-Language", "en")
                        .header("X-AUTH-TOKEN", authTokenService.getAuthToken())
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().is(201))
        ;
    }

}

package fm.api.rest.promotions;

import fm.api.rest.BaseDocumentation;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.testng.annotations.Test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by haho on 3/20/2020.
 */
public class PromotionsResourceTest extends BaseDocumentation {

    @Test
    @Rollback(false)
    public void testPromotionsCrawler() throws Exception {

        mockMvc
                .perform(get("/svc/promotions/crawler/shinhan")
                        .header("Accept-Language", "en")
                        .header("X-AUTH-TOKEN", authTokenService.getAuthToken())
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().is(200))
        ;

        mockMvc
                .perform(get("/svc/promotions/crawler/vib")
                        .header("Accept-Language", "en")
                        .header("X-AUTH-TOKEN", authTokenService.getAuthToken())
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().is(200))
        ;

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

}

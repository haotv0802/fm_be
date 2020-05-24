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
            .perform(get("/svc/promotions/list?title=G&start_date=2020-03-10&end_date=2020-12-10")
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
}

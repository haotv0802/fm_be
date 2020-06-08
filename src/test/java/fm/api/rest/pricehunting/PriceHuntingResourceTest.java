package fm.api.rest.pricehunting;

import fm.api.rest.BaseDocumentation;
import fm.api.rest.moneysource.MoneySourcePresenter;
import org.json.JSONObject;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.util.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by haoho on 6/8/20.
 */
public class PriceHuntingResourceTest extends BaseDocumentation {

    @Test
    @Rollback(false)
    public void testAddPrice() throws Exception {
        List<Price> prices = new ArrayList<>();

        Price price = new Price();
        price.setEmail("hoanhhao@gmail.com");
        price.setUrl("https://tiki.vn/thung-20-chai-nuoc-gao-woongjin-500ml-x20-chai-p20720569.html");
        prices.add(price);

        price = new Price();
        price.setEmail("hoanhhao@gmail.com");
        price.setUrl("https://tiki.vn/nuoc-tay-trang-lam-sach-sau-kiem-soat-ba-nhon-cho-da-dau-nhay-cam-la-roche-posay-micellar-water-ultra-oily-skin-400ml-p2616909.html");
        prices.add(price);

        price = new Price();
        price.setEmail("hoanhhao@gmail.com");
        price.setUrl("https://tiki.vn/bo-3-hop-silcot-bong-trang-diem-bong-tay-trang-nhat-ban-cao-cap-66-mieng-hop-p2738497.html");
        prices.add(price);

        price = new Price();
        price.setEmail("hoanhhao@gmail.com");
        price.setUrl("https://tiki.vn/kem-duong-am-eucerin-aqua-porin-active-50ml-p7157689.html");
        prices.add(price);

        price = new Price();
        price.setEmail("hoanhhao@gmail.com");
        price.setUrl("https://tiki.vn/kem-chong-nang-dang-sua-long-nhe-khong-nhon-rit-la-roche-posay-anthelios-shaka-fluid-spf-50-50ml-p470571.html");
        prices.add(price);

        for (Price price1 : prices) {
            mockMvc
                    .perform(patch("/svc/price/hunt")
                            .header("Accept-Language", "")
                            .header("X-AUTH-TOKEN", authTokenService.getAuthToken())
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(price1))
                    )
                    .andExpect(status().is(201))
                    ;
        }
    }

    @Test
    public void testCheckAndNotify() throws Exception {
        MvcResult result = mockMvc
                .perform(get("/svc/price/check")
                        .header("Accept-Language", "")
                        .header("X-AUTH-TOKEN", authTokenService.getAuthToken())
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().is(204))
                .andReturn();
    }
}

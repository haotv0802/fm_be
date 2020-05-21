package fm.api.rest.paymentmethods;

import fm.api.rest.BaseDocumentation;
import fm.api.rest.paymentmethods.beans.PaymentMethodPresenter;
import org.json.JSONObject;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.util.Assert;
import org.testng.annotations.Test;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by haho on 6/26/2017.
 */
public class PaymentMethodsResourceTest extends BaseDocumentation {

    @Test
    public void testGetPaymentMethods() throws Exception {
        MvcResult result = mockMvc
                .perform(get("/svc/paymentMethod/list")
                        .header("Accept-Language", "")
                        .header("X-AUTH-TOKEN", authTokenService.getAuthToken())
                )
                .andExpect(status().is(200))
                .andReturn();

        List<PaymentMethodPresenter> paymentMethodPresenter = objectMapper.readValue(
                result.getResponse().getContentAsString(), List.class);

        Assert.notNull(paymentMethodPresenter);
        Assert.isTrue(paymentMethodPresenter.size() > 0);
    }

    @Test
    public void tetAddPaymentMethod() throws Exception {
        PaymentMethodPresenter paymentMethod = new PaymentMethodPresenter();
        paymentMethod.setName("new Name");
        paymentMethod.setName("new Logo");

        MvcResult result = mockMvc
                .perform(post("/svc/paymentMethod")
                        .header("Accept-Language", "")
                        .header("X-AUTH-TOKEN", authTokenService.getAuthToken())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(paymentMethod))
                )
                .andExpect(status().is(201))
                .andReturn();

        // result example: {"paymentMethodId":6}
        Assert.notNull(result.getResponse().getContentAsString());
        JSONObject data = new JSONObject(result.getResponse().getContentAsString());

        Integer paymentMethodId = (Integer) data.get("paymentMethodId");
        Assert.notNull(paymentMethodId);
        Assert.isTrue(paymentMethodId > 0);
    }

    @Test
    public void tetUpdatePaymentMethod() throws Exception {
        // Get item in the list first
        MvcResult result = mockMvc
                .perform(get("/svc/paymentMethod/list")
                        .header("Accept-Language", "")
                        .header("X-AUTH-TOKEN", authTokenService.getAuthToken())
                )
                .andExpect(status().is(200))
                .andReturn();

        List<PaymentMethodPresenter> paymentMethodPresenter = objectMapper.readValue(
                result.getResponse().getContentAsString(), objectMapper.getTypeFactory().constructCollectionType(List.class, PaymentMethodPresenter.class));
        PaymentMethodPresenter payment = paymentMethodPresenter.get(0);


        // Then update item got above.
        result = mockMvc
                .perform(patch("/svc/paymentMethod")
                        .header("Accept-Language", "")
                        .header("X-AUTH-TOKEN", authTokenService.getAuthToken())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(payment))
                )
                .andExpect(status().is(204))
                .andReturn();
    }
}

package fm.api.rest.paymentmethods;

import fm.api.rest.BaseDocumentation;
import fm.api.rest.paymentmethods.beans.PaymentMethodPresenter;
import org.json.JSONObject;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
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
                result.getResponse().getContentAsString(), objectMapper.getTypeFactory().constructCollectionType(List.class, PaymentMethodPresenter.class));

        Assert.notNull(paymentMethodPresenter);
        Assert.isTrue(paymentMethodPresenter.size() > 0);
    }

    @Test
    public void tetAddPaymentMethod() throws Exception {
        PaymentMethodPresenter paymentMethod = new PaymentMethodPresenter();
        paymentMethod.setName("new Name");
        paymentMethod.setLogo("new Logo");

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
    public void tetAddPaymentMethodExisting() throws Exception {
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

        // then use payment got above to perform ADD request
        result = mockMvc
                .perform(post("/svc/paymentMethod")
                        .header("Accept-Language", "")
                        .header("X-AUTH-TOKEN", authTokenService.getAuthToken())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(payment))
                )
                .andExpect(status().is(400))
                .andReturn();

        // result example: {"faultCode":"payment.method.name.existing","faultMessage":"Name of payment method is already existing.","incidentId":"1"}
        Assert.notNull(result.getResponse().getContentAsString());
        JSONObject data = new JSONObject(result.getResponse().getContentAsString());
        Assert.isTrue(data.get("faultCode").equals("payment.method.name.existing"));
        Assert.isTrue(data.get("faultMessage").equals("Name of payment method is already existing."));
        Integer incidentId = Integer.parseInt(data.get("incidentId").toString());
        Assert.notNull(incidentId);
        Assert.isTrue(incidentId > 0);
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
        payment.setName("name updated");
        payment.setLogo("logo updated");

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

    @Test
    public void tetUpdatePaymentMethodExisting() throws Exception {
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
        PaymentMethodPresenter payment2 = paymentMethodPresenter.get(1);
        payment.setName(payment2.getName());

        // Then update item got above.
        result = mockMvc
                .perform(patch("/svc/paymentMethod")
                        .header("Accept-Language", "")
                        .header("X-AUTH-TOKEN", authTokenService.getAuthToken())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(payment))
                )
                .andExpect(status().is(400))
                .andReturn();

        // result example: {"faultCode":"payment.method.name.existing","faultMessage":"Name of payment method is already existing.","incidentId":"1"}
        Assert.notNull(result.getResponse().getContentAsString());
        JSONObject data = new JSONObject(result.getResponse().getContentAsString());
        Assert.isTrue(data.get("faultCode").equals("payment.method.name.existing"));
        Assert.isTrue(data.get("faultMessage").equals("Name of payment method is already existing."));
        Integer incidentId = Integer.parseInt(data.get("incidentId").toString());
        Assert.notNull(incidentId);
        Assert.isTrue(incidentId > 0);
    }
}

package fm.api.rest.moneysource;

import fm.api.rest.BaseDocumentation;
import fm.api.rest.bank.BankPresenter;
import fm.api.rest.paymentmethods.beans.PaymentMethodPresenter;
import fm.utils.FmDateUtils;
import org.json.JSONObject;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.util.Assert;
import org.testng.annotations.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by haho on 6/26/2017.
 */
public class MoneySourcesResourceTest extends BaseDocumentation {

    @Test
    public void tetAddPaymentMethod() throws Exception {
        // get bank id
        MvcResult result = mockMvc
                .perform(get("/svc/bank")
                        .header("Accept-Language", "")
                        .header("X-AUTH-TOKEN", authTokenService.getAuthToken())
                )
                .andExpect(status().is(200))
                .andReturn();


        List<BankPresenter> banks = objectMapper.readValue(
                result.getResponse().getContentAsString(), objectMapper.getTypeFactory().constructCollectionType(List.class, BankPresenter.class));
        Integer bankId = banks.get(0).getId();

        // get payment method
        result = mockMvc
                .perform(get("/svc/paymentMethod/list")
                        .header("Accept-Language", "")
                        .header("X-AUTH-TOKEN", authTokenService.getAuthToken())
                )
                .andExpect(status().is(200))
                .andReturn();

        List<PaymentMethodPresenter> paymentMethodPresenter = objectMapper.readValue(
                result.getResponse().getContentAsString(), objectMapper.getTypeFactory().constructCollectionType(List.class, PaymentMethodPresenter.class));
        Integer paymentMethodId = paymentMethodPresenter.get(0).getId();

        // test it
        MoneySourcePresenter moneySource = new MoneySourcePresenter();
        moneySource.setName("new Name");
        moneySource.setBankId(bankId);
        moneySource.setCardNumber("1234");
        moneySource.setCreditLimit(new BigDecimal(123));
        moneySource.setExpiryDate(FmDateUtils.parseDate("2025-02-02"));
        moneySource.setStartDate(FmDateUtils.parseDate("2019-02-02"));
        moneySource.setTerminated(false);
        moneySource.setPaymentMethodId(paymentMethodId);

        result = mockMvc
                .perform(post("/svc/moneysource")
                        .header("Accept-Language", "")
                        .header("X-AUTH-TOKEN", authTokenService.getAuthToken())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(moneySource))
                )
                .andExpect(status().is(201))
                .andReturn();

        // result example: {"moneySourceId":6}
        Assert.notNull(result.getResponse().getContentAsString());
        JSONObject data = new JSONObject(result.getResponse().getContentAsString());

        Integer moneySourceId = (Integer) data.get("moneySourceId");
        Assert.notNull(moneySourceId);
        Assert.isTrue(moneySourceId > 0);
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

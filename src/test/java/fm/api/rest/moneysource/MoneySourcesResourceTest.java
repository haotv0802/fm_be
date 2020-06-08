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
    public void tetAddMoneySource() throws Exception {
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
    public void tetAddMoneySourceExisting() throws Exception {
        // get money source
        MvcResult result = mockMvc
                .perform(get("/svc/moneysource")
                        .header("Accept-Language", "")
                        .header("X-AUTH-TOKEN", authTokenService.getAuthToken())
                )
                .andExpect(status().is(200))
                .andReturn();

        List<MoneySourcePresenter> moneySources = objectMapper.readValue(
                result.getResponse().getContentAsString(),
                objectMapper.getTypeFactory().constructCollectionType(List.class, MoneySourcePresenter.class));
        MoneySourcePresenter moneySource = moneySources.get(0);

        // test it
        result = mockMvc
                .perform(post("/svc/moneysource")
                        .header("Accept-Language", "")
                        .header("X-AUTH-TOKEN", authTokenService.getAuthToken())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(moneySource))
                )
                .andExpect(status().is(400))
                .andReturn();

        // result example: {"faultCode":"money.source.card.number.existing","faultMessage":"Card number is existing.","incidentId":"2"}
        Assert.notNull(result.getResponse().getContentAsString());
        JSONObject data = new JSONObject(result.getResponse().getContentAsString());

        Assert.isTrue(data.get("faultCode").equals("money.source.card.number.existing"));
        Assert.isTrue(data.get("faultMessage").equals("Card number is existing."));
        Integer incidentId = Integer.parseInt(data.get("incidentId").toString());
        Assert.notNull(incidentId);
        Assert.isTrue(incidentId > 0);
    }

    @Test
    public void testUpdateMoneySource() throws Exception {
        // get money source
        MvcResult result = mockMvc
                .perform(get("/svc/moneysource")
                        .header("Accept-Language", "")
                        .header("X-AUTH-TOKEN", authTokenService.getAuthToken())
                )
                .andExpect(status().is(200))
                .andReturn();

        List<MoneySourcePresenter> moneySources = objectMapper.readValue(
                result.getResponse().getContentAsString(),
                objectMapper.getTypeFactory().constructCollectionType(List.class, MoneySourcePresenter.class));
        MoneySourcePresenter moneySource = moneySources.get(0);
        moneySource.setName("new name of moneysource");

        // test it
        result = mockMvc
                .perform(patch("/svc/moneysource")
                        .header("Accept-Language", "")
                        .header("X-AUTH-TOKEN", authTokenService.getAuthToken())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(moneySource))
                )
                .andExpect(status().is(204))
                .andReturn();
    }

    @Test
    public void tetUpdateMoneySourceExisting() throws Exception {
        // get money source
        MvcResult result = mockMvc
                .perform(get("/svc/moneysource")
                        .header("Accept-Language", "")
                        .header("X-AUTH-TOKEN", authTokenService.getAuthToken())
                )
                .andExpect(status().is(200))
                .andReturn();

        List<MoneySourcePresenter> moneySources = objectMapper.readValue(
                result.getResponse().getContentAsString(),
                objectMapper.getTypeFactory().constructCollectionType(List.class, MoneySourcePresenter.class));
        MoneySourcePresenter moneySource = moneySources.get(0);
        MoneySourcePresenter moneySource2 = moneySources.get(1);
        moneySource.setCardNumber(moneySource2.getCardNumber());

        // test it
        result = mockMvc
                .perform(patch("/svc/moneysource")
                        .header("Accept-Language", "")
                        .header("X-AUTH-TOKEN", authTokenService.getAuthToken())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(moneySource))
                )
                .andExpect(status().is(400))
                .andReturn();

        // result example: {"faultCode":"payment.method.name.existing","faultMessage":"Name of payment method is already existing.","incidentId":"1"}
        Assert.notNull(result.getResponse().getContentAsString());
        JSONObject data = new JSONObject(result.getResponse().getContentAsString());
        Assert.isTrue(data.get("faultCode").equals("money.source.card.number.existing"));
        Assert.isTrue(data.get("faultMessage").equals("Card number is existing."));
        Integer incidentId = Integer.parseInt(data.get("incidentId").toString());
        Assert.notNull(incidentId);
        Assert.isTrue(incidentId > 0);
    }
}

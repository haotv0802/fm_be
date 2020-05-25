package fm.api.rest.banks;

import fm.api.rest.BaseDocumentation;
import fm.api.rest.bank.BankPresenter;
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
public class BanksResourceTest extends BaseDocumentation {

    @Test
    public void testGetBanks() throws Exception {
        MvcResult result = mockMvc
                .perform(get("/svc/bank")
                        .header("Accept-Language", "")
                        .header("X-AUTH-TOKEN", authTokenService.getAuthToken())
                )
                .andExpect(status().is(200))
                .andReturn();


        List<BankPresenter> bankPresenter = objectMapper.readValue(
                result.getResponse().getContentAsString(), objectMapper.getTypeFactory().constructCollectionType(List.class, BankPresenter.class));

        Assert.notNull(bankPresenter);
        Assert.isTrue(bankPresenter.size() > 0);
    }

    @Test
    public void testAddBank() throws Exception {
        BankPresenter bank = new BankPresenter();
        bank.setName("new Name");
        bank.setAddress("new add");
        bank.setLogo("new Logo");

        MvcResult result = mockMvc
                .perform(post("/svc/bank")
                        .header("Accept-Language", "")
                        .header("X-AUTH-TOKEN", authTokenService.getAuthToken())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(bank))
                )
                .andExpect(status().is(201))
                .andReturn();

        // result example: {"paymentMethodId":6}
        Assert.notNull(result.getResponse().getContentAsString());
        JSONObject data = new JSONObject(result.getResponse().getContentAsString());

        Integer bankId = (Integer) data.get("bankId");
        Assert.notNull(bankId);
        Assert.isTrue(bankId > 0);
    }

    @Test
    public void tetAddBankMethodExisting() throws Exception {
        // Get item in the list first
        MvcResult result = mockMvc
                .perform(get("/svc/bank")
                        .header("Accept-Language", "")
                        .header("X-AUTH-TOKEN", authTokenService.getAuthToken())
                )
                .andExpect(status().is(200))
                .andReturn();

        List<BankPresenter> paymentMethodPresenter = objectMapper.readValue(
                result.getResponse().getContentAsString(), objectMapper.getTypeFactory().constructCollectionType(List.class, BankPresenter.class));
        BankPresenter bank = paymentMethodPresenter.get(0);
        bank.setAddress("address");

        // then use payment got above to perform ADD request
        result = mockMvc
                .perform(post("/svc/bank")
                        .header("Accept-Language", "")
                        .header("X-AUTH-TOKEN", authTokenService.getAuthToken())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(bank))
                )
                .andExpect(status().is(400))
                .andReturn();

        // result example: {"faultCode":"bank.name.existing","faultMessage":"bank.name.existing","incidentId":"1"}
        Assert.notNull(result.getResponse().getContentAsString());
        JSONObject data = new JSONObject(result.getResponse().getContentAsString());
        Assert.isTrue(data.get("faultCode").equals("bank.name.existing"));
        Assert.isTrue(data.get("faultMessage").equals("Bank name is existing."));
        Integer incidentId = Integer.parseInt(data.get("incidentId").toString());
        Assert.notNull(incidentId);
        Assert.isTrue(incidentId > 0);
    }

    @Test
    public void tetUpdatePaymentMethod() throws Exception {
        // Get item in the list first
        MvcResult result = mockMvc
                .perform(get("/svc/bank")
                        .header("Accept-Language", "")
                        .header("X-AUTH-TOKEN", authTokenService.getAuthToken())
                )
                .andExpect(status().is(200))
                .andReturn();

        List<BankPresenter> paymentMethodPresenter = objectMapper.readValue(
                result.getResponse().getContentAsString(), objectMapper.getTypeFactory().constructCollectionType(List.class, BankPresenter.class));
        BankPresenter bank = paymentMethodPresenter.get(0);
        bank.setName("name updated");

        // Then update item got above.
        result = mockMvc
                .perform(patch("/svc/paymentMethod")
                        .header("Accept-Language", "")
                        .header("X-AUTH-TOKEN", authTokenService.getAuthToken())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(bank))
                )
                .andExpect(status().is(204))
                .andReturn();
    }

    @Test
    public void tetUpdatePaymentMethodExisting() throws Exception {
        // Get item in the list first
        MvcResult result = mockMvc
                .perform(get("/svc/bank")
                        .header("Accept-Language", "")
                        .header("X-AUTH-TOKEN", authTokenService.getAuthToken())
                )
                .andExpect(status().is(200))
                .andReturn();

        List<BankPresenter> paymentMethodPresenter = objectMapper.readValue(
                result.getResponse().getContentAsString(), objectMapper.getTypeFactory().constructCollectionType(List.class, BankPresenter.class));
        BankPresenter bank = paymentMethodPresenter.get(0);
        BankPresenter bank2 = paymentMethodPresenter.get(1);
        bank.setName(bank2.getName());

        // Then update item got above.
        result = mockMvc
                .perform(patch("/svc/bank")
                        .header("Accept-Language", "")
                        .header("X-AUTH-TOKEN", authTokenService.getAuthToken())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(bank))
                )
                .andExpect(status().is(400))
                .andReturn();

        // result example: {"faultCode":"payment.method.name.existing","faultMessage":"Name of payment method is already existing.","incidentId":"1"}
        Assert.notNull(result.getResponse().getContentAsString());
        JSONObject data = new JSONObject(result.getResponse().getContentAsString());
        Assert.isTrue(data.get("faultCode").equals("bank.name.existing"));
        Assert.isTrue(data.get("faultMessage").equals("Bank name is existing."));
        Integer incidentId = Integer.parseInt(data.get("incidentId").toString());
        Assert.notNull(incidentId);
        Assert.isTrue(incidentId > 0);
    }
}

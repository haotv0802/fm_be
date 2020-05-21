package fm.api.rest.individual;

import fm.api.rest.BaseDocumentation;
import fm.utils.FmDateUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.util.Assert;
import org.testng.annotations.Test;

import java.math.BigDecimal;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by HaoHo on 5/20/2020
 */
public class IndividualResourceTest extends BaseDocumentation {

    private static final Logger logger = LogManager.getLogger(IndividualResourceTest.class);

    @Test
    public void testGetIndividual() throws Exception {
        MvcResult result = mockMvc
                .perform(get("/svc/individual")
                        .header("Accept-Language", "en")
                        .header("X-AUTH-TOKEN", authTokenService.getAuthToken())
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().is(200))
                .andReturn();

        // result example: {"expenseId":21}
        IndividualPresenter individual = objectMapper.readValue(
                result.getResponse().getContentAsString(), IndividualPresenter.class);

        Assert.notNull(individual);
        Assert.isTrue(individual.getEmail().equalsIgnoreCase("hoanhhao@gmail.com"));
    }

    @Test
    @Rollback(false)
    public void testUpdateIndividual() throws Exception {

        IndividualPresenter individual = new IndividualPresenter();
        individual.setEmail("admin@gmail.com");
        individual.setFirstName("admin first");
        individual.setLastName("admin last");
        individual.setMiddleName("admin Middle");
        individual.setBirthday(FmDateUtils.parseDate("1988-04-19"));
        individual.setGender("male");
        individual.setPhoneNumber("0916516697");
        individual.setIncome(new BigDecimal(10000000));

        MvcResult result = mockMvc
                .perform(patch("/svc/individual")
                        .header("Accept-Language", "en")
                        .header("X-AUTH-TOKEN", authToken2Service.getAuthToken())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(individual))
                )
                .andExpect(status().is(201))
                .andReturn();

        // result example: {"individualId":6}
        Assert.notNull(result.getResponse().getContentAsString());
        JSONObject data = new JSONObject(result.getResponse().getContentAsString());

        Integer individualId = (Integer) data.get("individualId");
        Assert.notNull(individualId);
        Assert.isTrue(individualId > 0);
    }

}

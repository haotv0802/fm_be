package fm.api.rest.moneyflow;

import fm.api.constants.Types;
import fm.api.rest.BaseDocumentation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;
import org.springframework.http.MediaType;
import org.springframework.restdocs.mockmvc.RestDocumentationResultHandler;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.util.Assert;
import org.testng.annotations.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessResponse;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by haoho on 4/25/20.
 */
public class MoneyFlowResourceTest extends BaseDocumentation {

    private static final Logger logger = LogManager.getLogger(MoneyFlowResourceTest.class);

    /**
     * Normal case
     *
     * @throws Exception
     */
    @Test
    public void testGetExpenses() throws Exception {

        // Firstly, add expense into DB
        Item creation = new Item();
        creation.setName("new name");
        creation.setAmount(new BigDecimal(1234));
        creation.setDate(new Date());

        MvcResult result = mockMvc
                .perform(post("/svc/moneyflow")
                        .header("Accept-Language", "en")
                        .header("X-AUTH-TOKEN", authTokenService.getAuthToken())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(creation))
                )
                .andExpect(status().is(201))
                .andReturn();

        // result example: {"expenseId":21}
        Assert.notNull(result.getResponse().getContentAsString());
        JSONObject data = new JSONObject(result.getResponse().getContentAsString());

        Integer expenseId = (Integer) data.get("expenseId");
        Assert.notNull(expenseId);
        Assert.isTrue(expenseId > 0);

        // data has been added, now testing main service.
        RestDocumentationResultHandler restDocs = document(
                SNIPPET_NAME_PATTERN
                , preprocessResponse(prettyPrint())
                , getRequestHeaderByAuthentication()
                , responseFields(
                        getResponseFieldsAttributes(),
                        fieldWithPath("expenses[].id").description(getMessage("Id")).type(Types.LONG),
                        fieldWithPath("expenses[].userId").description(getMessage("User Id")).type(Types.LONG),
                        fieldWithPath("expenses[].amount").description(getMessage("Amount")).type(Types.BIG_DECIMAL),
                        fieldWithPath("expenses[].date").description(getMessage("Date")).type(Types.DATE),
                        fieldWithPath("expenses[].name").description(getMessage("Name")).type(Types.STRING),
                        fieldWithPath("expenses[].moneySourceId").description(getMessage("Money source Id")).type(Types.LONG),
                        fieldWithPath("expenses[].moneySourceName").description(getMessage("Money source Name")).type(Types.STRING),
                        fieldWithPath("expenses[].paymentMethod").description(getMessage("Payment method")).type(Types.STRING),
                        fieldWithPath("expenses[].cardNumber").description(getMessage("Card number")).type(Types.STRING),
                        fieldWithPath("expenses[].cardInfo").description(getMessage("Card info")).type(Types.STRING),
                        fieldWithPath("expenses[].updated").description(getMessage("Updated date")).type(Types.DATE),
                        fieldWithPath("expenses[].spending").description(getMessage("Is spent")).type(Types.BOOLEAN),
                        fieldWithPath("total").description(getMessage("Total")).type(Types.BIG_DECIMAL),
                        fieldWithPath("month").description(getMessage("Month")).type(Types.INTEGER),
                        fieldWithPath("year").description(getMessage("Year")).type(Types.INTEGER)
                )
        );

        result = mockMvc
                .perform(get("/svc/moneyflow")
                        .header("Accept-Language", "en")
                        .header("X-AUTH-TOKEN", authTokenService.getAuthToken())
                )
                .andExpect(status().is(200))
                .andDo(restDocs)
                .andReturn();

        ItemDetailsPresenter expensesDetailsPresenter = objectMapper.readValue(
                result.getResponse().getContentAsString(), ItemDetailsPresenter.class);

        Assert.notNull(expensesDetailsPresenter);
    }

    /**
     * Normal case
     *
     * @throws Exception
     */
    @Test
    public void testAddExpense() throws Exception {
        Item creation = new Item();
        creation.setName("new name");
        creation.setAmount(new BigDecimal(1234));
        creation.setDate(new Date());

        MvcResult result = mockMvc
                .perform(post("/svc/moneyflow")
                        .header("Accept-Language", "en")
                        .header("X-AUTH-TOKEN", authTokenService.getAuthToken())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(creation))
                )
                .andExpect(status().is(201))
                .andReturn();

        // result example: {"expenseId":21}
        Assert.notNull(result.getResponse().getContentAsString());
        JSONObject data = new JSONObject(result.getResponse().getContentAsString());

        Integer expenseId = (Integer) data.get("expenseId");

        Assert.notNull(expenseId);
        Assert.isTrue(expenseId > 0);
    }

    /**
     * Test Add without Amount input.
     *
     * @throws Exception
     * @deprecated Since, bean validation applied, so Unit Test can NOT catch HTTP code 400 returned in ResponseEntity, yet Server itself.
     */
    @Test
    public void testAddExpenseWithoutAmount() throws Exception {
//    Item creation = new Item();
//    creation.setName("new name");
//    creation.setDate(new Date());
//
//    MvcResult result =  mockMvc
//        .perform(post("/svc/moneyflow")
//            .header("Accept-Language", "en")
//            .header("X-AUTH-TOKEN", authTokenService.getAuthToken())
//            .contentType(MediaType.APPLICATION_JSON)
//            .content(objectMapper.writeValueAsString(creation))
//        )
//        .andExpect(status().is(400))
//        .andReturn()
//        ;
//
//    // result example: {"faultCode":"moneyflow.add.amount.notnull","faultMessage":"Amount should not be null.","incidentId":"4"}
//    Assert.notNull(result.getResponse().getContentAsString());
//    JSONObject data = new JSONObject(result.getResponse().getContentAsString());
//    Assert.isTrue(data.get("faultCode").equals("moneyflow.add.amount.notnull"));
//    Assert.isTrue(data.get("faultMessage").equals("Amount should not be null."));
//    Integer incidentId = Integer.parseInt(data.get("incidentId").toString());
//    Assert.notNull(incidentId);
//    Assert.isTrue(incidentId > 0);
    }

    @Test
//    @Rollback(false)
    public void testUpdateExpense() throws Exception {
        // Firstly, add expense into DB
        Item creation = new Item();
        creation.setName("new name");
        creation.setAmount(new BigDecimal(1234));
        creation.setDate(new Date());

        MvcResult result = mockMvc
                .perform(post("/svc/moneyflow")
                        .header("Accept-Language", "en")
                        .header("X-AUTH-TOKEN", authTokenService.getAuthToken())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(creation))
                )
                .andExpect(status().is(201))
                .andReturn();

        // result example: {"expenseId":21}
        Assert.notNull(result.getResponse().getContentAsString());
        JSONObject data = new JSONObject(result.getResponse().getContentAsString());

        Integer expenseId = (Integer) data.get("expenseId");
        Assert.notNull(expenseId);
        Assert.isTrue(expenseId > 0);

        // data has been added, we can take existing id for testing update service.
        result = mockMvc
                .perform(get("/svc/moneyflow")
                        .header("Accept-Language", "en")
                        .header("X-AUTH-TOKEN", authTokenService.getAuthToken())
                )
                .andExpect(status().is(200))
                .andReturn();

        ItemDetailsPresenter expensesDetailsPresenter = objectMapper.readValue(
                result.getResponse().getContentAsString(), ItemDetailsPresenter.class);

        Assert.notNull(expensesDetailsPresenter);

        List<ItemPresenter> items = expensesDetailsPresenter.getExpenses();
        ItemPresenter item = items.get(0);
        item.setUpdated(true);
        item.setId(expenseId);
        item.setName("just updated");

        result = mockMvc
                .perform(patch("/svc/moneyflow/list")
                        .header("Accept-Language", "en")
                        .header("X-AUTH-TOKEN", authTokenService.getAuthToken())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(items))
                )
                .andExpect(status().is(204))
                .andReturn()
        ;
    }

//    @Test // since amount is no valid so the service can not be proceeded.
    @Deprecated
    public void testUpdateExpenseWithWrongValues() throws Exception {

        MvcResult result = mockMvc
                .perform(get("/svc/moneyflow")
                        .header("Accept-Language", "en")
                        .header("X-AUTH-TOKEN", authTokenService.getAuthToken())
                )
                .andExpect(status().is(200))
                .andReturn();

        ItemDetailsPresenter expensesDetailsPresenter = objectMapper.readValue(
                result.getResponse().getContentAsString(), ItemDetailsPresenter.class);

        Assert.notNull(expensesDetailsPresenter);

        List<ItemPresenter> items = expensesDetailsPresenter.getExpenses();
        ItemPresenter item = items.get(0);
        item.setAmount(null);

        result = mockMvc
                .perform(patch("/svc/moneyflow/list")
                        .header("Accept-Language", "en")
                        .header("X-AUTH-TOKEN", authTokenService.getAuthToken())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(items))
                )
                .andExpect(status().is(400))
                .andReturn()
        ;

        // result example: {"faultCode":"moneyflow.edit.wrong.input","faultMessage":"[amount] property gets error, message: Amount cannot be null","incidentId":"30"}
        Assert.notNull(result.getResponse().getContentAsString());
        JSONObject data = new JSONObject(result.getResponse().getContentAsString());
        Assert.isTrue(data.get("faultCode").equals("moneyflow.edit.wrong.input"));
        Assert.isTrue(data.get("faultMessage").equals("[amount] property gets error, message: Amount cannot be null."));
        Integer incidentId = Integer.parseInt(data.get("incidentId").toString());
        Assert.notNull(incidentId);
        Assert.isTrue(incidentId > 0);
    }
}

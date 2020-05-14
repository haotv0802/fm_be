package fm.api.rest.moneyflow;

import fm.api.constants.Types;
import fm.api.rest.BaseDocumentation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;
import org.springframework.http.MediaType;
import org.springframework.restdocs.mockmvc.RestDocumentationResultHandler;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.util.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.headers.HeaderDocumentation.headerWithName;
import static org.springframework.restdocs.headers.HeaderDocumentation.responseHeaders;
import static org.springframework.restdocs.headers.HeaderDocumentation.requestHeaders;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessRequest;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessResponse;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by haoho on 4/25/20.
 */
public class MoneyFlowResourceTest extends BaseDocumentation {
    private final Logger LOGGER = LogManager.getLogger(getClass());

    /**
     * Normal case
     *
     * @throws Exception
     */
    @Test
    public void testGetExpenses() throws Exception {
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

        MvcResult result = mockMvc
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
    public void testUpdateExpense() throws Exception {

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
        items.get(0).setUpdated(true);

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

    @Test
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

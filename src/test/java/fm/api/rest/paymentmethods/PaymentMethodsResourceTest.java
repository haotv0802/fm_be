package fm.api.rest.paymentmethods;

import fm.api.rest.BaseDocumentation;
import fm.api.rest.paymentmethods.beans.PaymentMethodPresenter;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.util.Assert;
import org.testng.annotations.Test;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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
}

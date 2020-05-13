//package fm.api.rest.paymentmethods;
//
//import fm.api.rest.BaseDocumentation;
//import org.testng.annotations.Test;
//
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
///**
// * Created by haho on 6/26/2017.
// */
//public class PaymentMethodsResourceTest extends BaseDocumentation {
//
//  @Test
//  public void testGetCardsInformation() throws Exception {
//    mockMvc
//        .perform(get("/svc/paymentMethods")
//            .header("Accept-Language", "")
//            .header("X-AUTH-TOKEN", authTokenService.getAuthToken())
//        )
//        .andExpect(status().is(200))
//    ;
//  }
//}

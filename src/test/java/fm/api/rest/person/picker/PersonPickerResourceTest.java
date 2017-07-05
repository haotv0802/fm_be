package fm.api.rest.person.picker;

import fm.api.rest.BaseDocumentation;
import org.testng.annotations.Test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by haho on 7/5/2017.
 */
public class PersonPickerResourceTest extends BaseDocumentation {

  @Test
  public void testGetPersonsList() throws Exception {
    mockMvc
        .perform(get("/svc/personPicker")
            .header("Accept-Language", "en")
            .header("X-AUTH-TOKEN", authTokenService.getAuthToken())
        )
        .andExpect(status().is(200))
    ;
  }
}

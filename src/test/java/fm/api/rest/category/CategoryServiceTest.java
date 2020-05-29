package fm.api.rest.category;

import fm.api.rest.BaseDocumentation;
import org.junit.Test;
import org.springframework.http.MediaType;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by Quy on 5/26/2020.
 */
public class CategoryServiceTest extends BaseDocumentation {
  @Test
  public void getAllCate() throws Exception {
    mockMvc
        .perform(get("/svc//category/list/all")
            .header("Accept-Language", "en")
            .header("X-AUTH-TOKEN", authTokenService.getAuthToken())
            .contentType(MediaType.APPLICATION_JSON)
        )
        .andExpect(status().is(200))
    ;
  }
}

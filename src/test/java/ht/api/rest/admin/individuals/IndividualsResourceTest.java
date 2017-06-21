package ht.api.rest.admin.individuals;

import ht.api.rest.BaseAdminDocumentation;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.testng.annotations.Test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by haho on 04/03/2017.
 */
public class IndividualsResourceTest extends BaseAdminDocumentation {

  @Test
  public void testGetIndividuals() throws Exception {
    mockMvc
        .perform(get("/svc/admin/individuals")
            .header("Accept-Language", "en")
            .header("X-AUTH-TOKEN", authTokenService.getAuthToken())
        )
        .andExpect(status().is(200))
    ;
  }

  @Test
  public void testGetIndividualsCount() throws Exception {
    mockMvc
        .perform(get("/svc/admin/individuals/count")
            .header("Accept-Language", "en")
            .header("X-AUTH-TOKEN", authTokenService.getAuthToken())
        )
        .andExpect(status().is(200))
    ;
  }

  @Test
  public void testGetIndividualsWithPaging() throws Exception {
    mockMvc
        .perform(get("/svc/admin/individuals/paging")
            .header("Accept-Language", "en")
            .header("X-AUTH-TOKEN", authTokenService.getAuthToken())
            .param("page", "5")
            .param("size", "5")
//            .param("sort", "first_name,desc")
        )
        .andExpect(status().is(200))
    ;
  }

  @Test
  public void testIsUserNameExisting() throws Exception {
    mockMvc
        .perform(get("/svc/admin/individuals/isUserNameExisting/haho")
            .header("Accept-Language", "en")
            .header("X-AUTH-TOKEN", authTokenService.getAuthToken())
        )
        .andExpect(status().is(200))
    ;
  }

  @Test
  public void testIsUserNameExisting2() throws Exception {
    mockMvc
        .perform(get("/svc/admin/individuals/isUserNameExisting")
            .header("Accept-Language", "en")
            .header("X-AUTH-TOKEN", authTokenService.getAuthToken())
            .param("oldUserName", "admin")
            .param("userName", "admin1")
        )
        .andExpect(status().is(200))
        .andExpect(MockMvcResultMatchers.jsonPath("$.isUserNameExisting").value(true));
    ;
  }
}
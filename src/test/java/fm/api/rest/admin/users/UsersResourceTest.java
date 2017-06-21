package fm.api.rest.admin.users;

import com.fasterxml.jackson.databind.type.TypeFactory;
import fm.api.rest.BaseAdminDocumentation;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.testng.annotations.Test;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by haho on 3/22/2017.
 */
public class UsersResourceTest extends BaseAdminDocumentation {

  @Test
  public void testGetUsers() throws Exception {
    mockMvc
        .perform(get("/svc/admin/users")
            .header("Accept-Language", "en")
            .header("X-AUTH-TOKEN", authTokenService.getAuthToken())
        )
        .andExpect(status().is(200))
    ;
  }

  @Test
  public void testUpdateUsersRoles() throws Exception {
    MvcResult result = mockMvc
        .perform(get("/svc/admin/users")
            .header("Accept-Language", "en")
            .header("X-AUTH-TOKEN", authTokenService.getAuthToken())
        )
        .andExpect(status().is(200))
        .andReturn();

    String content = result.getResponse().getContentAsString();
    List<UserBean> users =
        objectMapper.readValue(
            content,
            TypeFactory.defaultInstance().constructCollectionType(List.class, UserBean.class)
          )
        ;
    users.get(3).setRoleId("4");

    mockMvc
        .perform(patch("/svc/admin/users/usersRolesUpdate")
            .header("Accept-Language", "en")
            .header("X-AUTH-TOKEN", authTokenService.getAuthToken())
            .contentType(MediaType.APPLICATION_JSON_UTF8)
            .content(objectMapper.writeValueAsString(users))
        )
        .andExpect(status().is(200))
    ;
  }
}

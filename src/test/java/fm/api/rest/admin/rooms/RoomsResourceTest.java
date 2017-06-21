package fm.api.rest.admin.rooms;

import com.fasterxml.jackson.databind.type.TypeFactory;
import fm.api.rest.BaseAdminDocumentation;
import fm.api.rest.admin.rooms.beans.RoomTypeBean;
import io.jsonwebtoken.lang.Assert;
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
public class RoomsResourceTest extends BaseAdminDocumentation {

  @Test
  public void testGetRoomTypes() throws Exception {
    mockMvc
        .perform(get("/svc/admin/rooms/roomTypes")
            .header("Accept-Language", "en")
            .header("X-AUTH-TOKEN", authTokenService.getAuthToken())
        )
        .andExpect(status().is(200))
    ;
  }

  @Test
  public void testUpdateRoomTypes() throws Exception {
    MvcResult result = mockMvc
        .perform(get("/svc/admin/rooms/roomTypes")
            .header("Accept-Language", "en")
            .header("X-AUTH-TOKEN", authTokenService.getAuthToken())
        )
        .andExpect(status().is(200))
        .andReturn()
    ;

    String resultAsString = result.getResponse().getContentAsString();

//    JSONArray array = new JSONArray(resultAsString);
    List<RoomTypeBean> roomTypes =
        this.objectMapper.readValue(
            resultAsString,
            TypeFactory.defaultInstance().constructCollectionType(List.class, RoomTypeBean.class)
        );

    RoomTypeBean roomType = roomTypes.get(0);

    roomType.setNumOfPeople(11);

    mockMvc
        .perform(patch("/svc/admin/rooms/roomTypes/update")
            .header("Accept-Language", "en")
            .header("X-AUTH-TOKEN", authTokenService.getAuthToken())
            .contentType(MediaType.APPLICATION_JSON)
            .content(this.objectMapper.writeValueAsString(roomType))
        )
        .andExpect(status().is(200))
    ;

    // check again to make sure data is updated
    result = mockMvc
        .perform(get("/svc/admin/rooms/roomTypes")
            .header("Accept-Language", "en")
            .header("X-AUTH-TOKEN", authTokenService.getAuthToken())
        )
        .andExpect(status().is(200))
        .andReturn()
    ;
    roomTypes =
        this.objectMapper.readValue(
            result.getResponse().getContentAsString(),
            TypeFactory.defaultInstance().constructCollectionType(List.class, RoomTypeBean.class)
        );
    roomType = roomTypes.get(0);
    Assert.notNull(roomType);
    Assert.isTrue(roomType.getNumOfPeople() == 11);
  }
}

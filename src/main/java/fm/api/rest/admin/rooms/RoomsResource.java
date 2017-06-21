package fm.api.rest.admin.rooms;

import fm.api.rest.admin.BaseAdminResource;
import fm.api.rest.admin.rooms.beans.RoomTypeBean;
import fm.api.rest.admin.rooms.interfaces.IRoomService;
import fm.auth.UserDetailsImpl;
import fm.common.beans.HeaderLang;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by haho on 3/22/2017.
 */
@RestController
public class RoomsResource extends BaseAdminResource {

  private final IRoomService roomService;

  @Autowired
  public RoomsResource(@Qualifier("adminRoomService") IRoomService roomService) {
    Assert.notNull(roomService);
    this.roomService = roomService;
  }

  @GetMapping("/rooms/roomTypes")
  @PreAuthorize("hasAuthority('ADMIN')")
  public List<RoomTypeBean> getRoomTypes(
      @AuthenticationPrincipal UserDetailsImpl userDetails,
      @HeaderLang String lang
  ) {
    return roomService.getRoomTypes();
  }

  @PatchMapping("/rooms/roomTypes/update")
  @PreAuthorize("hasAuthority('ADMIN')")
  public void updateRoomType(
      @AuthenticationPrincipal UserDetailsImpl userDetails,
      @HeaderLang String lang,
      @RequestBody RoomTypeBean bean
  ) {
    this.roomService.updateRoomType(bean);
  }
}

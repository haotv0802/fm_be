package fm.api.rest.rooms.interfaces;

import fm.api.rest.rooms.beans.RoomTypeBean;

import java.util.List;

/**
 * Created by haho on 3/23/2017.
 */
public interface IRoomService {
  List<RoomTypeBean> getRoomTypes();

  void updateRoomType(RoomTypeBean bean);
}

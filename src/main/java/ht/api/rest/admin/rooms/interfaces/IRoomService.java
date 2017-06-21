package ht.api.rest.admin.rooms.interfaces;

import ht.api.rest.admin.rooms.beans.RoomTypeBean;

import java.util.List;

/**
 * Created by haho on 3/23/2017.
 */
public interface IRoomService {
  List<RoomTypeBean> getRoomTypes();

  void updateRoomType(RoomTypeBean bean);
}

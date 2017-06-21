package fm.api.rest.htBK.rooms.interfaces;

import fm.api.rest.htBK.rooms.beans.RoomTypeBean;

import java.util.List;

/**
 * Created by haho on 3/23/2017.
 */
public interface IRoomDao {
  List<RoomTypeBean> getRoomTypes();

  void updateRoomType(RoomTypeBean bean);
}

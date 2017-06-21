package fm.api.rest.htBK.rooms;

import fm.api.rest.htBK.rooms.beans.RoomTypeBean;
import fm.api.rest.htBK.rooms.interfaces.IRoomDao;
import fm.api.rest.htBK.rooms.interfaces.IRoomService;
import io.jsonwebtoken.lang.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by haho on 3/22/2017.
 */
@Service("adminRoomService")
public class RoomService implements IRoomService {

  private final IRoomDao roomDao;

  @Autowired
  public RoomService(@Qualifier("adminRoomDao") IRoomDao roomDao) {
    Assert.notNull(roomDao);
    this.roomDao = roomDao;
  }

  @Override
  public List<RoomTypeBean> getRoomTypes() {
    return roomDao.getRoomTypes();
  }

  @Override
  public void updateRoomType(RoomTypeBean bean) {
    this.roomDao.updateRoomType(bean);
  }
}

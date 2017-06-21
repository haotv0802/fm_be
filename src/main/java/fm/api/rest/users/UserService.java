package fm.api.rest.users;

import fm.api.rest.users.intefaces.IUserDao;
import fm.api.rest.users.intefaces.IUserService;
import io.jsonwebtoken.lang.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by haho on 3/22/2017.
 */
@Service("userService")
public class UserService implements IUserService {

  private final IUserDao userDao;

  @Autowired
  public UserService(@Qualifier("userDao") IUserDao userDao) {
    Assert.notNull(userDao);
    this.userDao = userDao;
  }

  @Override
  public List<UserBean> getUsers() {
    return userDao.getUsers();
  }

  @Override
  public void updateUsersRoles(List<UserBean> users) {
    for (UserBean user: users) {
      this.userDao.updateUserRole(user);
    }
  }
}

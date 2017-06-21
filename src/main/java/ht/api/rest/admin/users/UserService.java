package ht.api.rest.admin.users;

import ht.api.rest.admin.users.intefaces.IUserDao;
import ht.api.rest.admin.users.intefaces.IUserService;
import io.jsonwebtoken.lang.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by haho on 3/22/2017.
 */
@Service("adminUserService")
public class UserService implements IUserService {

  private final IUserDao userDao;

  @Autowired
  public UserService(@Qualifier("adminUserDao") IUserDao userDao) {
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

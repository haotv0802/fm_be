package fm.api.rest.users.intefaces;

import fm.api.rest.users.UserBean;

import java.util.List;

/**
 * Created by haho on 3/22/2017.
 */
public interface IUserDao {
  List<UserBean> getUsers();

  void updateUserRole(UserBean user);
}

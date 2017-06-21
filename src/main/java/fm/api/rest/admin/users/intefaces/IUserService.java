package fm.api.rest.admin.users.intefaces;

import fm.api.rest.admin.users.UserBean;

import java.util.List;

/**
 * Created by haho on 3/22/2017.
 */
public interface IUserService {
  List<UserBean> getUsers();
  void updateUsersRoles(List<UserBean> users);
}

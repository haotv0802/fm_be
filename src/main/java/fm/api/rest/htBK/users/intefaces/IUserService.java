package fm.api.rest.htBK.users.intefaces;

import fm.api.rest.htBK.users.UserBean;

import java.util.List;

/**
 * Created by haho on 3/22/2017.
 */
public interface IUserService {
  List<UserBean> getUsers();
  void updateUsersRoles(List<UserBean> users);
}

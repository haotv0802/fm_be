package ht.api.rest.admin.users;

import ht.api.rest.admin.BaseAdminResource;
import ht.api.rest.admin.users.intefaces.IUserService;
import ht.auth.UserDetailsImpl;
import ht.common.beans.HeaderLang;
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
public class UsersResource extends BaseAdminResource {

  private final IUserService userService;

  @Autowired
  public UsersResource(@Qualifier("adminUserService") IUserService userService) {
    Assert.notNull(userService);
    this.userService = userService;
  }

  @GetMapping("/users")
  @PreAuthorize("hasAuthority('ADMIN')")
  public List<UserBean> getUsers(
      @AuthenticationPrincipal UserDetailsImpl userDetails,
      @HeaderLang String lang) {
    return userService.getUsers();
  }

  @PatchMapping("/users/usersRolesUpdate")
  @PreAuthorize("hasAuthority('ADMIN')")
  public void updateUsersRoles(
      @AuthenticationPrincipal UserDetailsImpl userDetails,
      @HeaderLang String lang,
      @RequestBody List<UserBean> userBeans
  ) {
    this.userService.updateUsersRoles(userBeans);
  }
}

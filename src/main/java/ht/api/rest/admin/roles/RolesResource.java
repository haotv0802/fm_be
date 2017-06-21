package ht.api.rest.admin.roles;

import ht.api.rest.admin.BaseAdminResource;
import ht.api.rest.admin.roles.interfaces.IRoleService;
import ht.auth.UserDetailsImpl;
import ht.common.beans.HeaderLang;
import org.apache.logging.log4j.core.util.KeyValuePair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by haho on 3/22/2017.
 */
@RestController
public class RolesResource extends BaseAdminResource {

  private final IRoleService roleService;

  @Autowired
  public RolesResource(@Qualifier("adminRoleService") IRoleService roleService) {
    Assert.notNull(roleService);
    this.roleService = roleService;
  }

  @GetMapping("/roles")
  @PreAuthorize("hasAuthority('ADMIN')")
  public List<String> getRoles(
      @AuthenticationPrincipal UserDetailsImpl userDetails
      ,@HeaderLang String lang) {
    return this.roleService.getRoles();
  }

  @GetMapping("/roles/keyValuePair")
  @PreAuthorize("hasAuthority('ADMIN')")
  public List<KeyValuePair> getRolesInfo(
      @AuthenticationPrincipal UserDetailsImpl userDetails
      ,@HeaderLang String lang) {
    return this.roleService.getRolesInfo();
  }
}

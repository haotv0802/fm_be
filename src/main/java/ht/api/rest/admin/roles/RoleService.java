package ht.api.rest.admin.roles;

import ht.api.rest.admin.roles.interfaces.IRoleDao;
import ht.api.rest.admin.roles.interfaces.IRoleService;
import io.jsonwebtoken.lang.Assert;
import org.apache.logging.log4j.core.util.KeyValuePair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by haho on 3/22/2017.
 */
@Service("adminRoleService")
public class RoleService implements IRoleService {

  private final IRoleDao roleDao;

  @Autowired
  public RoleService(@Qualifier("adminRoleDao") IRoleDao roleDao) {
    Assert.notNull(roleDao);
    this.roleDao = roleDao;
  }

  @Override
  public List<String> getRoles() {
    return this.roleDao.getRoles();
  }

  @Override
  public List<KeyValuePair> getRolesInfo() {
    return this.roleDao.getRolesInfo();
  }
}

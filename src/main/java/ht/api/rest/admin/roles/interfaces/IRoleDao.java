package ht.api.rest.admin.roles.interfaces;

import org.apache.logging.log4j.core.util.KeyValuePair;

import java.util.List;

/**
 * Created by haho on 4/5/2017.
 */
public interface IRoleDao {
  List<String> getRoles();

  List<KeyValuePair> getRolesInfo();
}

package fm.api.rest.admin.roles.interfaces;

import org.apache.logging.log4j.core.util.KeyValuePair;

import java.util.List;

/**
 * Created by haho on 4/5/2017.
 */
public interface IRoleService {
  List<String> getRoles();

  List<KeyValuePair> getRolesInfo();
}

package ht.api.rest.staff;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by haho on 3/3/2017.
 */
@RequestMapping(path = "/svc/staff")
public class BaseStaffResource {
  protected final Logger LOGGER = LogManager.getLogger(this.getClass());
}

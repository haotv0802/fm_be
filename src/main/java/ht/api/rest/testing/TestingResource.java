package ht.api.rest.testing;

import ht.api.rest.staff.BaseStaffResource;
import ht.auth.UserDetailsImpl;
import ht.common.beans.HeaderLang;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by haho on 3/13/2017.
 */
@RestController
public class TestingResource extends BaseStaffResource {

  @Autowired
  private TestingService testingService;

  @GetMapping("/testing")
  @PreAuthorize("hasAuthority('ADMIN')")
  public List<AuthorityBean> getAuthorities(
      @AuthenticationPrincipal UserDetailsImpl userDetails
      , @HeaderLang String lang) {
    this.LOGGER.info("Language: " + lang);
    this.LOGGER.info("Username: " + userDetails.getUsername());
    return testingService.getAuthorities();
  }
}

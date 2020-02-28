package fm.api.rest.individual;

import fm.api.rest.BaseResource;
import fm.api.rest.individual.interfaces.IIndividualService;
import fm.auth.UserDetailsImpl;
import fm.common.beans.HeaderLang;
import io.jsonwebtoken.lang.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by haoho on 2/28/20 13:46.
 */
@RestController
public class IndividualResource extends BaseResource {

  private IIndividualService individualService;

  @Autowired
  public IndividualResource(
      @Qualifier("individualService") IIndividualService individualService
  ) {
    Assert.notNull(individualService);

    this.individualService = individualService;
  }

  @GetMapping("/individual")
  @PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
  public IndividualPresenter getIndividual(
      @AuthenticationPrincipal UserDetailsImpl userDetails,
      @HeaderLang String lang
  ) {
    return this.individualService.getIndividual(userDetails.getUserId());
  }
}

package fm.api.rest.admin.individuals;

import fm.api.rest.admin.BaseAdminResource;
import fm.api.rest.admin.individuals.interfaces.IIndividualService;
import fm.auth.UserDetailsImpl;
import fm.common.beans.HeaderLang;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by haho on 3/22/2017.
 */
@RestController
public class IndividualsResource extends BaseAdminResource {

  private final IIndividualService individualService;

  @Autowired
  public IndividualsResource(@Qualifier("adminIndividualService") IIndividualService individualService) {
    Assert.notNull(individualService);
    this.individualService = individualService;
  }

  @GetMapping("/individuals")
  @PreAuthorize("hasAuthority('ADMIN')")
  public List<IndividualPresenter> getIndividuals(
      @AuthenticationPrincipal UserDetailsImpl userDetails,
      @HeaderLang String lang) {
    return this.individualService.getIndividuals();
  }

  @GetMapping("/individuals/count")
  @PreAuthorize("hasAuthority('ADMIN')")
  public ResponseEntity getIndividualsCount(
      @AuthenticationPrincipal UserDetailsImpl userDetails,
      @HeaderLang String lang) {
    int count = this.individualService.getNumberOfIndividuals();
    return new ResponseEntity(new Object() {
      public final Integer individualsCount = count;
    }, HttpStatus.OK);
  }

  @GetMapping("/individuals/paging")
  @PreAuthorize("hasAuthority('ADMIN')")
  public Slice<IndividualPresenter> getIndividualsWithPagination(
      @AuthenticationPrincipal UserDetailsImpl userDetails,
      @HeaderLang String lang,
      Pageable pageable
  ) {
    return this.individualService.getIndividuals(pageable);
  }

  @GetMapping("/individuals/isUserNameExisting/{username}")
  @PreAuthorize("hasAuthority('ADMIN')")
  public ResponseEntity isUserNameExisting(
      @AuthenticationPrincipal UserDetailsImpl userDetails,
      @HeaderLang String lang,
      @PathVariable(value = "username") String username
  ) {
    boolean value = this.individualService.isUserNameExisting(username);
    return new ResponseEntity(new Object() {
      public final Boolean isUserNameExisting = value;
    }, HttpStatus.OK);
  }

  @GetMapping("/individuals/isUserNameExisting")
  @PreAuthorize("hasAuthority('ADMIN')")
  public ResponseEntity isUserNameExisting(
      @AuthenticationPrincipal UserDetailsImpl userDetails,
      @HeaderLang String lang,
      @RequestParam(value = "oldUserName") String oldUserName,
      @RequestParam(value = "userName") String userName
  ) {
    if (!this.individualService.isUserNameExisting(oldUserName)) {
      return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }
    final boolean value = this.individualService.isUserNameExisting(oldUserName, userName);
    return new ResponseEntity(new Object() {
      public final Boolean isUserNameExisting = value;
    }, HttpStatus.OK);
  }
}
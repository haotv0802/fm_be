package fm.api.rest.personpicker;

import fm.api.rest.BaseResource;
import fm.api.rest.personpicker.beans.PersonPresenter;
import fm.api.rest.personpicker.interfaces.IPersonPickerService;
import fm.auth.UserDetailsImpl;
import fm.common.beans.HeaderLang;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by haho on 7/5/2017.
 */
@RestController
public class PersonPickerResource extends BaseResource {

  private final IPersonPickerService personPickerService;

  @Autowired
  public PersonPickerResource(@Qualifier("personPickerService") IPersonPickerService personPickerService) {
    Assert.notNull(personPickerService);

    this.personPickerService = personPickerService;
  }

  @GetMapping("/personPicker")
  @PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
  public List<PersonPresenter> getPersonsList(
      @AuthenticationPrincipal UserDetailsImpl userDetails,
      @HeaderLang String lang) {
    return personPickerService.getPersonsList(userDetails.getUserId());
  }

}

package fm.api.rest.person.picker;

import fm.api.rest.person.picker.beans.PersonPresenter;
import fm.api.rest.person.picker.interfaces.IPersonPickerDao;
import fm.api.rest.person.picker.interfaces.IPersonPickerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;

/**
 * Created by haho on 7/5/2017.
 */
@Service("personPickerService")
public class PersonPickerService implements IPersonPickerService {

  private final IPersonPickerDao personPickerDao;

  @Autowired
  public PersonPickerService(@Qualifier("personPickerDao") IPersonPickerDao personPickerDao) {
    Assert.notNull(personPickerDao);

    this.personPickerDao = personPickerDao;
  }

  @Override
  public List<PersonPresenter> getPersonsList(int userId) {
    return personPickerDao.getPersonsList(userId);
  }
}

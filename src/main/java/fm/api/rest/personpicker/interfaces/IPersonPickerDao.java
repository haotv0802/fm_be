package fm.api.rest.personpicker.interfaces;

import fm.api.rest.personpicker.beans.PersonPresenter;

import java.util.List;

/**
 * Created by haho on 7/5/2017.
 */
public interface IPersonPickerDao {
  List<PersonPresenter> getPersonsList(int userId);
}

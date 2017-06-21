package fm.api.rest.htBK.individuals.interfaces;

import fm.api.rest.htBK.individuals.IndividualPresenter;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

import java.util.List;

/**
 * Created by haho on 4/3/2017.
 */
public interface IIndividualService {
  List<IndividualPresenter> getIndividuals();

  Slice<IndividualPresenter> getIndividuals(Pageable pageable);

  Integer getNumberOfIndividuals();

  Boolean isUserNameExisting(String username);

  Boolean isUserNameExisting(String oldUserName, String userName);
}

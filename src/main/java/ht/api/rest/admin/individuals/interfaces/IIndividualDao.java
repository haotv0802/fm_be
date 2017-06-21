package ht.api.rest.admin.individuals.interfaces;

import ht.api.rest.admin.individuals.IndividualModel;
import ht.api.rest.admin.individuals.IndividualPresenter;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

import java.util.List;

/**
 * Created by haho on 4/3/2017.
 */
public interface IIndividualDao {
  List<IndividualModel> getIndividuals();

  Slice<IndividualPresenter> getIndividuals(Pageable pageable);

  Integer getNumberOfIndividuals();

  Boolean isUserNameExisting(String username);

  Boolean isUserNameExisting(String oldUserName, String userName);
}

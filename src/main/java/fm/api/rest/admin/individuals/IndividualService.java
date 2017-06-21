package fm.api.rest.admin.individuals;

import fm.api.rest.admin.individuals.interfaces.IIndividualDao;
import fm.api.rest.admin.individuals.interfaces.IIndividualService;
import io.jsonwebtoken.lang.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by haho on 3/22/2017.
 */
@Service("adminIndividualService")
public class IndividualService implements IIndividualService {

  private final IIndividualDao individualDao;

  @Autowired
  public IndividualService(@Qualifier("adminIndividualDao") IIndividualDao individualDao) {
    Assert.notNull(individualDao);
    this.individualDao = individualDao;
  }

  @Override
  public List<IndividualPresenter> getIndividuals() {
    List<IndividualModel> individuals = this.individualDao.getIndividuals();
    List<IndividualPresenter> individualList = new ArrayList<>();
    for (IndividualModel model : individuals) {
      IndividualPresenter presenter = new IndividualPresenter(model);
      individualList.add(presenter);
    }

    return individualList;
  }

  @Override
  public Slice<IndividualPresenter> getIndividuals(Pageable pageable) {
    return this.individualDao.getIndividuals(pageable);
  }

  @Override
  public Integer getNumberOfIndividuals() {
    return this.individualDao.getNumberOfIndividuals();
  }

  @Override
  public Boolean isUserNameExisting(String username) {
    return this.individualDao.isUserNameExisting(username);
  }

  @Override
  public Boolean isUserNameExisting(String oldUserName, String userName) {
    if (oldUserName.equals(userName)) {
      return false;
    } else {
      return this.individualDao.isUserNameExisting(userName);
    }
  }
}

package fm.api.rest.individual.interfaces;

import fm.api.rest.individual.IndividualModel;
import fm.api.rest.individual.IndividualPresenter;

/**
 * Created by haoho on 2/28/20 13:47.
 */
public interface IIndividualService {

  IndividualPresenter getIndividual(int userId);

  Integer saveIndividual(IndividualPresenter model);
}

package fm.api.rest.individual;

import fm.api.rest.individual.interfaces.IIndividualDao;
import fm.api.rest.individual.interfaces.IIndividualService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

/**
 * Created by haoho on 2/28/20 13:49.
 */
@Service("individualService")
public class IndividualService implements IIndividualService {

    private IIndividualDao individualDao;

    @Autowired
    public IndividualService(
            @Qualifier("individualDao") IIndividualDao individualDao
    ) {
        Assert.notNull(individualDao);

        this.individualDao = individualDao;
    }

    @Override
    public IndividualPresenter getIndividual(int userId) {
        return this.individualDao.getIndividual(userId);
    }

    @Override
    public Long saveIndividual(IndividualPresenter model) {
        if (individualDao.isIndividualExisting(model.getUserId())) {
            this.individualDao.updateIndividual(model);
            return model.getId();
        } else {
            return this.individualDao.addIndividual(model);
        }
    }

}

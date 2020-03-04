package fm.api.rest.bank;

import fm.api.rest.bank.interfaces.IBankDao;
import fm.api.rest.bank.interfaces.IBankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;

/**
 * Created by haoho on 3/2/20 10:59.
 */
@Service("bankService")
public class BankService implements IBankService {

  private IBankDao bankDao;

  @Autowired
  public BankService(@Qualifier("bankDao") IBankDao bankDao) {
    Assert.notNull(bankDao);

    this.bankDao = bankDao;
  }

  @Override
  public List<BankPresenter> getBanks(Integer userId) {
    return this.bankDao.getBanksByUserId(userId);
  }
}

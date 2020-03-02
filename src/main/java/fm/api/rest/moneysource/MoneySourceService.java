package fm.api.rest.moneysource;

import fm.api.rest.moneysource.interfaces.IMoneySourceDao;
import fm.api.rest.moneysource.interfaces.IMoneySourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

/**
 * Created by haoho on 3/2/20 14:55.
 */
@Service("moneySourceService")
public class MoneySourceService implements IMoneySourceService {

  private IMoneySourceDao moneySourceDao;

  @Autowired
  public MoneySourceService(@Qualifier("moneySourceDao") IMoneySourceDao moneySourceDao) {
    Assert.notNull(moneySourceDao);

    this.moneySourceDao = moneySourceDao;
  }
}

package fm.api.rest.bank.interfaces;

import fm.api.rest.bank.BankPresenter;

import java.util.List;

/**
 * Created by haoho on 3/2/20 10:58.
 */
public interface IBankDao {
  List<BankPresenter> getBanks(Integer userId);
}

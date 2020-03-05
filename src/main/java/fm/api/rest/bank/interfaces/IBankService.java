package fm.api.rest.bank.interfaces;

import fm.api.rest.bank.BankPresenter;

import java.util.List;

/**
 * Created by haoho on 3/2/20 10:59.
 */
public interface IBankService {
  List<BankPresenter> getBanks(Integer userId);

  List<BankPresenter> getAllBanks();
}

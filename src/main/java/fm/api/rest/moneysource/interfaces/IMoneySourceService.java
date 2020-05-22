package fm.api.rest.moneysource.interfaces;

import fm.api.rest.moneysource.MoneySourcePresenter;

/**
 * Created by haoho on 3/2/20 14:56.
 */
public interface IMoneySourceService {
    void updateMoneySource(MoneySourcePresenter moneySource);

    Integer addMoneySource(MoneySourcePresenter moneySource, Integer userId);
}

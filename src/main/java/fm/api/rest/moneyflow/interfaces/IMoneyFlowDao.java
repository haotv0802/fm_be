package fm.api.rest.moneyflow.interfaces;

import fm.api.rest.moneyflow.ItemDetailsPresenter;
import fm.api.rest.moneyflow.Item;
import fm.api.rest.moneyflow.ItemPresenter;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by haho on 6/22/2017.
 */
public interface IMoneyFlowDao {
  List<ItemPresenter> getExpenses(int userId);

  ItemDetailsPresenter getExpenesDetails(int userId);

  List<ItemDetailsPresenter> getPreviousExpensesDetails(int userId, int year);

  List<Integer> getYearsList(int userId);

  Long addExpense(Item item, int userId);

  void updateExpense(Item item);

  void updateExpense(BigDecimal amount, int userId, int expenseId);

  void updateAmount(int expenseId);

  void deleteExpense(int expenseId);

  boolean checkIfLoginUserOwner(int expenseId, int userId);
}

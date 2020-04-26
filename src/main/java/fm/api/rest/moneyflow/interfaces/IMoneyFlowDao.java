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

  ItemDetailsPresenter getExpensesDetails(int userId, String name);

  List<ItemDetailsPresenter> getExpensesByYear(int userId, int year, String name);

  ItemDetailsPresenter getExpensesDetailsByYearAndMonth(int userId, int year, int month);

  List<ItemDetailsPresenter> getLastMonths(int userId, String name);

  List<Integer> getYearsList(int userId);

  Long addExpense(Item item, int userId);

  void updateExpense(Item item);

  void updateExpense(BigDecimal amount, int userId, int expenseId);

  void deleteExpense(int expenseId);

  boolean checkIfLoginUserOwner(int expenseId, int userId);
}

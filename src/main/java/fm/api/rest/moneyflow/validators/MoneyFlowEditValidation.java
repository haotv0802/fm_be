package fm.api.rest.moneyflow.validators;

import fm.api.rest.moneyflow.ItemPresenter;

/**
 * Created by haho on 7/4/2017.
 */
public class MoneyFlowEditValidation {
  private int userId;
  private ItemPresenter expense;

  public int getUserId() {
    return userId;
  }

  public void setUserId(int userId) {
    this.userId = userId;
  }

  public ItemPresenter getExpense() {
    return expense;
  }

  public void setExpense(ItemPresenter expense) {
    this.expense = expense;
  }
}

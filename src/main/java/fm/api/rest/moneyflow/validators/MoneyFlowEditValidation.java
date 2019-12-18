package fm.api.rest.moneyflow.validators;

/**
 * Created by haho on 7/4/2017.
 */
public class MoneyFlowEditValidation {
  private int userId;
  private int expenseId;

  public int getUserId() {
    return userId;
  }

  public void setUserId(int userId) {
    this.userId = userId;
  }

  public int getExpenseId() {
    return expenseId;
  }

  public void setExpenseId(int expenseId) {
    this.expenseId = expenseId;
  }
}

package fm.api.rest.moneyflow.validators;

/**
 * Created by haho on 7/4/2017.
 */
public class MoneyFlowDeleteValidation {
  private Integer userId;
  private Integer expenseId;

  public int getUserId() {
    return userId;
  }

  public void setUserId(int userId) {
    this.userId = userId;
  }

  public Integer getExpenseId() {
    return expenseId;
  }

  public void setExpenseId(Integer expenseId) {
    this.expenseId = expenseId;
  }
}

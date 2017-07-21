package fm.api.rest.expenses;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by haho on 6/23/2017.
 */
public class ExpensesDetailsPresenter {
  private List<ExpensePresenter> expenses;
  private BigDecimal total;

  public List<ExpensePresenter> getExpenses() {
    return expenses;
  }

  public void setExpenses(List<ExpensePresenter> expenses) {
    this.expenses = expenses;
  }

  public BigDecimal getTotal() {
    return total;
  }

  public void setTotal(BigDecimal total) {
    this.total = total;
  }
}

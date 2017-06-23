package fm.api.rest.expenses;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by haho on 6/23/2017.
 */
public class Expenses {
  private List<Expense> expenses;
  private BigDecimal total;

  public List<Expense> getExpenses() {
    return expenses;
  }

  public void setExpenses(List<Expense> expenses) {
    this.expenses = expenses;
  }

  public BigDecimal getTotal() {
    return total;
  }

  public void setTotal(BigDecimal total) {
    this.total = total;
  }
}

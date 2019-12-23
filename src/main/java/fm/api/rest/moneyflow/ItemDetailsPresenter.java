package fm.api.rest.moneyflow;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by haho on 6/23/2017.
 */
public class ItemDetailsPresenter {
  private List<ItemPresenter> expenses;
  private BigDecimal total;

  public List<ItemPresenter> getExpenses() {
    return expenses;
  }

  public void setExpenses(List<ItemPresenter> expenses) {
    this.expenses = expenses;
  }

  public BigDecimal getTotal() {
    return total;
  }

  public void setTotal(BigDecimal total) {
    this.total = total;
  }
}

package fm.api.rest.moneyflow;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by haho on 6/22/2017.
 * The java object is for create/update actions of Expense.
 */
public class Item {
  private Integer id;
  @NotNull(message = "Amount cannot be null")
  private BigDecimal amount;
  private Date date;
  private String name;

//  @NotNull(message = "Money source cannot be null") // TODO in order to pass UnitTest, let it be like this temporarily.
  private Integer moneySourceId;

  private Boolean isSpending;

  public Item() {

  }

  public Item(ItemPresenter presenter) {
    this.setId(presenter.getId());
    this.setAmount(presenter.getAmount());
    this.setDate(presenter.getDate());
    this.setMoneySourceId(presenter.getMoneySourceId());
    this.setSpending(presenter.getSpending());
    this.setName(presenter.getName());
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public BigDecimal getAmount() {
    return amount;
  }

  public void setAmount(BigDecimal amount) {
    this.amount = amount;
  }

  public Date getDate() {
    return date;
  }

  public void setDate(Date date) {
    this.date = date;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Integer getMoneySourceId() {
    return moneySourceId;
  }

  public void setMoneySourceId(Integer moneySourceId) {
    this.moneySourceId = moneySourceId;
  }

  public Boolean getSpending() {
    return isSpending;
  }

  public void setSpending(Boolean spending) {
    isSpending = spending;
  }
}

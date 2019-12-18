package fm.api.rest.moneyflow;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by haho on 6/22/2017.
 * The java object is for create/update actions of Expense.
 */
public class Item {
  private Integer id;
  private BigDecimal amount;
  private Date date;
  private String name;
  private Integer moneySourceId;

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
}

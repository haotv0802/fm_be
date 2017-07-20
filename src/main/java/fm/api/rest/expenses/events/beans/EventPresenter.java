package fm.api.rest.expenses.events.beans;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Date: 7/19/2017 Time: 4:40 PM
 * <p>
 * TODO: WRITE THE DESCRIPTION HERE
 *
 * @author haho
 */
public class EventPresenter {
  private Integer id;
  private Integer userId;
  private String name;
  private BigDecimal amount;
  private Date date;
  private String forPerson;
  private Integer eventTypeId;
  private List<EventExpensePresenter> expenses;
  private BigDecimal total;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Integer getUserId() {
    return userId;
  }

  public void setUserId(Integer userId) {
    this.userId = userId;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
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

  public String getForPerson() {
    return forPerson;
  }

  public void setForPerson(String forPerson) {
    this.forPerson = forPerson;
  }

  public Integer getEventTypeId() {
    return eventTypeId;
  }

  public void setEventTypeId(Integer eventTypeId) {
    this.eventTypeId = eventTypeId;
  }

  public List<EventExpensePresenter> getExpenses() {
    return expenses;
  }

  public void setExpenses(List<EventExpensePresenter> expenses) {
    this.expenses = expenses;
  }

  public BigDecimal getTotal() {
    return total;
  }

  public void setTotal(BigDecimal total) {
    this.total = total;
  }
}

package fm.api.rest.expenses.events.beans;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by haho on 6/22/2017.
 * The java object is for create/update actions of Expense.
 */
public class Expense {
  private Integer id;
  private BigDecimal amount;
  private Date date;
  private String place;
  private String forPerson;
  private Integer cardId;
  private Boolean isAnEvent;

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

  public String getPlace() {
    return place;
  }

  public void setPlace(String place) {
    this.place = place;
  }

  public String getForPerson() {
    return forPerson;
  }

  public void setForPerson(String forPerson) {
    this.forPerson = forPerson;
  }

  public Integer getCardId() {
    return cardId;
  }

  public void setCardId(Integer cardId) {
    this.cardId = cardId;
  }

  public Boolean getAnEvent() {
    return isAnEvent;
  }

  public void setAnEvent(Boolean anEvent) {
    isAnEvent = anEvent;
  }
}
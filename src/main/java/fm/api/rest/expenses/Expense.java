package fm.api.rest.expenses;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by haho on 6/22/2017.
 * The java object is for create/update actions of Expense.
 */
public class Expense {
  private int id;
  private BigDecimal amount;
  private Date date;
  private String place;
  private String forPerson;
  private int cardId;
  private boolean isAnEvent;

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

  public int getCardId() {
    return cardId;
  }

  public void setCardId(int cardId) {
    this.cardId = cardId;
  }

  public boolean isAnEvent() {
    return isAnEvent;
  }

  public void setAnEvent(boolean anEvent) {
    isAnEvent = anEvent;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }
}
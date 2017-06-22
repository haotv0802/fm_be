package fm.api.rest.expenses;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by haho on 6/22/2017.
 */
public class ExpenseBean {
  private int id;
  private int userId;
  private BigDecimal amount;
  private Date date;
  private String place;
  private String forPerson;
  private Boolean isAnEvent;
  private int cardId;
  private Boolean payInCash;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public int getUserId() {
    return userId;
  }

  public void setUserId(int userId) {
    this.userId = userId;
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

  public Boolean getAnEvent() {
    return isAnEvent;
  }

  public void setAnEvent(Boolean anEvent) {
    isAnEvent = anEvent;
  }

  public int getCardId() {
    return cardId;
  }

  public void setCardId(int cardId) {
    this.cardId = cardId;
  }

  public Boolean getPayInCash() {
    return payInCash;
  }

  public void setPayInCash(Boolean payInCash) {
    this.payInCash = payInCash;
  }
}
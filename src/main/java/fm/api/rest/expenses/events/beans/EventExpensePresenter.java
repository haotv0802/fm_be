package fm.api.rest.expenses.events.beans;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Date: 7/19/2017 Time: 4:52 PM
 * <p>
 * TODO: WRITE THE DESCRIPTION HERE
 *
 * @author haho
 */
public class EventExpensePresenter {
  private Integer id;
  private BigDecimal amount;
  private Date date;
  private String place;
  private String forPerson;
  private Integer cardId;
  private String paymentMethod;
  private String cardNumber;
  private String cardInfo;

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

  public String getPaymentMethod() {
    return paymentMethod;
  }

  public void setPaymentMethod(String paymentMethod) {
    this.paymentMethod = paymentMethod;
  }

  public String getCardNumber() {
    return cardNumber;
  }

  public void setCardNumber(String cardNumber) {
    this.cardNumber = cardNumber;
  }

  public String getCardInfo() {
    return cardInfo;
  }

  public void setCardInfo(String cardInfo) {
    this.cardInfo = cardInfo;
  }
}

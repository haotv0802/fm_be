package fm.api.rest.moneyflow;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by haho on 6/22/2017.
 * The java object is for values presentation.
 */
public class ExpensePresenter {
  private Integer id;
  private Integer userId;
  private BigDecimal amount;
  private Date date;
  private String name;
  private Integer moneySourceId;
  private String paymentMethod;
  private String cardNumber;
  private String cardInfo;
  private Boolean isSpending;

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

  public Boolean getSpending() {
    return isSpending;
  }

  public void setSpending(Boolean spending) {
    isSpending = spending;
  }
}

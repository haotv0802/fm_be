package fm.api.rest.moneysource;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by haoho on 3/2/20 15:00.
 */
public class MoneySourcePresenter {
  private Long id;
  private String name;
  private Date startDate;
  private Date expiryDate;
  private String cardNumber;
  private BigDecimal creditLimit;
  private Boolean terminated;
  private Long bankId;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Date getStartDate() {
    return startDate;
  }

  public void setStartDate(Date startDate) {
    this.startDate = startDate;
  }

  public Date getExpiryDate() {
    return expiryDate;
  }

  public void setExpiryDate(Date expiryDate) {
    this.expiryDate = expiryDate;
  }

  public String getCardNumber() {
    return cardNumber;
  }

  public void setCardNumber(String cardNumber) {
    this.cardNumber = cardNumber;
  }

  public BigDecimal getCreditLimit() {
    return creditLimit;
  }

  public void setCreditLimit(BigDecimal creditLimit) {
    this.creditLimit = creditLimit;
  }

  public Boolean getTerminated() {
    return terminated;
  }

  public void setTerminated(Boolean terminated) {
    this.terminated = terminated;
  }

  public Long getBankId() {
    return bankId;
  }

  public void setBankId(Long bankId) {
    this.bankId = bankId;
  }
}

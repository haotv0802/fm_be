package fm.api.rest.individual;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by haoho on 2/28/20 14:16.
 */
public class IndividualPresenter {
  private Long id;
  private String firstName;
  private String middleName;
  private String lastName;
  private Date birthday;
  private String gender;
  private String email;
  private String phoneNumber;
  private BigDecimal income;

  // Money Source
  private Long moneySourceId;
  private String moneySourceName;
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

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getMiddleName() {
    return middleName;
  }

  public void setMiddleName(String middleName) {
    this.middleName = middleName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public Date getBirthday() {
    return birthday;
  }

  public void setBirthday(Date birthday) {
    this.birthday = birthday;
  }

  public String getGender() {
    return gender;
  }

  public void setGender(String gender) {
    this.gender = gender;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPhoneNumber() {
    return phoneNumber;
  }

  public void setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
  }

  public BigDecimal getIncome() {
    return income;
  }

  public void setIncome(BigDecimal income) {
    this.income = income;
  }

  public Long getMoneySourceId() {
    return moneySourceId;
  }

  public void setMoneySourceId(Long moneySourceId) {
    this.moneySourceId = moneySourceId;
  }

  public String getMoneySourceName() {
    return moneySourceName;
  }

  public void setMoneySourceName(String moneySourceName) {
    this.moneySourceName = moneySourceName;
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

package fm.api.rest.individual;

import fm.api.rest.moneysource.MoneySourcePresenter;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

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
  private List<MoneySourcePresenter> moneySourcePresenters;

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

  public List<MoneySourcePresenter> getMoneySourcePresenters() {
    return moneySourcePresenters;
  }

  public void setMoneySourcePresenters(List<MoneySourcePresenter> moneySourcePresenters) {
    this.moneySourcePresenters = moneySourcePresenters;
  }
}

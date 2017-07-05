package fm.api.rest.person.picker.beans;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by haho on 7/5/2017.
 */
public class PersonPresenter {
  private Integer id;
  private String firstName;
  private String lastName;
  private String middleName;
  private Date birthday;
  private Boolean gender;
  private String email;
  private String phoneNumber;
  private BigDecimal officialIncome;
  private Integer monthlyPaymentDate;
  private Integer userId;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getMiddleName() {
    return middleName;
  }

  public void setMiddleName(String middleName) {
    this.middleName = middleName;
  }

  public Date getBirthday() {
    return birthday;
  }

  public void setBirthday(Date birthday) {
    this.birthday = birthday;
  }

  public Boolean getGender() {
    return gender;
  }

  public void setGender(Boolean gender) {
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

  public BigDecimal getOfficialIncome() {
    return officialIncome;
  }

  public void setOfficialIncome(BigDecimal officialIncome) {
    this.officialIncome = officialIncome;
  }

  public Integer getMonthlyPaymentDate() {
    return monthlyPaymentDate;
  }

  public void setMonthlyPaymentDate(Integer monthlyPaymentDate) {
    this.monthlyPaymentDate = monthlyPaymentDate;
  }

  public Integer getUserId() {
    return userId;
  }

  public void setUserId(Integer userId) {
    this.userId = userId;
  }
}
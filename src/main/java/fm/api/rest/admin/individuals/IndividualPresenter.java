package fm.api.rest.admin.individuals;

import java.util.Date;

/**
 * Created by haho on 3/22/2017.
 */
public class IndividualPresenter {
  private Integer id;
  private String firstName;
  private String middleName;
  private String lastName;
  private Date birthday;
  private String gender;
  private String email;
  private String phoneNumber;
  private String imageURL;
  private String imageInfo;
  private String userName;
  private String role;

  public IndividualPresenter(IndividualModel model) {
    this.setId(model.getId());
    this.setFirstName(model.getFirstName());
    this.setMiddleName(model.getMiddleName());
    this.setLastName(model.getLastName());
    this.setBirthday(model.getBirthday());
    this.setGender(model.getGender());
    this.setEmail(model.getEmail());
    this.setPhoneNumber(model.getPhoneNumber());
    this.setImageURL(model.getImageURL());
    this.setImageInfo(model.getImageInfo());
    this.setUserName(model.getUserName());
    this.setRole(model.getRole());
  }

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

  public String getImageURL() {
    return imageURL;
  }

  public void setImageURL(String imageURL) {
    this.imageURL = imageURL;
  }

  public String getImageInfo() {
    return imageInfo;
  }

  public void setImageInfo(String imageInfo) {
    this.imageInfo = imageInfo;
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public String getRole() {
    return role;
  }

  public void setRole(String role) {
    this.role = role;
  }
}

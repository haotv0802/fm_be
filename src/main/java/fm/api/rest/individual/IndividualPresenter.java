package fm.api.rest.individual;

import com.fasterxml.jackson.annotation.JsonFormat;
import fm.api.rest.moneysource.MoneySourcePresenter;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Created by haoho on 2/28/20 14:16.
 */
public class IndividualPresenter {
    private Integer id;
    @NotNull(message = "First Name cannot be null")
    private String firstName;
    @NotNull(message = "Middle Name cannot be null")
    private String middleName;
    @NotNull(message = "Last Name cannot be null")
    private String lastName;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date birthday;
    @NotNull(message = "Gender cannot be null")
    private String gender;
    @NotNull(message = "Email cannot be null")
    private String email;
    @NotNull(message = "Phone number cannot be null")
    private String phoneNumber;

    private Integer userId;

    @NotNull(message = "Income cannot be null")
    private BigDecimal income;

    @Valid
    private List<MoneySourcePresenter> moneySourcePresenters;

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

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public void updateIndividual(IndividualPresenter individual) {
        this.setFirstName(individual.getFirstName());
        this.setMiddleName(individual.getMiddleName());
        this.setLastName(individual.getLastName());
        this.setUserId(individual.getUserId());
        this.setEmail(individual.getEmail());
        this.setBirthday(individual.getBirthday());
        this.setIncome(individual.getIncome());
        this.setPhoneNumber(individual.getPhoneNumber());
        this.setMoneySourcePresenters(individual.getMoneySourcePresenters());
        this.setGender(individual.getGender());
    }
}

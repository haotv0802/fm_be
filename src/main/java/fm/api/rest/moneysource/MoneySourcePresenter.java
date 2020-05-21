package fm.api.rest.moneysource;

import fm.api.rest.bank.BankPresenter;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by haoho on 3/2/20 15:00.
 */
public class MoneySourcePresenter {
    private Long id;
    @NotNull(message = "Name cannot be null")
    private String name;
    @NotNull(message = "Start date cannot be null")
    private Date startDate;
    @NotNull(message = "Expiry date cannot be null")
    private Date expiryDate;
    @NotNull(message = "Card number cannot be null")
    private String cardNumber;
    @NotNull(message = "payment method cannot be null")
    private String paymentMethodId;
    @NotNull(message = "Credit limit cannot be null")
    private BigDecimal creditLimit;
    @NotNull(message = "Terminated cannot be null")
    private Boolean terminated;
    @NotNull(message = "Bank id cannot be null")
    private Integer bankId;
    private BankPresenter bank;
    @NotNull(message = "User id cannot be null")
    private Integer userId;

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

    public Integer getBankId() {
        return bankId;
    }

    public void setBankId(Integer bankId) {
        this.bankId = bankId;
    }

    public BankPresenter getBank() {
        return bank;
    }

    public void setBank(BankPresenter bank) {
        this.bank = bank;
    }

    public String getPaymentMethodId() {
        return paymentMethodId;
    }

    public void setPaymentMethodId(String paymentMethodId) {
        this.paymentMethodId = paymentMethodId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}

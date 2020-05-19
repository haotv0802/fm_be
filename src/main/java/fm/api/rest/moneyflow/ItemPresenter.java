package fm.api.rest.moneyflow;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

/**
 * Created by haho on 6/22/2017.
 * The java object is for values presentation.
 */
public class ItemPresenter {
    private Integer id;
    private Integer userId;
    @NotNull(message = "Amount cannot be null")
    private BigDecimal amount;
    private LocalDate date;
    private String name;
    @NotNull(message = "Money source cannot be null")
    private Integer moneySourceId;
    private String moneySourceName;
    private String paymentMethod;
    private String cardNumber;
    private String cardInfo;
    private Boolean isSpending;
    private Boolean isUpdated = false;

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

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
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

    public Boolean getUpdated() {
        return isUpdated;
    }

    public void setUpdated(Boolean updated) {
        isUpdated = updated;
    }

    public String getMoneySourceName() {
        return moneySourceName;
    }

    public void setMoneySourceName(String moneySourceName) {
        this.moneySourceName = moneySourceName;
    }
}

package fm.api.rest.bankinterests.crawlers;

import java.math.BigDecimal;

/**
 * Created by HaoHo on 5/26/2020
 */
public class BankInterestsModel {
    private Long id;
    private Integer startMonth;
    private Integer endMonth;
    private Integer startAmount;
    private Integer endAmount;
    private BigDecimal interest;
    private String url;
    private Integer bankId;
    private String interestType;
    private String loanType;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getStartMonth() {
        return startMonth;
    }

    public void setStartMonth(Integer startMonth) {
        this.startMonth = startMonth;
    }

    public Integer getEndMonth() {
        return endMonth;
    }

    public void setEndMonth(Integer endMonth) {
        this.endMonth = endMonth;
    }

    public Integer getStartAmount() {
        return startAmount;
    }

    public void setStartAmount(Integer startAmount) {
        this.startAmount = startAmount;
    }

    public Integer getEndAmount() {
        return endAmount;
    }

    public void setEndAmount(Integer endAmount) {
        this.endAmount = endAmount;
    }

    public BigDecimal getInterest() {
        return interest;
    }

    public void setInterest(BigDecimal interest) {
        this.interest = interest;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getBankId() {
        return bankId;
    }

    public void setBankId(Integer bankId) {
        this.bankId = bankId;
    }

    public String getInterestType() {
        return interestType;
    }

    public void setInterestType(String interestType) {
        this.interestType = interestType;
    }

    public String getLoanType() {
        return loanType;
    }

    public void setLoanType(String loanType) {
        this.loanType = loanType;
    }
}

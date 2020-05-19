package fm.api.rest.promotions.crawler;

import java.time.LocalDate;

/**
 * Quy created on 3/11/2020
 */
public class PromotionCrawlerModel {

    private Long id;
    private String title;
    private String content;
    private String discount;
    private LocalDate startDate;
    private LocalDate endDate;
    private int categoryId;
    private int bankId;
    private String htmlText;
    private String url;
    private String installmentPeriod;
    private String imgURL;
    private String cardType;
    private String condition;
    private String location;

    public PromotionCrawlerModel(String title, String content, String discount, String installmentPeriod,
                                 LocalDate startDate, LocalDate endDate, int categoryId, int bankId, String htmlText,
                                 String url, String imgURL, String cardType, String condition, String location) {
        this.title = title;
        this.content = content;
        this.discount = discount;
        this.startDate = startDate;
        this.endDate = endDate;
        this.categoryId = categoryId;
        this.bankId = bankId;
        this.htmlText = htmlText;
        this.url = url;
        this.imgURL = imgURL;
        this.cardType = cardType;
        this.condition = condition;
        this.location = location;
        this.installmentPeriod = installmentPeriod;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public int getBankId() {
        return bankId;
    }

    public void setBankId(int bankId) {
        this.bankId = bankId;
    }

    public String getHtmlText() {
        return htmlText;
    }

    public void setHtmlText(String htmlText) {
        this.htmlText = htmlText;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getImgURL() {
        return imgURL;
    }

    public void setImgURL(String imgURL) {
        this.imgURL = imgURL;
    }

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getInstallmentPeriod() {
        return installmentPeriod;
    }

    public void setInstallmentPeriod(String installmentPeriod) {
        this.installmentPeriod = installmentPeriod;
    }

    @Override
    public String toString() {
        return "PromotionCrawlerModel{" +
                "title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", discount='" + discount + '\'' +
                ", startDate='" + startDate + '\'' +
                ", endDate='" + endDate + '\'' +
                ", categoryId=" + categoryId +
                ", bankId=" + bankId +
//                ", htmlText='" + htmlText + '\'' +
                ", url='" + url + '\'' +
                ", installmentPeriod='" + installmentPeriod + '\'' +
                ", imgURL='" + imgURL + '\'' +
                ", cardType='" + cardType + '\'' +
                ", condition='" + condition + '\'' +
                ", location='" + location + '\'' +
                '}';
    }
}

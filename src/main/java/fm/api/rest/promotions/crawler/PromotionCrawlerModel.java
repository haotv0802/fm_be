package fm.api.rest.promotions.crawler;

import java.sql.Date;

public class PromotionCrawlerModel {
//    private int id;
//    private String title;
//    private String content;
//    private String discount;
//    private Date start_date;
//    private Date end_date;
//    private int category_id;
//    private int bank_id;

    private String title;
    private String content;
    private String discount;
    private String startDate;
    private String endDate;
    private String category;
    private String bankId;
    private String htmlText;
    private String linkDetail;
    private String imgURL;
    private String cardType;
    private String condition;
    private String location;

    public PromotionCrawlerModel(String title, String content, String discount, String startDate, String endDate, String category, String bankId, String htmlText, String linkDetail, String imgURL, String cardType, String condition, String location) {
        this.title = title;
        this.content = content;
        this.discount = discount;
        this.startDate = startDate;
        this.endDate = endDate;
        this.category = category;
        this.bankId = bankId;
        this.htmlText = htmlText;
        this.linkDetail = linkDetail;
        this.imgURL = imgURL;
        this.cardType = cardType;
        this.condition = condition;
        this.location = location;
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

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getBankId() {
        return bankId;
    }

    public void setBankId(String bankId) {
        this.bankId = bankId;
    }

    public String getHtmlText() {
        return htmlText;
    }

    public void setHtmlText(String htmlText) {
        this.htmlText = htmlText;
    }

    public String getLinkDetail() {
        return linkDetail;
    }

    public void setLinkDetail(String linkDetail) {
        this.linkDetail = linkDetail;
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
}
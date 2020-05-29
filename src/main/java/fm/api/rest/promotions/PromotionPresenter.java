package fm.api.rest.promotions;

import java.time.LocalDate;

/**
 * Created by haoho on 2/27/20 10:03.
 */
public class PromotionPresenter {
    private Long id;
    private String title;
    private String content;
    private String discount;
    private LocalDate start_date;
    private LocalDate end_date;
    private int category_id;
    private int bank_id;
    private String installmentPeriod;
    private String htmlText;
    private String url;
    private String imgURL;
    private String cardType;
    private String condition;
    private String location;

    public PromotionPresenter() {
    }

    public PromotionPresenter(String title, String content, String discount, String installmentPeriod,
                              LocalDate start_date, LocalDate end_date, int category_id, int bankId, String htmlText,
                              String url, String imgURL, String cardType, String condition, String location) {
        this.title = title;
        this.content = content;
        this.discount = discount;
        this.installmentPeriod = installmentPeriod;
        this.start_date = start_date;
        this.end_date = end_date;
        this.category_id = category_id;
        this.bank_id = bankId;
        this.htmlText = htmlText;
        this.url = url;
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

    public LocalDate getStart_date() {
        return start_date;
    }

    public void setStart_date(LocalDate start_date) {
        this.start_date = start_date;
    }

    public LocalDate getEnd_date() {
        return end_date;
    }

    public void setEnd_date(LocalDate end_date) {
        this.end_date = end_date;
    }

    public int getCategory_id() {
        return category_id;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }

    public int getBank_id() {
        return bank_id;
    }

    public void setBank_id(int bank_id) {
        this.bank_id = bank_id;
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}

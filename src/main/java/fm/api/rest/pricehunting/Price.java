package fm.api.rest.pricehunting;

import java.math.BigDecimal;

/**
 * Created by HaoHo on 6/8/2020
 */
public class Price {
    private Long id;
    private String email;
    private String title;
    private String url;
    private BigDecimal price;
    private BigDecimal expectedPrice;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getExpectedPrice() {
        return expectedPrice;
    }

    public void setExpectedPrice(BigDecimal expectedPrice) {
        this.expectedPrice = expectedPrice;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}

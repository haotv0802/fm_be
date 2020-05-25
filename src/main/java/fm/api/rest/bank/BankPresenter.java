package fm.api.rest.bank;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by haoho on 3/2/20 10:57.
 */
public class BankPresenter {
    private Integer id;
    @NotNull(message = "Name cannot be null")
    @Size(min = 1, max = 50, message = "Name must be between 1 and 50 characters")
    private String name;

    @NotNull(message = "Address cannot be null")
    @Size(min = 1, max = 100, message = "Address must be between 1 and 50 characters")
    private String address;

    private String website;
    
    private String logo;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }
}

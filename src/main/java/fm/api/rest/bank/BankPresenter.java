package fm.api.rest.bank;

/**
 * Created by haoho on 3/2/20 10:57.
 */
public class BankPresenter {
  private Long id;
  private String name;
  private String address;
  private String website;

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
}
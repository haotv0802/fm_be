package fm.api.rest.paymentmethods.beans;

/**
 * Created by haoho on 3/5/20 16:23.
 */
public class PaymentMethod {
  private Long id;
  private String name;

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
}

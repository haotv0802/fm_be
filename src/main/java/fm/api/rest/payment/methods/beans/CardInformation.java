package fm.api.rest.payment.methods.beans;

/**
 * Created by haho on 6/26/2017.
 */
public class CardInformation {
  private int id;
  private String cardNumber;
  private String cardType;
  private String cardInfo;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getCardNumber() {
    return cardNumber;
  }

  public void setCardNumber(String cardNumber) {
    this.cardNumber = cardNumber;
  }

  public String getCardType() {
    return cardType;
  }

  public void setCardType(String cardType) {
    this.cardType = cardType;
  }

  public String getCardInfo() {
    return cardInfo;
  }

  public void setCardInfo(String cardInfo) {
    this.cardInfo = cardInfo;
  }
}

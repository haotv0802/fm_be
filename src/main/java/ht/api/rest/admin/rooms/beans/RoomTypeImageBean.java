package ht.api.rest.admin.rooms.beans;

/**
 * Created by haho on 3/24/2017.
 */
public class RoomTypeImageBean {
  private int id;
  private String imageURL;
  private String imageInfo;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getImageURL() {
    return imageURL;
  }

  public void setImageURL(String imageURL) {
    this.imageURL = imageURL;
  }

  public String getImageInfo() {
    return imageInfo;
  }

  public void setImageInfo(String imageInfo) {
    this.imageInfo = imageInfo;
  }
}

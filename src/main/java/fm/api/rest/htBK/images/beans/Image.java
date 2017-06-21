package fm.api.rest.htBK.images.beans;

import java.util.Date;

/**
 * Created by haho on 3/27/2017.
 * This image is for Individuals & Promotions
 */
public class Image {
  private int id;
  private String name;
  private String imageURL;
  private String imageInfo;
  private String description;
  private Date createdOn;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
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

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Date getCreatedOn() {
    return createdOn;
  }

  public void setCreatedOn(Date createdOn) {
    this.createdOn = createdOn;
  }
}

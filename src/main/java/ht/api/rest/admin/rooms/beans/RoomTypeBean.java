package ht.api.rest.admin.rooms.beans;

import java.util.List;

/**
 * Created by haho on 3/22/2017.
 */
public class RoomTypeBean {
  private int id;
  private String name;
  private int numOfPeople;
  private int numOfBeds;
  private String typeOfBed;

  private List<DayPrice> prices;
  private List<RoomTypeImageBean> images;

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

  public int getNumOfPeople() {
    return numOfPeople;
  }

  public void setNumOfPeople(int numOfPeople) {
    this.numOfPeople = numOfPeople;
  }

  public int getNumOfBeds() {
    return numOfBeds;
  }

  public void setNumOfBeds(int numOfBeds) {
    this.numOfBeds = numOfBeds;
  }

  public String getTypeOfBed() {
    return typeOfBed;
  }

  public void setTypeOfBed(String typeOfBed) {
    this.typeOfBed = typeOfBed;
  }

  public List<RoomTypeImageBean> getImages() {
    return images;
  }

  public void setImages(List<RoomTypeImageBean> images) {
    this.images = images;
  }

  public List<DayPrice> getPrices() {
    return prices;
  }

  public void setPrices(List<DayPrice> prices) {
    this.prices = prices;
  }
}

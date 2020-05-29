package fm.api.rest.category;

/**
 * Created by Quy on 5/26/2020.
 */
public class CategoryPresenter {
  private int id;
  private String name;

  public CategoryPresenter(int id, String name) {
    this.id = id;
    this.name = name;
  }

  public CategoryPresenter() {
  }

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
}

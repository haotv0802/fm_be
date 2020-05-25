package fm.api.rest.category.interfaces;

import fm.api.rest.category.CategoryPresenter;

import java.util.List;

/**
 * Created by Quy on 5/26/2020.
 */
public interface ICategoryDao {
  public List<CategoryPresenter> getAllCate();
}

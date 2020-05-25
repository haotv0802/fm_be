package fm.api.rest.category;

import fm.api.rest.category.interfaces.ICategoryDao;
import fm.api.rest.category.interfaces.ICategoryService;
import io.jsonwebtoken.lang.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Quy on 5/26/2020.
 */
@Service("cateService")
public class CategoryService implements ICategoryService {
  private ICategoryDao categoryDao;

  @Autowired
  public CategoryService(@Qualifier("cateDao") ICategoryDao categoryDao) {
    Assert.notNull(categoryDao);
    this.categoryDao = categoryDao;
  }

  @Override
  public List<CategoryPresenter> getAllCate() {
    return this.categoryDao.getAllCate();
  }
}

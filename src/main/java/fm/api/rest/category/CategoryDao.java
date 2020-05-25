package fm.api.rest.category;

import fm.api.rest.category.interfaces.ICategoryDao;
import fm.common.dao.DaoUtils;
import io.jsonwebtoken.lang.Assert;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Quy on 5/26/2020.
 */
@Service("cateDao")
public class CategoryDao implements ICategoryDao {
  private static final Logger logger = LogManager.getLogger(CategoryDao.class);
  private final NamedParameterJdbcTemplate namedTemplate;

  @Autowired
  public CategoryDao(NamedParameterJdbcTemplate namedTemplate) {
    Assert.notNull(namedTemplate);
    this.namedTemplate = namedTemplate;
  }


  @Override
  public List<CategoryPresenter> getAllCate() {
    final String sql = "SELECT id,name FROM fm_promotion_categories";
    final MapSqlParameterSource paramsMap = new MapSqlParameterSource();
    DaoUtils.debugQuery(logger, sql, paramsMap.getValues());

    List<CategoryPresenter> listResult = namedTemplate.query(sql, paramsMap, (rs, rowNum) -> {
      CategoryPresenter presenter = new CategoryPresenter();
      presenter.setId(rs.getInt("id"));
      presenter.setName(rs.getString("name"));
      return presenter;
    });
    return listResult;
  }
}

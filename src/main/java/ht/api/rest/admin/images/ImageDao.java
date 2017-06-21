package ht.api.rest.admin.images;

import ht.api.rest.admin.images.beans.Image;
import ht.api.rest.admin.images.interfaces.IImageDao;
import ht.common.dao.DaoUtils;
import io.jsonwebtoken.lang.Assert;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by haho on 3/22/2017.
 */
@Repository("adminImageDao")
public class ImageDao implements IImageDao {
  private static final Logger LOGGER = LogManager.getLogger(ImageDao.class);

  private final NamedParameterJdbcTemplate namedTemplate;

  @Autowired
  public ImageDao(NamedParameterJdbcTemplate namedTemplate) {
    Assert.notNull(namedTemplate);
    this.namedTemplate = namedTemplate;
  }

  @Override
  public List<Image> getImages() {
    final String sql = "SELECT                                                     "
                     + "	id, name, image_url, image_info, description, created_on "
                     + "FROM                                                       "
                     + "	image                                                    "
        ;

    final MapSqlParameterSource paramsMap = new MapSqlParameterSource();

    DaoUtils.debugQuery(LOGGER, sql, paramsMap.getValues());

    List<Image> images = namedTemplate.query(sql, paramsMap, (rs, rowNum) -> buildImage(rs));

    return images;
  }

  private Image buildImage(ResultSet rs) throws SQLException {
    Image img = new Image();
    img.setId(rs.getInt("id"));
    img.setName(rs.getString("name"));
    img.setImageURL(rs.getString("image_url"));
    img.setImageInfo(rs.getString("image_info"));
    img.setDescription(rs.getString("description"));
    img.setCreatedOn(rs.getDate("created_on"));
    return img;
  }

  @Override
  public Image getImageById(Integer id) {
    final String sql = "SELECT                                                     "
                     + "	id, name, image_url, image_info, description, created_on "
                     + "FROM                                                       "
                     + "	image WHERE id = :id                                     "
        ;

    final MapSqlParameterSource paramsMap = new MapSqlParameterSource();
    paramsMap.addValue("id", id);

    DaoUtils.debugQuery(LOGGER, sql, paramsMap.getValues());

    Image image = null;
    try {
      image = namedTemplate.queryForObject(sql, paramsMap, (rs, rowNum) -> buildImage(rs));
    } catch (EmptyResultDataAccessException ex) {
      this.LOGGER.info(ex);
    }
    return image;
  }

  @Override
  public void updateImage(Image image) {
    final String sql = "UPDATE image                 "
                     + "SET                          "
                     + "	name = :name,              "
//                     + "	image_url = :imageURL,     "
                     + "	image_info = :imageInfo,   "
                     + "	description = :description,"
                     + "	updated_on = NOW()         "
                     + "WHERE                        "
                     + "	id = :id                   "
        ;
    final MapSqlParameterSource paramsMap = new MapSqlParameterSource();
    paramsMap.addValue("id", image.getId());
    paramsMap.addValue("name", image.getName());
//    paramsMap.addValue("imageURL", image.getImageURL());
    paramsMap.addValue("imageInfo", image.getImageInfo());
    paramsMap.addValue("description", image.getDescription());

    DaoUtils.debugQuery(LOGGER, sql, paramsMap.getValues());

    namedTemplate.update(sql, paramsMap);
  }

  @Override
  public Boolean isImageNameExisting(String name) {
    final String sql = "SELECT COUNT(*) FROM image WHERE name = :name"
        ;

    final MapSqlParameterSource paramsMap = new MapSqlParameterSource();
    paramsMap.addValue("name", name);

    DaoUtils.debugQuery(LOGGER, sql, paramsMap.getValues());

    return namedTemplate.queryForObject(sql, paramsMap, Integer.class) > 0 ? true : false;
  }

}
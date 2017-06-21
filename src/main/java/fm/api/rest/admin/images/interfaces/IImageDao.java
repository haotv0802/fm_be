package fm.api.rest.admin.images.interfaces;

import fm.api.rest.admin.images.beans.Image;

import java.util.List;

/**
 * Created by haho on 3/28/2017.
 */
public interface IImageDao {
  List<Image> getImages();

  Image getImageById(Integer id);

  void updateImage(Image image);

  Boolean isImageNameExisting(String name);
}

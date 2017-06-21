package ht.api.rest.admin.images.interfaces;

import ht.api.rest.admin.images.beans.Image;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by haho on 3/28/2017.
 */
public interface IImageService {
  List<Image> getImages();

  Image getImageById(Integer id);

  void updateImage(int id, MultipartFile multipartFile) throws IOException;

  void updateImageInfo(Image image);

  ResponseEntity getImageFileById(Integer id, HttpServletResponse response, String ext) throws IOException;
}

package fm.api.rest.admin.images.validators;

import fm.api.rest.admin.images.beans.Image;
import fm.api.rest.admin.images.interfaces.IImageDao;
import fm.common.ValidationException;
import fm.common.Validator;
import io.jsonwebtoken.lang.Assert;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * Created by haho on 5/10/2017.
 */
@Service("updateImageNameValidator")
public class UpdateImageValidator implements Validator<Image> {

  private final IImageDao imageDao;

  public UpdateImageValidator(@Qualifier("adminImageDao") IImageDao imageDao) {
    Assert.notNull(imageDao);
    this.imageDao = imageDao;
  }

  @Override
  public String defaultFaultCode() {
    return "image.validation.name.invalid";
  }

  @Override
  public void validate(Image image, String faultCode, Object... args) {
    Image oldImage = this.imageDao.getImageById(image.getId());
    if (!image.getName().equals(oldImage.getName())) {
      if (this.imageDao.isImageNameExisting(image.getName())) {
        throw new ValidationException("admin.image.nameExisting");
      }
    }
  }

}
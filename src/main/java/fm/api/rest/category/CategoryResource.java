package fm.api.rest.category;

import fm.api.rest.BaseResource;
import fm.api.rest.category.interfaces.ICategoryService;
import fm.auth.UserDetailsImpl;
import io.jsonwebtoken.lang.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Quy on 5/26/2020.
 */
@RestController
public class CategoryResource extends BaseResource {
  private ICategoryService categoryService;

  @Autowired
  public CategoryResource(@Qualifier("cateService") ICategoryService categoryService) {
    Assert.notNull(categoryService);
    this.categoryService = categoryService;
  }


  @GetMapping("/category/list/all")
  @PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
  public List<CategoryPresenter> getAllCate( @AuthenticationPrincipal UserDetailsImpl userDetails){
    return this.categoryService.getAllCate();
  }
}

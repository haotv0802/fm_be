package fm.api.rest.promotions;


import fm.api.rest.BaseResource;
import fm.api.rest.promotions.crawler.interfaces.IBankPromotion;
import fm.api.rest.promotions.interfaces.IPromotionService;
import fm.auth.UserDetailsImpl;
import io.jsonwebtoken.lang.Assert;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Quy created on 3/11/2020
 */
@RestController
public class PromotionResource extends BaseResource {

  private static final Logger logger = LogManager.getLogger(PromotionResource.class);

  private final IBankPromotion bankPromotion;

  private final IPromotionService promotionService;

  @Autowired
  public PromotionResource(@Qualifier("bankPromotion") IBankPromotion bankPromotion, @Qualifier("promotionService") IPromotionService promotionService) {
    Assert.notNull(bankPromotion);
    Assert.notNull(promotionService);
    this.bankPromotion = bankPromotion;
    this.promotionService = promotionService;
  }

  @GetMapping("/promotions/crawler/{bankID}")
  @PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
  public void crawlingPromotion(@AuthenticationPrincipal UserDetailsImpl userDetails,
                                @PathVariable String bankID) {

    this.bankPromotion.crawl(bankID);
  }

  @GetMapping("/promotions/crawlall")
  @PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
  public void crawlAll(@AuthenticationPrincipal UserDetailsImpl userDetails) {
    this.bankPromotion.crawlAll();
  }

  @GetMapping("/promotions/crawlall/threads")
  @PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
  public void crawlAllByMultiThreads(@AuthenticationPrincipal UserDetailsImpl userDetails) {
    this.bankPromotion.crawlAllByMultiThreads();
  }

  @GetMapping(value = "/promotions/list", produces = "text/plain;charset=UTF-8")
  @PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
  public List<PromotionPresenter> getAllPromotion(@AuthenticationPrincipal UserDetailsImpl userDetails,
                              @RequestParam(required = false, defaultValue = "") String title,
                              @RequestParam(required = false) String content,
                              @RequestParam (required = false)  @DateTimeFormat(pattern = "yyyy-MM-dd")String start_date,
                              @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") String end_date,
                              @RequestParam(required = false) Integer bank_id,
                              @RequestParam(required = false) Integer category_id) {
    String text = "Giảm ngay";
    if(text.equals("content")){
      String text2 = "Giảm ngay";
    }
    List<PromotionPresenter> listResult = this.promotionService.getAllPromotions(title, content, start_date, end_date, bank_id, category_id);

    return listResult;

  }
}

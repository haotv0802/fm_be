 /* Quy created on 3/13/2020 */
 package fm.api.rest.promotions.crawler.interfaces;

 import fm.api.rest.promotions.PromotionPresenter;

 import java.util.List;

 public interface IBankService {
  void SCBBankPromoCrawling();
  void VIBBankPromoCrawling(List<PromotionPresenter> promotionPresenterList);
  void SHBankPromotionCrawling();
 }

 /* Quy created on 3/13/2020 */
 package fm.api.rest.promotions.crawler;

 import fm.api.rest.promotions.crawler.interfaces.IBankService;
 import fm.api.rest.promotions.crawler.utils.SCBBank;
 import fm.api.rest.promotions.crawler.utils.ShinHanBank;
 import fm.api.rest.promotions.crawler.utils.VIBBank;

 public class BankPromoServiceImpl implements IBankService {
  @Override
  public void SCBBankPromoCrawling() {
   SCBBank bank = new SCBBank();
   bank.getListPromotionInfo();
  }

  @Override
  public void VIBBankPromoCrawling() {
   VIBBank bank = new VIBBank();
   bank.getListPromotionInfo();
  }

  @Override
  public void SHBankPromotionCrawling() {
   ShinHanBank bank = new ShinHanBank();
   bank.doPostRequest("");
  }
 }

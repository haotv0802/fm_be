package fm.api.rest.promotions.crawler;
/* Quy created on 3/11/2020  */

import fm.api.rest.BaseResource;
import fm.api.rest.promotions.crawler.interfaces.IBankPromotionCrawler;
import fm.api.rest.promotions.crawler.interfaces.IBankPromotionService;
import fm.api.rest.promotions.crawler.interfaces.IPromotionCrawlerService;
import fm.auth.UserDetailsImpl;
import fm.common.beans.HeaderLang;
import io.jsonwebtoken.lang.Assert;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

@RestController
public class PromotionCrawlerResouce extends BaseResource {
    private static final Logger LOGGER = LogManager.getLogger(PromotionCrawlerResouce.class);
    private IBankPromotionCrawler bankPromotionCrawler;
    private IPromotionCrawlerService bankPromotionService;

    @Autowired
    public PromotionCrawlerResouce(
            @Qualifier("bankPromotionCrawler") IBankPromotionCrawler bankPromotionCrawler,
            @Qualifier("promotionCrawlerService") IPromotionCrawlerService bankPromotionService
    ) {
        Assert.notNull(bankPromotionCrawler);
        this.bankPromotionCrawler = bankPromotionCrawler;
        this.bankPromotionService = bankPromotionService;
    }

    @GetMapping("/promotions/crawler/{bankID}")
    @PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
    public String crawlingPromotion(@AuthenticationPrincipal UserDetailsImpl userDetails,
                                    @HeaderLang String lang,
                                    @PathVariable int bankID){
        String message = "FALSE";
        Map<String, List<PromotionCrawlerModel>> listMap = new TreeMap<>();
        switch (bankID){
            case 0:System.out.println("SCB");
                message="Success SCB";
                listMap= bankPromotionCrawler.SCBBankPromotion();
            break;
            case 1:System.out.println("VIB");
                message="Success VIB";
                PromotionCrawlerModel model = new PromotionCrawlerModel("Agoda","Giảm thêm 7% khi đặt phòng khách sạn tại www.agoda.com/vib và thanh toán bằng thẻ VIB Khuyến mãi chỉ được áp dụng trên giá trị tiền phòng (không bao gồm thuế địa phương, phí dịch vụ và những chi phí phụ thu khác) Khuyến mãi không được chuyển đổi, cộng dồn và không được sử dụng kết hợp với bất kỳ giảm giá, khuyến mãi, mặt hàng giảm giá và các mặt hàng giá cố định (trừ trường hợp quy định) Khuyến mãi không được chuyển đổi qua thành tiền mặt hoặc bất kì sản phẩm nào khác Chủ thẻ đủ điều kiện tham gia sẽ bị ràng buộc bởi quy định và điều kiện của Agoda và VIB Khuyến mãi được áp dụng cho các khách sạn chấp nhận thanh toán trả trước có thể hiện dòng chữ “Khuyến mại hợp lệ”. Phần giảm giá sẽ được thể hiện tại bước “Chi tiết thanh toán” sau khi chủ thẻ VIB đăng nhập số thẻ hợp lệ. Phần giá trị giảm thể hiện trước dòng thuế & phí dịch vụ của khách sạn & được áp dụng trước khi xác nhận giá cuối cùng của đơn đặt phòng. Ưu đãi này chỉ áp dụng khi khách hàng sử dụng thẻ VIB để thanh toán. Khuyến mãi không áp dụng cho các giao dịch thanh toán trực tiếp tại khách sạn. Chương trình khuyến mãi có thể kết thúc sớm nếu hết ngân sách.","7%","","31/12/2020","1","2","AA","AA","AA","A","AA","A");
                bankPromotionService.insertBankPromotion(model);
//                listMap=bankPromotionCrawler.VIBBankPromotion();
//                for(String item : listMap.keySet()){
//                    for(PromotionCrawlerModel model : listMap.get(item)){
//                        bankPromotionService.insertBankPromotion(model);
//                    }
//                }


            break;
            case 2:System.out.println("ShinHan");
                message="Success SH";
                bankPromotionCrawler.SHBankPromotion();
                break;
        }


        return message;
    }

}

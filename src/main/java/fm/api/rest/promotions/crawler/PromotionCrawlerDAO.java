package fm.api.rest.promotions.crawler;
/**
 * Quy created on 3/11/2020
 */


import fm.api.rest.promotions.crawler.interfaces.IPromotionCrawlerDAO;
import io.jsonwebtoken.lang.Assert;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("promotionCrawlerDao")
public class PromotionCrawlerDAO implements IPromotionCrawlerDAO {

    private static final Logger LOGGER = LogManager.getLogger(PromotionCrawlerDAO.class);
    private final NamedParameterJdbcTemplate namedTemplate;

    @Autowired
    public PromotionCrawlerDAO(NamedParameterJdbcTemplate namedTemplate){
        Assert.notNull(namedTemplate);
        this.namedTemplate = namedTemplate;

    }

    // Save promotion Value into DB
    @Override
    public boolean savePromotion(PromotionCrawlerModel promoModel) {
        final String sqlStatement=
                "INSERT INTO"
                +"fm_promotions p "
                +"(title,"
                +"content,"
                +"discount,"
                +"start_date,"
                +"end_date,"
                +"category_id,"
                +"bank_id)"
                +"VALUES"
                +"(:title,"
                +":content,"
                +":discount,"
                +":start_date,"
                +":end_date,"
                +":category_id,"
                +":bank_id)";
        final MapSqlParameterSource paramsMap = new MapSqlParameterSource();
        paramsMap.addValue("title",promoModel.getTitle());
        paramsMap.addValue("content",promoModel.getContent());
        paramsMap.addValue("discount",promoModel.getDiscount());
        if(promoModel.getStartDate().equals("")){
            paramsMap.addValue("start_date","Từ Nay");
        }else{
            paramsMap.addValue("start_date",promoModel.getStartDate());
        }
        paramsMap.addValue("end_date",promoModel.getEndDate());
        paramsMap.addValue("category_id","1");
        paramsMap.addValue("bank_id","2");
        namedTemplate.update(sqlStatement,paramsMap);
        return true;
    }

    @Test
    public void getSth(){
        PromotionCrawlerModel model = new PromotionCrawlerModel("Agoda","Giảm thêm 7% khi đặt phòng khách sạn tại www.agoda.com/vib và thanh toán bằng thẻ VIB Khuyến mãi chỉ được áp dụng trên giá trị tiền phòng (không bao gồm thuế địa phương, phí dịch vụ và những chi phí phụ thu khác) Khuyến mãi không được chuyển đổi, cộng dồn và không được sử dụng kết hợp với bất kỳ giảm giá, khuyến mãi, mặt hàng giảm giá và các mặt hàng giá cố định (trừ trường hợp quy định) Khuyến mãi không được chuyển đổi qua thành tiền mặt hoặc bất kì sản phẩm nào khác Chủ thẻ đủ điều kiện tham gia sẽ bị ràng buộc bởi quy định và điều kiện của Agoda và VIB Khuyến mãi được áp dụng cho các khách sạn chấp nhận thanh toán trả trước có thể hiện dòng chữ “Khuyến mại hợp lệ”. Phần giảm giá sẽ được thể hiện tại bước “Chi tiết thanh toán” sau khi chủ thẻ VIB đăng nhập số thẻ hợp lệ. Phần giá trị giảm thể hiện trước dòng thuế & phí dịch vụ của khách sạn & được áp dụng trước khi xác nhận giá cuối cùng của đơn đặt phòng. Ưu đãi này chỉ áp dụng khi khách hàng sử dụng thẻ VIB để thanh toán. Khuyến mãi không áp dụng cho các giao dịch thanh toán trực tiếp tại khách sạn. Chương trình khuyến mãi có thể kết thúc sớm nếu hết ngân sách.","7%","","31/12/2020","1","2","AA","AA","AA","A","AA","A");
        savePromotion(model);
    }
}

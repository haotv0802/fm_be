package fm.api.rest.promotions.crawler;

import fm.api.rest.promotions.PromotionPresenter;
import fm.api.rest.promotions.crawler.interfaces.IPromotionCrawlerDAO;
import fm.api.rest.promotions.crawler.interfaces.IPromotionCrawlerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;

@Service("promotionCrawlerService")
public class PromotionCrawlerService implements IPromotionCrawlerService {
    private IPromotionCrawlerDAO promotionCrawlerDAO;

    @Autowired
    public PromotionCrawlerService(@Qualifier("promotionCrawlerDao") IPromotionCrawlerDAO promotionCrawlerDAO) {
        Assert.notNull(promotionCrawlerDAO);
        this.promotionCrawlerDAO = promotionCrawlerDAO;
    }


    @Override
    public void saveBankPromotion(PromotionCrawlerModel model) {
        promotionCrawlerDAO.savePromotion(model);
    }

    @Override
    public List<PromotionPresenter> getPromotionByBankId(int bankID, int cateID) {
        return promotionCrawlerDAO.getPromotionByBankId(bankID, cateID);
    }
}

package fm.api.rest.promotions.crawler;

import fm.api.rest.promotions.crawler.interfaces.IBankPromotion;
import fm.api.rest.promotions.crawler.interfaces.IPromotionCrawlerService;
import io.jsonwebtoken.lang.Assert;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("bankPromotion")
public class BankPromotion implements IBankPromotion {
    private static final Logger logger = LogManager.getLogger(BankPromotion.class);

    private BankCrawlerFactory bankCrawlerFactory;
    private IPromotionCrawlerService promotionCrawlerService;

    @Autowired
    public BankPromotion(@Qualifier("bankCrawlerFactory") BankCrawlerFactory bankCrawlerFactory,
                         @Qualifier("promotionCrawlerService") IPromotionCrawlerService promotionCrawlerService) {
        Assert.notNull(bankCrawlerFactory);
        Assert.notNull(promotionCrawlerService);
        this.bankCrawlerFactory = bankCrawlerFactory;
        this.promotionCrawlerService = promotionCrawlerService;
    }

    @Override
    public void crawl(String bankName) {
        Map<Integer, List<PromotionCrawlerModel>> map = this.bankCrawlerFactory.getBankCrawler(bankName).crawl();
        /// DAO check, ínert, updte
        // Get Each Infomation of promotion insert into DB
        for (Integer category : map.keySet()) {
            for (PromotionCrawlerModel model : map.get(category)) {
                logger.info("saving promotion {}", model.toString());
                this.promotionCrawlerService.saveBankPromotion(model);
            }

        }
    }

}

package fm.api.rest.promotions.crawler;

import fm.api.rest.promotions.crawler.interfaces.IBankPromotion;
import fm.api.rest.promotions.crawler.interfaces.IBankPromotionCrawler;
import fm.api.rest.promotions.crawler.interfaces.IPromotionCrawlerService;
import io.jsonwebtoken.lang.Assert;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Service("bankPromotion")
public class BankPromotion implements IBankPromotion {
    private static final Logger logger = LogManager.getLogger(BankPromotion.class);

    private final BankCrawlerFactory bankCrawlerFactory;
    private final IPromotionCrawlerService promotionCrawlerService;
    private final ListableBeanFactory beanFactory; // use to load all implementations of IBankPromotionCrawler interfaces

    @Autowired
    public BankPromotion(@Qualifier("bankCrawlerFactory") BankCrawlerFactory bankCrawlerFactory,
                         @Qualifier("promotionCrawlerService") IPromotionCrawlerService promotionCrawlerService,
                         ListableBeanFactory beanFactory) {
        Assert.notNull(bankCrawlerFactory);
        Assert.notNull(promotionCrawlerService);
        Assert.notNull(beanFactory);

        this.bankCrawlerFactory = bankCrawlerFactory;
        this.promotionCrawlerService = promotionCrawlerService;
        this.beanFactory = beanFactory;
    }

    @Override
    public void crawl(String bankName) {
        Map<Integer, List<PromotionCrawlerModel>> data = this.bankCrawlerFactory.getBankCrawler(bankName).crawl();
        saveCrawledData(data);
    }

    @Override
    public void crawlAll() {
        Collection<IBankPromotionCrawler> crawlers = beanFactory.getBeansOfType(IBankPromotionCrawler.class).values();
        Iterator<IBankPromotionCrawler> iterator = crawlers.iterator();

        while (iterator.hasNext()) {
            IBankPromotionCrawler crawler = iterator.next();
            Map<Integer, List<PromotionCrawlerModel>> data =  crawler.crawl();
            saveCrawledData(data);
        }
    }

    private void saveCrawledData(Map<Integer, List<PromotionCrawlerModel>> data) {
        for (Integer category : data.keySet()) {
            for (PromotionCrawlerModel model : data.get(category)) {
                logger.info("saving promotion {}", model.toString());
                this.promotionCrawlerService.saveBankPromotion(model);
            }
        }
    }

}

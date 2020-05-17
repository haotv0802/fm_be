package fm.api.rest.promotions.crawler;

import fm.api.rest.promotions.PromotionPresenter;
import fm.api.rest.promotions.crawler.interfaces.IBankPromotion;
import fm.api.rest.promotions.crawler.interfaces.IBankPromotionCrawler;
import fm.api.rest.promotions.crawler.interfaces.IPromotionCrawlerService;
import io.jsonwebtoken.lang.Assert;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Service("bankPromotion")
public class BankPromotionService implements IBankPromotion {
    private static final Logger logger = LogManager.getLogger(BankPromotionService.class);

    private final BankCrawlerFactory bankCrawlerFactory;
    private final IPromotionCrawlerService promotionCrawlerService;
    private final ListableBeanFactory beanFactory; // use to load all implementations of IBankPromotionCrawler interfaces
    private final ThreadPoolTaskExecutor executor;

    @Autowired
    public BankPromotionService(@Qualifier("bankCrawlerFactory") BankCrawlerFactory bankCrawlerFactory,
                                @Qualifier("promotionCrawlerService") IPromotionCrawlerService promotionCrawlerService,
                                ListableBeanFactory beanFactory,
                                ThreadPoolTaskExecutor executor
    ) {
        Assert.notNull(bankCrawlerFactory);
        Assert.notNull(promotionCrawlerService);
        Assert.notNull(beanFactory);
        Assert.notNull(executor);

        this.bankCrawlerFactory = bankCrawlerFactory;
        this.promotionCrawlerService = promotionCrawlerService;
        this.beanFactory = beanFactory;
        this.executor = executor;
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

    @Override
    public void crawlAllByMultiThreads() {
        Collection<IBankPromotionCrawler> crawlers = beanFactory.getBeansOfType(IBankPromotionCrawler.class).values();
        Iterator<IBankPromotionCrawler> iterator = crawlers.iterator();

        while (iterator.hasNext()) {
            IBankPromotionCrawler crawler = iterator.next();
            this.executor.execute(new CrawlingTask(promotionCrawlerService, crawler));
        }
    }

    @Override
    public List<PromotionPresenter> getCrawledData() {
        return null;
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

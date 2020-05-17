package fm.api.rest.promotions.crawler;

import fm.api.rest.promotions.crawler.interfaces.IBankPromotionCrawler;
import fm.api.rest.promotions.crawler.interfaces.IPromotionCrawlerService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Map;

/**
 * Created by haoho on 5/16/20.
 */
public class CrawlingTask implements Runnable {

    private static final Logger logger = LogManager.getLogger(CrawlingTask.class);

    private IPromotionCrawlerService promotionCrawlerService;
    private IBankPromotionCrawler crawler;

    public CrawlingTask(IPromotionCrawlerService promotionCrawlerService, IBankPromotionCrawler crawler) {
        Assert.notNull(promotionCrawlerService);
        Assert.notNull(crawler);

        this.promotionCrawlerService = promotionCrawlerService;
        this.crawler = crawler;
    }

    @Override
    public void run() {
        logger.info("Start thread {}", crawler.toString());
        Map<Integer, List<PromotionCrawlerModel>> data =  crawler.crawl();
        for (Integer category : data.keySet()) {
            for (PromotionCrawlerModel model : data.get(category)) {
                logger.info("saving promotion {}", model.toString());
                this.promotionCrawlerService.saveBankPromotion(model);
            }
        }
        logger.info("End thread {}", crawler.toString());
    }
}

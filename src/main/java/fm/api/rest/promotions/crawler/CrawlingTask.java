package fm.api.rest.promotions.crawler;

import fm.api.rest.promotions.crawler.interfaces.IBankPromotion;
import fm.api.rest.promotions.crawler.interfaces.IBankPromotionCrawler;
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

    private IBankPromotionCrawler crawler;

    private final IBankPromotion bankPromotion;

    public CrawlingTask(IBankPromotion bankPromotion,
                        IBankPromotionCrawler crawler) {
        Assert.notNull(crawler);
        Assert.notNull(bankPromotion);

        this.crawler = crawler;
        this.bankPromotion = bankPromotion;
    }

    @Override
    public void run() {
        logger.info("Start thread {}", crawler.toString());
        Map<Integer, List<PromotionCrawlerModel>> data = crawler.crawl();
        for (Integer category : data.keySet()) {
            for (PromotionCrawlerModel model : data.get(category)) {
                logger.info("saving promotion {}", model.toString());
                this.bankPromotion.saveCrawledData(data);
            }
        }
        logger.info("End thread {}", crawler.toString());
    }
}

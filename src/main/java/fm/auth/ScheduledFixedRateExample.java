package fm.auth;

import fm.api.rest.subscriptions.SubscriptionDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * Created by HaoHo on 6/8/2020
 */
@Component
@EnableAsync
public class ScheduledFixedRateExample {

    private static final Logger logger = LogManager.getLogger(ScheduledFixedRateExample.class);

    @Async
    @Scheduled(fixedRate = 1000)
    public void scheduleFixedRateTaskAsync() throws InterruptedException {
        logger.info("Fixed rate task async - " + System.currentTimeMillis() / 1000);
        Thread.sleep(2000);
    }

}

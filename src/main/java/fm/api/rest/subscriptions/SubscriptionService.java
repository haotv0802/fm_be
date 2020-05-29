package fm.api.rest.subscriptions;

import fm.utils.CryptoUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;

/**
 * Created by HaoHo on 5/29/2020
 */
@Service("subscriptionService")
public class SubscriptionService implements ISubscriptionService {
    private static final Logger logger = LogManager.getLogger(SubscriptionService.class);

    private final ISubscriptionDao subscriptionDao;

    @Value("${token.secret}")
    private String secret;

    @Autowired
    public SubscriptionService(@Qualifier("subscriptionDao") ISubscriptionDao subscriptionDao) {
        Assert.notNull(subscriptionDao);

        this.subscriptionDao = subscriptionDao;
    }

    @Override
    public List<SubscriptionModel> getSubscribersByType(String type) {
        return subscriptionDao.getSubscribersByType(type);
    }

    @Override
    public void approveSubscription(String encryptedCode) {
        String code = CryptoUtils.decrypt(encryptedCode, secret);
    }

    @Override
    public void subscribe(SubscribePresenter subscribePresenter) {

    }

    private void sendConfirmationCode(String email) {
        String encryptedString = CryptoUtils.encrypt(email, secret);

    }
}

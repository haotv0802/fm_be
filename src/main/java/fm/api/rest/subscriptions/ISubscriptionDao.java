package fm.api.rest.subscriptions;

import java.util.List;

/**
 * Created by HaoHo on 5/29/2020
 */
public interface ISubscriptionDao {
    List<SubscriptionModel> getSubscribersByType(String type);

    void approveSubscriber(String verificationCode);

    void subscribe(SubscriptionModel subscription);
}

package fm.api.rest.subscriptions;

import java.util.List;

/**
 * Created by HaoHo on 5/29/2020
 */
public interface ISubscriptionService {
    List<SubscriptionModel> getSubscribersByType(String type);

    void approveSubscription(String email);

    void subscribe(SubscribePresenter subscribePresenter);
}

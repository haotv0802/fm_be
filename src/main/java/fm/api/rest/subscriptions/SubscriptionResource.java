package fm.api.rest.subscriptions;

import fm.api.rest.BaseResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * Created by HaoHo on 5/29/2020
 */
@RestController
public class SubscriptionResource extends BaseResource {
    private final ISubscriptionService subscriptionService;

    @Autowired
    public SubscriptionResource(@Qualifier("subscriptionService") ISubscriptionService subscriptionService) {
        this.subscriptionService = subscriptionService;
    }

    @GetMapping("/subscription/anonymous/confirmation")
    public ResponseEntity confirm(
            @RequestParam(value = "code", required = true) String code
    ) {
        subscriptionService.approveSubscription(code);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/subscription/anonymous/subscribe")
    public ResponseEntity subscribe(
            @Valid @RequestBody SubscribePresenter subscribe
    ) {
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/subscription/confirmation")
    @PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
    public ResponseEntity confirmByUser(
            @RequestParam(value = "code", required = true) String code
    ) {
        subscriptionService.approveSubscription(code);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}

package ht.api.rest.customer.messages;

import ht.api.rest.customer.BaseCustomerResource;
import ht.auth.UserDetailsImpl;
import ht.common.beans.HeaderLang;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * Created by haho on 6/7/2017.
 */
@RestController("customerMessagesResource")
public class MessagesResource extends BaseCustomerResource {

  private final Logger logger = LogManager.getLogger(getClass());

  @Autowired
  @Qualifier("customerMessagesService")
  private MessagesService messagesService;

  @GetMapping("/messages")
  @PreAuthorize("hasAuthority('CUSTOMER')")
  public Map<String, Map<String, String>> getMessages(
      @AuthenticationPrincipal UserDetailsImpl userDetails,
      @HeaderLang String lang
  ) {
    Map<String, Map<String, String>> maps = messagesService.getCustomerMessages(lang);
    return maps;
  }

}
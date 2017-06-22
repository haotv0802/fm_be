package fm.common.messages;

import fm.api.rest.BaseResource;
import fm.auth.UserDetailsImpl;
import fm.common.beans.HeaderLang;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * Created by haho on 6/7/2017.
 */
@RestController("messagesResource")
public class MessagesResource extends BaseResource {

  private final Logger logger = LogManager.getLogger(getClass());

  private final MessagesService messagesService;

  public MessagesResource(@Qualifier("messagesService") MessagesService messagesService) {
    Assert.notNull(messagesService);

    this.messagesService = messagesService;
  }

  @GetMapping("/messages")
  public Map<String, Map<String, String>> getMessages(
      @AuthenticationPrincipal UserDetailsImpl userDetails,
      @HeaderLang String lang
  ) {
    Map<String, Map<String, String>> maps = messagesService.getMessages(lang);
    logger.info("maps: " + maps);
    return maps;
  }

}
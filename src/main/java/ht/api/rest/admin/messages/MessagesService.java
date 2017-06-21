package ht.api.rest.admin.messages;

import ht.api.rest.admin.messages.interfaces.IMessagesDao;
import ht.api.rest.admin.messages.interfaces.IMessagesService;
import io.jsonwebtoken.lang.Assert;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Created by haho on 6/7/2017.
 */
@Service("adminMessagesService")
public class MessagesService implements IMessagesService {

  private final IMessagesDao messagesDao;

  public MessagesService(@Qualifier("adminMessagesDao") IMessagesDao messagesDao) {
    Assert.notNull(messagesDao);

    this.messagesDao = messagesDao;
  }

  @Override
  public Map<String, Map<String, String>> getAdminMessages(String lang) {
    return this.messagesDao.getMessages(lang);
  }
}
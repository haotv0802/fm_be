package ht.common.messages;

import ht.common.messages.interfaces.IMessagesDao;
import ht.common.messages.interfaces.IMessagesService;
import io.jsonwebtoken.lang.Assert;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Created by haho on 6/7/2017.
 */
@Service("messagesService")
public class MessagesService implements IMessagesService {

  private final IMessagesDao messagesDao;

  public MessagesService(@Qualifier("messagesDao") IMessagesDao messagesDao) {
    Assert.notNull(messagesDao);

    this.messagesDao = messagesDao;
  }

  @Override
  public Map<String, Map<String, String>> getAdminMessages(String lang) {
    return this.messagesDao.getMessages(lang);
  }
}
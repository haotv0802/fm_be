package fm.common.messages;

import fm.common.messages.interfaces.IMessagesDao;
import fm.common.messages.interfaces.IMessagesService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

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
  public Map<String, Map<String, String>> getMessages(String lang) {
    return this.messagesDao.getMessages(lang);
  }
}
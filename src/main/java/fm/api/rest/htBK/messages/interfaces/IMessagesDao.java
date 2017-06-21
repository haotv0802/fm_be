package fm.api.rest.htBK.messages.interfaces;

import java.util.Map;

/**
 * Created by haho on 6/12/2017.
 */
public interface IMessagesDao {
  Map<String, Map<String, String>> getMessages(String language);
}

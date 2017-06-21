package ht.api.rest.admin.messages.interfaces;

import java.util.Map;

/**
 * Created by haho on 6/7/2017.
 */
public interface IMessagesService {
  Map<String, Map<String, String>> getAdminMessages(String lang);
}

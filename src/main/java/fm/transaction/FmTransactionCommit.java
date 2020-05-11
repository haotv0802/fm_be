package fm.transaction;

import javax.servlet.http.HttpSession;

/**
 * Created by haoho
 * Date:  21/12/2016  Time: 10:50
 */
public interface FmTransactionCommit {

  String commitAttribute = "isPossibleToCommit";

  void permitCommit();

  void forbidCommit();

  boolean isCommitPermitted();

  void permitCommit(HttpSession session);

  void forbidCommit(HttpSession session);

  boolean isCommitPermitted(HttpSession session);
}

package fm.transaction;

import javax.servlet.http.HttpSession;

/**
 * Property of CODIX Bulgaria EAD
 * Created by zalexiev
 * Date:  21/12/2016  Time: 10:50
 */
public interface ImxTransactionCommit {

  String commitAttribute = "isPossibleToCommit";

  void permitCommit();

  void forbidCommit();

  boolean isCommitPermitted();

  void permitCommit(HttpSession session);

  void forbidCommit(HttpSession session);

  boolean isCommitPermitted(HttpSession session);
}

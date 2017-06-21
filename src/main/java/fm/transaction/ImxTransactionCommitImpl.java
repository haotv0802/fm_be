package fm.transaction;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.web.context.request.RequestContextHolder;

import javax.servlet.http.HttpSession;

import static org.springframework.web.context.request.RequestAttributes.SCOPE_SESSION;

/**
 * Property of CODIX Bulgaria EAD
 * Created by zalexiev
 * Date:  21/12/2016  Time: 10:54
 */
@Service
public class ImxTransactionCommitImpl implements ImxTransactionCommit {

  private Logger logger = LogManager.getLogger(getClass());

  @Override
  public void permitCommit() {

    RequestContextHolder
        .currentRequestAttributes()
        .setAttribute(commitAttribute, true, SCOPE_SESSION);

    logger.debug("Commit is set to permitted for session {}", RequestContextHolder.currentRequestAttributes().getSessionId());
  }

  @Override
  public void forbidCommit() {
    RequestContextHolder
        .currentRequestAttributes()
        .setAttribute(commitAttribute, false, SCOPE_SESSION);

    logger.debug("Commit is set to forbidden for session {}", RequestContextHolder.currentRequestAttributes().getSessionId());
  }

  @Override
  public boolean isCommitPermitted() {

    String sessionID = RequestContextHolder.currentRequestAttributes().getSessionId();

    Object attr = RequestContextHolder
        .currentRequestAttributes()
        .getAttribute(commitAttribute, SCOPE_SESSION);

    Assert.notNull(attr, commitAttribute + "not set!");
    Assert.isInstanceOf(Boolean.class, attr, commitAttribute + "is not boolean!");

    boolean isPermitted = (boolean) attr;

    if (isPermitted)
      logger.debug("Commit is permitted for session {}", sessionID);
    else
      logger.debug("Commit is forbidden for session {}", sessionID);

    return isPermitted;
  }

  @Override
  public void permitCommit(HttpSession session) {
    Assert.notNull(session);

    session.setAttribute(commitAttribute, true);

    logger.debug("Commit is set to permitted for session {}", session.getId());
  }

  @Override
  public void forbidCommit(HttpSession session) {
    Assert.notNull(session);

    session.setAttribute(commitAttribute, false);

    logger.debug("Commit is set to forbidden for session {}", session.getId());
  }

  @Override
  public boolean isCommitPermitted(HttpSession session) {
    Assert.notNull(session);

    Object attr = session.getAttribute(commitAttribute);

    Assert.notNull(attr, commitAttribute + "not set!");

    boolean isCommitPermited = (boolean) attr;

    if (isCommitPermited)
      logger.debug("Commit is set to permitted for session {}", session.getId());
    else
      logger.debug("Commit is set to forbidden for session {}", session.getId());

    return isCommitPermited;
  }
}

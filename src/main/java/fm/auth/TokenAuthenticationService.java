package fm.auth;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataAccessException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Calendar;
import java.util.Date;

@Service
public class TokenAuthenticationService {
  private static final Logger logger = LogManager.getLogger(TokenAuthenticationService.class);

  private final TokenHandler tokenHandler;

  @Autowired
  final private LoginDao loginDao;

  @Autowired
  public TokenAuthenticationService(@Value("${token.secret}") String secret, LoginDao loginDao) {
    tokenHandler = new TokenHandler(secret);
    this.loginDao = loginDao;
  }

  public void addAuthentication(HttpServletResponse response, Authentication authentication) {
    final UserDetails user = (UserDetails) authentication.getPrincipal();
    final Date expDate = computeExpirationDate();

    final Integer tokenId = loginDao.storeUserDetailsToToken(TokenType.ACCESS, user, expDate);

    response.addHeader(AuthConstants.AUTH_HEADER_NAME, tokenHandler.createTokenForUser(tokenId, user, expDate));
  }

  private Date computeExpirationDate() {
    Calendar c = Calendar.getInstance();
    c.add(Calendar.DAY_OF_YEAR, 1);
    return c.getTime();
  }

  public Authentication getAuthentication(HttpServletRequest request) {
    final String token = resolveToken(request);
    if (token != null) {
      try {
        final Integer tokenId = tokenHandler.getAuthId(token);

        if (null != tokenId) {
          final UserDetails userDetails = loginDao.readUserDetailsFromToken(tokenId);
          if (userDetails != null) {
            return new UsernamePasswordAuthenticationToken(userDetails, userDetails.getPassword(), userDetails.getAuthorities());
          }
        }
      } catch (DataAccessException e) {
        logger.debug(e.getMessage(), e);
      }
    }

    logger.warn("Auth token not extracted from header");
    return null;
  }

  private String resolveToken(HttpServletRequest request) {
    String token = request.getHeader(AuthConstants.AUTH_HEADER_NAME);
    if (null == token) {
      token = request.getParameter(AuthConstants.AUTH_HEADER_NAME);
      if (null == token) {
        token = request.getParameter(AuthConstants.AUTH_HEADER_NAME.toLowerCase());
      }
    }

    if (logger.isDebugEnabled()) {
      logger.debug("Resolved token" + token);
    }

    return token;
  }

}
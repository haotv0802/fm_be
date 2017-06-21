package fm.auth;

import io.jsonwebtoken.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Date;

public class TokenHandler {
  private static final Logger logger = LogManager.getLogger(TokenHandler.class);

  private static final SignatureAlgorithm DEFAULT_ALGO = SignatureAlgorithm.HS512;

  private final String secret;


  public TokenHandler(String secret) {
    this.secret = secret;
  }


  public String createTokenForUser(Integer id, UserDetails auth, Date expDate) {
    final String compact = Jwts.builder()
        .setSubject(auth.getUsername())
        .setExpiration(expDate)
        .setId(encryptId(id))
        .signWith(DEFAULT_ALGO, secret)
        .compact();

    if (logger.isDebugEnabled()) {
      logger.debug(compact);
    }
    return compact;
  }

  private String encryptId(Integer plainId) {
    //todo ecrypt it
    return Integer.toString(plainId);
  }

  /**
   * Gets authentication id from token
   * <p>
   * TODO: proper exception handling, as this is called in filter and exceptions are not hitting global handlers
   *
   * @param token
   * @return
   */
  public Integer getAuthId(String token) {

    Integer id = null;
    try {
      String tokenId =
          Jwts.parser()
              .setSigningKey(secret)
              .parseClaimsJws(token)
              .getBody()
              .getId();

      id = decryptId(tokenId);
    } catch (ExpiredJwtException | UnsupportedJwtException | MalformedJwtException | IllegalArgumentException | SignatureException e) {
      logger.error(e.getMessage(), e);
    }

    return id;
  }

  private Integer decryptId(String encId) {
    //todo decrypt
    return Integer.parseInt(encId);
  }

}

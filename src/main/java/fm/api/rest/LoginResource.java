package fm.api.rest;

import fm.auth.*;
import fm.common.beans.HeaderLang;
import io.jsonwebtoken.lang.Assert;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by haho on 3/3/2017.
 */
@RestController
@RequestMapping(path = "/svc")
public class LoginResource {

  private static final Logger logger = LogManager.getLogger(LoginResource.class);

  @Autowired
  private TokenAuthenticationService tokenAuthenticationService;

  @Autowired
  private LoginDao loginDao;

  @PostMapping("/login")
  public ResponseEntity login(@RequestBody Credentials credentials, HttpServletRequest request, HttpServletResponse response) {
    Assert.notNull(credentials);
    logger.info("In Resource");
    try {
//      CredentialsResult result =
          loginDao.checkCredentials(credentials);
    } catch (Exception e) {
      return new ResponseEntity(HttpStatus.UNAUTHORIZED);
    }

    UserDetailsImpl userDetails = loginDao.findOneByUsername(credentials.getUserName());
    UsernamePasswordAuthenticationToken authentication =
        new UsernamePasswordAuthenticationToken(userDetails, userDetails.getPassword(), userDetails.getAuthorities());

    tokenAuthenticationService.addAuthentication(response, authentication);
//    return new ResponseEntity(HttpStatus.OK);
    return new ResponseEntity(userDetails.getAuthorities(), HttpStatus.OK);
  }
}

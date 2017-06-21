package fm.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

@Component("authenticationProvider")
public class DaoAuthenticationProviderImpl extends DaoAuthenticationProvider {

  @Autowired
  @Qualifier("authService")
  @Override
  public void setUserDetailsService(UserDetailsService userDetailsService) {
    super.setUserDetailsService(userDetailsService);
  }

  @Autowired
  @Qualifier("pwdEncoder")
  @Override
  public void setPasswordEncoder(Object passwordEncoder) {
    super.setPasswordEncoder(passwordEncoder);
  }

  @Autowired
  @Qualifier("hideUserNotFound")
  public void setHideUserNotFoundExceptions(Boolean hideUserNotFound) {
    super.setHideUserNotFoundExceptions(hideUserNotFound);
  }

  @Override
  public Authentication authenticate(Authentication authentication) throws AuthenticationException {
    final Authentication authResult;
    try {
      authResult = super.authenticate(authentication);

//      final UserDetailsImpl principal = (UserDetailsImpl)authResult.getPrincipal();
//      final UserTokenDetails details = (UserTokenDetails) authentication.getDetails();

//      String tty;
//      if (systemSettings.isFixedIp()) {
//        tty = loginDao.getUserTTYByPermHostSessId(details.getRemoteAddress());
//      }
//      else {
//        tty = loginDao.getUserTTYByUserName(principal.getUsername());
//      }
//      principal.setTty(tty);
//
//      loginDao.unblockUserAccount(principal.getUsername());
//      loginDao.deleteEnvRecord(details);
//      loginDao.createEnvRecord(principal,  details);

      return authResult;
    } catch (BadCredentialsException e) {

//      final long now = new Date().getTime();
//      final String userName = authentication.getName();
//      final PasswordSettings userPasswordSettings = passwordSettings.getUserPasswordSettings(userName);
//
//      final int softLimitAttempts = userPasswordSettings.getSoftLimitAttempts();
//      final int hardLimitAttempts = userPasswordSettings.getHardLimitAttempts();
//      final long blockInMillis = userPasswordSettings.getBlockPeriod() * 60 * 1000;
//
//      if (softLimitAttempts > 0 || hardLimitAttempts > 0) {
//        final int failedAttempts = loginDao.incLoginFailures(userName);
//
//        if (failedAttempts > softLimitAttempts && failedAttempts <= hardLimitAttempts) {
//          throw new SoftLimitReachedException(String.format("Soft limit(%s) reached, attempts: %s", softLimitAttempts, failedAttempts ));
//        }
//
//        if (failedAttempts > hardLimitAttempts) {
//          loginDao.blockUserAccount(userName, new Date(now + blockInMillis));
//          throw new HardLimitReachedException(String.format("Hard limit(%s) reached, attempts: %s", hardLimitAttempts, failedAttempts ));
//        }
//      }

      throw e;
    } catch (LockedException e) {

//      //this user is locked!
//      String error = "";
//      UserAttempts userAttempts = userDetailsDao.getUserAttempts(authentication.getName());
//
//      if(userAttempts!=null){
//        Date lastAttempts = userAttempts.getLastModified();
//        error = "User account is locked! <br><br>Username : " + authentication.getName() + "<br>Last Attempts : " + lastAttempts;
//      }else{
//        error = e.getMessage();
//      }

      //throw new LockedException(error);
      throw new LockedException("bliah");
    }
  }
}

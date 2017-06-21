package fm.auth;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.authentication.encoding.BasePasswordEncoder;

public class PasswordEncoderImpl extends BasePasswordEncoder
{
  private static final Logger logger = LogManager.getLogger(PasswordEncoderImpl.class);

  private final org.springframework.security.crypto.password.PasswordEncoder delegate;

  public PasswordEncoderImpl(org.springframework.security.crypto.password.PasswordEncoder pwdEncoder)
  {
    this.delegate = pwdEncoder;
  }

  @Override
  public String encodePassword(String rawPass, Object salt)
  {
    return delegate.encode(rawPass);
  }

  /**
   * Code nesting
   * <code>if (!passwordEncoder.isPasswordValid(userDetails.getPassword(), presentedPassword, salt)) {<code/>
   // TODO this is only for start up, as the form should provide a digested password and not on reverse
   *
   *
   * @param encPass
   * @param rawPass
   * @param salt
   * @return
   */
  @Override
  public boolean isPasswordValid(String encPass, String rawPass, Object salt)
  {
    return delegate.matches(encPass, delegate.encode(rawPass));
  }

  /**
   * Hashes a string using the default imx algorithm
   *
   * @param plain - the plain string to hash
   * @return hash of the plain string
   */
  final public static String dafaultHash(String plain)
  {
    String ret = "";
    byte[] bytePass = plain.getBytes();
    byte[] b = new byte[1];
    String tmp1, tmp2;
    int v1, ascii;
    for (int i = 0; i < bytePass.length; i++) {
      v1 = (int) Math.floor(bytePass[i] / 16);
      if (v1 > 9) {
        ascii = 'a' + v1 - 10;
      }
      else {
        ascii = '0' + v1;
      }
      b[0] = (byte) ascii;
      tmp1 = new String(b);
      v1 = bytePass[i] - 16 * v1;
      if (v1 > 9) {
        ascii = 'a' + v1 - 10;
      }
      else {
        ascii = '0' + v1;
      }
      b[0] = (byte) ascii;
      tmp2 = new String(b);
      ret = tmp2 + ret + tmp1;
    }
    return ret;
  }
//
//  /**
//   * Constant time comparison to prevent against timing attacks.
//   */
//  private boolean matches(byte[] expected, byte[] actual) {
//    if (expected.length != actual.length) {
//      return false;
//    }
//
//    int result = 0;
//    for (int i = 0; i < expected.length; i++) {
//      result |= expected[i] ^ actual[i];
//    }
//    return result == 0;
//  }
}

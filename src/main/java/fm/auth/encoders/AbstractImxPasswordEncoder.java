package fm.auth.encoders;

import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Created by glengarski on 04/11/2016.
 */
public abstract class AbstractImxPasswordEncoder implements PasswordEncoder {

  abstract public String encode(CharSequence rawPassword);

  /**
   * Constant time comparison to prevent against timing attacks.
   *
   * @param expectedPwd
   * @param actualPwd
   * @return
   */
  @Override
  public boolean matches(CharSequence expectedPwd, String actualPwd) {

    byte expected[] = expectedPwd.toString().getBytes();
    byte actual[] = actualPwd.toString().getBytes();
    if (expected.length != actual.length) {
      return false;
    }

    int result = 0;
    for (int i = 0; i < expected.length; i++) {
      result |= expected[i] ^ actual[i];
    }
    return result == 0;
  }
}

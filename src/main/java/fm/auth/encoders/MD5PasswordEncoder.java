package fm.auth.encoders;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by glengarski on 04/11/2016.
 */
public class MD5PasswordEncoder extends AbstractImxPasswordEncoder {

  @Override
  public String encode(CharSequence rawPassword) {
    MessageDigest digest = null;
    try {
      digest = MessageDigest.getInstance("MD5");
    } catch (NoSuchAlgorithmException e) {
      e.printStackTrace();
    }
    byte[] array = digest.digest(rawPassword.toString().getBytes());
    StringBuffer sb = new StringBuffer();
    for (int i = 0; i < array.length; ++i) {
      sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1, 3));
    }
    return sb.toString().toUpperCase();
  }
}

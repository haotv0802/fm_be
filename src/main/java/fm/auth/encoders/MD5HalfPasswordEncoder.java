package fm.auth.encoders;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by glengarski on 04/11/2016.
 */
public class MD5HalfPasswordEncoder extends AbstractImxPasswordEncoder {

  @Override
  public String encode(CharSequence rawPassword) {
    MessageDigest digest = null;
    try {
      digest = MessageDigest.getInstance("MD5");
    } catch (NoSuchAlgorithmException e) {
      e.printStackTrace();
    }
    byte[] array = digest.digest(rawPassword.toString().getBytes());
    StringBuffer md5Hash = new StringBuffer();
    for (int i = 0; i < array.length; ++i) {
      md5Hash.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1, 3));
    }
    String fullMD5Hash = md5Hash.toString().toUpperCase();


    StringBuilder sb = new StringBuilder();

    for (int i = 0; i < fullMD5Hash.length(); i += 4) {
      sb.append(fullMD5Hash.substring(i, i + 2));
    }

    return sb.toString();

  }
}

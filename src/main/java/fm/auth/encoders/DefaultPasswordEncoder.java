package fm.auth.encoders;

/**
 * Created by glengarski on 04/11/2016.
 */
public class DefaultPasswordEncoder extends AbstractImxPasswordEncoder {
  @Override
  public String encode(CharSequence rawPassword) {
    String ret = "";
    byte[] bytePass = rawPassword.toString().getBytes();
    byte[] b = new byte[1];
    String tmp1, tmp2;
    int v1, ascii;
    for (int i = 0; i < bytePass.length; i++) {
      v1 = (int) Math.floor(bytePass[i] / 16);
      if (v1 > 9) {
        ascii = 'a' + v1 - 10;
      } else {
        ascii = '0' + v1;
      }
      b[0] = (byte) ascii;
      tmp1 = new String(b);
      v1 = bytePass[i] - 16 * v1;
      if (v1 > 9) {
        ascii = 'a' + v1 - 10;
      } else {
        ascii = '0' + v1;
      }
      b[0] = (byte) ascii;
      tmp2 = new String(b);
      ret = tmp2 + ret + tmp1;
    }
    return ret;
  }
}

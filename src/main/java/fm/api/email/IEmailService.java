package fm.api.email;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

public interface IEmailService {
    void sendEmail(String title, String content) throws MessagingException, InterruptedException;

    public void sendEmail(String to, String title, String content) throws MessagingException, InterruptedException;

    void sendEmail(String from, String fromPass, String to, String title, String content) throws MessagingException, InterruptedException;
}

package fm.api.rest.email;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * Created by HaoHo on 5/28/2020
 */
@Service("emailService")
public class EmailService implements IEmailService {
    static Properties mailServerProperties;
    static Session getMailSession;
    static MimeMessage generateMailMessage;
    static int count = 1;
    private static final Logger logger = LogManager.getLogger(EmailService.class);

    private final IEmailHistoryDao emailHistoryDao;

    @Autowired
    public EmailService(@Qualifier("emailHistoryDao") IEmailHistoryDao emailHistoryDao) {
        Assert.notNull(emailHistoryDao);

        this.emailHistoryDao = emailHistoryDao;
    }

    @Override
    public void sendEmail(String title, String content) {
        sendEmail("emailtest180115@gmail.com", "wfuynbpmxjylscgo", "hoanhhao@gmail.com", title, content);
    }

    @Override
    public void sendEmail(String to, String title, String content) {
        sendEmail("emailtest180115@gmail.com", "wfuynbpmxjylscgo", to, title, content);
    }

    @Override
    public void sendEmail(String from, String fromPass, String to, String title, String content) {
        Email email = new Email(); // tracking in history
        email.setTo(from);
        email.setFrom(to);
        email.setContent(content);
        email.setTitle(title);


        try {
            // Step1
            logger.info("\n 1st ===> setup Mail Server Properties..");
            mailServerProperties = System.getProperties();
            mailServerProperties.put("mail.smtp.port", "587");
            mailServerProperties.put("mail.smtp.auth", "true");
            mailServerProperties.put("mail.smtp.starttls.enable", "true");
            logger.info("Mail Server Properties have been setup successfully..");

            // Step2
            logger.info("\n\n 2nd ===> get Mail Session..");
            getMailSession = Session.getDefaultInstance(mailServerProperties, null);
            generateMailMessage = new MimeMessage(getMailSession);
            generateMailMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

            generateMailMessage.setSubject(title);
            String emailBody = content;
            generateMailMessage.setContent(emailBody, "text/html; charset=utf-8");
            logger.info("Mail Session has been created successfully..");

            // Step3
            logger.info("\n\n 3rd ===> Get Session and Send mail");
            Transport transport = getMailSession.getTransport("smtp");

            transport.connect("smtp.gmail.com", from, fromPass);
            Thread.sleep(1000);  // 2 secs
            count++;
            transport.sendMessage(generateMailMessage, generateMailMessage.getAllRecipients());
            transport.close();
            email.setStatus("SENT");
        } catch (AddressException ex) {
            logger.error(ex.getMessage(), ex);
            email.setStatus("ERROR");
        } catch (MessagingException ex) {
            logger.error(ex.getMessage(), ex);
            email.setStatus("ERROR");
        } catch (InterruptedException ex) {
            logger.error(ex.getMessage(), ex);
            email.setStatus("ERROR");
        } finally {
            emailHistoryDao.addSentEmail(email); // TODO check if the same email is sent in short period of time.
        }
    }

//    public static void main(String[] args) throws MessagingException, InterruptedException {
//        EmailService emailService = new EmailService();
//        emailService.sendEmail(FmLocalDateUtils.format(LocalDateTime.now()), FmLocalDateUtils.format(LocalDateTime.now()));
//        emailService.sendEmail("haotv0802@gmail.com", "to Hao TV" + FmLocalDateUtils.format(LocalDateTime.now()), "content for Hao TV" + FmLocalDateUtils.format(LocalDateTime.now()));
//    }
}

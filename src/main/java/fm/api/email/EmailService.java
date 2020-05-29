package fm.api.email;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
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

    @Override
    public void sendEmail(String title, String content) throws MessagingException, InterruptedException {
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
        generateMailMessage.addRecipient(Message.RecipientType.TO, new InternetAddress("hoanhhao@gmail.com"));

        generateMailMessage.setSubject(title);
        String emailBody = content;
        generateMailMessage.setContent(emailBody, "text/html; charset=utf-8");
        logger.info("Mail Session has been created successfully..");

        // Step3
        logger.info("\n\n 3rd ===> Get Session and Send mail");
        Transport transport = getMailSession.getTransport("smtp");

        // Enter your correct gmail UserID and Password
        // if you have 2FA enabled then provide App Specific Password
//      if (count >= 0 && count <= 100) {
//        transport.connect("smtp.gmail.com", "salomon3000@gmail.com", "vswauhzfgslzowmv");
//      } else  if (count > 100 && count <= 200) {
//        transport.connect("smtp.gmail.com", "emailtest180115@gmail.com", "wfuynbpmxjylscgo");
//      }
//      if (count % 100 == 0) {
//        System.out.println("Sleeping");
//        Thread.sleep(1000 * 60 * 2);  // 2 mins
//      }
        transport.connect("smtp.gmail.com", "emailtest180115@gmail.com", "wfuynbpmxjylscgo");
//    transport.connect("smtp.gmail.com", "salomon3000@gmail.com", "vswauhzfgslzowmv");
        Thread.sleep(1000);  // 2 secs
        count++;
        transport.sendMessage(generateMailMessage, generateMailMessage.getAllRecipients());
        transport.close();
    }

    @Override
    public void sendEmail(String to, String title, String content) throws MessagingException, InterruptedException {
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

        transport.connect("smtp.gmail.com", "emailtest180115@gmail.com", "wfuynbpmxjylscgo");
        Thread.sleep(1000);  // 2 secs
        count++;
        transport.sendMessage(generateMailMessage, generateMailMessage.getAllRecipients());
        transport.close();
    }

    @Override
    public void sendEmail(String from, String fromPass, String to, String title, String content) throws MessagingException, InterruptedException {
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
    }

    public static void main(String[] args) throws MessagingException, InterruptedException {
        EmailService emailService = new EmailService();
        emailService.sendEmail("hao234", "hao234");
        emailService.sendEmail("haotv0802@gmail.com", "to Hao TV", "content for Hao TV");
    }
}

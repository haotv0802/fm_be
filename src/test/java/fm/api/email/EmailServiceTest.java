//package fm.api.email;
//
//import fm.api.rest.BaseDocumentation;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.transaction.annotation.Transactional;
//import org.testng.annotations.Test;
//
//import javax.mail.MessagingException;
//import javax.mail.internet.AddressException;
//
///**
// * Created by HaoHo on 5/29/2020
// */
//
//@RunWith(SpringRunner.class)
//@Transactional
//
//public class EmailServiceTest {
//
//    @Qualifier("emailService")
//    @Autowired
//    private IEmailService emailService;
//
//    @Test
//    public void generateAndSendEmail() throws AddressException, MessagingException, InterruptedException {
//        this.emailService.sendEmail("hao", "hao");
//    }
//}

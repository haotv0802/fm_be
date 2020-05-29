package fm.api.rest.email;

public interface IEmailService {
    void sendEmail(String title, String content);

    void sendEmail(String to, String title, String content);

    void sendEmail(String from, String fromPass, String to, String title, String content);
}

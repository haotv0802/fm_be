package fm.api.rest.email;

public interface IEmailHistoryDao {
    void addSentEmail(Email email);

    Email getEmail(String from, String to, String title, String content);
}

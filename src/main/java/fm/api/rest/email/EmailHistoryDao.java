package fm.api.rest.email;

import fm.api.rest.bank.BankPresenter;
import fm.common.dao.DaoUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

/**
 * Created by HaoHo on 5/29/2020
 */
@Repository("emailHistoryDao")
public class EmailHistoryDao implements IEmailHistoryDao {

    private static final Logger logger = LogManager.getLogger(EmailHistoryDao.class);

    private final NamedParameterJdbcTemplate namedTemplate;

    @Autowired
    public EmailHistoryDao(NamedParameterJdbcTemplate namedTemplate) {
        Assert.notNull(namedTemplate);

        this.namedTemplate = namedTemplate;
    }

    @Override
    public void addSentEmail(Email email) {
        final String sql = ""
                + "INSERT INTO          "
                + " fm_email_history (  "
                + "    from_email,      "
                + "    to_email,        "
                + "    title,           "
                + "    content,         "
                + "    status)          "
                + " VALUES (            "
                + "    :from,           "
                + "    :to,             "
                + "    :title,          "
                + "    :content,        "
                + "    :status)         ";

        final MapSqlParameterSource paramsMap = new MapSqlParameterSource();
        paramsMap.addValue("from", email.getFrom());
        paramsMap.addValue("to", email.getTo());
        paramsMap.addValue("title", email.getTitle());
        paramsMap.addValue("content", email.getContent());
        paramsMap.addValue("status", email.getStatus());

        DaoUtils.debugQuery(logger, sql, paramsMap.getValues());

        KeyHolder keyHolder = new GeneratedKeyHolder();
        namedTemplate.update(sql, paramsMap, keyHolder);
        final Integer id = keyHolder.getKey().intValue();
    }

    @Override
    public Email getEmail(String from, String to, String title, String content) {
        final String sql = ""
                + "SELECT                   "
                + " from_email,             "
                + " to_email,               "
                + " title,                  "
                + " status,                 "
                + " created,                "
                + " content                 "
                + "FROM                     "
                + " fm_email_history        "
                + "WHERE                    "
                + " from_email = :from      "
                + " AND to_email = :to      "
                + " AND title = :title      "
                + " AND content = :content  ";

        final MapSqlParameterSource paramsMap = new MapSqlParameterSource();
        paramsMap.addValue("from", from);
        paramsMap.addValue("to", to);
        paramsMap.addValue("title", title);
        paramsMap.addValue("content", content);

        DaoUtils.debugQuery(logger, sql, paramsMap.getValues());

        return namedTemplate.queryForObject(sql, paramsMap, (rs, rowNum) -> {
                    Email email = new Email();
            email.setTitle(rs.getString("from_email"));
            email.setFrom(rs.getString("to_email"));
            email.setTo(rs.getString("address"));
            email.setContent(rs.getString("website"));
                    return email;
                }
        );
    }
}

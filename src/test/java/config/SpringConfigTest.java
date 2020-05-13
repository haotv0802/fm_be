package config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mysql.cj.jdbc.MysqlDataSource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.sql.DataSource;
import java.sql.SQLException;

@Configuration
public class SpringConfigTest {

  private final Logger LOGGER = LogManager.getLogger(getClass());

  @Autowired
  private NamedParameterJdbcTemplate namedTemplate;

  @Bean(name = "tstMsgSource")
  public MessageSource messageSource() {
    ResourceBundleMessageSource ms = new ResourceBundleMessageSource();
    ms.setBasenames(
        "i18n.LoginResource"
    );
    ms.setUseCodeAsDefaultMessage(true);
    return ms;
  }

  @Bean(name = "testObjectMapper")
  public ObjectMapper testObjectMapper() {
    return new ObjectMapper();
  }
}

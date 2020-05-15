package config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

@Configuration
public class SpringConfigTest {

    private static final Logger logger = LogManager.getLogger(SpringConfigTest.class);

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

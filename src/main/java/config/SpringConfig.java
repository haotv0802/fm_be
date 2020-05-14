package config;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.hazelcast.config.Config;
import com.hazelcast.config.NetworkConfig;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.mysql.cj.jdbc.MysqlDataSource;
import fm.auth.LoggingEnhancingFilter;
import fm.auth.PasswordEncoderImpl;
import fm.auth.TokenAuthenticationService;
import fm.auth.encoders.DefaultPasswordEncoder;
import fm.auth.encoders.MD5HalfPasswordEncoder;
import fm.auth.encoders.MD5PasswordEncoder;
import fm.auth.encoders.SHA1PasswordEncoder;
import fm.auth.filters.*;
import fm.common.HeaderLangHandlerMethodArgumentResolver;
import fm.transaction.ConnectionsWatchdog;
import fm.transaction.TransactionFilter;
import fm.transaction.TransactionsList;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.data.web.SortHandlerMethodArgumentResolver;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.channel.ChannelProcessingFilter;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.HttpStatusReturningLogoutSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.session.hazelcast.config.annotation.web.http.EnableHazelcastHttpSession;
import org.springframework.session.web.http.HeaderHttpSessionStrategy;
import org.springframework.session.web.http.HttpSessionStrategy;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.TimeUnit;

//Marks this class as configuration
@Configuration
//Enable API documentation
// Specifies which package to scan
@ComponentScan({"fm"}) // xml config: <context:component-scan base-package="fm"/>
// Enables Spring's annotations
@EnableWebMvc
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class SpringConfig extends WebMvcConfigurerAdapter {

  private static final Logger logger = LogManager.getLogger(SpringConfig.class);

  @Autowired
  private Environment env;

  private static final long sessionTimeoutInSec = 1800L;

  public SpringConfig() {
  }

  @Bean
  public MappingJackson2HttpMessageConverter customJackson2HttpMessageConverter() {
    MappingJackson2HttpMessageConverter jsonConverter = new MappingJackson2HttpMessageConverter();

    ObjectMapper objectMapper = new ObjectMapper();
    objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, true);
    objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

    jsonConverter.setObjectMapper(objectMapper);
    return jsonConverter;
  }

  @Override
  public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
    converters.add(customJackson2HttpMessageConverter());
  }

  @Bean(name = "dataSource")
  public DataSource dataSource() throws SQLException {
    final String databaseUrl = "jdbc:mysql://localhost:3306/finance_management?useLegacyDatetimeCode=false&serverTimezone=Asia/Ho_Chi_Minh";
//    final String databaseUrl = env.getProperty("database.url");
    final String usr = "root";
    final String pass = "fmsystem123";

    logger.debug("databaseUrl=={}", databaseUrl);

    MysqlDataSource ds = new MysqlDataSource();
    ds.setURL(databaseUrl);
    ds.setUser(usr);
    ds.setPassword(pass);

    return ds;
//    return new ManagedDataSourceProxy(ds);
  }

  @Bean(name = "txManager")
  public PlatformTransactionManager txManager() throws SQLException {
    return new DataSourceTransactionManager(dataSource());
  }

  @Bean(name = "transactionTemplate")
  public TransactionTemplate transactionTemplate() throws SQLException {
    return new TransactionTemplate(txManager());
  }


  @Override
  public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
    configurer.defaultContentType(MediaType.APPLICATION_JSON);
  }

  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    super.addInterceptors(registry);
  }


  @Bean(name = "multipartResolver")
  public CommonsMultipartResolver createMultipartResolver() {
    CommonsMultipartResolver resolver = new CommonsMultipartResolver();
    resolver.setDefaultEncoding("utf-8");
    resolver.setMaxUploadSize(1000000);
    return resolver;
  }

  /**
   * RestControllers argument injections
   * 1) Paging and Sorting arguments
   * 2) HTLang arguments
   *
   * @param argumentResolvers list af argumentResolvers
   */
  @Override
  public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
    PageableHandlerMethodArgumentResolver resolver = new PageableHandlerMethodArgumentResolver();
    resolver.setOneIndexedParameters(true);
    resolver.setFallbackPageable(new PageRequest(0, 25));
    argumentResolvers.add(resolver);

    argumentResolvers.add(new SortHandlerMethodArgumentResolver());
    argumentResolvers.add(new HeaderLangHandlerMethodArgumentResolver());

    super.addArgumentResolvers(argumentResolvers);
  }

  @Configuration
  @EnableHazelcastHttpSession(maxInactiveIntervalInSeconds = (int) sessionTimeoutInSec, sessionMapName = "spring:session:sessions")
  protected static class SessionConfig {
    @Bean
    public HazelcastInstance embeddedHazelcast() {
      Config cfg = new Config();
      cfg.setProperty("hazelcast.logging.type", "slf4j");
      final NetworkConfig netConfig = cfg.getNetworkConfig();
      netConfig.getJoin().getTcpIpConfig().setEnabled(false);
      netConfig.getJoin().getMulticastConfig().setEnabled(false);

      return Hazelcast.newHazelcastInstance(cfg);
    }

    @Bean
    public HttpSessionStrategy httpSessionStrategy() {
      return new HeaderHttpSessionStrategy();
    }

  }

  @Bean
  public TransactionFilter txFilter() {
    return new TransactionFilter();
  }

  @Bean(destroyMethod = "stop")
  public ConnectionsWatchdog connectionsWatchdog() {
    TransactionsList transactions = TransactionsList.getInstance();
    ConnectionsWatchdog watcher = new ConnectionsWatchdog(TimeUnit.SECONDS.toMillis(60), transactions);
    Thread watcherThread = new Thread(watcher);
    watcherThread.setDaemon(true);
    watcherThread.start();

    return watcher;
  }

  @Bean(name = "jdbcTemplate")
  public JdbcTemplate jdbcTemplate()
      throws SQLException {
    return new JdbcTemplate(dataSource());
  }

  @Bean(name = "namedTemplate")
  public NamedParameterJdbcTemplate namedTemplate()
      throws SQLException {
    return new NamedParameterJdbcTemplate(dataSource());
  }

  @Configuration
  @EnableWebSecurity
  @EnableGlobalMethodSecurity(prePostEnabled = true) // ENABLE @PreAuthorize & @PostAuthorize annotations
                                                     // <security:global-method-security pre-post-annotations="enabled" />
  protected static class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    PlatformTransactionManager txManager;

    @Resource(name = "authService")
    private UserDetailsService userDetailsService;

    @Autowired
    private RestAuthenticationEntryPoint restAuthenticationEntryPoint;

    @Autowired
    private TokenAuthenticationService tokenAuthenticationService;

    @Autowired
    private CorsFilter corsFilter;

    @Autowired
    private AccessDeniedHandlerImpl accessDeniedHandlerImpl;

    @Bean(name = "messageSource")
    public MessageSource messageSource() {
      ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
      messageSource.setBasenames(
          "i18n.LoginResource",
          "i18n.admin_image",
          "i18n.admin_messages",
          "i18n.moneyflow"
      );
      messageSource.setUseCodeAsDefaultMessage(true);
      return messageSource;
    }

    @Bean
    public AuthenticationFailureHandlerImpl customizedAuthenticationFailureHandler() {
      return new AuthenticationFailureHandlerImpl(messageSource());
    }

    @Bean
    public AuthenticationSuccessHandlerImpl customizedAuthenticationSuccessHandler() {
      return new AuthenticationSuccessHandlerImpl();
    }

    @Override
    protected UserDetailsService userDetailsService() {
      return userDetailsService;
    }

    @Bean
    public AccessDeniedHandlerImpl customizedAccessDeniedHandler() {
      return new AccessDeniedHandlerImpl(messageSource());
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
      http
          .sessionManagement()
          .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//          .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
//          .maximumSessions(10)
//          .and()
          .and()
          .exceptionHandling()
          .authenticationEntryPoint(restAuthenticationEntryPoint)
          .accessDeniedHandler(accessDeniedHandlerImpl)
          .and()
          .csrf().disable()
          .authorizeRequests()
          //allow anonymous POSTs to login
          .antMatchers(HttpMethod.POST, "/svc/login").permitAll()
          .antMatchers(HttpMethod.GET, "/svc/messages").permitAll()
          //all other request need to be authenticated
          .antMatchers("/svc/**").authenticated()
          .and()
          .logout()
          .logoutRequestMatcher(new AntPathRequestMatcher("/logout", HttpMethod.POST.name()))
          .invalidateHttpSession(true)
          .logoutSuccessHandler(new HttpStatusReturningLogoutSuccessHandler())
          .and()
          // custom CORS filter as the mvc cors config doesn't play well, yet, with security
          .addFilterBefore(corsFilter, ChannelProcessingFilter.class)
          // custom JSON based authentication by POST of {"userName":"<name>","userPass":"<password>"}
          .addFilterBefore(statelessLoginFilter(), UsernamePasswordAuthenticationFilter.class)
          .addFilterBefore(statelessAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
          .addFilterAfter(loggingEnhancingFilter(), FilterSecurityInterceptor.class)
      ;
    }

    @Bean
    public CorsFilter corsFilter() {
      UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
      CorsConfiguration config = new CorsConfiguration();
      config.setAllowCredentials(true); // you USUALLY want this
      config.addAllowedOrigin("*");
      config.addAllowedHeader("*");
      config.addAllowedMethod("GET");
      config.addAllowedMethod("HEAD");
      config.addAllowedMethod("POST");
      config.addAllowedMethod("DELETE");
      config.addAllowedMethod("PATCH");
      config.addAllowedMethod("PUT");

      config.addExposedHeader("Location");
      config.addExposedHeader("X-AUTH-TOKEN");
      source.registerCorsConfiguration("/**", config);
      return new CorsFilter(source);
    }

    @Bean
    LoggingEnhancingFilter loggingEnhancingFilter() {
      return new LoggingEnhancingFilter();
    }

    @Bean
    StatelessLoginFilter statelessLoginFilter() throws Exception {
      return new StatelessLoginFilter(
          "/login"
          , authenticationManagerBean()
          , customizedAuthenticationFailureHandler()
          , customizedAuthenticationSuccessHandler());
    }

    @Bean
    StatelessAuthenticationFilter statelessAuthenticationFilter() {
      return new StatelessAuthenticationFilter(tokenAuthenticationService);
    }

    //TODO split mvc and security config
    @Bean
    public Object pwdEncoder() {
      PasswordEncoder passwordEncoder = null;
      // (SHA1) Used when connect to UDAL for testing e_transact
      // String passHashAlg = "SHA1";
      String passHashAlg = "DEFAULT";
      switch (passHashAlg) {
        case "MD5/0.5":
          passwordEncoder = new MD5HalfPasswordEncoder();
          break;
        case "MD5":
          passwordEncoder = new MD5PasswordEncoder();
          break;
        case "SHA1":
          passwordEncoder = new SHA1PasswordEncoder();
          break;
        case "DEFAULT":
          passwordEncoder = new DefaultPasswordEncoder();
          break;
        default:
          throw new IllegalArgumentException("Unknown password hash type");
      }

      return new PasswordEncoderImpl(passwordEncoder) {
      };
    }

    @Bean
    public Boolean hideUserNotFound() {
      return false;
    }

  }
}

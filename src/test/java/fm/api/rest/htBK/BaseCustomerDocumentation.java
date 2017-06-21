package fm.api.rest.htBK;

import com.fasterxml.jackson.databind.ObjectMapper;
import fm.transaction.TransactionFilter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.io.IoBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
import org.springframework.restdocs.ManualRestDocumentation;
import org.springframework.restdocs.constraints.ConstraintDescriptions;
import org.springframework.restdocs.constraints.ResourceBundleConstraintDescriptionResolver;
import org.springframework.restdocs.payload.FieldDescriptor;
import org.springframework.restdocs.payload.PayloadDocumentation;
import org.springframework.session.ExpiringSession;
import org.springframework.session.web.http.SessionRepositoryFilter;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.StringUtils;
import org.springframework.web.context.WebApplicationContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Locale;
import java.util.Map;

import static java.util.ResourceBundle.getBundle;
import static org.springframework.restdocs.cli.CliDocumentation.curlRequest;
import static org.springframework.restdocs.http.HttpDocumentation.httpRequest;
import static org.springframework.restdocs.http.HttpDocumentation.httpResponse;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.snippet.Attributes.attributes;
import static org.springframework.restdocs.snippet.Attributes.key;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.util.StringUtils.collectionToDelimitedString;

/**
 * Created by haho
 * Date:  05/05/2016 Time: 5:15 PM
 */
@WebAppConfiguration
@ContextConfiguration(
    locations = {
        "/config/spring-mvc.xml",
        "/config/spring-mvc-test.xml"
    })
public abstract class BaseCustomerDocumentation extends AbstractTransactionalTestNGSpringContextTests {
  protected final Logger logger = LogManager.getLogger(getClass());

  @Autowired
  @Qualifier("customerAuthTokenService")
  protected IAuthTokenService authTokenService;

  protected final String APP_BUILD_NAME = "/v9";

  protected final String SNIPPET_NAME_PATTERN = "{class-name}/{method-name}";

  protected String[] langs = {"en", "fr"};

  protected MockMvc mockMvc;

  @Autowired
  @Qualifier("testObjectMapper")
  protected ObjectMapper objectMapper;

  @Autowired
  private WebApplicationContext wac;

  @Autowired
  @Qualifier("tstMsgSource")
  private MessageSource messageSource;

  public final ManualRestDocumentation restDocumentation = new ManualRestDocumentation("target/generated-snippets");

  @Autowired
  private SessionRepositoryFilter<? extends ExpiringSession> sessionRepositoryFilter;

  public static Locale locale = new Locale("en");

  @Autowired
  private TransactionFilter txFilter;

  @BeforeClass
  public void setup() throws UnknownHostException {
    final PrintWriter printWriter = IoBuilder.forLogger(logger).buildPrintWriter();

    mockMvc =
        MockMvcBuilders
            .webAppContextSetup(wac)
            .addFilter(sessionRepositoryFilter)
            .addFilter(txFilter)
            .apply(springSecurity())
            .apply(documentationConfiguration(this.restDocumentation)
                .uris()
                .withScheme("http")
                .withHost(InetAddress.getLocalHost().getHostName())
                .withPort(8080)
                .and()
                .snippets()
                .withDefaults(
                    curlRequest(getCurlRequestAttributes())
                    , httpRequest(getHttpRequestAttributes())
                    , httpResponse(getHttpResponseAttributes())
                )
            )
            .alwaysDo(print(printWriter))
            .build();
  }

  @BeforeMethod
  public void setUp(Method method) {
    this.restDocumentation.beforeTest(getClass(), method.getName());
  }

  @AfterMethod
  public void tearDown() {
    this.restDocumentation.afterTest();
  }

  protected String msgI18n(String property) {
    return messageSource.getMessage(property, null, locale);
  }

  protected String msgI18n(String property, Locale locale) {
    return messageSource.getMessage(property, null, locale);
  }

  protected String msgI18n(String property, Object[] args, Locale locale) {
    return messageSource.getMessage(property, args, locale);
  }


//  protected RestDocumentationResultHandler documentPrettyPrintReqResp(String useCase) {
//    return document(useCase,
//                    preprocessRequest(prettyPrint()),
//                    preprocessResponse(prettyPrint()));
//    }
//  protected RestDocumentationResultHandler documentPrettyPrintReqResp() {
//    return documentPrettyPrintReqResp(SNIPPET_NAME_PATTERN);
//  }

  //  protected Snippet getRequestHeaders() {
//    return requestHeaders(
//        getRequestHeadersAttributes()
//        ,headerWithName("X-AUTH-TOKEN").description(msgI18n(MessageProperties.X_AUTH_TOKEN))
//        ,headerWithName("Accept-Language").description(msgI18n(MessageProperties.ACCEPT_LANGUAGE)).optional());
//  }
//
//  protected Snippet getRequestHeaderByLanguage() {
//    return requestHeaders(
//        getRequestHeadersAttributes()
//        ,headerWithName("Accept-Language").description(msgI18n(MessageProperties.ACCEPT_LANGUAGE)).optional());
//  }
//
//  protected Snippet getRequestHeaderByAuthentication() {
//    return requestHeaders(
//        getRequestHeadersAttributes()
//        ,headerWithName("X-AUTH-TOKEN").description(msgI18n(MessageProperties.X_AUTH_TOKEN)));
//  }
//
//  protected Snippet getRequestHeadersWithTransaction() {
//    return requestHeaders(
//        getRequestHeadersAttributes()
//        ,headerWithName("X-AUTH-TOKEN").description(msgI18n(MessageProperties.X_AUTH_TOKEN))
//        ,headerWithName("Accept-Language").description(msgI18n(MessageProperties.ACCEPT_LANGUAGE)).optional()
//        ,headerWithName("txId").description(msgI18n(MessageProperties.TRANSACT_ID)).optional());
//  }
//
//  protected Snippet getResponseHeaderByAuthentication() {
//    return responseHeaders(
//        getResponseHeadersAttributes()
//        ,headerWithName("X-AUTH-TOKEN").description(msgI18n(MessageProperties.X_AUTH_TOKEN)));
//  }
//
//  protected Map<String, Object> getRequestFieldsAttributes() {
//    return attributes(
//        key("title"            ).value(msgI18n(MessageProperties.REQUEST_FIELD_TITLE))
//        ,key("option"          ).value(msgI18n(MessageProperties.OPTIONAL))
//        ,key("required"        ).value(msgI18n(MessageProperties.REQUIRED))
//        ,key("tablePath"       ).value(msgI18n(MessageProperties.REQUEST_FIELD_PATH))
//        ,key("tableType"       ).value(msgI18n(MessageProperties.REQUEST_FIELD_TYPE))
//        ,key("tableDescription").value(msgI18n(MessageProperties.REQUEST_FIELD_DESCRIPTION))
//        ,key("tableCondition"  ).value(msgI18n(MessageProperties.REQUEST_FIELD_CONDITION))
//        ,key("tableConstraints").value(msgI18n(MessageProperties.REQUEST_FIELD_CONSTRAINTS))
//    );
//  }
//
//  protected Map<String, Object> getRequestParamsAttributes() {
//    return attributes(
//        key("title"            ).value(msgI18n(MessageProperties.REQUEST_PARAM_TITLE))
//        ,key("option"          ).value(msgI18n(MessageProperties.OPTIONAL))
//        ,key("required"        ).value(msgI18n(MessageProperties.REQUIRED))
//        ,key("tableParam"      ).value(msgI18n(MessageProperties.REQUEST_PARAM_PARAMETER))
//        ,key("tableDescription").value(msgI18n(MessageProperties.REQUEST_PARAM_DESCRIPTION))
//        ,key("tableCondition"  ).value(msgI18n(MessageProperties.REQUEST_PARAM_CONDITION))
//        ,key("tableConstraints").value(msgI18n(MessageProperties.REQUEST_PARAM_CONSTRAINTS))
//    );
//  }
//
//  protected Map<String, Object> getRequestPartsAttributes() {
//    return attributes(
//      key("title"            ).value(msgI18n(MessageProperties.REQUEST_PARAM_TITLE))
//      ,key("option"          ).value(msgI18n(MessageProperties.OPTIONAL))
//      ,key("required"        ).value(msgI18n(MessageProperties.REQUIRED))
//      ,key("tableParam"      ).value(msgI18n(MessageProperties.REQUEST_PARAM_PARAMETER))
//      ,key("tableDescription").value(msgI18n(MessageProperties.REQUEST_PARAM_DESCRIPTION))
//      ,key("tableCondition"  ).value(msgI18n(MessageProperties.REQUEST_PARAM_CONDITION))
//      ,key("tableConstraints").value(msgI18n(MessageProperties.REQUEST_PARAM_CONSTRAINTS))
//    );
//  }
//
  private Map<String, Object> getCurlRequestAttributes() {
    return attributes(
        key("title").value(msgI18n(MessageProperties.CURL_REQUEST_TITLE)));
  }

  private Map<String, Object> getHttpRequestAttributes() {
    return attributes(
        key("title").value(msgI18n(MessageProperties.HTTP_REQUEST_TITLE)));
  }

  private Map<String, Object> getHttpResponseAttributes() {
    return attributes(
        key("title").value(msgI18n(MessageProperties.HTTP_RESPONSE_TITLE)));
  }

//  protected Map<String, Object> getRequestHeadersAttributes() {
//    return attributes(
//        key("option"           ).value(msgI18n(MessageProperties.OPTIONAL))
//        ,key("required"        ).value(msgI18n(MessageProperties.REQUIRED))
//        ,key("title"           ).value(msgI18n(MessageProperties.HTTP_REQUEST_HEADER_TITLE))
//        ,key("tableName"       ).value(msgI18n(MessageProperties.HTTP_REQUEST_HEADER_NAME))
//        ,key("tableDescription").value(msgI18n(MessageProperties.HTTP_REQUEST_HEADER_DESCRIPTION))
//        ,key("tableCondition"  ).value(msgI18n(MessageProperties.HTTP_REQUEST_HEADER_CONDITION)));
//  }
//
//  protected Map<String, Object> getResponseFieldsAttributes() {
//    return attributes(
//        key("title"            ).value(msgI18n(MessageProperties.RESPONSE_FIELD_TITLE))
//        ,key("tablePath"       ).value(msgI18n(MessageProperties.RESPONSE_FIELD_PATH))
//        ,key("tableType"       ).value(msgI18n(MessageProperties.RESPONSE_FIELD_TYPE))
//        ,key("tableDescription").value(msgI18n(MessageProperties.RESPONSE_FIELD_DESCRIPTION)));
//  }
//
//  protected Map<String, Object> getPathParamsAttributes() {
//    return attributes(
//        key("title"            ).value(msgI18n(MessageProperties.PATH_PARAM_TITLE))
//        ,key("tableParam"      ).value(msgI18n(MessageProperties.PATH_PARAM_PARAMETER))
//        ,key("tableDescription").value(msgI18n(MessageProperties.PATH_PARAM_DESCRIPTION))
//        ,key("tableConstraints").value(msgI18n(MessageProperties.PATH_PARAM_CONSTRAINTS))
//    );
//  }
//
//  protected Map<String, Object> getResponseHeadersAttributes() {
//    return attributes(
//        key("title"            ).value(msgI18n(MessageProperties.HTTP_RESPONSE_HEADER_TITLE))
//        ,key("tableName"       ).value(msgI18n(MessageProperties.HTTP_RESPONSE_HEADER_NAME))
//        ,key("tableDescription").value(msgI18n(MessageProperties.HTTP_RESPONSE_HEADER_DESCRIPTION)));
//  }

  protected static class ConstrainedFields {

    private final ConstraintDescriptions cd;

    public ConstrainedFields(Class<?> input) {
      cd = getConstraintDescriptions(input);
    }

    public FieldDescriptor WithPath(String path) {
      return
          fieldWithPath(path)
              .attributes(key("constraints").value(collectionToDelimitedString(cd.descriptionsForProperty(path), ". ")));
    }

    private ConstraintDescriptions getConstraintDescriptions(Class<?> clazz) {
      return new
          ConstraintDescriptions(
          clazz,
          new ResourceBundleConstraintDescriptionResolver(
              getBundle("org.springframework.restdocs.constraints.ConstraintDescriptions", locale))
      );
    }
  }

  public FieldDescriptor fieldWithParams(String patch, Object description, Object type) {
    return PayloadDocumentation.fieldWithPath(patch).description(description).type(type);
  }

  public FieldDescriptor fieldWithParams(String patch, String message, Object type) {

    String description = StringUtils.isEmpty(msgI18n(message)) ? message : msgI18n(message);
    return PayloadDocumentation.fieldWithPath(patch).description(description).type(type);
  }
}
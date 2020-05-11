package fm.api.rest;

/**
 * @author haho
 *
 */
public class MessageProperties {

  // Common properties
  public static final String X_AUTH_TOKEN     = "common.headerWithName.X-AUTH-TOKEN.description";
  public static final String ACCEPT_LANGUAGE  = "common.headerWithName.Accept-Language.description";
  public static final String TRANSACT_ID      = "common.headerWithName.transact.id.description";
  public static final String PAGE_SIZE        = "common.pageSize.description";
  public static final String PAGE_NUMBER      = "common.pageNumber.description";
  public static final String SORT_PARAM       = "common.sortParam.description";
  public static final String OPTIONAL         = "common.optional";
  public static final String REQUIRED         = "common.required";
  public static final String CONDITIONALLY    = "common.conditionally";
  public static final String CASE_REF         = "common.caseRef";
  public static final String DOC_REF          = "common.docRef";
  public static final String REF_INDIVIDUAL   = "common.refIndividual";

  public static final String PAGING_RESPONSE_CONTENT  = "paging.response.content";
  public static final String PAGING_RESPONSE_SIZE     = "paging.response.size";
  public static final String PAGING_RESPONSE_NUMBER   = "paging.response.number";
  public static final String PAGING_RESPONSE_LAST     = "paging.response.last";
  public static final String PAGING_RESPONSE_SORT     = "paging.response.sort";
  public static final String PAGING_RESPONSE_FIRST    = "paging.response.first";
  public static final String PAGING_RESPONSE_NUM_ELEM = "paging.response.num.elem";

  // Request fields table header
  public static final String REQUEST_FIELD_TITLE       = "request.field.title";
  public static final String REQUEST_FIELD_PATH        = "request.field.path";
  public static final String REQUEST_FIELD_TYPE        = "request.field.type";
  public static final String REQUEST_FIELD_DESCRIPTION = "request.field.description";
  public static final String REQUEST_FIELD_CONDITION   = "request.field.condition";
  public static final String REQUEST_FIELD_CONSTRAINTS = "request.field.constraints";

  // Response fields table
  public static final String RESPONSE_FIELD_TITLE       = "response.field.title";
  public static final String RESPONSE_FIELD_PATH        = "response.field.path";
  public static final String RESPONSE_FIELD_TYPE        = "response.field.type";
  public static final String RESPONSE_FIELD_DESCRIPTION = "response.field.description";

  // Request params table header
  public static final String REQUEST_PARAM_TITLE        = "request.param.title";
  public static final String REQUEST_PARAM_PARAMETER    = "request.param.parameter";
  public static final String REQUEST_PARAM_DESCRIPTION  = "request.param.description";
  public static final String REQUEST_PARAM_CONDITION    = "request.param.condition";
  public static final String REQUEST_PARAM_CONSTRAINTS  = "request.param.constraints";

  // Path params table header
  public static final String PATH_PARAM_TITLE       = "path.param.title";
  public static final String PATH_PARAM_PARAMETER   = "path.param.parameter";
  public static final String PATH_PARAM_DESCRIPTION = "path.param.description";
  public static final String PATH_PARAM_CONSTRAINTS = "path.param.constraints";

  // Curl request
  public static final String CURL_REQUEST_TITLE = "curl.request.title";

  // Http request
  public static final String HTTP_REQUEST_TITLE = "http.request.title";

  // Http response
  public static final String HTTP_RESPONSE_TITLE = "http.response.title";

  // Http request header
  public static final String HTTP_REQUEST_HEADER_TITLE       = "http.request.header.title";
  public static final String HTTP_REQUEST_HEADER_NAME        = "http.request.header.name";
  public static final String HTTP_REQUEST_HEADER_DESCRIPTION = "http.request.header.description";
  public static final String HTTP_REQUEST_HEADER_CONDITION   = "http.request.header.condition";

  // Http response header
  public static final String HTTP_RESPONSE_HEADER_TITLE       = "http.response.header.title";
  public static final String HTTP_RESPONSE_HEADER_NAME        = "http.response.header.name";
  public static final String HTTP_RESPONSE_HEADER_DESCRIPTION = "http.response.header.description";
}

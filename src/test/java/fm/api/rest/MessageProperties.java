/**
 *
 */
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

  // Common constraints
  public static final String CONSTRAINT_CASE_NOT_ARCHIVED = "case.not.archived";

  // iMX Error Snippet
  public static final String IMX_ERROR_SNIPPET_TITLE         = "imx.error.snippet.title";
  public static final String IMX_ERROR_SNIPPET_TABLE_CODE    = "imx.error.snippet.table.code";
  public static final String IMX_ERROR_SNIPPET_TABLE_MESSAGE = "imx.error.snippet.table.message";

  // SetupResourceTest
  public static final String SYSTEM_SETUP_POSITIVE_FILE            = "system.setup.positive.file";
  public static final String SYSTEM_SETUP_CURRENCY_OF_COMPANY      = "system.setup.currency.of.company";
  public static final String SYSTEM_SETUP_DISP_CURRENCY_OF_COMPANY = "system.setup.disp.currency.of.company";

  // CaseMenuResourceTest properties
  public static final String CASE_MENU_RESOURCE_CASE_REF = "caseMenuResource.pathParameter.caseRef.description";
  public static final String CASE_MENU_RESOURCE_ID = "caseMenuResource.fieldWithPath.id.description";
  public static final String CASE_MENU_RESOURCE_NAME = "caseMenuResource.fieldWithPath.name.description";
  public static final String CASE_MENU_RESOURCE_DISPLAY_NAME = "caseMenuResource.fieldWithPath.displayName.description";
  public static final String CASE_MENU_RESOURCE_SHORTCUT = "caseMenuResource.fieldWithPath.shortcut.description";
  public static final String CASE_MENU_RESOURCE_ICON = "caseMenuResource.fieldWithPath.icon.description";
  public static final String CASE_MENU_RESOURCE_ACTION_TYPE = "caseMenuResource.fieldWithPath.actionType.description";
  public static final String CASE_MENU_RESOURCE_ACTION_CMD = "caseMenuResource.fieldWithPath.actionCmd.description";
  public static final String CASE_MENU_RESOURCE_CHILDS = "caseMenuResource.fieldWithPath.childs.description";
  public static final String CASE_MENU_RESOURCE_CHILDS_ARRAY = "caseMenuResource.fieldWithPath.childs[].description";
  public static final String CASE_MENU_RESOURCE_CHILDS_ARRAY_ID = "caseMenuResource.fieldWithPath.childs[].id.description";
  public static final String CASE_MENU_RESOURCE_CHILDS_ARRAY_NAME = "caseMenuResource.fieldWithPath.childs[].name.description";
  public static final String CASE_MENU_RESOURCE_CHILDS_ARRAY_DISPLAY_NAME = "caseMenuResource.fieldWithPath.childs[].displayName.description";
  public static final String CASE_MENU_RESOURCE_CHILDS_ARRAY_SHORTCUT = "caseMenuResource.fieldWithPath.childs[].shortcut.description";
  public static final String CASE_MENU_RESOURCE_CHILDS_ARRAY_ICON = "caseMenuResource.fieldWithPath.childs[].icon.description";
  public static final String CASE_MENU_RESOURCE_CHILDS_ARRAY_ACTION_TYPE = "caseMenuResource.fieldWithPath.childs[].actionType.description";
  public static final String CASE_MENU_RESOURCE_CHILDS_ARRAY_ACTION_CMD = "caseMenuResource.fieldWithPath.childs[].actionCmd.description";

  public static final String CASE_PROCEDURES_FROZEN_PROCCODE         = "case.procedures.frozen.proccode";
  public static final String CASE_PROCEDURES_FROZEN_PROCTITLE        = "case.procedures.frozen.proctitle";
  public static final String CASE_PROCEDURES_FROZEN_DISPLAYPROCTITLE = "case.procedures.frozen.displayproctitle";
  public static final String CASE_PROCEDURES_FROZEN_STARTDATE        = "case.procedures.frozen.startdate";
  public static final String CASE_PROCEDURES_FROZEN_PARTYTYPE        = "case.procedures.frozen.partytype";
  public static final String CASE_PROCEDURES_FROZEN_DISPLAYPARTYTYPE = "case.procedures.frozen.displaypartytype";
  public static final String CASE_PROCEDURES_FROZEN_PARTYNAME        = "case.procedures.frozen.partyname";

  public static final String CASE_PROCEDURES_ACTIVE_REFELEM          = "case.procedures.active.refelem";
  public static final String CASE_PROCEDURES_ACTIVE_LIBELLE          = "case.procedures.active.libelle";
  public static final String CASE_PROCEDURES_ACTIVE_ENDDATE          = "case.procedures.active.enddate";
  public static final String CASE_PROCEDURES_ACTIVE_ENTRYDATE        = "case.procedures.active.entrydate";
  public static final String CASE_PROCEDURES_ACTIVE_DEADLINE         = "case.procedures.active.deadline";
  public static final String CASE_PROCEDURES_ACTIVE_PROCCODE         = "case.procedures.active.proccode";
  public static final String CASE_PROCEDURES_ACTIVE_PROCTITLE        = "case.procedures.active.proctitle";
  public static final String CASE_PROCEDURES_ACTIVE_DISPLAYPROCTITLE = "case.procedures.active.displayproctitle";
  public static final String CASE_PROCEDURES_ACTIVE_ESSTATUS         = "case.procedures.active.esstatus";
  public static final String CASE_PROCEDURES_ACTIVE_PARTYTYPE        = "case.procedures.active.partytype";
  public static final String CASE_PROCEDURES_ACTIVE_DISPLAYPARTYTYPE = "case.procedures.active.displaypartytype";
  public static final String CASE_PROCEDURES_ACTIVE_PARTYNAME        = "case.procedures.active.partyname";
  public static final String CASE_PROCEDURES_ACTIVE_IMX_UN_ID        = "case.procedures.active.imx_un_id";
  public static final String CASE_PROCEDURES_ACTIVE_CASEREF          = "case.procedures.active.caseref";

  public static final String CASE_PROCEDURES_COUNT                   = "case.procedures.count";
  // TranslationResourceTest properties
  public static final String TRANSLATION_RESOURCE_MODULE                  = "translationResource.pathParameter.module.description";
  public static final String TRANSLATION_RESOURCE_TYPE_MESSAGE            = "translationResource.pathParameter.typeMessage.description";
  public static final String TRANSLATION_RESOURCE_MAP_KEY                 = "translationResource.map.key";
  public static final String TRANSLATION_RESOURCE_MAP_KEY_VALUE           = "translationResource.map.key.value";
  public static final String TRANSLATION_RESOURCE_MAP_KEY_VALUE_KEY       = "translationResource.map.key.value.key";
  public static final String TRANSLATION_RESOURCE_MAP_KEY_VALUE_KEY_VALUE = "translationResource.map.key.value.key.value";

  // LoginResourceTest properties
  public static final String LOGIN_RESOURCE_USER_NAME = "loginResource.fieldWithPath.userName.description";
  public static final String LOGIN_RESOURCE_PASSWORD = "loginResource.fieldWithPath.password.description";
  public static final String LOGIN_RESOURCE_USER_LANG = "loginResource.fieldWithPath.userLang.description";

  // AuthorizationTest properties
  public static final String AUTHORIZATION_RESOURCE_NUM_CASES = "authorizationResource.fieldWithPath.numCases.description";
  public static final String AUTHORIZATION_RESOURCE_NUM_PENDING_TASKS = "authorizationResource.fieldWithPath.numPendingTasks.description";

  // UserInfoResourceTest properties
  public static final String USER_INFO_RESOURCE_NUM_CASES = "authorizationResource.fieldWithPath.numCases.description";
  public static final String USER_INFO_RESOURCE_NUM_PENDING_TASKS = "authorizationResource.fieldWithPath.numPendingTasks.description";

  public static final String USER_INFO_RESOURCE_AUTHORITY      = "authorizationResource.fieldWithPath.authority.description";
  public static final String USER_INFO_RESOURCE_DOMAIN         = "authorizationResource.fieldWithPath.authority.domain";
  public static final String USER_INFO_RESOURCE_DOMAIN_TYP     = "authorizationResource.fieldWithPath.domain.typ";
  public static final String USER_INFO_RESOURCE_DOMAIN_KEY     = "authorizationResource.fieldWithPath.domain.key";
  public static final String USER_INFO_RESOURCE_DOMAIN_KEY_ABR = "authorizationResource.fieldWithPath.domain.key.abr";
  public static final String USER_INFO_RESOURCE_DOMAIN_KEY_VAL = "authorizationResource.fieldWithPath.domain.key.val";
  public static final String USER_INFO_RESOURCE_PERMISSIONS    = "authorizationResource.fieldWithPath.permissions.description";
  public static final String USER_INFO_RESOURCE_PERMISSIONS_C  = "authorizationResource.fieldWithPath.permissions.c";
  public static final String USER_INFO_RESOURCE_PERMISSIONS_R  = "authorizationResource.fieldWithPath.permissions.r";
  public static final String USER_INFO_RESOURCE_PERMISSIONS_U  = "authorizationResource.fieldWithPath.permissions.u";
  public static final String USER_INFO_RESOURCE_PERMISSIONS_D  = "authorizationResource.fieldWithPath.permissions.d";


  // CasesResourceTest properties
  public static final String CASES_RESOURCE_CASES_REF = "casesResource.pathParameter.caseRef.description";
  public static final String CASES_RESOURCE_LAST = "casesResource.fieldWithPath.last.description";
  public static final String CASES_RESOURCE_TOTAL_ELEMENTS = "casesResource.fieldWithPath.totalElements.description";
  public static final String CASES_RESOURCE_TOTAL_PAGES = "casesResource.fieldWithPath.totalPages.description";
  public static final String CASES_RESOURCE_SIZE = "casesResource.fieldWithPath.size.description";
  public static final String CASES_RESOURCE_NUMBER = "casesResource.fieldWithPath.number.description";
  public static final String CASES_RESOURCE_SORT = "casesResource.fieldWithPath.sort.description";
  public static final String CASES_RESOURCE_FIRST = "casesResource.fieldWithPath.first.description";
  public static final String CASES_RESOURCE_NUMBER_ELEMENTS = "casesResource.fieldWithPath.numberOfElements.description";
  public static final String CASES_RESOURCE_CONTENT = "casesResource.fieldWithPath.content.description";
  public static final String CASES_RESOURCE_CONTENT_ARRAY = "casesResource.fieldWithPath.content[].description";
  public static final String CASES_RESOURCE_CONTENT_ARRAY_INTREF = "casesResource.fieldWithPath.content[].intRef.description";
  public static final String CASES_RESOURCE_CONTENT_ARRAY_EXTREF = "casesResource.fieldWithPath.content[].extRef.description";
  public static final String CASES_RESOURCE_CONTENT_ARRAY_CASE_CATEGORY = "casesResource.fieldWithPath.content[].caseCategory.description";
  public static final String CASES_RESOURCE_CONTENT_ARRAY_CURRENCY = "casesResource.fieldWithPath.content[].currency.description";
  public static final String CASES_RESOURCE_CONTENT_ARRAY_DEBTOR = "casesResource.fieldWithPath.content[].debtor.description";
  public static final String CASES_RESOURCE_CONTENT_ARRAY_CLIENT = "casesResource.fieldWithPath.content[].client.description";
  public static final String CASES_RESOURCE_CONTENT_ARRAY_BALANCE = "casesResource.fieldWithPath.content[].balance.description";
  public static final String CASES_RESOURCE_CONTENT_ARRAY_PRINCIPAL = "casesResource.fieldWithPath.content[].principal.description";
  public static final String CASES_RESOURCE_CONTENT_ARRAY_IS_IN_CTX = "casesResource.fieldWithPath.content[].is.in.ctx.description";

  public static final String CASES_RESOURCE_CATEGORY_BIND         = "casesResource.category.bind";
  public static final String CASES_RESOURCE_CATEGORY_REFINDVIDUAL = "casesResource.category.refIndividual";
  public static final String CASES_RESOURCE_CATEGORY_COST_FLAG    = "casesResource.category.costFlag";
  public static final String CASES_RESOURCE_CATEGORY_OLD_CATEGORY = "casesResource.category.oldCategory";
  public static final String CASES_RESOURCE_CATEGORY_CATEGORY     = "casesResource.category.category";
  public static final String CASES_RESOURCE_CATEGORY_ABBREV       = "casesResource.category.abbrev";

  public static final String CASES_RESOURCE_INVOLVED_PARTY_REF_INDIVIDUAL   = "casesResource.category.involvedParty.refIndividual";
  public static final String CASES_RESOURCE_INVOLVED_PARTY_TYPE             = "casesResource.category.involvedParty.type";
  public static final String CASES_RESOURCE_INVOLVED_PARTY_COMMENT          = "casesResource.category.involvedParty.comment";

  public static final String CASES_RESOURCE_VISUALIZATION_CASE_CATEGORY 		  = "casesResource.visualization.case.category";
  public static final String CASES_RESOURCE_VISUALIZATION_PARAMETER_TYPE      = "casesResource.visualization.parameter.type";
  public static final String CASES_RESOURCE_VISUALIZATION_PARAMETER_COMMENT   = "casesResource.visualization.parameter.comment";

  // ScoreResourceTest properties
  public static final String SCORES_RESOURCE_SCORE_ID                 = "scoresResource.score.id";
  public static final String SCORES_RESOURCE_SCORE_ELEMENT            = "scoresResource.score.element";
  public static final String SCORES_RESOURCE_SCORE_CRITERION1         = "scoresResource.score.criterion1";
  public static final String SCORES_RESOURCE_SCORE_RESULT_CRITERION1  = "scoresResource.score.resultCriterion1";
  public static final String SCORES_RESOURCE_SCORE_RESULT_ELEMENT     = "scoresResource.score.resultElement";
  public static final String SCORES_RESOURCE_SCORE_CRITERION2         = "scoresResource.score.criterion2";
  public static final String SCORES_RESOURCE_SCORE_RESULT_CRITERION2  = "scoresResource.score.resultCriterion2";
  public static final String SCORES_RESOURCE_SCORE_NUMBER             = "scoresResource.score.number";
  public static final String SCORES_RESOURCE_SCORE_TYPE               = "scoresResource.score.type";
  public static final String SCORES_RESOURCE_SCORE_ABBREV             = "scoresResource.score.abbrev";
  public static final String SCORES_RESOURCE_SCORE_RULE_DESCRIPTION   = "scoresResource.score.ruleDescription";

  public static final String SCORES_RESOURCE_DATE       = "scoresResource.date";
  public static final String SCORES_RESOURCE_SCORE      = "scoresResource.score";
  public static final String SCORES_RESOURCE_FINAL_NOTE = "scoresResource.finalNote";

  public static final String SCORES_RESOURCE_COMMENT_ID     = "scoresResource.comment.id";
  public static final String SCORES_RESOURCE_COMMENT_DATE   = "scoresResource.comment.date";
  public static final String SCORES_RESOURCE_CREATOR        = "scoresResource.comment.creator";
  public static final String SCORES_RESOURCE_TITLE          = "scoresResource.comment.title";
  public static final String SCORES_RESOURCE_COMMENT        = "scoresResource.comment.comment";

  //CaseIssuedDocumentResourceTest
  public static final String ISSUED_DOCUMENT_TEXT_REF =                "issuedDocument.fieldWithPath.textRef.description";
  public static final String ISSUED_DOCUMENT_TITLE =                   "issuedDocument.fieldWithPath.title.description";
  public static final String ISSUED_DOCUMENT_DISPLAY_TITLE =           "issuedDocument.fieldWithPath.display.title.description";
  public static final String ISSUED_DOCUMENT_ADDRESSEE =               "issuedDocument.fieldWithPath.addressee.description";
  public static final String ISSUED_DOCUMENT_PAPER =                   "issuedDocument.fieldWithPath.paper.description";
  public static final String ISSUED_DOCUMENT_CREATION_DATE =           "issuedDocument.fieldWithPath.creationDate.description";
  public static final String ISSUED_DOCUMENT_CREATOR =                 "issuedDocument.fieldWithPath.creator.description";
  public static final String ISSUED_DOCUMENT_ISSUE_DATE =              "issuedDocument.fieldWithPath.issueDate.description";
  public static final String ISSUED_DOCUMENT_PRINTER_NAME =            "issuedDocument.fieldWithPath.printerName.description";
  public static final String ISSUED_DOCUMENT_NUMBER_OF_PRINT_COPIES =  "issuedDocument.fieldWithPath.numberOfPrintCopies.description";
  public static final String ISSUED_DOCUMENT_NOT_SENT_DATE =           "issuedDocument.fieldWithPath.notSentDate.description";
  public static final String ISSUED_DOCUMENT_COMMENTS =                "issuedDocument.fieldWithPath.comments.description";
  public static final String ISSUED_DOCUMENT_SATUS =                   "issuedDocument.fieldWithPath.status.description";
  public static final String ISSUED_DOCUMENT_SATUS_CHANGE_USER_LOGIN = "issuedDocument.fieldWithPath.statusChangeUserLogin.description";
  public static final String ISSUED_DOCUMENT_SATUS_CHANGE_DATE =       "issuedDocument.fieldWithPath.statusChangeDate.description";
  public static final String ISSUED_DOCUMENT_SCANNED_DATE =            "issuedDocument.fieldWithPath.scannedDate.description";
  public static final String ISSUED_DOCUMENT_UNSENT_REASON =           "issuedDocument.fieldWithPath.unsentReason.description";
  public static final String ISSUED_DOCUMENT_PRINTER =                 "issuedDocument.fieldWithPath.printer.description";
  public static final String ISSUED_DOCUMENT_EDITOR_FILE_STATUS =      "issuedDocument.fieldWithPath.editor.file.status.description";
  public static final String ISSUED_DOCUMENT_EDITOR_TMP_ID =           "issuedDocument.fieldWithPath.editor.tmp.id.description";


  //CaseReceivedDocumentResourceTest
  public static final String RECEIVED_DOCUMENT_SUSPENSIVE_LEGAL_REMEDY    = "receivedDocument.fieldWithPath.suspensiveLegalRemedy.description";
  public static final String RECEIVED_DOCUMENT_COMMENT                    = "receivedDocument.fieldWithPath.comment.description";
  public static final String RECEIVED_DOCUMENT_TITLE1                     = "receivedDocument.fieldWithPath.title1.description";
  public static final String RECEIVED_DOCUMENT_TITLE2                     = "receivedDocument.fieldWithPath.title2.description";
  public static final String RECEIVED_DOCUMENT_TEXT                       = "receivedDocument.fieldWithPath.text.description";
  public static final String RECEIVED_DOCUMENT_AMOUNT                     = "receivedDocument.fieldWithPath.amount.description";
  public static final String RECEIVED_DOCUMENT_RECEIVED_ON                = "receivedDocument.fieldWithPath.receivedOn.description";
  public static final String RECEIVED_DOCUMENT_DATE_SCANNED               = "receivedDocument.fieldWithPath.dateScanned.description";
  public static final String RECEIVED_DOCUMENT_IMAGE_REFERENCE            = "receivedDocument.fieldWithPath.imageReference.description";
  public static final String RECEIVED_DOCUMENT_FIRST_PAGE                 = "receivedDocument.fieldWithPath.firstPage.description";
  public static final String RECEIVED_DOCUMENT_PAGE_NUMBER                = "receivedDocument.fieldWithPath.pageNumber.description";
  public static final String RECEIVED_DOCUMENT_FREE_COMMENTS              = "receivedDocument.fieldWithPath.freeComments.description";
  public static final String RECEIVED_DOCUMENT_MARKER_ATTACHED_FILE       = "receivedDocument.fieldWithPath.markerAttachedFile.description";
  public static final String RECEIVED_DOCUMENT_ON                         = "receivedDocument.fieldWithPath.on.description";
  public static final String RECEIVED_DOCUMENT_DOCUMENT_NAME              = "receivedDocument.fieldWithPath.documentName.description";
  public static final String RECEIVED_DOCUMENT_TEXT_TMP_ID                = "receivedDocument.fieldWithPath.textTmpId.description";
  public static final String RECEIVED_DOCUMENT_REF_STOCK                  = "receivedDocument.fieldWithPath.refStock.description";
  public static final String RECEIVED_UPLOADED_DOCUMENT                   = "receivedDocument.fieldWithPath.uploadedDocument.description";

  //CaseReceivedDocumentAttachmentResourceTest
  public static final String RECEIVED_DOCUMENT_ATTACHMENT_DISPLAY_NAME    = "receivedDocument.attachment.displayName";
  public static final String RECEIVED_DOCUMENT_ATTACHMENT_TYPE            = "receivedDocument.attachment.type";
  public static final String RECEIVED_DOCUMENT_ATTACHMENT_URL             = "receivedDocument.attachment.url";
  public static final String RECEIVED_DOCUMENT_ATTACHMENT_IMAGE_EXT       = "receivedDocument.attachment.imgExt";

  // DomainResourceTest properties
  public static final String DOMAIN_RESOURCE_REF_INFO = "domainResource.fieldWithPath.typeId.description";

  public static final String DOMAIN_RESOURCE_TYPE                  = "domainResource.type";
  public static final String DOMAIN_RESOURCE_VALUE                 = "domainResource.value";
  public static final String DOMAIN_RESOURCE_ABBREVIATION          = "domainResource.abbreviation";
  public static final String DOMAIN_RESOURCE_DISPLAY_VALUE         = "domainResource.display.value";
  public static final String DOMAIN_RESOURCE_DISPLAY_ABBREVIATION  = "domainResource.display.abbreviation";

  public static final String DOMAIN_RESOURCE_CONTACT_POSITION_VALUE                 = "domainResource.contactPosition.value";
  public static final String DOMAIN_RESOURCE_CONTACT_POSITION_ABBREVIATION          = "domainResource.contactPosition.abbreviation";
  public static final String DOMAIN_RESOURCE_CONTACT_POSITION_DISPLAY_VALUE         = "domainResource.contactPosition.displayValue";
  public static final String DOMAIN_RESOURCE_CONTACT_POSITION_DISPLAY_ABBREVIATION  = "domainResource.contactPosition.displayAbbreviation";

  public static final String DOMAIN_RESOURCE_SCORE_NUMBER             = "domainResource.score.number";
  public static final String DOMAIN_RESOURCE_SCORE_TYPE               = "domainResource.score.type";
  public static final String DOMAIN_RESOURCE_SCORE_ABBREV             = "domainResource.score.abbrev";

  public static final String DOMAIN_RESOURCE_PROFESSION_VALUE         = "domainResource.profession";
  public static final String DOMAIN_RESOURCE_PROFESSION               = "domainResource.profession.value";
  public static final String DOMAIN_RESOURCE_DISPLAY_PROFESSION_VALUE = "domainResource.display.profession";
  public static final String DOMAIN_RESOURCE_DISPLAY_PROFESSION       = "domainResource.display.profession.value";

  public static final String DOMAIN_RESOURCE_PROFESSION_SPECIALITY                = "domainResource.profession.speciality";
  public static final String DOMAIN_RESOURCE_PROFESSION_SPECIALITY_ABBREV         = "domainResource.profession.speciality.abbrev";
  public static final String DOMAIN_RESOURCE_DISPLAY_PROFESSION_SPECIALITY        = "domainResource.display.profession.speciality";
  public static final String DOMAIN_RESOURCE_DISPLAY_PROFESSION_SPECIALITY_ABBREV = "domainResource.display.profession.speciality.abbrev";

  public static final String DOMAIN_RESOURCE_STATE                = "domainResource.state";
  public static final String DOMAIN_RESOURCE_STATE_ABBREV         = "domainResource.state.abbrev";
  public static final String DOMAIN_RESOURCE_DISPLAY_STATE        = "domainResource.display.state";
  public static final String DOMAIN_RESOURCE_DISPLAY_STATE_ABBREV = "domainResource.display.state.abbrev";

  public static final String DOMAIN_RESOURCE_FORM_MASK         = "domainResource.form.mask";
  public static final String DOMAIN_RESOURCE_DISPLAY_FORM_MASK = "domainResource.display.form.mask";

  public static final String DOMAIN_RESOURCE_CNIL_GROUPING_CODE = "domainResource.cnil.grouping.code";
  public static final String DOMAIN_RESOURCE_CNIL_GROUPING_NAME = "domainResource.cnil.grouping.name";

  public static final String DOMAIN_RESOURCE_LANGUAGE                = "domainResource.language";
  public static final String DOMAIN_RESOURCE_LANGUAGE_ABBREV         = "domainResource.language.abbrev";
  public static final String DOMAIN_RESOURCE_DISPLAY_LANGUAGE        = "domainResource.display.language";
  public static final String DOMAIN_RESOURCE_LANGUAGE_DISPLAY_ABBREV = "domainResource.language.display.abbrev";

  // ClientAccountSearchResourceTest properties
  public static final String CLIENT_ACCOUNT_RESOURCE_INT_REF = "clientAccount.parameterWithName.intCaseRef.description";
  public static final String CLIENT_ACCOUNT_RESOURCE_EXT_REF = "clientAccount.parameterWithName.extCaseRef.description";
  public static final String CLIENT_ACCOUNT_RESOURCE_INCLUDE_REV = "clientAccount.parameterWithName.includeRevCtr.description";

  // CaseSearchResourceTest properties
  public static final String CASE_SEARCH_RESOURCE_CONTENT                 = "caseSearch.content";
  public static final String CASE_SEARCH_RESOURCE_CONTENT_PARENT_CASE_ID  = "caseSearch.content_parent_case_id";
  public static final String CASE_SEARCH_RESOURCE_CONTENT_CURRENCY        = "caseSearch.currency";
  public static final String CASE_SEARCH_RESOURCE_CONTENT_PARTY1NAME      = "caseSearch.party1name";
  public static final String CASE_SEARCH_RESOURCE_CONTENT_PARTY2NAME      = "caseSearch.party2name";
  public static final String CASE_SEARCH_RESOURCE_CONTENT_PARTY2ADDRESS   = "caseSearch.party2address";

  public static final String CASE_SEARCH_RESOURCE_CASE_REF                = "caseSearch.caseRef";
  public static final String CASE_SEARCH_RESOURCE_CLIENT_CASE_REF         = "caseSearch.clientCaseRef";
  public static final String CASE_SEARCH_RESOURCE_EXT_CASE_REF            = "caseSearch.extCaseRef";
  public static final String CASE_SEARCH_RESOURCE_CASE_CATEGORY           = "caseSearch.caseCategory";
  public static final String CASE_SEARCH_RESOURCE_ACTIVE                  = "caseSearch.active";
  public static final String CASE_SEARCH_RESOURCE_SEARCH_ARCHIVED         = "caseSearch.searchArchived";
  public static final String CASE_SEARCH_RESOURCE_PARTY_TYPE              = "caseSearch.partyType";
  public static final String CASE_SEARCH_RESOURCE_NUM_VCS                 = "caseSearch.numVCS";
  public static final String CASE_SEARCH_RESOURCE_INDIVIDUAL_REF          = "caseSearch.individualRef";
  public static final String CASE_SEARCH_RESOURCE_EXT_INDIVIDUAL_REF      = "caseSearch.extIndividualRef";
  public static final String CASE_SEARCH_RESOURCE_LEGAL_ID                = "caseSearch.legalID";
  public static final String CASE_SEARCH_RESOURCE_FIRST_NAME              = "caseSearch.firstName";
  public static final String CASE_SEARCH_RESOURCE_NAME                    = "caseSearch.name";
  public static final String CASE_SEARCH_RESOURCE_BIRTHDATE               = "caseSearch.birthdate";
  public static final String CASE_SEARCH_RESOURCE_ADDRESS                 = "caseSearch.address";
  public static final String CASE_SEARCH_RESOURCE_TERRITORIAL_DIVISION    = "caseSearch.territorialDivision";
  public static final String CASE_SEARCH_RESOURCE_POST_CODE               = "caseSearch.postCode";
  public static final String CASE_SEARCH_RESOURCE_CITY                    = "caseSearch.city";
  public static final String CASE_SEARCH_RESOURCE_COUNTRY                 = "caseSearch.country";
  public static final String CASE_SEARCH_RESOURCE_EMAIL                   = "caseSearch.email";
  public static final String CASE_SEARCH_RESOURCE_BANK_ACC_NUM            = "caseSearch.bankAccNum";
  public static final String CASE_SEARCH_RESOURCE_PHONE                   = "caseSearch.phone";
  public static final String CASE_SEARCH_RESOURCE_PHONETIC_SEARCH         = "caseSearch.phoneticSearch";
  public static final String CASE_SEARCH_RESOURCE_ALTERNATIVE_SEARCH      = "caseSearch.alternativeSearch";
  public static final String CASE_SEARCH_RESOURCE_TEMP_RESOURCE           = "caseSearch.tmpResId";

  public static final String CASE_SEARCH_RESOURCE_FILTER_CATEG_DOSS       = "caseSearch.filterCategdoss";
  public static final String CASE_SEARCH_RESOURCE_FILTER_PARENT_CASE_ID   = "caseSearch.filterParentCaseId";
  public static final String CASE_SEARCH_RESOURCE_FILTER_CASE_CURR        = "caseSearch.filterCaseCurr";
  public static final String CASE_SEARCH_RESOURCE_FILTER_CLIENT_NAME      = "caseSearch.filterClientName";
  public static final String CASE_SEARCH_RESOURCE_FILTER_DEBTOR_NAME      = "caseSearch.filterDebtorName";
  public static final String CASE_SEARCH_RESOURCE_FILTER_DEBTOR_WITH_NAME = "caseSearch.filterDebtorFirstName";
  public static final String CASE_SEARCH_RESOURCE_FILTER_INDIVIDUAL_REF   = "caseSearch.filterIndividuaRefExt";
  public static final String CASE_SEARCH_RESOURCE_FILTER_DOSSIER_ACTIVE   = "caseSearch.filterDossierActive";
  public static final String CASE_SEARCH_RESOURCE_FILTER_ELEMENT_BIEN     = "caseSearch.filterElementBien";
  public static final String CASE_SEARCH_RESOURCE_FILTER_REFDOSS          = "caseSearch.filterRefdoss";
  public static final String CASE_SEARCH_RESOURCE_FILTER_ACREDOSS         = "caseSearch.filterAcredoss";

  public static final String CASE_SEARCH_RESOURCE_ORDER_BY_REF_IMX               = "individualSearch.parameterWithName.orderByRefImx.description";
  public static final String CASE_SEARCH_RESOURCE_ORDER_BY_CONTRACT_REF          = "individualSearch.parameterWithName.orderByContractRef.description";
  public static final String CASE_SEARCH_RESOURCE_ORDER_BY_PARTY_1_NAME          = "individualSearch.parameterWithName.orderByParty1Name.description";
  public static final String CASE_SEARCH_RESOURCE_ORDER_BY_PARTY_2_NAME          = "individualSearch.parameterWithName.orderByParty2Name.description";


// CaseEventResourceTest properties
  public static final String CASES_EVENTS_EVENT_CONTENT          = "cases.events.event.content";
  public static final String CASES_EVENTS_EVENT_TYPE             = "cases.events.event.type";
  public static final String CASES_EVENTS_EVENT_DISPLAY_TYPE     = "cases.events.event.type.translated";
  public static final String CASES_EVENTS_EVENT_REF              = "cases.events.event.ref";
  public static final String CASES_EVENTS_EVENT_FINANCIAL        = "cases.events.event.financial";
  public static final String CASES_EVENTS_EVENT_STATUS           = "cases.events.event.status";
  public static final String CASES_EVENTS_EVENT_SCREEN           = "cases.events.event.screen";
  public static final String CASES_EVENTS_EVENT_REJECTED         = "cases.events.event.rejected";
  public static final String CASES_EVENTS_EVENT_PROCESSING_DATE  = "cases.events.event.processing.date";
  public static final String CASES_EVENTS_EVENT_ENTRANCE_DATE    = "cases.events.event.entrance.date";
  public static final String CASES_EVENTS_EVENT_TITLE            = "cases.events.event.display.title";
  public static final String CASES_EVENTS_EVENT_DISPLAY_TITLE    = "cases.events.event.display.title.translated";
  public static final String CASES_EVENTS_EVENT_PARTY_NAME       = "cases.events.event.party.name";
  public static final String CASES_EVENTS_EVENT_AMOUNT_CASE      = "cases.events.event.amount.case";
  public static final String CASES_EVENTS_EVENT_CURRENCY_CASE    = "cases.events.event.currency.case";
  public static final String CASES_EVENTS_EVENT_AMOUNT_MVT       = "cases.events.event.amount.mvt";
  public static final String CASES_EVENTS_EVENT_CURRENCY_MVT     = "cases.events.event.currency.mvt";
  public static final String CASES_EVENTS_EVENT_WITH_ATT         = "cases.events.event.attachment";
  public static final String CASES_EVENTS_EVENT_ORIGIN           = "cases.events.event.origin";

  public static final String CASE_EVENT_RESOURCE_CRITERIA_FILTER_DISPLAY_EVENT_TYPE = "caseEventSearch.criteria.fltrDisplayEventType";
  public static final String CASE_EVENT_RESOURCE_CRITERIA_FILTER_PROCESSING_DATE    = "caseEventSearch.criteria.fltrProcessingDate";
  public static final String CASE_EVENT_RESOURCE_CRITERIA_FILTER_ENTRANCE_DATE      = "caseEventSearch.criteria.fltrEntranceDate";
  public static final String CASE_EVENT_RESOURCE_CRITERIA_FILTER_DISPLAY_TITLE      = "caseEventSearch.criteria.fltrDisplayTitle";
  public static final String CASE_EVENT_RESOURCE_CRITERIA_FILTER_PARTY_NAME         = "caseEventSearch.criteria.fltrPartyName";
  public static final String CASE_EVENT_RESOURCE_CRITERIA_FILTER_AMOUNTCASE         = "caseEventSearch.criteria.fltrAmountCase";
  public static final String CASE_EVENT_RESOURCE_CRITERIA_FILTER_AMOUNTMVT          = "caseEventSearch.criteria.fltrAmountMvt";
  public static final String CASE_EVENT_RESOURCE_CRITERIA_FILTER_CURRENCYMVT        = "caseEventSearch.criteria.fltrCurrencyMvt";
  public static final String CASE_EVENT_RESOURCE_CRITERIA_FILTER_ORIGIN             = "caseEventSearch.criteria.fltrOrigin";
  public static final String CASE_EVENT_RESOURCE_CRITERIA_FILTER_STATUS             = "caseEventSearch.criteria.fltrStatus";

  public static final String CASE_ES_HISTORY_PROC_CODE                = "case.es.history.proc.code";
  public static final String CASE_ES_HISTORY_PROC_CODE_TRANS          = "case.es.history.proc.code.trans";
  public static final String CASE_ES_HISTORY_PROC_NAME                = "case.es.history.proc.name";
  public static final String CASE_ES_HISTORY_PROC_NAME_TRANS          = "case.es.history.proc.name.trans";
  public static final String CASE_ES_HISTORY_PROC_DATE_START          = "case.es.history.proc.date.start";
  public static final String CASE_ES_HISTORY_PROC_DATE_END            = "case.es.history.proc.date.end";
  public static final String CASE_ES_HISTORY_PROC_PARTY_TYPE          = "case.es.history.proc.party.type";
  public static final String CASE_ES_HISTORY_PROC_PARTY_TYPE_TRANS    = "case.es.history.proc.party.type.trans";
  public static final String CASE_ES_HISTORY_PROC_PRINCIPAL_START     = "case.es.history.proc.principal.start";
  public static final String CASE_ES_HISTORY_PROC_PRINCIPAL_END       = "case.es.history.proc.principal.end";
  public static final String CASE_ES_HISTORY_PROC_TMP_RSRC_ID         = "case.es.history.proc.TMP.resource.id";
  public static final String CASE_ES_HISTORY_LOCATION_RESPONSE        = "case.es.history.proc.location.response" ;
  public static final String CASE_ES_HISTORY_PROC_CASE_REF            = "case.es.history.proc.case.ref" ;
  public static final String CASE_ES_HISTORY_PROC_SIZE                = "case.es.history.proc.size";
  public static final String CASE_ES_HISTORY_PROC_NUMBER              = "case.es.history.proc.number";
  public static final String CASE_ES_HISTORY_PROC_FIRST_PAGE          = "case.es.history.proc.first.page";
  public static final String CASE_ES_HISTORY_PROC_LAST_PAGE           = "case.es.history.proc.last.page";
  public static final String CASE_ES_HISTORY_PROC_NUMBER_OF_ELEMENTS  = "case.es.history.proc.number.of.elements";
  public static final String CASE_ES_HISTORY_PROC_SORT_DIRECTION      = "case.es.history.proc.sort.direction";
  public static final String CASE_ES_HISTORY_PROC_SORT_BY_PROPERTY    = "case.es.history.proc.sort.by.property";
  public static final String CASE_ES_HISTORY_PROC_IGNORE_CASE         = "case.es.history.proc.ignore.case";
  public static final String CASE_ES_HISTORY_PROC_NULL_HANDLING       = "case.es.history.proc.null.handling";
  public static final String CASE_ES_HISTORY_PROC_ASCENDING           = "case.es.history.proc.ascending";
  public static final String CASE_ES_HISTORY_PROC_PROC_CODE           = "case.es.history.proc.procCode";
  public static final String CASE_ES_HISTORY_PROC_DISPLAY_PROC_CODE   = "case.es.history.proc.displayProcCode";
  public static final String CASE_ES_HISTORY_PROC_PROC_TITLE          = "case.es.history.proc.procTitle";
  public static final String CASE_ES_HISTORY_PROC_DISPLAY_PROC_TITLE  = "case.es.history.proc.displayProcTitle";
  public static final String CASE_ES_HISTORY_PROC_START_DATE          = "case.es.history.proc.startDate";
  public static final String CASE_ES_HISTORY_PROC_END_DATE            = "case.es.history.proc.endDate";
  public static final String CASE_ES_HISTORY_PROC_DISPLAY_PARTY_TYPE  = "case.es.history.proc.display.party.type";
  public static final String CASE_ES_HISTORY_PROC_PARTY_NAME          = "case.es.history.proc.party.name";
  public static final String CASE_ES_HISTORY_PROC_PRIN_START          = "case.es.history.proc.prin.start";
  public static final String CASE_ES_HISTORY_PROC_PRIN_END            = "case.es.history.proc.prin.end";

  //START Es Statuses
  public static final String CASE_ES_HISTORY_STATUS_CASE_REF         = "case.es.history.status.case.ref";
  public static final String CASE_ES_HISTORY_STATUS_TMP_RES_ID       = "case.es.history.status.tmp_res_id";
  public static final String CASE_ES_HISTORY_STATUS_DATE             = "case.es.history.status.date";
  public static final String CASE_ES_HISTORY_STATUS_ESSTATUS         = "case.es.history.status.esstatus";
  public static final String CASE_ES_HISTORY_STATUS_DISPLAYESSTATUS  = "case.es.history.status.displayesstatus";
  public static final String CASE_ES_HISTORY_STATUS_SEGMENT          = "case.es.history.status.segment";
  public static final String CASE_ES_HISTORY_STATUS_DISPLAYSEGMENT   = "case.es.history.status.displaysegment";
  public static final String CASE_ES_HISTORY_STATUS_MANAGER          = "case.es.history.status.manager";
  public static final String CASE_ES_HISTORY_STATUS_DEADLINE         = "case.es.history.status.deadline";
  public static final String CASE_ES_HISTORY_STATUS_MANUAL           = "case.es.history.status.manual";
  public static final String CASE_ES_HISTORY_STATUS_STARTDATE        = "case.es.history.status.startdate";
  public static final String CASE_ES_HISTORY_STATUS_UNIQUE_ID        = "case.es.history.status.unique.id";

  public static final String CASE_ES_HISTORY_STATUS_FLTR_DATE            = "case.es.history.status.fltr.date";
  public static final String CASE_ES_HISTORY_STATUS_FLTR_ESSTATUS        = "case.es.history.status.fltr.esstatus";
  public static final String CASE_ES_HISTORY_STATUS_FLTR_DISPLAYESSTATUS = "case.es.history.status.fltr.displayesstatus";
  public static final String CASE_ES_HISTORY_STATUS_FLTR_SEGMENT         = "case.es.history.status.fltr.segment";
  public static final String CASE_ES_HISTORY_STATUS_FLTR_DISPLAYSEGMENT  = "case.es.history.status.fltr.displaysegment";
  public static final String CASE_ES_HISTORY_STATUS_FLTR_MANAGER         = "case.es.history.status.fltr.manager";
  public static final String CASE_ES_HISTORY_STATUS_FLTR_DEADLINE        = "case.es.history.status.fltr.deadline";
  public static final String CASE_ES_HISTORY_STATUS_FLTR_MANUAL          = "case.es.history.status.fltr.manual";
  public static final String CASE_ES_HISTORY_STATUS_FLTR_STARTDATE       = "case.es.history.status.fltr.startdate";
  //END Es Statuses

  public static final String CASE_MANAGERS_MANAGER_NAME       = "case.managers.manager.name";
  public static final String CASE_MANAGERS_GROUPNAME          = "case.managers.groupname";
  public static final String CASE_MANAGERS_DISPLAY_GROUPNAME  = "case.managers.display.groupname";
  public static final String CASE_MANAGERS_START_DATE         = "case.managers.start.date";
  public static final String CASE_MANAGERS_END_DATE           = "case.managers.end.date";
  public static final String CASE_MANAGERS_BU                 = "case.managers.bu";
  public static final String CASE_MANAGERS_PAGE_SIZE          = "case.managers.paging.size";
  public static final String CASE_MANAGERS_PAGE_NUMBER        = "case.managers.paging.page.number";
  public static final String CASE_MANAGERS_FIRST_PAGE         = "case.managers.first.page";
  public static final String CASE_MANAGERS_LAST_PAGE          = "case.managers.last.page";
  public static final String CASE_MANAGERS_NUMBER_OF_ELEMENTS = "case.managers.number.of.elements";
  public static final String CASE_MANAGERS_SORT_DIRECTION     = "case.managers.sort.direction";
  public static final String CASE_MANAGERS_SORT_BY_PROPERTY   = "case.managers.sort.by.property";
  public static final String CASE_MANAGERS_IGNORE_CASE        = "case.managers.ignore.case";
  public static final String CASE_MANAGERS_NULL_HANDLING      = "case.managers.null.handling";
  public static final String CASE_MANAGERS_ASCENDING          = "case.managers.ascending";
  public static final String CASE_MANAGERS_LOCATION_RESPONSE  = "case.managers.location.response";
  public static final String CASE_MANAGERS_CASE_REF           = "case.managers.manager.case.ref";
  public static final String CASE_MANAGERS_CRITERIA_TMP_ID    = "case.managers.search.criteria.tempid";
  public static final String CASE_MANAGERS_UPDATE_BY          = "case.managers.update.by";

  ////CaseLettersResourceTest
  public static final String  CASES_EVENTS_LETTERS_LETTER_NAME                  = "cases.events.letters.letterName";
  public static final String  CASES_EVENTS_LETTERS_LETTER_DISPLAY_NAME          = "cases.events.letters.letterDisplayName";
  public static final String  CASES_EVENTS_LETTERS_LETTER_TYPE                  = "cases.events.letters.letterType";
  public static final String  CASES_EVENTS_LETTERS_LETTER_DISPLAY_TYPE          = "cases.events.letters.letterDisplayType";
  // CaseLettersResourceTest.getRecipients
  public static final String  CASES_EVENTS_LETTERS_RECIPIENT_REF_RECIPIENT      = "cases.events.letters.recipient.ref.recipient";
  public static final String  CASES_EVENTS_LETTERS_RECIPIENT_PARTY_ROLE         = "cases.events.letters.recipient.party.role";
  public static final String  CASES_EVENTS_LETTERS_RECIPIENT_DISPLAY_PARTY_ROLE = "cases.events.letters.recipient.display.party.role";
  public static final String  CASES_EVENTS_LETTERS_RECIPIENT_ALT_MEDIAS         = "cases.events.letters.recipient.alt.medias";
  public static final String  CASES_EVENTS_LETTERS_RECIPIENT_ORDER_NUM          = "cases.events.letters.recipient.order.num";
  public static final String  CASES_EVENTS_LETTERS_RECIPIENT_REF_LETTER         = "cases.events.letters.recipient.ref.letter";
  public static final String  CASES_EVENTS_LETTERS_RECIPIENT_DEFAULT            = "cases.events.letters.recipient.default";
  //CaseLettersResourceTest.getOptParagraphs
  public static final String CASES_EVENTS_LETTERS_PARAGRAPH_BE_SCHEMA_PORT               = "cases.events.letters.paragraph.be.schema.port";
  public static final String CASES_EVENTS_LETTERS_PARAGRAPH_REF                          = "cases.events.letters.paragraph.ref";
  public static final String CASES_EVENTS_LETTERS_PARAGRAPH_NAME                         = "cases.events.letters.paragraph.name";
  public static final String CASES_EVENTS_LETTERS_PARAGRAPH_DESCRIPTION                  = "cases.events.letters.paragraph.description";
  public static final String CASES_EVENTS_LETTERS_PARAGRAPH_EDITORSERVICES               = "cases.events.letters.paragraph.editorservices";
  public static final String CASES_EVENTS_LETTERS_PARAGRAPH_EDITORSERVICES_OPENEDITORURL = "cases.events.letters.paragraph.editorservices.openeditorurl";
  public static final String CASES_EVENTS_LETTERS_PARAGRAPH_EDITORSERVICES_OBJSTATEURL   = "cases.events.letters.paragraph.editorservices.objstateurl";
  public static final String CASES_EVENTS_LETTERS_PARAGRAPH_EDITORSERVICES_OBJTEMPID     = "cases.events.letters.paragraph.editorservices.objtempid";
  public static final String CASES_EVENTS_LETTERS_PARAGRAPH_EDITORSERVICES_OBJREFTEXT    = "cases.events.letters.paragraph.editorservices.objreftext";
  //CaseLettersResourceTest.testGetMandatoryVariables
  public static final String CASES_EVENTS_LETTERS_VARS_PARAGRAPHS           = "cases.events.letters.vars.paragraphs";
  public static final String CASES_EVENTS_LETTERS_VARS_RECIPIENTS           = "cases.events.letters.vars.recipients";
  public static final String CASES_EVENTS_LETTERS_VARS_RSP                  = "cases.events.letters.vars.rsp";
  public static final String CASES_EVENTS_LETTERS_VARS_RSP_VAR_REF          = "cases.events.letters.vars.rsp.var.ref";
  public static final String CASES_EVENTS_LETTERS_VARS_RSP_VAR_NAME         = "cases.events.letters.vars.rsp.var.name";
  public static final String CASES_EVENTS_LETTERS_VARS_RSP_DISPLAY_VAR_NAME = "cases.events.letters.vars.rsp.display.var.name";
  public static final String CASES_EVENTS_LETTERS_VARS_RSP_VALUE            = "cases.events.letters.vars.rsp.value";
  //CaseLettersResourceTest.testGetMandatoryVariables.testGetDataForPreview
  public static final String CASES_EVENTS_LETTERS_PREVIEW_REQ_BE_SHEMA_PORT                = "cases.events.letters.preview.req.be.shema.port";
  public static final String CASES_EVENTS_LETTERS_PREVIEW_REQ_SEL_OPT_PARAGS               = "cases.events.letters.preview.req.sel.opt.parags";
  public static final String CASES_EVENTS_LETTERS_PREVIEW_REQ_SEL_RECIPIENTS               = "cases.events.letters.preview.req.sel.recipients";
  public static final String CASES_EVENTS_LETTERS_PREVIEW_REQ_SEL_MAN_VARS                 = "cases.events.letters.preview.req.sel.man.vars";
  public static final String CASES_EVENTS_LETTERS_PREVIEW_RSP_TEMP_ID                      = "cases.events.letters.preview.rsp.temp.id";
  public static final String CASES_EVENTS_LETTERS_PREVIEW_RSP_RECIPIENT_REF                = "cases.events.letters.preview.rsp.recipient.ref";
  public static final String CASES_EVENTS_LETTERS_PREVIEW_RSP_EDITORSERVICES               = "cases.events.letters.preview.rsp.editorservices";
  public static final String CASES_EVENTS_LETTERS_PREVIEW_RSP_EDITORSERVICES_OPENEDITORURL = "cases.events.letters.preview.rsp.editorservices.openeditorurl";
  public static final String CASES_EVENTS_LETTERS_PREVIEW_RSP_EDITORSERVICES_OBJSTATEURL   = "cases.events.letters.preview.rsp.editorservices.objstateurl";
  public static final String CASES_EVENTS_LETTERS_PREVIEW_RSP_EDITORSERVICES_OBJTEMPID     = "cases.events.letters.preview.rsp.editorservices.objtempid";
  public static final String CASES_EVENTS_LETTERS_PREVIEW_RSP_EDITORSERVICES_OBJREFTEXT    = "cases.events.letters.preview.rsp.editorservices.objreftext";
  public static final String CASES_EVENTS_LETTERS_PREVIEW_RSP_ALT_MEDIAS                   = "cases.events.letters.preview.rsp.alt.medias";
  //CaseLettersResourceTest.testIssueNoPreview
  public static final String CASES_EVENTS_LETTERS_ISSUE_NPRV_PARAGS               = "cases.events.letters.issue.nprv.parags";
  public static final String CASES_EVENTS_LETTERS_ISSUE_NPRV_RCPTS                = "cases.events.letters.issue.nprv.rcpts";
  public static final String CASES_EVENTS_LETTERS_ISSUE_NPRV_RCPTS_REF_RECIPIENT  = "cases.events.letters.issue.nprv.rcpts.ref.recipient";
  public static final String CASES_EVENTS_LETTERS_ISSUE_NPRV_RCPTS_AM             = "cases.events.letters.issue.nprv.rcpts.am";
  public static final String CASES_EVENTS_LETTERS_ISSUE_NPRV_RCPTS_AM_DM          = "cases.events.letters.issue.nprv.rcpts.am.dm";
  public static final String CASES_EVENTS_LETTERS_ISSUE_NPRV_RCPTS_AM_TO          = "cases.events.letters.issue.nprv.rcpts.am.to";
  public static final String CASES_EVENTS_LETTERS_ISSUE_NPRV_VARS                 = "cases.events.letters.issue.nprv.vars";
  public static final String CASES_EVENTS_LETTERS_ISSUE_NPRV_VARS_VARREF          = "cases.events.letters.issue.nprv.vars.varref";
  public static final String CASES_EVENTS_LETTERS_ISSUE_NPRV_VARS_VARNAME         = "cases.events.letters.issue.nprv.vars.varname";
  public static final String CASES_EVENTS_LETTERS_ISSUE_NPRV_VARS_DISPLAY_VARNAME = "cases.events.letters.issue.nprv.vars.display.varname";
  public static final String CASES_EVENTS_LETTERS_ISSUE_NPRV_VARS_VALUE           = "cases.events.letters.issue.nprv.vars.value";
  public static final String CASES_EVENTS_LETTERS_ISSUE_NPRV_ISSUEDATE            = "cases.events.letters.issue.nprv.issuedate";
  public static final String CASES_EVENTS_LETTERS_ISSUE_NPRV_ALREADYISSUED        = "cases.events.letters.issue.nprv.alreadyissued";
  //CaseLettersResourceTest.testIssueNoPreview
  public static final String CASES_EVENTS_LETTERS_ISSUE_PRV_TEMPID        = "cases.events.letters.issue.prv.tempid";
  public static final String CASES_EVENTS_LETTERS_ISSUE_PRV_RECIPIENT     = "cases.events.letters.issue.prv.recipient";
  public static final String CASES_EVENTS_LETTERS_ISSUE_PRV_ALTMEDIA      = "cases.events.letters.issue.prv.altmedia";
  public static final String CASES_EVENTS_LETTERS_ISSUE_PRV_ALTMEDIATO    = "cases.events.letters.issue.prv.altmediato";
  public static final String CASES_EVENTS_LETTERS_ISSUE_PRV_ALREADYISSUED = "cases.events.letters.issue.prv.alreadyissued";

  // IndividualSearchResourceTest properties
  public static final String INDIVIDUAL_SEARCH_RESOURCE_LEGAL_ID              = "individualSearch.parameterWithName.legalId.description";
  public static final String INDIVIDUAL_SEARCH_FIRST_NAME                     = "individualSearch.parameterWithName.firstName.description";
  public static final String INDIVIDUAL_SEARCH_RESOURCE_NAME                  = "individualSearch.parameterWithName.name.description";
  public static final String INDIVIDUAL_SEARCH_RESOURCE_BIRTH_DAY             = "individualSearch.parameterWithName.birthDate.description";
  public static final String INDIVIDUAL_SEARCH_RESOURCE_ADDRESS               = "individualSearch.parameterWithName.address.description";
  public static final String INDIVIDUAL_SEARCH_TERRITORIAL_DIVISION           = "individualSearch.parameterWithName.territorialDivision.description";
  public static final String INDIVIDUAL_SEARCH_RESOURCE_POSTCODE              = "individualSearch.parameterWithName.postCode.description";
  public static final String INDIVIDUAL_SEARCH_RESOURCE_CITY                  = "individualSearch.parameterWithName.city.description";
  public static final String INDIVIDUAL_SEARCH_RESOURCE_COUNTRY               = "individualSearch.parameterWithName.country.description";
  public static final String INDIVIDUAL_SEARCH_RESOURCE_EMAIL                 = "individualSearch.parameterWithName.email.description";
  public static final String INDIVIDUAL_SEARCH_RESOURCE_BANK_ACC_NUM          = "individualSearch.parameterWithName.bankAccNum.description";
  public static final String INDIVIDUAL_SEARCH_RESOURCE_PHONE                 = "individualSearch.parameterWithName.phone.description";
  public static final String INDIVIDUAL_SEARCH_RESOURCE_PRTYTYPE              = "individualSearch.parameterWithName.prtyType.description";
  public static final String INDIVIDUAL_SEARCH_RESOURCE_PHONETIC_SEARCH       = "individualSearch.parameterWithName.phoneticSearch.description";
  public static final String INDIVIDUAL_SEARCH_RESOURCE_ALTERNATIVE_SEARCH    = "individualSearch.parameterWithName.alternativeSearch.description";
  public static final String INDIVIDUAL_SEARCH_RESOURCE_INDIV_REF_EXT         = "individualSearch.parameterWithName.indivRefExt.description";
  public static final String INDIVIDUAL_SEARCH_RESOURCE_FILTER_LEGAL          = "individualSearch.parameterWithName.filterLegal.description";
  public static final String INDIVIDUAL_SEARCH_RESOURCE_FILTER_FIRST_NAME     = "individualSearch.parameterWithName.filterFirstName.description";
  public static final String INDIVIDUAL_SEARCH_RESOURCE_FILTER_NAME           = "individualSearch.parameterWithName.filterName.description";
  public static final String INDIVIDUAL_SEARCH_RESOURCE_FILTER_POSTCODE       = "individualSearch.parameterWithName.filterPostCode.description";
  public static final String INDIVIDUAL_SEARCH_RESOURCE_FILTER_CITY           = "individualSearch.parameterWithName.filterCity.description";
  public static final String INDIVIDUAL_SEARCH_RESOURCE_FILTER_COUNTRY        = "individualSearch.parameterWithName.filterCountry.description";
  public static final String INDIVIDUAL_SEARCH_RESOURCE_FILTER_ADDRESS        = "individualSearch.parameterWithName.filterAddress.description";
  public static final String INDIVIDUAL_SEARCH_RESOURCE_FILTER_INDIV_REF_EXT  = "individualSearch.parameterWithName.filterIndivRefExt.description";
  public static final String INDIVIDUAL_SEARCH_RESOURCE_FILTER_PERSON_ID      = "individualSearch.parameterWithName.filterPersonId.description";
  public static final String INDIVIDUAL_SEARCH_RESOURCE_FILTER_PHONE          = "individualSearch.parameterWithName.filterPhone.description";
  public static final String INDIVIDUAL_SEARCH_RESOURCE_FILTER_RAISSOC1       = "individualSearch.parameterWithName.filterRaissoc1.description";
  public static final String INDIVIDUAL_SEARCH_RESOURCE_FILTER_NOIDENT        = "individualSearch.parameterWithName.filterNoIdent.description";
  public static final String INDIVIDUAL_SEARCH_RESOURCE_FILTER_VILLE          = "individualSearch.parameterWithName.filterVille.description";
  public static final String INDIVIDUAL_SEARCH_RESOURCE_FILTER_DIVISION       = "individualSearch.parameterWithName.filterDivision.description";
  public static final String INDIVIDUAL_SEARCH_RESOURCE_FILTER_DTNAISS        = "individualSearch.parameterWithName.filterDtnaiss.description";

  public static final String INDIVIDUAL_SEARCH_RESOURCE_ORDER_BY_FIRSTNAME       = "individualSearch.parameterWithName.orderByFirstName.description";
  public static final String INDIVIDUAL_SEARCH_RESOURCE_ORDER_BY_NAME            = "individualSearch.parameterWithName.orderByName.description";
  public static final String INDIVIDUAL_SEARCH_RESOURCE_ORDER_BY_POSTCODE        = "individualSearch.parameterWithName.orderByPostCode.description";
  public static final String INDIVIDUAL_SEARCH_RESOURCE_ORDER_BY_CITY            = "individualSearch.parameterWithName.orderByCity.description";
  public static final String INDIVIDUAL_SEARCH_RESOURCE_ORDER_BY_COUNTRY         = "individualSearch.parameterWithName.orderByCountry.description";
  public static final String INDIVIDUAL_SEARCH_RESOURCE_ORDER_BY_ADDRESS         = "individualSearch.parameterWithName.orderByAddress.description";
  public static final String INDIVIDUAL_SEARCH_RESOURCE_ORDER_BY_INDIV_REF_EXT   = "individualSearch.parameterWithName.orderByIndivRefExt.description";
  public static final String INDIVIDUAL_SEARCH_CONTENT                           = "individualSearch.fieldWithPath.content";

  public static final String INDIVIDUAL_CONTACT_RESOURCE_CONTACT_REF_INDIVIDUAL = "individual.contact.refIndividual";
  public static final String INDIVIDUAL_CONTACT_RESOURCE_CONTACT_TYPE           = "individual.contact.type";
  public static final String INDIVIDUAL_CONTACT_RESOURCE_CONTACT_COUNTRY        = "individual.contact.country";
  public static final String INDIVIDUAL_CONTACT_RESOURCE_CONTACT_PHONE_NUMBER   = "individual.contact.phoneNumber";
  public static final String INDIVIDUAL_CONTACT_RESOURCE_CONTACT_EMAIL          = "individual.contact.email";
  public static final String INDIVIDUAL_CONTACT_RESOURCE_CONTACT_TITLE          = "individual.contact.title";
  public static final String INDIVIDUAL_CONTACT_RESOURCE_CONTACT_UPDATE_SOURCE  = "individual.contact.updateSource";
  public static final String INDIVIDUAL_CONTACT_RESOURCE_CONTACT_UPDATE_DATE    = "individual.contact.updateDate";
  public static final String INDIVIDUAL_CONTACT_RESOURCE_CONTACT_VALID          = "individual.contact.valid";


  public static final String INDIVIDUAL_EMPLOYERS_NAME           = "individual.employers.name";
  public static final String INDIVIDUAL_EMPLOYERS_CITY           = "individual.employers.city";
  public static final String INDIVIDUAL_EMPLOYERS_STATUS         = "individual.employers.status";
  public static final String INDIVIDUAL_EMPLOYERS_POST_CODE      = "individual.employers.post.code";
  public static final String INDIVIDUAL_EMPLOYERS_PHONE          = "individual.employers.phone";
  public static final String INDIVIDUAL_EMPLOYERS_ENTRY          = "individual.employers.entry";
  public static final String INDIVIDUAL_EMPLOYERS_UPDATE_SOURCE  = "individual.employers.update.source";
  public static final String INDIVIDUAL_EMPLOYERS_UPDATE_DATE    = "individual.employers.update.date";
  public static final String INDIVIDUAL_EMPLOYERS_ENTRY_DATE     = "individual.employers.entry.date";
  public static final String INDIVIDUAL_EMPLOYERS_ADDRESS        = "individual.employers.address";
  public static final String INDIVIDUAL_EMPLOYERS_COMMENT        = "individual.employers.comment";
  public static final String INDIVIDUAL_EMPLOYERS_REF_EMPLOYER   = "individual.employers.ref.employer";
  public static final String INDIVIDUAL_EMPLOYERS_ROW_ID         = "individual.employers.row.id";
  public static final String INDIVIDUAL_EMPLOYERS_SEIZURE        = "individual.employers.seizure";
  public static final String INDIVIDUAL_EMPLOYERS_IMX_UN_ID      = "individual.employers.imx_un_id";
  public static final String INDIVIDUAL_EMPLOYERS_REF_INDIVIDUAL = "individual.employers.refIndividual";
  // Constraints
  public static final String CONSTRAINT_INDIVIDUAL_EMPLOYERS_SEIZURE = "constraint.individual.employers.seizure";
  public static final String CONSTRAINT_INDIVIDUAL_EMPLOYERS_STATUS  = "constraint.individual.employers.status";
  public static final String CONSTRAINT_PHYSICAL_PERSON              = "physical.persons.only";

  public static final String INDIVIDUAL_REF_INDIVIDUAL        = "individual.refIndividual";
  public static final String INDIVIDUAL_OTHER_REF_INDIVIDUAL  = "individual.otherIndividualRef";
  public static final String INDIVIDUAL_PERSON_TYPE           = "individual.personType";
  public static final String INDIVIDUAL_NAME                  = "individual.name";
  public static final String INDIVIDUAL_FIRST_NAME            = "individual.first.name";
  public static final String INDIVIDUAL_MIDDLE_NAME           = "individual.middle.name";
  public static final String INDIVIDUAL_LEGAL_FORM            = "individual.legalForm";
  public static final String INDIVIDUAL_REG_COUNTRY           = "individual.regCountry";
  public static final String INDIVIDUAL_REG_PLACE             = "individual.regPlace";
  public static final String INDIVIDUAL_IDENTIFIER_KEY        = "individual.identifierKey";
  public static final String INDIVIDUAL_VAT_NO                = "individual.vatNo";
  public static final String INDIVIDUAL_SIREN                 = "individual.siren";
  public static final String INDIVIDUAL_LANGUAGE              = "individual.language";
  public static final String INDIVIDUAL_TEMP_INDIVIDUAL       = "individual.tempIndividual";
  public static final String INDIVIDUAL_COUNTRY               = "individual.country";
  public static final String INDIVIDUAL_DISPLAY_COUNTRY       = "individual.displayCountry";
  public static final String INDIVIDUAL_CITY                  = "individual.city";
  public static final String INDIVIDUAL_POST_CODE             = "individual.postCode";
  public static final String INDIVIDUAL_STATE                 = "individual.state";
  public static final String INDIVIDUAL_ADD1                  = "individual.add1";
  public static final String INDIVIDUAL_ADD2                  = "individual.add2";
  public static final String INDIVIDUAL_ADD3                  = "individual.add3";
  public static final String INDIVIDUAL_ADD4                  = "individual.add4";
  public static final String INDIVIDUAL_PHONE                 = "individual.phone";
  public static final String INDIVIDUAL_MOBILE                = "individual.mobile";
  public static final String INDIVIDUAL_FAX                   = "individual.fax";
  public static final String INDIVIDUAL_EMAIL                 = "individual.email";
  public static final String INDIVIDUAL_NATIONALITY           = "individual.nationality";
  public static final String INDIVIDUAL_BIRTH_DATE            = "individual.birth.date";
  public static final String INDIVIDUAL_IMX_UN_ID             = "individual.imx_un_id";
  public static final String INDIVIDUAL_13_MONTH_SALARY       = "individual.13.month.salary";
  public static final String INDIVIDUAL_NB_CHILDREN           = "individual.nb.children";
  public static final String INDIVIDUAL_HOUSING               = "individual.housing";
  public static final String INDIVIDUAL_HOME_TYPE             = "individual.home.type";
  public static final String INDIVIDUAL_HOUSING_SINCE         = "individual.housing.since";
  public static final String INDIVIDUAL_THIRD_PERS_NB         = "individual.third.pers.nb";
  public static final String INDIVIDUAL_SECONDARY_PROP        = "individual.secondary.prop";
  public static final String INDIVIDUAL_CUST_CITY             = "individual.cust.city";

  // External Providers data
  public static final String INDIVIDUAL_REQUEST_REFERENCE         = "individual.reqRef";
  public static final String INDIVIDUAL_IMX_NAME                  = "individual.imx.name";
  public static final String INDIVIDUAL_OPERATION                 = "individual.operation";
  public static final String INDIVIDUAL_EXTERNAL_PROVIDER         = "individual.externalProvider";
  public static final String INDIVIDUAL_PROVIDER_NAME             = "individual.providerName";
  public static final String INDIVIDUAL_RESULT                    = "individual.result";
  public static final String INDIVIDUAL_DISPLAY_RESULT            = "individual.displayResult";
  public static final String INDIVIDUAL_SENDING_DATE              = "individual.sendingDate";
  public static final String INDIVIDUAL_ERROR_MESSAGE             = "individual.errorMessage";
  public static final String INDIVIDUAL_COPR_NAME                 = "individual.corpName";
  public static final String INDIVIDUAL_CORP_NAME1                = "individual.corpName1";
  public static final String INDIVIDUAL_ACTIVITY_TYPE             = "individual.activityType";
  public static final String INDIVIDUAL_ADDRESS                   = "individual.address";
  public static final String INDIVIDUAL_LEGAL_ID                  = "individual.legalId";
  public static final String INDIVIDUAL_SIC_CODE                  = "individual.sicCode";
  public static final String INDIVIDUAL_CONFIDENCE_CODE           = "individual.confidenceCode";
  public static final String INDIVIDUAL_MG_BUSINESS_NAME          = "individual.mgBusinessName";
  public static final String INDIVIDUAL_MG_STREET_NB              = "individual.mgStreetNB";
  public static final String INDIVIDUAL_MG_STREET                 = "individual.mgStreet";
  public static final String INDIVIDUAL_MG_CITY                   = "individual.mgCity";
  public static final String INDIVIDUAL_COMPANY_TYPE              = "individual.companyType";
  public static final String INDIVIDUAL_DISPLAY_COMPANY_TYPE      = "individual.displayCompanyType";
  public static final String INDIVIDUAL_TYPE_OWNER_SHIP           = "individual.typeOwnerShip";
  public static final String INDIVIDUAL_DISPLAY_LEGAL_FORM        = "individual.displayLegalForm";
  public static final String INDIVIDUAL_DISPLAY_STATE             = "individual.displayState";
  public static final String INDIVIDUAL_TELEX                     = "individual.telex";
  public static final String INDIVIDUAL_WEBSITE                   = "individual.website";
  public static final String INDIVIDUAL_EXT_PROV_ID               = "individual.extProvId";
  public static final String INDIVIDUAL_NACE_CODE                 = "individual.naceCode";
  public static final String INDIVIDUAL_PROCESSED_INDIV_REF       = "individual.processed.indivRef";
  public static final String INDIVIDUAL_PROCESSED_OTHER_INDIV_REF = "individual.processed.otherIndivRef";

  public static final String INDIVIDUAL_CONTACT_DEPARTMENT            = "individual.contact.department";
  public static final String INDIVIDUAL_CONTACT_POSITION_ABBREVIATION = "individual.contact.positionAbbreviation";
  public static final String INDIVIDUAL_CONTACT_TYPE                  = "individual.contact.type";
  public static final String INDIVIDUAL_CONTACT_NAME                  = "individual.contact.name";
  public static final String INDIVIDUAL_CONTACT_FIRST_NAME            = "individual.contact.firstName";
  public static final String INDIVIDUAL_CONTACT_PHONE                 = "individual.contact.phone";
  public static final String INDIVIDUAL_CONTACT_MOBILE                = "individual.contact.mobile";
  public static final String INDIVIDUAL_CONTACT_FAX                   = "individual.contact.fax";
  public static final String INDIVIDUAL_CONTACT_EMAIL                 = "individual.contact.email";
  public static final String INDIVIDUAL_CONTACT_REFERENCE             = "individual.contact.contactRef";

  public static final String INDIVIDUAL_BANK_DOM_CODE   = "individual.bank.domCode";
  public static final String INDIVIDUAL_BANK_COUNTRY    = "individual.bank.country";
  public static final String INDIVIDUAL_BANK_BANK_CODE  = "individual.bank.bankCode";
  public static final String INDIVIDUAL_BANK_BIC        = "individual.bank.bic";
  public static final String INDIVIDUAL_BANK_BANK_NAME  = "individual.bank.bankName";
  public static final String INDIVIDUAL_BANK_CITY       = "individual.bank.city";
  public static final String INDIVIDUAL_BANK_CURRENCY  = "individual.bank.currency";
  public static final String INDIVIDUAL_BANK_DOM       = "individual.bank.dom";
  public static final String INDIVIDUAL_BANK_PAYMENT   = "individual.bank.payment";
  public static final String INDIVIDUAL_BANK_SEPA      = "individual.bank.sepa";
  public static final String INDIVIDUAL_BANK_ACT       = "individual.bank.act";
  public static final String INDIVIDUAL_BANK_REFERENCE = "individual.bank.bankRef";

  public static final String INDIVIDUAL_CHARGE_TYPE          = "individual.charge.type";
  public static final String INDIVIDUAL_CHARGE_CALENDAR_DAY  = "individual.charge.calendar.day";
  public static final String INDIVIDUAL_CHARGE_AMOUNT        = "individual.charge.amount";
  public static final String INDIVIDUAL_CHARGE_REF           = "individual.charge.ref";
  public static final String INDIVIDUAL_CHARGE_UPD_SOURCE    = "individual.charge.upd.source";
  public static final String INDIVIDUAL_CHARGE_UPD_DATE      = "individual.charge.upd.date";

  public static final String INDIVIDUAL_CREDIT_INSTITUTION = "individual.credit.institution";
  public static final String INDIVIDUAL_CREDIT_END_DATE    = "individual.credit.end.date";
  public static final String INDIVIDUAL_CREDIT_SUBJECT     = "individual.credit.subject";
  public static final String INDIVIDUAL_CREDIT_DT1INSTLMT  = "individual.credit.dt1instlmt";
  public static final String INDIVIDUAL_CREDIT_AMTINSTLMT  = "individual.credit.amtinstlmt";
  public static final String INDIVIDUAL_CREDIT_AMOUNT      = "individual.credit.amount";
  public static final String INDIVIDUAL_CREDIT_RECEIVED_ON = "individual.credit.received.on";
  public static final String INDIVIDUAL_CREDIT_REF         = "individual.credit.ref";

  public static final String INDIVIDUAL_REVENUE_ORIGIN        = "individual.revenue.origin";
  public static final String INDIVIDUAL_REVENUE_CALENDAR_DAY  = "individual.revenue.calendar.day";
  public static final String INDIVIDUAL_REVENUE_AMOUNT        = "individual.revenue.amount";
  public static final String INDIVIDUAL_REVENUE_AMTSPOUSE     = "individual.revenue.amtspouse";
  public static final String INDIVIDUAL_REVENUE_UPD_SOURCE    = "individual.revenue.upd.source";
  public static final String INDIVIDUAL_REVENUE_UPD_DATE      = "individual.revenue.upd.date";
  public static final String INDIVIDUAL_REVENUE_REF           = "individual.revenue.ref";

  public static final String INDIVIDUAL_FACTOR_SETUP_REF              = "individual.factor.setup.ref";
  public static final String INDIVIDUAL_FACTOR_SETUP_SEPA             = "individual.factor.setup.sepa";
  public static final String INDIVIDUAL_FACTOR_SETUP_AGGR_RECEIVABLES = "individual.factor.setup.aggr.receivables";

  public static final String EDITOR_MODE            = "editor.mode";
  public static final String EDITOR_BACKEND_URL     = "editor.backend.url";
  public static final String EDITOR_EDITED_TEXT_REF = "editor.edited.text.ref";
  public static final String EDITOR_EDITED_TEMP_ID  = "editor.edited.temp.id";

  /*Initial piece document*/
  public static final String INITIAL_DOCUMENT_RESOURCE_ABRREV         = "initialDocument.parameterWithName.abrrev.description";
  public static final String INITIAL_DOCUMENT_RESOURCE_DISPLAY_ABRREV = "initialDocument.parameterWithName.displayAbrrev.description";
  public static final String INITIAL_DOCUMENT_RESOURCE_DATA_DOCUMENT  = "initialDocument.parameterWithName.dataDocument.description";
  public static final String INITIAL_DOCUMENT_DISPLAY_DATA_DOCCUMENT  = "initialDocument.parameterWithName.displayDataDoccument.description";
  public static final String INITIAL_DOCUMENT_RESOURCE_SCREEN_NAME    = "initialDocument.parameterWithName.screenName.description";
  public static final String INITIAL_DOCUMENT_RESOURCE_UNIQ           = "initialDocument.parameterWithName.uniq.description";
  public static final String INITIAL_DOCUMENT_RESOURCE_AFF            = "initialDocument.parameterWithName.aff.description";
  public static final String INITIAL_DOCUMENT_RESOURCE_USER_CREATION  = "initialDocument.parameterWithName.userCreation.description";
  public static final String INITIAL_DOCUMENT_RESOURCE_CASE_CATEGORY  = "initialDocument.parameterWithName.caseCategory.description";

  // StaffMembersResourceTest properties
  public static final String STAFF_MEMBER_LOGIN            = "staff.member.login";
  public static final String STAFF_MEMBER_NAME             = "staff.member.name";
  public static final String STAFF_MEMBER_FIRST_NAME       = "staff.member.first.name";
  public static final String STAFF_MEMBER_REF_INDIVIDUAL   = "staff.member.refIndividual";
  public static final String STAFF_MEMBER_REF_PERSON       = "staff.member.refPerson";

  //IndicationsResourceTest
  public static final String INDICATION_REFERENCE               = "indication.reference";
  public static final String INDICATION_ES_STATUS               = "indication.es.status";
  public static final String INDICATION_DISPLAY_ES_STATUS       = "indication.display.es.status";
  public static final String INDICATION_ES_STATUS_FREE_TEXT     = "indication.es.status.free.text";
  public static final String INDICATION_ES_STATUS_DATE_DEFERRED = "indication.es.status.date.deferred";
  public static final String INDICATION_ES_STATUS_ENTER_FOR     = "indication.es.status.entered.for";

  public static final String CONSTRAINT_EXISTING_STAFF_MEMBER  = "constraint.existing.staff.member";
  public static final String CONSTRAINT_INDICATION_ES_STATUS   = "constraint.indication.es.status";

  //Segmentation active and histories
  public static final String SEGMENTATION_ACTIVE_START_DATE       = "segmentation.active.startDate";
  public static final String SEGMENTATION_ACTIVE_SCORE            = "segmentation.active.score";
  public static final String SEGMENTATION_ACTIVE_SEGMENT_DISPLAY  = "segmentation.active.segment.display";
  public static final String SEGMENTATION_ACTIVE_PHASE_DISPLAY    = "segmentation.active.phase.display";

  public static final String SEGMENTATION_HISTORY_CONTENT         = "segmentation.history.content";
  public static final String SEGMENTATION_HISTORY_PHASE_DISPLAY   = "segmentation.history.phase.display";
  public static final String SEGMENTATION_HISTORY_STARTDATE       = "segmentation.history.startDate";
  public static final String SEGMENTATION_HISTORY_ENDDATE         = "segmentation.history.endDate";
  public static final String SEGMENTATION_HISTORY_SEGMENT_DISPLAY = "segmentation.history.segment.display";

  // Message Visualization
  public static final String MESSSAGE_VISUALIZATION_ID                   = "message.visualization.id";
  public static final String MESSSAGE_VISUALIZATION_DATE                 = "message.visualization.date";
  public static final String MESSSAGE_VISUALIZATION_CASE_REF             = "message.visualization.caseRef";
  public static final String MESSSAGE_VISUALIZATION_AGS_REF              = "message.visualization.agsRef";
  public static final String MESSSAGE_VISUALIZATION_FROM_NAME            = "message.visualization.fromName";
  public static final String MESSSAGE_VISUALIZATION_TO_NAME              = "message.visualization.toName";
  public static final String MESSSAGE_VISUALIZATION_SUBJECT              = "message.visualization.subject";
  public static final String MESSSAGE_VISUALIZATION_BODY                 = "message.visualization.body";
  public static final String MESSSAGE_VISUALIZATION_ATTACHMENT_HOST_NAME = "message.visualization.attachment.host.name";
  public static final String MESSSAGE_VISUALIZATION_ATTACHMENT_NAME      = "message.visualization.attachmentName";
  public static final String MESSSAGE_VISUALIZATION_CASE_MANAGER_LOGIN   = "message.visualization.caseManagerLogin";
  public static final String MESSSAGE_VISUALIZATION_URGENT               = "message.visualization.urgent";
  public static final String MESSSAGE_VISUALIZATION_ANSWER_REQUESTED     = "message.visualization.answerRequested";
  public static final String MESSSAGE_VISUALIZATION_STATUS               = "message.visualization.status";
  public static final String MESSSAGE_VISUALIZATION_ANSWER_FROM          = "message.visualization.answerFrom";
  public static final String MESSSAGE_VISUALIZATION_ANSWER_TO            = "message.visualization.answerTo";
  public static final String MESSSAGE_VISUALIZATION_ANSWER_SUBJECT       = "message.visualization.answerSubject";
  public static final String MESSSAGE_VISUALIZATION_ANSWER_MESSAGE_ID    = "message.visualization.answerMessageId";
  public static final String MESSSAGE_VISUALIZATION_REFTEXT              = "message.visualization.refText";
  public static final String MESSSAGE_VISUALIZATION_MANAGER_ID           = "message.visualization.managerId";
  public static final String MESSSAGE_VISUALIZATION_TMP_ID               = "message.visualization.tmpId";

  // Limit resource
  //      limit request
  public static final String LIMIT_REQUEST_TYPE_LIMIT         = "limit.request.typeLimit";
  public static final String LIMIT_REQUEST_LIMIT_LEVEL        = "limit.request.limitLevel";
  public static final String LIMIT_REQUEST_REFDOSS            = "limit.request.refdoss";
  public static final String LIMIT_REQUEST_REF_GROUP_CL       = "limit.request.refGroulCl";
  public static final String LIMIT_REQUEST_REF_GROUP_DB       = "limit.request.refGroupDb";
  public static final String LIMIT_REQUEST_REF_CL             = "limit.request.refCl";
  public static final String LIMIT_REQUEST_REF_DB             = "limit.request.refDb";
  public static final String LIMIT_REQUEST_REF_FACTOR         = "limit.request.refFactor";
  public static final String LIMIT_REQUEST_REF_INDIV_DB       = "limit.request.refIndivDb";
  public static final String LIMIT_REQUEST_LEVEL_REFDOSS      = "limit.request.levelRefdoss";
  public static final String LIMIT_REQUEST_TYPE_CONCENTRATION = "limit.request.typeConcentration";
  public static final String LIMIT_REQUEST_CALLING_FORM       = "limit.request.callingForm";

  //      limit
  public static final String LIMIT_LEVEL                    = "limit.limitLevel";
  public static final String LIMIT_DISPLAY_LEVEL            = "limit.displayLevel";
  public static final String LIMIT_GROUP_NAME               = "limit.groupName";
  public static final String LIMIT_CL_GROUP_NAME            = "limit.clGroupName";
  public static final String LIMIT_DB_GROUP_NAME            = "limit.dbGroupName";
  public static final String LIMIT_REF_DOC                  = "limit.refDoc";
  public static final String LIMIT_CASE_REF                 = "limit.caseRef";
  public static final String LIMIT_CLIENT                   = "limit.client";
  public static final String LIMIT_INVOICE_CONCENTRATION    = "limit.invoiceConcentration";
  public static final String LIMIT_FACTOR                   = "limit.factor";
  public static final String LIMIT_CURRENT_LIMIT            = "limit.currentLimit";
  public static final String LIMIT_CONSUMED_AMOUNT          = "limit.consumedAmount";
  public static final String LIMIT_LIMIT_AMOUNT_OR_PERCENT  = "limit.isAmountOrPercent";
  public static final String LIMIT_LIMIT_DISABLED           = "limit.disabled";
  public static final String LIMIT_CONCENTRATION_TYPE       = "limit.concentrationType";

  //      limit details
  public static final String LIMIT_LIMIT_TYPE         = "limit.limitType";
  public static final String LIMIT_START_DATE         = "limit.startDate";
  public static final String LIMIT_END_DATE           = "limit.endDate";
  public static final String LIMIT_AMOUNT             = "limit.amount";
  public static final String LIMIT_PERCENT            = "limit.percent";
  public static final String LIMIT_NUMBER             = "limit.number";
  public static final String LIMIT_PAYMENT_OF_BILL    = "limit.paymentOfBill";
  public static final String LIMIT_NUM_OF_DAYS        = "limit.numOfDays";
  public static final String LIMIT_PAYMENT_CONDITION  = "limit.paymentCondition";
  public static final String LIMIT_CURRENCY           = "limit.currency";
  public static final String LIMIT_TYPE               = "limit.type";
  public static final String LIMIT_DISABLED           = "limit.disabled";
  public static final String LIMIT_REF                = "limit.ref";
  public static final String LIMIT_FORM_MASK          = "limit.formMask";
  public static final String LIMIT_CONCENTRATION      = "limit.concentration";
  public static final String LIMIT_SURPASSED          = "limit.surpassed";
  public static final String LIMIT_COUNTRY            = "limit.country";
  public static final String LIMIT_SICCODE            = "limit.siccode";
  public static final String LIMIT_CREATOR            = "limit.creator";
  public static final String LIMIT_LIMIT_DETAILID     = "limit.limitDetailid";
  //      exceptions
  public static final String LIMIT_EXCEPTION_REF  = "limit.exceptionRef";
  public static final String LIMIT_INDIV_REF      = "limit.indivRef";
  public static final String LIMIT_EXT_DB_REF     = "limit.extDbRef";
  public static final String LIMIT_DEBTOR_NAME    = "limit.debtorName";
//  public static final String LIMIT_RATE           = "limit.rate";
  //      limit country
  public static final String LIMIT_COUNTRY_ABREV  = "limit.country.abrev";
  public static final String LIMIT_DETAIL_ID      = "limit.detailId";
  public static final String LIMIT_SIC_CODE       = "limit.sic.code";


  //Bar Objective resource for screen e_band_obj.fmb
  public static final String BAR_OBJECTIVE_RESOURCE_MT_OBJ_ENC_J                 = "barObjective.parameterWithName.mtObjEncJ.description";
  public static final String BAR_OBJECTIVE_RESOURCE_MT_ENC_J                     = "barObjective.parameterWithName.mtEncJ.description";
  public static final String BAR_OBJECTIVE_RESOURCE_DIFF_PERC_J                  = "barObjective.parameterWithName.diffPercJ.description";
  public static final String BAR_OBJECTIVE_RESOURCE_OBJ_DOSS_J                   = "barObjective.parameterWithName.objDossJ.description";
  public static final String BAR_OBJECTIVE_RESOURCE_DOSS_TRAITER_J               = "barObjective.parameterWithName.dossTraiterJ.description";
  public static final String BAR_OBJECTIVE_RESOURCE_DOSS_NONTRAITER_J            = "barObjective.parameterWithName.dossNontraiterJ.description";
  public static final String BAR_OBJECTIVE_RESOURCE_OBJ_APPELS_J                 = "barObjective.parameterWithName.objAppelsJ.description";
  public static final String BAR_OBJECTIVE_RESOURCE_NB_APPELS_J                  = "barObjective.parameterWithName.nbAppelsJ.description";
  public static final String BAR_OBJECTIVE_RESOURCE_DIFF_NB_J                    = "barObjective.parameterWithName.diffNbJ.description";
  public static final String BAR_OBJECTIVE_RESOURCE_OBJ_PP_J                     = "barObjective.parameterWithName.objPpJ.description";
  public static final String BAR_OBJECTIVE_RESOURCE_MT_PP_J                      = "barObjective.parameterWithName.mtPpJ.description";
  public static final String BAR_OBJECTIVE_RESOURCE_RUPTURE_J                    = "barObjective.parameterWithName.ruptureJ.description";
  public static final String BAR_OBJECTIVE_RESOURCE_MT_RUPTURE_J                 = "barObjective.parameterWithName.mtRuptureJ.description";
  public static final String BAR_OBJECTIVE_RESOURCE_OBJ_MT_ECH_J                 = "barObjective.parameterWithName.objMtEchJ.description";
  public static final String BAR_OBJECTIVE_RESOURCE_MT_MOYEN_PP_J                = "barObjective.parameterWithName.mtMoyenPpJ.description";
  public static final String BAR_OBJECTIVE_RESOURCE_NB_APP_ENTR_J                = "barObjective.parameterWithName.nbAppEntrJ.description";
  public static final String BAR_OBJECTIVE_RESOURCE_NB_APP_SORT_J                = "barObjective.parameterWithName.nbAppSortJ.description";
  public static final String BAR_OBJECTIVE_RESOURCE_NB_APP_DIR_J                 = "barObjective.parameterWithName.nbAppDirJ.description";
  public static final String BAR_OBJECTIVE_RESOURCE_NB_APP_DIR_M                 = "barObjective.parameterWithName.nbAppDirM.description";
  public static final String BAR_OBJECTIVE_RESOURCE_NB_APP_DIR_M1                = "barObjective.parameterWithName.nbAppDirM1.description";
  public static final String BAR_OBJECTIVE_RESOURCE_NB_APP_RVT_J                 = "barObjective.parameterWithName.nbAppRvtJ.description";
  public static final String BAR_OBJECTIVE_RESOURCE_NB_APP_RVT_M                 = "barObjective.parameterWithName.nbAppRvtM.description";
  public static final String BAR_OBJECTIVE_RESOURCE_NB_APP_RVT_M1                = "barObjective.parameterWithName.nbAppRvtM1.description";
  public static final String BAR_OBJECTIVE_RESOURCE_MT_OBJ_ENC_M                 = "barObjective.parameterWithName.mtObjEncM.description";
  public static final String BAR_OBJECTIVE_RESOURCE_MT_ENC_M                     = "barObjective.parameterWithName.mtEncM.description";
  public static final String BAR_OBJECTIVE_RESOURCE_DIFF_PERC_M                  = "barObjective.parameterWithName.diffPercM.description";
  public static final String BAR_OBJECTIVE_RESOURCE_OBJ_DOSS_M                   = "barObjective.parameterWithName.objDossM.description";
  public static final String BAR_OBJECTIVE_RESOURCE_DOSS_TRAITER_M               = "barObjective.parameterWithName.dossTraiterM.description";
  public static final String BAR_OBJECTIVE_RESOURCE_DOSS_NONTRAITER_M            = "barObjective.parameterWithName.dossNontraiterM.description";
  public static final String BAR_OBJECTIVE_RESOURCE_OBJ_APPELS_M                 = "barObjective.parameterWithName.objAppelsM.description";
  public static final String BAR_OBJECTIVE_RESOURCE_NB_APPELS_M                  = "barObjective.parameterWithName.nbAppelsM.description";
  public static final String BAR_OBJECTIVE_RESOURCE_DIFF_NB_M                    = "barObjective.parameterWithName.diffNbM.description";
  public static final String BAR_OBJECTIVE_RESOURCE_OBJ_PP_M                     = "barObjective.parameterWithName.objPpM.description";
  public static final String BAR_OBJECTIVE_RESOURCE_MT_PP_M                      = "barObjective.parameterWithName.mtPpM.description";
  public static final String BAR_OBJECTIVE_RESOURCE_RUPTURE_M                    = "barObjective.parameterWithName.ruptureM.description";
  public static final String BAR_OBJECTIVE_RESOURCE_MT_RUPTURE_M                 = "barObjective.parameterWithName.mtRuptureM.description";
  public static final String BAR_OBJECTIVE_RESOURCE_OBJ_MT_ECH_M                 = "barObjective.parameterWithName.objMtEchM.description";
  public static final String BAR_OBJECTIVE_RESOURCE_MT_MOYEN_PP_M                = "barObjective.parameterWithName.mtMoyenPpM.description";
  public static final String BAR_OBJECTIVE_RESOURCE_NB_APP_ENTR_M                = "barObjective.parameterWithName.nbAppEntrM.description";
  public static final String BAR_OBJECTIVE_RESOURCE_NB_APP_SORT_M                = "barObjective.parameterWithName.nbAppSortM.description";
  public static final String BAR_OBJECTIVE_RESOURCE_MT_OBJ_ENC_M1                = "barObjective.parameterWithName.mtObjEncM1.description";
  public static final String BAR_OBJECTIVE_RESOURCE_MT_ENC_M1                    = "barObjective.parameterWithName.mtEncM1.description";
  public static final String BAR_OBJECTIVE_RESOURCE_DIFF_PERC_M1                 = "barObjective.parameterWithName.diffPercM1.description";
  public static final String BAR_OBJECTIVE_RESOURCE_OBJ_DOSS_M1                  = "barObjective.parameterWithName.objDossM1.description";
  public static final String BAR_OBJECTIVE_RESOURCE_DOSS_TRAITER_M1              = "barObjective.parameterWithName.dossTraiterM1.description";
  public static final String BAR_OBJECTIVE_RESOURCE_DOSS_NONTRAITER_M1           = "barObjective.parameterWithName.dossNontraiterM1.description";
  public static final String BAR_OBJECTIVE_RESOURCE_OBJ_APPELS_M1                = "barObjective.parameterWithName.objAppelsM1.description";
  public static final String BAR_OBJECTIVE_RESOURCE_NB_APPELS_M1                 = "barObjective.parameterWithName.nbAppelsM1.description";
  public static final String BAR_OBJECTIVE_RESOURCE_DIFF_NB_M1                   = "barObjective.parameterWithName.diffNbM1.description";
  public static final String BAR_OBJECTIVE_RESOURCE_OBJ_PP_M1                    = "barObjective.parameterWithName.objPpM1.description";
  public static final String BAR_OBJECTIVE_RESOURCE_MT_PP_M1                     = "barObjective.parameterWithName.mtPpM1.description";
  public static final String BAR_OBJECTIVE_RESOURCE_RUPTURE_M1                   = "barObjective.parameterWithName.ruptureM1.description";
  public static final String BAR_OBJECTIVE_RESOURCE_MT_RUPTURE_M1                = "barObjective.parameterWithName.mtRuptureM1.description";
  public static final String BAR_OBJECTIVE_RESOURCE_OBJ_MT_ECH_M1                = "barObjective.parameterWithName.objMtEchM1.description";
  public static final String BAR_OBJECTIVE_RESOURCE_MT_MOYEN_PP_M1               = "barObjective.parameterWithName.mtMoyenPpM1.description";
  public static final String BAR_OBJECTIVE_RESOURCE_NB_APP_ENTR_M1               = "barObjective.parameterWithName.nbAppEntrM1.description";
  public static final String BAR_OBJECTIVE_RESOURCE_NB_APP_SORT_M1               = "barObjective.parameterWithName.nbAppSortM1.description";
  public static final String BAR_OBJECTIVE_RESOURCE_NB_PP_M                      = "barObjective.parameterWithName.nbPpM.description";
  public static final String BAR_OBJECTIVE_RESOURCE_NB_PP_J                      = "barObjective.parameterWithName.nbPpJ.description";
  public static final String BAR_OBJECTIVE_RESOURCE_NB_PP_M1                     = "barObjective.parameterWithName.nbPpM1.description";


  public static final String FRONT_END_ERROR_MESSAGE_DUMP              = "front.end.error.message.dump";
  public static final String FRONT_END_ERROR_MESSAGE_STACK             = "front.end.error.message.stack";
  public static final String FRONT_END_ERROR_MESSAGE_ERROR_INCIDENT_ID = "front.end.error.message.error.incident.id";
  public static final String FRONT_END_ERROR_MESSAGE_CODE              = "front.end.error.message.code";
  public static final String FRONT_END_ERROR_MESSAGE_CONTEXT           = "front.end.error.message.context";
  public static final String FRONT_END_ERROR_MESSAGE_ERROR_MESSAGE     = "front.end.error.message.error.message";


  public static final String TODO_LIST_ELEMENTS               = "todo.list.parameterWithName.elements.description";
  public static final String TODO_LIST_ELEMENTS_ID            = "todo.list.parameterWithName.elements.id.description";
  public static final String TODO_LIST_ELEMENTS_TODO          = "todo.list.parameterWithName.elements.toDo.description";
  public static final String TODO_LIST_ELEMENTS_DISPLAY_TODO  = "todo.list.parameterWithName.elements.displayTodo.description";
  public static final String TODO_LIST_ELEMENTS_DONE          = "todo.list.parameterWithName.elements.done.description";
  public static final String TODO_LIST_ELEMENTS_DATE          = "todo.list.parameterWithName.elements.date.description";
  public static final String TODO_LIST_ELEMENTS_COMMENT       = "todo.list.parameterWithName.elements.comment.description";
  public static final String TODO_LIST_ELEMENTS_STATUS        = "todo.list.parameterWithName.elements.status.description";
  public static final String TODO_LIST_FREECOMMENT            = "todo.list.parameterWithName.freeComment.description";
  public static final String ELEMENTS_UPDATE_STATUS           = "todo.list.parameterWithName.elements.update.status.description";
  public static final String FREE_COMMENT_UPDATE_STATUS       = "todo.list.parameterWithName.freeComment.update.status.description";

	//invoice bundles
	public static final String E_AGGROUP_BUNDLE_ID                                = "aggroup.bundle.id";
	public static final String E_AGGROUP_CASE_REF                                 = "aggroup.case.ref";
	public static final String E_AGGROUP_FACTOR_NAME                              = "aggroup.factor.name";
	public static final String E_AGGROUP_CL_NAME                                  = "aggroup.cl.name";
	public static final String E_AGGROUP_CONTRACT_ID                              = "aggroup.contract.id";
	public static final String E_AGGROUP_EXT_GROUP_ID                             = "aggroup.ext.group.id";
	public static final String E_AGGROUP_GROUPING_DATE                            = "aggroup.grouping.dat";
	public static final String E_AGGROUP_GROUP_ID                                 = "aggroup.group.id";
	public static final String E_AGGROUP_CRETION_DATE                             = "aggroup.cretion.date";
	public static final String E_AGGROUP_CREATOR                                  = "aggroup.creator";
	public static final String E_AGGROUP_FIN_DATE                                 = "aggroup.fin.date";
	public static final String E_AGGROUP_EXT_PMT_DATE                             = "aggroup.ext.pmt.date";
	public static final String E_AGGROUP_AVG_DUE_DATE                             = "aggroup.avg.due.date";
	public static final String E_AGGROUP_VAL_DATE_PMT                             = "aggroup.val.date.pmt";
	public static final String E_AGGROUP_CONTENT                                  = "aggroup.content";
	public static final String E_AGGROUP_DB_NAME                                  = "aggroup.db.name";
	public static final String E_AGGROUP_FIN_ELEM_TYPE                            = "aggroup.fin.elem.type";
	public static final String E_AGGROUP_INVOICE_NO                               = "aggroup.invoice.no";
	public static final String E_AGGROUP_EMISSION_DATE                            = "aggroup.emission.date";
	public static final String E_AGGROUP_CREATION_DATE                            = "aggroup.creation.date";
	public static final String E_AGGROUP_DUE_DATE                                 = "aggroup.due.date";
	public static final String E_AGGROUP_TRN_CURRENCY                             = "aggroup.trn.currency";
	public static final String E_AGGROUP_TRN_AMOUNT                               = "aggroup.trn.amount";
	public static final String E_AGGROUP_CASE_CURR_CURRENCY                       = "aggroup.case.curr.currency";
	public static final String E_AGGROUP_CASE_CURR_AMOUNT                         = "aggroup.case.curr.amount";
	public static final String E_AGGROUP_FUND_CURRENCY                            = "aggroup.fund.currency";
	public static final String E_AGGROUP_FUND_AMOUNT                              = "aggroup.fund.amount";
	public static final String E_AGGROUP_MATCHED_CURRENCY                         = "aggroup.matched.currency";
	public static final String E_AGGROUP_MATCHED_AMOUNT                           = "aggroup.matched.amount";
	public static final String E_AGGROUP_MATCHED_CASE_CURRENCY                    = "aggroup.matched.case.currency";
	public static final String E_AGGROUP_MATCHED_CASE_AMOUNT                      = "aggroup.matched.case.amount";
	public static final String E_AGGROUP_MATCHING_DATE                            = "aggroup.matching.date";
	public static final String E_AGGROUP_OVERDUE                                  = "aggroup.overdue";
	public static final String E_AGGROUP_BALINTRN_CURRENCY                        = "aggroup.balintrn.currency";
	public static final String E_AGGROUP_BALINTRN_AMOUNT                          = "aggroup.balintrn.amount";
	public static final String E_AGGROUP_BAL_CASE_CURRENCY                        = "aggroup.bal.case.currency";
	public static final String E_AGGROUP_BAL_CASE_AMOUNT                          = "aggroup.bal.case.amount";
	public static final String E_AGGROUP_SEARCH_TEMP_RESOURCE                     = "aggroup.search.tmpResId";
	public static final String E_AGGROUP_SEARCH_CRITERIA_FILTER_DB_NAME           = "aggroup.search.criteria.fltrDbName";
	public static final String E_AGGROUP_SEARCH_CRITERIA_FILTER_INVOICE_NO        = "aggroup.search.criteria.fltrInvoiceNo";
	public static final String E_AGGROUP_SEARCH_CRITERIA_FILTER_EMISSION_DATE     = "aggroup.search.criteria.fltrEmissionDate";
	public static final String E_AGGROUP_SEARCH_CRITERIA_FILTER_CREATION_DATE     = "aggroup.search.criteria.fltrCreationDate";
	public static final String E_AGGROUP_SEARCH_CRITERIA_FILTER_DUE_DATE          = "aggroup.search.criteria.fltrDueDate";
	public static final String E_AGGROUP_SEARCH_CRITERIA_FILTER_TRN_AMOUNT        = "aggroup.search.criteria.fltrTrnAmount";
	public static final String E_AGGROUP_SEARCH_CRITERIA_FILTER_TRN_CURRENCY      = "aggroup.search.criteria.fltrTrnCurrency";
	public static final String E_AGGROUP_SEARCH_CRITERIA_FILTER_MATCHED_AMOUNT    = "aggroup.search.criteria.fltrMatchedAmount";
	public static final String E_AGGROUP_SEARCH_CRITERIA_FILTER_MATCHED_CURRENCY  = "aggroup.search.criteria.fltrMatchedCurrency";
	public static final String E_AGGROUP_SEARCH_CRITERIA_FILTER_MATCHING_DATE     = "aggroup.search.criteria.fltrMatchingDate";
	public static final String E_AGGROUP_SEARCH_CRITERIA_FILTER_BAL_IN_TRN_AMOUNT = "aggroup.search.criteria.fltrBalInTrnAmount";

  //ShortTermCreditResourceTest properties
  public static final String SHORT_TERM_CREDIT_CASE_REFERENCE               = "short.term.credit.case.reference";
  public static final String SHORT_TERM_CREDIT_EXTERNAL_CASE_REFERENCE      = "short.term.credit.external.case.reference";
  public static final String SHORT_TERM_CREDIT_CREDITOR_NAME                = "short.term.credit.creditor.name";
  public static final String SHORT_TERM_CREDIT_DEBTOR_NAME                  = "short.term.credit.debtor.name";
  public static final String SHORT_TERM_CREDIT_CBS_CONTRACT_NUMBER          = "short.term.credit.cbs.contract.number";
  public static final String SHORT_TERM_CREDIT_CONTRACT_STATUS              = "short.term.credit.contract.status";
  public static final String SHORT_TERM_CREDIT_LOAN_CREATION_DATE           = "short.term.credit.loan.creation.date";
  public static final String SHORT_TERM_CREDIT_LOAN_SIGNATURE_DATE          = "short.term.credit.loan.signature.date";
  public static final String SHORT_TERM_CREDIT_LOAN_EXPECTED_DATE           = "short.term.credit.loan.expected.date";
  public static final String SHORT_TERM_CREDIT_LOAN_REAL_END_DATE           = "short.term.credit.loan.real.end.date";
  public static final String SHORT_TERM_CREDIT_OVERINDEBTEDNESS             = "short.term.credit.overindebtedness";
  public static final String SHORT_TERM_CREDIT_LOAN_FORFEITURE_DATE         = "short.term.credit.loan.forfeiture.date";
  public static final String SHORT_TERM_CREDIT_LOAN_FORFEITURE_AMOUNT       = "short.term.credit.loan.forfeiture.amount";
  public static final String SHORT_TERM_CREDIT_LOAN_THRESHOLD               = "short.term.credit.loan.threshold";
  public static final String SHORT_TERM_CREDIT_COMMERCIAL_AGENCY            = "short.term.credit.commercial.agency";
  public static final String SHORT_TERM_CREDIT_COMMERCIAL_CBS_MANAGER       = "short.term.credit.commercial.cbs.manager";
  public static final String SHORT_TERM_CREDIT_LOAN_CURRENCY                = "short.term.credit.loan.currency";
  public static final String SHORT_TERM_CREDIT_LOAN_PRODUCT_CATEGORY        = "short.term.credit.loan.product.category";
  public static final String SHORT_TERM_CREDIT_LOAN_PRODUCT                 = "short.term.credit.loan.product";
  public static final String SHORT_TERM_CREDIT_PRODUCT_CODE                 = "short.term.credit.product.code";
  public static final String SHORT_TERM_CREDIT_FINANCING_AGENCY             = "short.term.credit.financing.agency";
  public static final String SHORT_TERM_CREDIT_FINANCED_ITEM                = "short.term.credit.financed.item";
  public static final String SHORT_TERM_CREDIT_MATURITY_DATE                = "short.term.credit.maturity.date";
  public static final String SHORT_TERM_CREDIT_LOAN_AMOUNT                  = "short.term.credit.loan.amount";
  public static final String SHORT_TERM_CREDIT_AMOUNT_USED                  = "short.term.credit.amount.used";
  public static final String SHORT_TERM_CREDIT_AVAILABLE_AMOUNT             = "short.term.credit.available.amount";
  public static final String SHORT_TERM_CREDIT_REINSTRIBUTION_TYPE          = "short.term.credit.reinstribution.type";
  public static final String SHORT_TERM_CREDIT_REIMBURSEMENT_PERIODICITY    = "short.term.credit.reimbursement.periodicity";
  public static final String SHORT_TERM_CREDIT_MONTHLY_PAYMENT_DAY          = "short.term.credit.monthly.payment.day";
  public static final String SHORT_TERM_CREDIT_MATURITY_DATE_AMOUNT         = "short.term.credit.maturity.date.amount";
  public static final String SHORT_TERM_CREDIT_NUMBER_OF_REPORTS            = "short.term.credit.number.of.reports";
  public static final String SHORT_TERM_CREDIT_FIRST_MATURITY_DATE          = "short.term.credit.first.maturity.date";
  public static final String SHORT_TERM_CREDIT_NEXT_MATURITY_DATE           = "short.term.credit.next.maturity.date";
  public static final String SHORT_TERM_CREDIT_LAST_MATURITY_DATE           = "short.term.credit.last.maturity.date";
  public static final String SHORT_TERM_CREDIT_FIRST_UNPAID_DATE            = "short.term.credit.first.unpaid.date";
  public static final String SHORT_TERM_CREDIT_INTEREST_TYPE                = "short.term.credit.interest.type";
  public static final String SHORT_TERM_CREDIT_INTEREST_INDEX               = "short.term.credit.interest.index";
  public static final String SHORT_TERM_CREDIT_CONTRACT_INTEREST_RATE       = "short.term.credit.contract.interest.rate";
  public static final String SHORT_TERM_CREDIT_LOAN_MARGIN                  = "short.term.credit.loan.margin";
  public static final String SHORT_TERM_CREDIT_LATE_INTEREST_INDEX          = "short.term.credit.late.interest.index";
  public static final String SHORT_TERM_CREDIT_LATE_INTEREST_RATE           = "short.term.credit.late.interest.rate";
  public static final String SHORT_TERM_CREDIT_LATE_INTEREST_MARGIN         = "short.term.credit.late.interest.margin";
  public static final String SHORT_TERM_CREDIT_INSURANCE_CODE_ONE           = "short.term.credit.insurance.code.one";
  public static final String SHORT_TERM_CREDIT_INSURANCE_CODE_TWO           = "short.term.credit.insurance.code.two";
  public static final String SHORT_TERM_CREDIT_ACCOUNT_POSITION             = "short.term.credit.account.position";
  public static final String SHORT_TERM_CREDIT_DEBTOR_BALANCE_START_DATE    = "short.term.credit.debtor.balance.start.date";
  public static final String SHORT_TERM_CREDIT_PAYMENT_METHOD               = "short.term.credit.payment.method";
  public static final String SHORT_TERM_CREDIT_DEDUCTION_ACCOUNT_REFERENCE  = "short.term.credit.deduction.account.reference";
  public static final String SHORT_TERM_CREDIT_LOAN_HOLDER                  = "short.term.credit.loan.holder";
  public static final String SHORT_TERM_CREDIT_ACCOUNT_PRODUCT_REFERENCE    = "short.term.credit.account.product.reference";
  public static final String SHORT_TERM_CREDIT_CBS_RECOVERY_ACCOUNT_REF     = "short.term.credit.cbs.recovery.account.ref";
  public static final String SHORT_TERM_CREDIT_BASEL_DEFAULT_MOTIVE         = "short.term.credit.basel.default.motive";
  public static final String SHORT_TERM_CREDIT_DEFAULT_BASE_ENTRY_DATE      = "short.term.credit.default.base.entry.date";
  public static final String SHORT_TERM_CREDIT_LOCAL_MOTIVE                 = "short.term.credit.local.motive";
  public static final String SHORT_TERM_CREDIT_LOCAL_DEFAULT_ENTRY_TYPE     = "short.term.credit.local.default.entry.type";
  public static final String SHORT_TERM_CREDIT_CALCULATED_PROVISIONS_AMOUNT = "short.term.credit.calculated.provisions.amount";
  public static final String SHORT_TERM_CREDIT_CLIENT_FIRST_ADDRESS         = "short.term.credit.client.first.address";
  public static final String SHORT_TERM_CREDIT_CLIENT_SECOND_ADDRESS        = "short.term.credit.client.second.address";
  public static final String SHORT_TERM_CREDIT_CLIENT_THIRD_ADDRESS         = "short.term.credit.client.third.address";
  public static final String SHORT_TERM_CREDIT_CLIENT_FOURTH_ADDRESS        = "short.term.credit.client.fourth.address";
  public static final String SHORT_TERM_CREDIT_COUNTRY                      = "short.term.credit.country";
  public static final String SHORT_TERM_CREDIT_POSTAL_CODE                  = "short.term.credit.postal.code";
  public static final String SHORT_TERM_CREDIT_CITY                         = "short.term.credit.city";

  public static final String CLIENT_INVOICING_REFINDIVIDUAL                    = "client.invoicing.refindividual";
  public static final String CLIENT_INVOICING_SHOW                             = "client.invoicing.show";
  public static final String CLIENT_INVOICING_ENTRY_TYPE                       = "client.invoicing.entry.type";
  public static final String CLIENT_INVOICING_DOCUMENT_NO                      = "client.invoicing.document.no";
  public static final String CLIENT_INVOICING_CASE_ID                          = "client.invoicing.case.id";
  public static final String CLIENT_INVOICING_DATE                             = "client.invoicing.date";
  public static final String CLIENT_INVOICING_END_DATE                         = "client.invoicing.end.date";
  public static final String CLIENT_INVOICING_AMOUNT                           = "client.invoicing.amount";
  public static final String CLIENT_INVOICING_PAID_AMOUNT                      = "client.invoicing.paid.amount";
  public static final String CLIENT_INVOICING_DEBIT                            = "client.invoicing.debit";
  public static final String CLIENT_INVOICING_CREDIT                           = "client.invoicing.credit";
  public static final String CLIENT_INVOICING_PAID_CREDIT                      = "client.invoicing.paid.credit";
  public static final String CLIENT_INVOICING_REMINDER                         = "client.invoicing.reminder";
  public static final String CLIENT_INVOICING_REMINDER_DATE                    = "client.invoicing.reminder.date";
  public static final String CLIENT_INVOICING_ALLOCATIONS_ON_HOLD              = "client.invoicing.allocations_on.hold";
  public static final String CLIENT_INVOICING_BALANCE                          = "client.invoicing.balance";
  public static final String CLIENT_INVOICING_GLOBALINVOICENO                  = "client.invoicing.globalinvoiceno";
  public static final String CLIENT_INVOICING_TYPE                             = "client.invoicing.type";
  public static final String CLIENT_INVOICING_CLNAME                           = "client.invoicing.clname";
  public static final String CLIENT_INVOICING_CLCASEID                         = "client.invoicing.clcaseid";
  public static final String CLIENT_INVOICING_INVOICEDATE                      = "client.invoicing.invoicedate";
  public static final String CLIENT_INVOICING_INVOICINGDETAILS_INVOICINGITEM   = "client.invoicing.invoicingdetails.invoicingitem";
  public static final String CLIENT_INVOICING_INVOICINGDETAILS_DESIGNATION     = "client.invoicing.invoicingdetails.designation";
  public static final String CLIENT_INVOICING_INVOICINGDETAILS_INVOICETYPE     = "client.invoicing.invoicingdetails.invoicetype";
  public static final String CLIENT_INVOICING_INVOICINGDETAILS_AMOUNT          = "client.invoicing.invoicingdetails.amount";
  public static final String CLIENT_INVOICING_INVOICINGDETAILS_VAT             = "client.invoicing.invoicingdetails.vat";
  public static final String CLIENT_INVOICING_INVOICINGDETAILS_AMOUNTSIGN      = "client.invoicing.invoicingdetails.amountsign";
  public static final String CLIENT_INVOICING_INVOICINGDETAILS_PAIDORCANCELLED = "client.invoicing.invoicingdetails.paidorcancelled";
  public static final String CLIENT_INVOICING_INVOICINGDETAILS_REPAYMENTS      = "client.invoicing.invoicingdetails.repayments";
  public static final String CLIENT_INVOICING_PAYMENTID                        = "client.invoicing.paymentid";
  public static final String CLIENT_INVOICING_PAYMENTDATE                      = "client.invoicing.paymentdate";
  public static final String CLIENT_INVOICING_INVOICENO                        = "client.invoicing.invoiceno";
  public static final String CLIENT_INVOICING_INVOICINGITEM                    = "client.invoicing.invoicingitem";
  public static final String CLIENT_INVOICING_PAYMENTTYPE                      = "client.invoicing.paymenttype";
  public static final String CLIENT_INVOICING_POSTCODE                         = "client.invoicing.postcode";
  public static final String CLIENT_INVOICING_CITY                             = "client.invoicing.city";
  public static final String CLIENT_INVOICING_PHONE                            = "client.invoicing.phone";
  public static final String CLIENT_INVOICING_ADDRESS                          = "client.invoicing.address";
  public static final String CLIENT_INVOICING_PAYMENTSTATE_PAYMENTID           = "client.invoicing.paymentstate.paymentid";
  public static final String CLIENT_INVOICING_PAYMENTSTATE_PAYMENTMETHOD       = "client.invoicing.paymentstate.paymentmethod";
  public static final String CLIENT_INVOICING_PAYMENTSTATE_GLOBALINVOICENO     = "client.invoicing.paymentstate.globalinvoiceno";
  public static final String CLIENT_INVOICING_PAYMENTSTATE_PAYMENTDATE         = "client.invoicing.paymentstate.paymentdate";
  public static final String CLIENT_INVOICING_PAYMENTSTATE_AMOUNT              = "client.invoicing.paymentstate.amount";
  // Constraints
  public static final String CONSTRAINT_CLIENT_INVOICING_SHOW                  = "constraint.client.invoicing.show";

  //CurrentAccountResourceTest properties
  public static final String CURRENT_ACCOUNT_CASE_REFERENCE          = "current.account.case.reference";
  public static final String CURRENT_ACCOUNT_EXTERNAL_CASE_REFERENCE = "current.account.external.case.reference";
  public static final String CURRENT_ACCOUNT_CLIENT_NAME             = "current.account.client.name";
  public static final String CURRENT_ACCOUNT_DEBTOR_NAME             = "current.account.debtor.name";
  public static final String CURRENT_ACCOUNT_CONTRACT_NUMBER         = "current.account.contract.number";
  public static final String CURRENT_ACCOUNT_CREATION_DATE           = "current.account.creation.date";
  public static final String CURRENT_ACCOUNT_AP_DATE                 = "current.account.ap.date";
  public static final String CURRENT_ACCOUNT_AP_DUE_AMOUNT           = "current.account.ap.due.amount";
  public static final String CURRENT_ACCOUNT_COLLECTION_THRESHOLD    = "current.account.collection.threshold";
  public static final String CURRENT_ACCOUNT_REAL_END_DATE           = "current.account.real.end.date";
  public static final String CURRENT_ACCOUNT_SALES_AGENCY            = "current.account.sales.agency";
  public static final String CURRENT_ACCOUNT_SALES_MANAGER           = "current.account.sales.manager";
  public static final String CURRENT_ACCOUNT_CONTRACT_CURRENCY       = "current.account.contract.currency";
  public static final String CURRENT_ACCOUNT_CATEGORY                = "current.account.category";
  public static final String CURRENT_ACCOUNT_PRODUCT                 = "current.account.product";
  public static final String CURRENT_ACCOUNT_LOCAL_PRODUCT           = "current.account.local.product";
  public static final String CURRENT_ACCOUNT_FINANCING_AGENCY        = "current.account.financing.agency";
  public static final String CURRENT_ACCOUNT_INTEREST_TYPE           = "current.account.interest.type";
  public static final String CURRENT_ACCOUNT_INTEREST_INDEX          = "current.account.interest.index";
  public static final String CURRENT_ACCOUNT_INTEREST_RATE           = "current.account.interest.rate";
  public static final String CURRENT_ACCOUNT_MARGIN                  = "current.account.margin";
  public static final String CURRENT_ACCOUNT_IR_INDEX                = "current.account.ir.index";
  public static final String CURRENT_ACCOUNT_IR_RATE                 = "current.account.ir.rate";
  public static final String CURRENT_ACCOUNT_IR_MARGIN               = "current.account.ir.margin";
  public static final String CURRENT_ACCOUNT_INSURANCE_CODE1         = "current.account.insurance.code1";
  public static final String CURRENT_ACCOUNT_INSURANCE_CODE2         = "current.account.insurance.code2";
  public static final String CURRENT_ACCOUNT_PERMANENT_AUTH_NUMBER   = "current.account.permanent.auth.number";
  public static final String CURRENT_ACCOUNT_PERMANENT_AUTH_AMOUNT   = "current.account.permanent.auth.amount";
  public static final String CURRENT_ACCOUNT_PERM_AUTH_START_DATE    = "current.account.perm.auth.start.date";
  public static final String CURRENT_ACCOUNT_PERM_AUTH_END_DATE      = "current.account.perm.auth.end.date";
  public static final String CURRENT_ACCOUNT_DATE_OVERDRAFT_EXC      = "current.account.date.overdraft.exc";
  public static final String CURRENT_ACCOUNT_NUMBER_DAYS_EXC         = "current.account.number.days.exc";
  public static final String CURRENT_ACCOUNT_DEBIT_BAL_START_DATE    = "current.account.debit.bal.start.date";
  public static final String CURRENT_ACCOUNT_NB_DAYS_DEBIT_BALANCE   = "current.account.nb.days.debit.balance";
  public static final String CURRENT_ACCOUNT_AUTHORIZATION_PERIOD    = "current.account.authorization.period";
  public static final String CURRENT_ACCOUNT_ACCOUNT_POSITION        = "current.account.account.position";
  public static final String CURRENT_ACCOUNT_AUTH_OVERDRAFT_AMOUNT   = "current.account.auth.overdraft.amount";
  public static final String CURRENT_ACCOUNT_BANK_CARD_STATUS        = "current.account.bank.card.status";
  public static final String CURRENT_ACCOUNT_FUTURE_BANK_CARD_AMOUNT = "current.account.future.bank.card.amount";
  public static final String CURRENT_ACCOUNT_NOTIF_THIRD_HOLDER_DATE = "current.account.notif.third.holder.date";
  public static final String CURRENT_ACCOUNT_REMAINING_AMOUNT        = "current.account.remaining.amount";
  public static final String CURRENT_ACCOUNT_RECOVERY_ACCOUNT        = "current.account.recovery.account";
  public static final String CURRENT_ACCOUNT_CALCULATED_PROVISIONS   = "current.account.calculated.provisions";
  public static final String CURRENT_ACCOUNT_BASEL_DEFAULT_MOTIVE    = "current.account.basel.default.motive";
  public static final String CURRENT_ACCOUNT_DEFAULT_BASEL_ENTRY_DT  = "current.account.default.basel.entry.dt";
  public static final String CURRENT_ACCOUNT_LOCAL_DEFAULT_MOTIVE    = "current.account.local.default.motive";
  public static final String CURRENT_ACCOUNT_DEFAULT_LOCAL_ENTRY_DT  = "current.account.default.local.entry.dt";
  public static final String CURRENT_ACCOUNT_CONTRACT_ADDRESS1       = "current.account.contract.address1";
  public static final String CURRENT_ACCOUNT_CONTRACT_ADDRESS2       = "current.account.contract.address2";
  public static final String CURRENT_ACCOUNT_CONTRACT_ADDRESS3       = "current.account.contract.address3";
  public static final String CURRENT_ACCOUNT_CONTRACT_ADDRESS4       = "current.account.contract.address4";
  public static final String CURRENT_ACCOUNT_COUNTRY                 = "current.account.country";
  public static final String CURRENT_ACCOUNT_POSTAL_CODE             = "current.account.postal.code";
  public static final String CURRENT_ACCOUNT_CITY                    = "current.account.city";
  public static final String CURRENT_ACCOUNT_REFPIECE                = "current.account.refpiece";
  public static final String CURRENT_ACCOUNT_DCP_REFERENCE           = "current.account.dcp.reference";
  public static final String CURRENT_ACCOUNT_DCP_START_DATE          = "current.account.dcp.start.date";
  public static final String CURRENT_ACCOUNT_DCP_END_DATE            = "current.account.dcp.end.date";
  public static final String CURRENT_ACCOUNT_DCP_AMOUNT              = "current.account.dcp.amount";
  public static final String CURRENT_ACCOUNT_AUTH_START_DATE         = "current.account.auth.start.date";
  public static final String CURRENT_ACCOUNT_AUTH_END_DATE           = "current.account.auth.end.date";
  public static final String CURRENT_ACCOUNT_AUTH_AMOUNT             = "current.account.auth.amount";

  public static String TRANSACTION_HEADER_NAME_DESC = "transaction.header.name.desc";

  //Widget properties
  public static final String WIDGET_WTG_PROC_CATEGORY                = "widget.wtg.proc.category";
  public static final String WIDGET_WTG_PROC_PROCEDURE_NAME          = "widget.wtg.proc.procedure.name";
  public static final String WIDGET_WTG_PROC_DISP_PROCEDURE_NAME     = "widget.wtg.proc.disp.procedure.name";
  public static final String WIDGET_WTG_PROC_NUMBER_STAGES           = "widget.wtg.proc.number.stages";

  public static final String WIDGET_WTG_MANAGER_REF_USER             = "widget.wtg.manager.ref.user";
  public static final String WIDGET_WTG_MANAGER_USER_NAME            = "widget.wtg.manager.user.name";
  public static final String WIDGET_WTG_MANAGER_NUMBER_STAGES        = "widget.wtg.manager.number.stages";

  public static final String WIDGET_MANAGER_INVOICES_STATE           = "widget.manager.invoices.state";
  public static final String WIDGET_MANAGER_INVOICES_AGING           = "widget.manager.invoices.aging";
  public static final String WIDGET_MANAGER_INVOICES_NUMBER          = "widget.manager.invoices.number";

  public static final String WIDGET_TASKS_PERIOD            = "widget.tasks.period";
  public static final String WIDGET_TASKS_PERIOD_ID         = "widget.tasks.period.id";
  public static final String WIDGET_TASKS_PERIOD_START      = "widget.tasks.period.start";
  public static final String WIDGET_TASKS_PERIOD_END        = "widget.tasks.period.end";
  public static final String WIDGET_TASKS_NUM_TASKS         = "widget.tasks.num.tasks";
  public static final String WIDGET_TASK_DETAILS_PERIOD_ID =  "widget.task.details.period_id";
  public static final String WIDGET_TASK_DETAILS_NAME      =  "widget.task.details.name";
  public static final String WIDGET_TASK_DETAILS_NUMBER    =  "widget.task.details.number";

  //Communication channels
  public static final String E_INDIV_TAB_TEL_EMAIL_REF_INDIVIDUAL              = "communication.channel.ref.individual";
  public static final String E_INDIV_TAB_TEL_EMAIL_TYPE                        = "communication.channel.type";
  public static final String E_INDIV_TAB_TEL_EMAIL_DISPLAY_TYPE                = "communication.channel.display.type";
  public static final String E_INDIV_TAB_TEL_EMAIL_TYPE_VALUE                  = "communication.channel.type.value";
  public static final String E_INDIV_TAB_TEL_EMAIL_DISPLAY_TYPE_VALUE          = "communication.channel.display.type.value";
  public static final String E_INDIV_TAB_TEL_EMAIL_COUNTRY                     = "communication.channel.country";
  public static final String E_INDIV_TAB_TEL_EMAIL_DISPLAY_COUNTRY             = "communication.channel.display.country";
  public static final String E_INDIV_TAB_TEL_EMAIL_NB_OR_EMAIL                 = "communication.channel.nb.or.email";
  public static final String E_INDIV_TAB_TEL_EMAIL_TITLE                       = "communication.channel.title";
  public static final String E_INDIV_TAB_TEL_EMAIL_UPD_SOURCE                  = "communication.channel.upd.source";
  public static final String E_INDIV_TAB_TEL_EMAIL_UPD_DATE                    = "communication.channel.upd.date";
  public static final String E_INDIV_TAB_TEL_EMAIL_IMX_UNIQUE_ID               = "communication.channel.imx.unique.id";
  public static final String E_INDIV_TAB_TEL_EMAIL_VAL                         = "communication.channel.val";
  public static final String E_INDIV_TAB_TEL_EMAIL_LETTER_SEND_PREF            = "communication.channel.letter.send.pref";
  public static final String E_INDIV_TAB_TEL_EMAIL_EMAIL_CC                    = "communication.channel.email.cc";
  public static final String E_INDIV_TAB_TEL_EMAIL_IS_EMAIL_CLIENT_OUTPUT      = "communication.channel.is.email.client.output";
  public static final String E_INDIV_TAB_TEL_EMAIL_CONTENT                     = "communication.channel.content";

  //Cancelled elements properties
  public static final String CANCELLED_ELEMENT_MGMT_DATE         = "cancelled.element.mgmt.date";
  public static final String CANCELLED_ELEMENT_TYPE              = "cancelled.element.type";
  public static final String CANCELLED_ELEMENT_DISPLAY_TYPE      = "cancelled.element.display.type";
  public static final String CANCELLED_ELEMENT_TITLE             = "cancelled.element.title";
  public static final String CANCELLED_ELEMENT_DISPLAY_TITLE     = "cancelled.element.display.title";
  public static final String CANCELLED_ELEMENT_LOGIN             = "cancelled.element.login";
  public static final String CANCELLED_ELEMENT_DISPLAY_LOGIN     = "cancelled.element.display.login";
  public static final String CANCELLED_ELEMENT_AMOUNT            = "cancelled.element.amount";
  public static final String CANCELLED_ELEMENT_CANCELLATION      = "cancelled.element.cancellation";
  public static final String CANCELLED_ELEMENT_CANCELLATION_DATE = "cancelled.element.cancellation.date";
  public static final String CANCELLED_ELEMENT_IMX_UN_ID         = "cancelled.element.imx.un.id";

  public static final String CANCELLED_ELEMENT_SEARCH_ID         = "cancelled.element.search.id";

  public static final String CANCELLED_ELEMENT_FLTR_MGMT_DATE         = "cancelled.element.fltr.mgmt.date";
  public static final String CANCELLED_ELEMENT_FLTR_TYPE              = "cancelled.element.fltr.type";
  public static final String CANCELLED_ELEMENT_FLTR_DISPLAY_TYPE      = "cancelled.element.fltr.display.type";
  public static final String CANCELLED_ELEMENT_FLTR_TITLE             = "cancelled.element.fltr.title";
  public static final String CANCELLED_ELEMENT_FLTR_DISPLAY_TITLE     = "cancelled.element.fltr.display.title";
  public static final String CANCELLED_ELEMENT_FLTR_LOGIN             = "cancelled.element.fltr.login";
  public static final String CANCELLED_ELEMENT_FLTR_DISPLAY_LOGIN     = "cancelled.element.fltr.display.login";
  public static final String CANCELLED_ELEMENT_FLTR_AMOUNT            = "cancelled.element.fltr.amount";
  public static final String CANCELLED_ELEMENT_FLTR_CANCELLATION      = "cancelled.element.fltr.cancellation";
  public static final String CANCELLED_ELEMENT_FLTR_CANCELLATION_DATE = "cancelled.element.fltr.cancellation.date";
  public static final String CANCELLED_ELEMENT_FLTR_IMX_UN_ID         = "cancelled.element.fltr.imx.un.id";

  //Deleted involved parties properties
  public static final String DELETED_PARTIES_ROLE          = "deleted.parties.role";
  public static final String DELETED_PARTIES_DISPLAY_ROLE  = "deleted.parties.display.role";
  public static final String DELETED_PARTIES_LAST_NAME     = "deleted.parties.last.name";
  public static final String DELETED_PARTIES_FIRST_NAME    = "deleted.parties.first.name";
  public static final String DELETED_PARTIES_EXTERNAL_REF  = "deleted.parties.external.ref";
  public static final String DELETED_PARTIES_CREATE_DATE   = "deleted.parties.create.date";
  public static final String DELETED_PARTIES_CANCEL_DATE   = "deleted.parties.cancel.date";
  public static final String DELETED_PARTIES_LOGIN         = "deleted.parties.login";

  //Msg_queue calls properties
  public static final String MSG_QUEUE_CASE_REF         = "msg.queue.case.ref";
  public static final String MSG_QUEUE_PRTY             = "msg.queue.prty";
  public static final String MSG_QUEUE_CALL_ID          = "msg.queue.call.id";
  public static final String MSG_QUEUE_BUFF             = "msg.queue.buff";
  public static final String MSG_QUEUE_CALLS_TYPE       = "msg.queue.calls.type";
  public static final String MSG_QUEUE_CALLS            = "msg.queue.calls";
  public static final String MSG_QUEUE_AMOUNT           = "msg.queue.amount";
  public static final String MSG_QUEUE_CALLS_DATE       = "msg.queue.calls.date";
  public static final String MSG_QUEUE_USER_LOGIN       = "msg.queue.user.login";
  public static final String MSG_QUEUE_LOCKED_REF       = "msg.queue.locked.ref";
  public static final String MSG_QUEUE_LOCKED_CASE_TYPE = "msg.queue.locked.case.type";
  public static final String MSG_QUEUE_LOCKED_BY_USER   = "msg.queue.locked.by.user";
  public static final String MSG_QUEUE_DATE_LOCK        = "msg.queue.date.lock";
  public static final String MSG_QUEUE_LOCKED_BY        = "msg.queue.locked.by";


  //Initial Gpiece Revolving Credit
  public static final String INITIAL_DOCUMENT_REVOLVING_CREDIT_CONTRACT_ID                   = "gpieceRevolvingCredit.parameterWithName.contractId.description";
  public static final String INITIAL_DOCUMENT_REVOLVING_CREDIT_CREATION_DATE                 = "gpieceRevolvingCredit.parameterWithName.creationDate.description";
  public static final String INITIAL_DOCUMENT_REVOLVING_CREDIT_SIGNDATE                      = "gpieceRevolvingCredit.parameterWithName.signDate.description";
  public static final String INITIAL_DOCUMENT_REVOLVING_CREDIT_END_DATE                      = "gpieceRevolvingCredit.parameterWithName.endDate.description";
  public static final String INITIAL_DOCUMENT_REVOLVING_CREDIT_DUE_AMOUNT_DT                 = "gpieceRevolvingCredit.parameterWithName.dueAmountDT.description";
  public static final String INITIAL_DOCUMENT_REVOLVING_CREDIT_COLLECTION_THRESHOLD          = "gpieceRevolvingCredit.parameterWithName.collectionThreshold.description";
  public static final String INITIAL_DOCUMENT_REVOLVING_CREDIT_AGENCY                        = "gpieceRevolvingCredit.parameterWithName.agency.description";
  public static final String INITIAL_DOCUMENT_REVOLVING_CREDIT_COMMERCIAL_MANAGER            = "gpieceRevolvingCredit.parameterWithName.commercialManager.description";
  public static final String INITIAL_DOCUMENT_REVOLVING_CREDIT_LOCAL_PRODUCT                 = "gpieceRevolvingCredit.parameterWithName.localProduct.description";
  public static final String INITIAL_DOCUMENT_REVOLVING_CREDIT_FIN_AGENCY                    = "gpieceRevolvingCredit.parameterWithName.finAgency.description";
  public static final String INITIAL_DOCUMENT_REVOLVING_CREDIT_SELLER_CODE                   = "gpieceRevolvingCredit.parameterWithName.sellerCode.description";
  public static final String INITIAL_DOCUMENT_REVOLVING_CREDIT_SELLER_NAME                   = "gpieceRevolvingCredit.parameterWithName.sellerName.description";
  public static final String INITIAL_DOCUMENT_REVOLVING_CREDIT_MAX_CREDIT_LIMIT              = "gpieceRevolvingCredit.parameterWithName.maxCreditLimit.description";
  public static final String INITIAL_DOCUMENT_REVOLVING_CREDIT_AVAILABLEAMT                  = "gpieceRevolvingCredit.parameterWithName.availableAmt.description";
  public static final String INITIAL_DOCUMENT_REVOLVING_CREDIT_TYPE_REIMBURS                 = "gpieceRevolvingCredit.parameterWithName.typeReimburs.description";
  public static final String INITIAL_DOCUMENT_REVOLVING_CREDIT_DISPLAY_TYPE_REIMBURS         = "gpieceRevolvingCredit.parameterWithName.displayTypeReimburs.description";
  public static final String INITIAL_DOCUMENT_REVOLVING_CREDIT_REIMBURS_PERIODICITY          = "gpieceRevolvingCredit.parameterWithName.reimbursPeriodicity.description";
  public static final String INITIAL_DOCUMENT_REVOLVING_CREDIT_EIMBURS_PERIODICITY           = "gpieceRevolvingCredit.parameterWithName.eimbursPeriodicity.description";
  public static final String INITIAL_DOCUMENT_REVOLVING_CREDIT_MONTLY_PMT_DATE               = "gpieceRevolvingCredit.parameterWithName.montlyPmtDate.description";
  public static final String INITIAL_DOCUMENT_REVOLVING_CREDIT_MATURITY_DATE_AMOUNT          = "gpieceRevolvingCredit.parameterWithName.maturityDateAmount.description";
  public static final String INITIAL_DOCUMENT_REVOLVING_CREDIT_FIRST_DATE                    = "gpieceRevolvingCredit.parameterWithName.firstDate.description";
  public static final String INITIAL_DOCUMENT_REVOLVING_CREDIT_NEXT_PMT_DATE                 = "gpieceRevolvingCredit.parameterWithName.nextPmtDate.description";
  public static final String INITIAL_DOCUMENT_REVOLVING_CREDIT_NB_EXTENSIONS                 = "gpieceRevolvingCredit.parameterWithName.nbExtensions.description";
  public static final String INITIAL_DOCUMENT_REVOLVING_CREDIT_FIRST_UNPAID_DATE             = "gpieceRevolvingCredit.parameterWithName.firstUnpaidDate.description";
  public static final String INITIAL_DOCUMENT_REVOLVING_CREDIT_INTEREST_TYPE                 = "gpieceRevolvingCredit.parameterWithName.interestType.description";
  public static final String INITIAL_DOCUMENT_REVOLVING_CREDIT_DISPLAY_INTEREST_TYPE         = "gpieceRevolvingCredit.parameterWithName.displayInterestType.description";
  public static final String INITIAL_DOCUMENT_REVOLVING_CREDIT_INTEREST_INDEX                = "gpieceRevolvingCredit.parameterWithName.interestIndex.description";
  public static final String INITIAL_DOCUMENT_REVOLVING_CREDIT_DISPLAY_INTEREST_RATE         = "gpieceRevolvingCredit.parameterWithName.displayInterestRate.description";
  public static final String INITIAL_DOCUMENT_REVOLVING_CREDIT_MARGIN                        = "gpieceRevolvingCredit.parameterWithName.margin.description";
  public static final String INITIAL_DOCUMENT_REVOLVING_CREDIT_LATE_PMT_INDEX                = "gpieceRevolvingCredit.parameterWithName.latePmtIndex.description";
  public static final String INITIAL_DOCUMENT_REVOLVING_CREDIT_DISPLAY_LATE_PMTRATE          = "gpieceRevolvingCredit.parameterWithName.displayLatePmtRate.description";
  public static final String INITIAL_DOCUMENT_REVOLVING_CREDIT_IRINDEX_MARGIN                = "gpieceRevolvingCredit.parameterWithName.irIndexMargin.description";
  public static final String INITIAL_DOCUMENT_REVOLVING_CREDIT_INSURANCE_CODE1               = "gpieceRevolvingCredit.parameterWithName.insuranceCode1.description";
  public static final String INITIAL_DOCUMENT_REVOLVING_CREDIT_INSURANCE_CODE2               = "gpieceRevolvingCredit.parameterWithName.insuranceCode2.description";
  public static final String INITIAL_DOCUMENT_REVOLVING_CREDIT_UNMATUREDAMT                  = "gpieceRevolvingCredit.parameterWithName.unmaturedAmt.description";
  public static final String INITIAL_DOCUMENT_REVOLVING_CREDIT_DISPLAY_UNMATUREDAMT          = "gpieceRevolvingCredit.parameterWithName.displayUnmaturedAmt.description";
  public static final String INITIAL_DOCUMENT_REVOLVING_CREDIT_TOTAL_UNMATUREDAMT            = "gpieceRevolvingCredit.parameterWithName.totalUnmaturedAmt.description";
  public static final String INITIAL_DOCUMENT_REVOLVING_CREDIT_INTEREST_RATE                 = "gpieceRevolvingCredit.parameterWithName.interestRate.description";
  public static final String INITIAL_DOCUMENT_REVOLVING_CREDIT_LATE_PMT_RATE                 = "gpieceRevolvingCredit.parameterWithName.latePmtRate.description";
  public static final String INITIAL_DOCUMENT_REVOLVING_CREDIT_PAYMENT_METHOD                = "gpieceRevolvingCredit.parameterWithName.paymentMethod.description";
  public static final String INITIAL_DOCUMENT_REVOLVING_CREDIT_DISPLAY_PAYMENT_METHOD        = "gpieceRevolvingCredit.parameterWithName.displayPaymentMethod.description";
  public static final String INITIAL_DOCUMENT_REVOLVING_CREDIT_DISPLAY_DEDUCTION_ACCONT      = "gpieceRevolvingCredit.parameterWithName.displayDeductionAccont.description";
  public static final String INITIAL_DOCUMENT_REVOLVING_CREDIT_HOLDER                        = "gpieceRevolvingCredit.parameterWithName.holder.description";
  public static final String INITIAL_DOCUMENT_REVOLVING_CREDIT_DISPLAY_RECOVERY_ACCOUNT      = "gpieceRevolvingCredit.parameterWithName.displayRecoveryAccount.description";
  public static final String INITIAL_DOCUMENT_REVOLVING_CREDIT_DEDUCTION_ACCONT              = "gpieceRevolvingCredit.parameterWithName.deductionAccont.description";
  public static final String INITIAL_DOCUMENT_REVOLVING_CREDIT_RECOVERY_ACCOUNT              = "gpieceRevolvingCredit.parameterWithName.recoveryAccount.description";
  public static final String INITIAL_DOCUMENT_REVOLVING_CREDIT_BAIL_OUT_DEFAULT_MOTIV        = "gpieceRevolvingCredit.parameterWithName.bailOutDefaultMotiv.description";
  public static final String INITIAL_DOCUMENT_REVOLVING_CREDIT_DEFAULT_BAILOUTDATE           = "gpieceRevolvingCredit.parameterWithName.defaultBailOutDate.description";
  public static final String INITIAL_DOCUMENT_REVOLVING_CREDIT_LOCAL_DEFAULT_MOTIVE          = "gpieceRevolvingCredit.parameterWithName.localDefaultMotive.description";
  public static final String INITIAL_DOCUMENT_REVOLVING_CREDIT_LOCAL_DEFAULT_ENTRY_DATE      = "gpieceRevolvingCredit.parameterWithName.localDefaultEntryDate.description";
  public static final String INITIAL_DOCUMENT_REVOLVING_CREDIT_CALCULATED_PROVISIONS         = "gpieceRevolvingCredit.parameterWithName.calculatedProvisions.description";

//Initial B1 Revolving Credit
  public static final String INITIAL_DOCUMENT_REVOLVING_CREDIT_B1_REFRENCEDB                 = "gpieceRevolvingCredit.parameterWithName.refrenceDb.description";
  public static final String INITIAL_DOCUMENT_REVOLVING_CREDIT_B1_CLIENTTYPE                 = "gpieceRevolvingCredit.parameterWithName.clientType.description";
  public static final String INITIAL_DOCUMENT_REVOLVING_CREDIT_B1_APPLICATION_DATE           = "gpieceRevolvingCredit.parameterWithName.applicationDate.description";
  public static final String INITIAL_DOCUMENT_REVOLVING_CREDIT_B1_TTY                        = "gpieceRevolvingCredit.parameterWithName.tty.description";
  public static final String INITIAL_DOCUMENT_REVOLVING_CREDIT_B1_USERREFERENCE              = "gpieceRevolvingCredit.parameterWithName.userReference.description";
  public static final String INITIAL_DOCUMENT_REVOLVING_CREDIT_B1_USERLOGIN                  = "gpieceRevolvingCredit.parameterWithName.userLogin.description";
  public static final String INITIAL_DOCUMENT_REVOLVING_CREDIT_B1_FILENAME                   = "gpieceRevolvingCredit.parameterWithName.fileName.description";
  public static final String INITIAL_DOCUMENT_REVOLVING_CREDIT_B1_FINANCIALAMORTISATIONID    = "gpieceRevolvingCredit.parameterWithName.financialAmortisationId.description";
  public static final String INITIAL_DOCUMENT_REVOLVING_CREDIT_B1_INITIALPIECE               = "gpieceRevolvingCredit.parameterWithName.initialPiece.description";
  public static final String INITIAL_DOCUMENT_REVOLVING_CREDIT_B1_CASEREFERENCE              = "gpieceRevolvingCredit.parameterWithName.caseReference.description";
  public static final String INITIAL_DOCUMENT_REVOLVING_CREDIT_B1_EXTERNALCASEREFERENCE      = "gpieceRevolvingCredit.parameterWithName.externalCaseReference.description";
  public static final String INITIAL_DOCUMENT_REVOLVING_CREDIT_B1_NAMEDB                     = "gpieceRevolvingCredit.parameterWithName.nameDb.description";
  public static final String INITIAL_DOCUMENT_REVOLVING_CREDIT_B1_NAMECL                     = "gpieceRevolvingCredit.parameterWithName.nameCl.description";
  public static final String INITIAL_DOCUMENT_REVOLVING_CREDIT_B1_CREDITORADR1               = "gpieceRevolvingCredit.parameterWithName.creditorAdr1.description";
  public static final String INITIAL_DOCUMENT_REVOLVING_CREDIT_CREDITORADR2                  = "gpieceRevolvingCredit.parameterWithName.creditorAdr2.description";
  public static final String INITIAL_DOCUMENT_REVOLVING_CREDIT_CREDITORADR3                  = "gpieceRevolvingCredit.parameterWithName.creditorAdr3.description";
  public static final String INITIAL_DOCUMENT_REVOLVING_CREDIT_CREDITORADR4                  = "gpieceRevolvingCredit.parameterWithName.creditorAdr4.description";
  public static final String INITIAL_DOCUMENT_REVOLVING_CREDIT_DISPLAYCREDITORCOUNTRY        = "gpieceRevolvingCredit.parameterWithName.displayCreditorCountry.description";
  public static final String INITIAL_DOCUMENT_REVOLVING_CREDIT_CREDITORPOSTCODE              = "gpieceRevolvingCredit.parameterWithName.creditorPostCode.description";
  public static final String INITIAL_DOCUMENT_REVOLVING_CREDIT_CREDITORCITY                  = "gpieceRevolvingCredit.parameterWithName.creditorCity.description";
  public static final String INITIAL_DOCUMENT_REVOLVING_CREDIT_CREDITORCOUNTRY               = "gpieceRevolvingCredit.parameterWithName.creditorCountry.description";
  public static final String INITIAL_DOCUMENT_REVOLVING_CREDIT_LOANREFERENCE                 = "gpieceRevolvingCredit.parameterWithName.loanReference.description";
  public static final String INITIAL_DOCUMENT_REVOLVING_CREDIT_INSTALLMENTDUEDATE            = "gpieceRevolvingCredit.parameterWithName.installmentDueDate.description";
  public static final String INITIAL_DOCUMENT_REVOLVING_CREDIT_B1_CASECATEGORY               = "gpieceRevolvingCredit.parameterWithName.caseCategory.description";
  public static final String INITIAL_DOCUMENT_REVOLVING_CREDIT_B1_DISPLAYCASECATEGORY        = "gpieceRevolvingCredit.parameterWithName.displayCaseCategory.description";
  public static final String INITIAL_DOCUMENT_REVOLVING_CREDIT_B1_CODEINITIALPIECE           = "gpieceRevolvingCredit.parameterWithName.codeInitialPiece.description";
  public static final String INITIAL_DOCUMENT_REVOLVING_CREDIT_B1_DISPLAYINITIALPIECE        = "gpieceRevolvingCredit.parameterWithName.displayInitialPiece.description";
  public static final String INITIAL_DOCUMENT_REVOLVING_CREDIT_B1_GLOBALNEXTPMTDATE          = "gpieceRevolvingCredit.parameterWithName.globalNextPmtDate.description";
  public static final String INITIAL_DOCUMENT_REVOLVING_CREDIT_B1_ADR1                       = "gpieceRevolvingCredit.parameterWithName.adr1.description";
  public static final String INITIAL_DOCUMENT_REVOLVING_CREDIT_B1_ADR2                       = "gpieceRevolvingCredit.parameterWithName.adr2.description";
  public static final String INITIAL_DOCUMENT_REVOLVING_CREDIT_B1_ADR3                       = "gpieceRevolvingCredit.parameterWithName.adr3.description";
  public static final String INITIAL_DOCUMENT_REVOLVING_CREDIT_B1_ADR4                       = "gpieceRevolvingCredit.parameterWithName.adr4.description";
  public static final String INITIAL_DOCUMENT_REVOLVING_CREDIT_B1_COUNTRY                    = "gpieceRevolvingCredit.parameterWithName.country.description";
  public static final String INITIAL_DOCUMENT_REVOLVING_CREDIT_B1_POSTECODE                  = "gpieceRevolvingCredit.parameterWithName.posteCode.description";
  public static final String INITIAL_DOCUMENT_REVOLVING_CREDIT_B1_CITY                       = "gpieceRevolvingCredit.parameterWithName.city.description";
  public static final String INITIAL_DOCUMENT_B1_REVOLVING_CREDIT_B1_FORFEITDUEDATE          = "gpieceRevolvingCredit.parameterWithName.forfeitDueDate.description";
  public static final String INITIAL_DOCUMENT_REVOLVING_CREDIT_B1_CONTRACTCURRENCY           = "gpieceRevolvingCredit.parameterWithName.contractCurrency.description";

  //Case Balance
  public static final String CASE_BALANCE_TITLE                     = "caseBalance.parameterWithName.title.description";
  public static final String CASE_BALANCE_DOC_REF                   = "caseBalance.parameterWithName.doc.ref.description";
  public static final String CASE_BALANCE_BALANCE_TYPE              = "caseBalance.parameterWithName.balanceType.description";
  public static final String CASE_BALANCE_AMOUNT                    = "caseBalance.parameterWithName.amount.description";
  public static final String CASE_BALANCE_PUG_PAID_BY_FACTOR        = "caseBalance.parameterWithName.pugPaidByFactor.description";
  public static final String CASE_BALANCE_CLIENT_REAL_RISK          = "caseBalance.parameterWithName.clientRealRisk.description";
  public static final String CASE_BALANCE_COURT_COSTS_FACTOR        = "caseBalance.parameterWithName.courtCostsFactor.description";
  public static final String CASE_BALANCE_COURT_COSTS_CLIENT        = "caseBalance.parameterWithName.courtCostsClient.description";
  public static final String CASE_BALANCE_LAWYER_COSTS_FACTOR       = "caseBalance.parameterWithName.lawyerCostsFactor.description";
  public static final String CASE_BALANCE_LAWYER_COSTS_CLIENT       = "caseBalance.parameterWithName.lawyerCostsClient.description";
  public static final String CASE_BALANCE_PROFIT_FACTOR             = "caseBalance.parameterWithName.profitFactor.description";
  public static final String CASE_BALANCE_PROFIT_CLIENT             = "caseBalance.parameterWithName.profitClient.description";
  public static final String CASE_BALANCE_DIFFERENCE_PUG_PAID       = "caseBalance.parameterWithName.differencePugPaid.description";
  public static final String CASE_BALANCE_BALANCE_OF_DB_ACCOUNT     = "caseBalance.parameterWithName.balanceOfDbAccount.description";
  public static final String CASE_BALANCE_CANCELLATION_TITLE        = "caseBalance.parameterWithName.cancellationTitle.description";


  //CaseSmsResourceTest properties
  public static final String CASES_SMS_TXT        = "caseSmsResource.sms.txt";
  public static final String CASES_SMS_DATE       = "caseSmsResource.sms.date";
  public static final String CASES_SMS_SENDER     = "caseSmsResource.sms.sender";
  public static final String CASES_SMS_IMX_UN_ID  = "caseSmsResource.sms.imx.un.id";

  public static final String CASES_PARTIES_NAME             = "caseSmsResource.parties.name";
  public static final String CASES_PARTIES_FIRST_NAME       = "caseSmsResource.parties.first.name";
  public static final String CASES_PARTIES_REF_INDIVIDUAL   = "caseSmsResource.parties.ref.individual";
  public static final String CASES_PARTIES_REF_TYPE         = "caseSmsResource.parties.ref.type";
  public static final String CASES_PARTIES_DISPLAY_REF_TYPE = "caseSmsResource.parties.display.ref.type";
  public static final String CASES_PARTIES_IMX_UN_ID        = "caseSmsResource.parties.imx.un.id";

  public static final String CASES_PARTIES_PHONE_TYPE         = "caseSmsResource.parties.phone.type";
  public static final String CASES_PARTIES_DISPLAY_PHONE_TYPE = "caseSmsResource.parties.display.phone.type";
  public static final String CASES_PARTIES_PHONE_NUMBER       = "caseSmsResource.parties.phone.number";
  public static final String CASES_PARTIES_PHONE_TITLE        = "caseSmsResource.parties.phone.title";
  public static final String CASES_PARTIES_CONTACT_NAME       = "caseSmsResource.parties.contact.name";
  public static final String CASES_PARTIES_SMS_PRIORITY       = "caseSmsResource.parties.sms.priority";
  public static final String CASES_PARTIES_PHONE_IMX_UN_ID    = "caseSmsResource.parties.phone.imx.un.id";
  //DebtorAccountResourceTest
  public static final String CLDB_ACCOUNT_REACTIVATE_STATUS                = "cldb.account.reactivate.status";

  public static final String CLDB_ACCOUNT_HEADER_FIELDS_REF                = "cldb.account.header.fields.ref";
  public static final String CLDB_ACCOUNT_HEADER_FIELDS_CLIENT             = "cldb.account.header.fields.client";
  public static final String CLDB_ACCOUNT_HEADER_FIELDS_CLIENT_COUNTRY     = "cldb.account.header.fields.client.country";
  public static final String CLDB_ACCOUNT_HEADER_FIELDS_CLIENT_REF         = "cldb.account.header.fields.client.ref";
  public static final String CLDB_ACCOUNT_HEADER_FIELDS_DEBTOR             = "cldb.account.header.fields.debtor";
  public static final String CLDB_ACCOUNT_HEADER_FIELDS_DEBTOR_COUNTRY     = "cldb.account.header.fields.debtor.country";
  public static final String CLDB_ACCOUNT_HEADER_FIELDS_DEBTOR_REF         = "cldb.account.header.fields.debtor.ref";
  public static final String CLDB_ACCOUNT_HEADER_FIELDS_COFACTOR           = "cldb.account.header.fields.cofactor";
  public static final String CLDB_ACCOUNT_HEADER_FIELDS_COFACTOR_COUNTRY   = "cldb.account.header.fields.cofactor.country";
  public static final String CLDB_ACCOUNT_HEADER_FIELDS_COFACTOR_DB_NUMBER = "cldb.account.header.fields.cofactor.db.number";
  public static final String CLDB_ACCOUNT_HEADER_FIELDS_COFACTOR_REF       = "cldb.account.header.fields.cofactor.ref";
  public static final String CLDB_ACCOUNT_HEADER_FIELDS_STATEMENT          = "cldb.account.header.fields.statement";
  public static final String CLDB_ACCOUNT_HEADER_FIELDS_STATEMENT_CURRENCY = "cldb.account.header.fields.statement.currency";
  public static final String CLDB_ACCOUNT_HEADER_FIELDS_NEW_ACC            = "cldb.account.header.fields.new.acc";
  //
  public static final String CLDB_ACCOUNT_BODY_FIELDS_CLOSURE_DATE                     = "cldb.account.body.fields.closure.date";
  public static final String CLDB_ACCOUNT_BODY_FIELDS_CLOSURE_REASON                   = "cldb.account.body.fields.closure.reason";
  public static final String CLDB_ACCOUNT_BODY_FIELDS_DISP_CLOSURE_REASON              = "cldb.account.body.fields.disp.closure.reason";
  public static final String CLDB_ACCOUNT_BODY_FIELDS_STATUS                           = "cldb.account.body.fields.client.status";
  public static final String CLDB_ACCOUNT_BODY_FIELDS_DISP_STATUS                      = "cldb.account.body.fields.client.disp.status";
  public static final String CLDB_ACCOUNT_BODY_FIELDS_LEGAL_PROC                       = "cldb.account.body.fields.debtor.legal.proc";
  public static final String CLDB_ACCOUNT_BODY_FIELDS_DAMAGE_NUM                       = "cldb.account.body.fields.damage.num";
  public static final String CLDB_ACCOUNT_BODY_FIELDS_DISPUTED_ACCOUNT                 = "cldb.account.body.fields.disputed.account";
  public static final String CLDB_ACCOUNT_BODY_FIELDS_COMPENSATION                     = "cldb.account.body.fields.compensation";
  public static final String CLDB_ACCOUNT_BODY_FIELDS_LCR_NOT_ACCEPTED                 = "cldb.account.body.fields.lcr.not.accepted";
  public static final String CLDB_ACCOUNT_BODY_FIELDS_FINLIMZERO                       = "cldb.account.body.fields.finlimzero";
  public static final String CLDB_ACCOUNT_BODY_FIELDS_FINONDB_REFUSE_FTR               = "cldb.account.body.fields.finondb.refuse.ftr";
  public static final String CLDB_ACCOUNT_BODY_FIELDS_EXPECTED_TURNOVER                = "cldb.account.body.fields.expected.turnover";
  public static final String CLDB_ACCOUNT_BODY_FIELDS_EXPECTED_NB_INVOICES             = "cldb.account.body.fields.expected.nb.invoices";
  public static final String CLDB_ACCOUNT_BODY_FIELDS_CCONDBREFUSINGFTR                = "cldb.account.body.fields.ccondbrefusingftr";
  public static final String CLDB_ACCOUNT_BODY_FIELDS_AMTMISSINGINV                    = "cldb.account.body.fields.amtmissinginv";
  public static final String CLDB_ACCOUNT_BODY_FIELDS_TRHNONREGNOTIFLETTERDB           = "cldb.account.body.fields.trhnonregnotifletterdb";
  public static final String CLDB_ACCOUNT_BODY_FIELDS_TRHREGNOTIFLETTERDB              = "cldb.account.body.fields.trhregnotifletterdb";
  public static final String CLDB_ACCOUNT_BODY_FIELDS_SET_OFF_RISK                     = "cldb.account.body.fields.set.off.risk";
  public static final String CLDB_ACCOUNT_BODY_FIELDS_FORCED_DB_COUNTRY                = "cldb.account.body.fields.forced.db.country";
  public static final String CLDB_ACCOUNT_BODY_FIELDS_PMT_BY_DIRECT_DEBIT              = "cldb.account.body.fields.pmt.by.direct.debit";
  public static final String CLDB_ACCOUNT_BODY_FIELDS_PERCENTAGE                       = "cldb.account.body.fields.percentage";
  public static final String CLDB_ACCOUNT_BODY_FIELDS_NB_DAYS                          = "cldb.account.body.fields.nb.days";
  public static final String CLDB_ACCOUNT_BODY_FIELDS_MINPMTINSTLMTPERC                = "cldb.account.body.fields.minpmtinstlmtperc";
  public static final String CLDB_ACCOUNT_BODY_FIELDS_GRACE_PERIOD                     = "cldb.account.body.fields.grace.period";
  public static final String CLDB_ACCOUNT_BODY_FIELDS_NOREVFORUNDERUSE                 = "cldb.account.body.fields.norevforunderuse";
  public static final String CLDB_ACCOUNT_BODY_FIELDS_BLOCKREVFOROVERDUE               = "cldb.account.body.fields.blockrevforoverdue";
  public static final String CLDB_ACCOUNT_BODY_FIELDS_PMT_COND                         = "cldb.account.body.fields.pmt.cond";
  public static final String CLDB_ACCOUNT_BODY_FIELDS_PMT_DISP_COND                    = "cldb.account.body.fields.disp.pmt.cond";
  public static final String CLDB_ACCOUNT_BODY_FIELDS_DELIVERY_TERMS                   = "cldb.account.body.fields.delivery.terms";
  public static final String CLDB_ACCOUNT_BODY_FIELDS_THNBDAYSAGECACALC                = "cldb.account.body.fields.thnbdaysagecacalc";
  public static final String CLDB_ACCOUNT_BODY_FIELDS_THPERCCADISAPPR                  = "cldb.account.body.fields.thperccadisappr";
  public static final String CLDB_ACCOUNT_BODY_FIELDS_NO_RET_CA                        = "cldb.account.body.fields.no.ret.ca";
  public static final String CLDB_ACCOUNT_BODY_FIELDS_CALCULATED_DUE_DATE_SCHEME       = "cldb.account.body.fields.calculated.due.date.scheme";
  public static final String CLDB_ACCOUNT_BODY_FIELDS_DISP_CALCULATED_DUE_DATE_SCHEME  = "cldb.account.body.fields.disp.calculated.due.date.scheme";
  public static final String CLDB_ACCOUNT_BODY_FIELDS_NON_WORKING_DAY                  = "cldb.account.body.fields.non.working.day";
  public static final String CLDB_ACCOUNT_BODY_FIELDS_DISP_NON_WORKING_DAY             = "cldb.account.body.fields.disp.non.working.day";
  public static final String CLDB_ACCOUNT_BODY_FIELDS_INV_DATE                         = "cldb.account.body.fields.inv.date";
  public static final String CLDB_ACCOUNT_BODY_FIELDS_TOLERANCE_AMT                    = "cldb.account.body.fields.tolerance.amt";
  public static final String CLDB_ACCOUNT_BODY_FIELDS_TAKEOVER_CEDP                    = "cldb.account.body.fields.takeover.cedp";
  public static final String CLDB_ACCOUNT_BODY_FIELDS_OPENANDFUPFEESAUTOCHARGE         = "cldb.account.body.fields.openandfupfeesautocharge";
  public static final String CLDB_ACCOUNT_BODY_FIELDS_SUPPRESS_FIN                     = "cldb.account.body.fields.suppress.fin";
  public static final String CLDB_ACCOUNT_BODY_FIELDS_DISPDUR_BEFORE_RETROCESSION      = "cldb.account.body.fields.dispdur.before.retrocession";
  public static final String CLDB_ACCOUNT_BODY_FIELDS_DELAY_CTORTINV                   = "cldb.account.body.fields.delay.ctortinv";
  public static final String CLDB_ACCOUNT_BODY_FIELDS_MAX_PMT_TERM                     = "cldb.account.body.fields.max.pmt.term";
  public static final String CLDB_ACCOUNT_BODY_FIELDS_MIN_PMT_TERM                     = "cldb.account.body.fields.min.pmt.term";
  public static final String CLDB_ACCOUNT_BODY_FIELDS_DISPUTES_TAKEN_FOR_CONCENTR      = "cldb.account.body.fields.disputes.taken.for.concentr";
  public static final String CLDB_ACCOUNT_BODY_FIELDS_ORDERS_APPROVAL                  = "cldb.account.body.fields.orders.approval";
  public static final String CLDB_ACCOUNT_BODY_FIELDS_RETROACTIVITY_DUR                = "cldb.account.body.fields.retroactivity.dur";
  public static final String CLDB_ACCOUNT_BODY_FIELDS_NORM_PMT_DELAY                   = "cldb.account.body.fields.norm.pmt.delay";
  public static final String CLDB_ACCOUNT_BODY_FIELDS_OVERDUE_DISAPPR_FOR_NEW_INV      = "cldb.account.body.fields.overdue.disappr.for.new.inv";
  public static final String CLDB_ACCOUNT_BODY_FIELDS_IMPORTANT_INV_TRHLD              = "cldb.account.body.fields.important.inv.trhld";
  public static final String CLDB_ACCOUNT_BODY_FIELDS_PAPER_INV_MANDATORY              = "cldb.account.body.fields.paper.inv.mandatory";
  public static final String CLDB_ACCOUNT_BODY_FIELDS_PAPER_REMITTANCE_DELAY           = "cldb.account.body.fields.paper.remittance.delay";
  public static final String CLDB_ACCOUNT_BODY_FIELDS_MAX_DUE_DATE                     = "cldb.account.body.fields.max.due.date";
  public static final String CLDB_ACCOUNT_BODY_FIELDS_SILENT                           = "cldb.account.body.fields.silent";
  public static final String CLDB_ACCOUNT_BODY_FIELDS_PERIOD_TYPE                      = "cldb.account.body.fields.period.type";
  public static final String CLDB_ACCOUNT_BODY_FIELDS_DISP_PERIOD_TYPE                 = "cldb.account.body.fields.disp.period.type";
  public static final String CLDB_ACCOUNT_BODY_FIELDS_NB_PERIODS                       = "cldb.account.body.fields.nb.periods";
  public static final String CLDB_ACCOUNT_BODY_FIELDS_LATTER_PERCENTAGE                = "cldb.account.body.fields.latter.percentage";
  public static final String CLDB_ACCOUNT_BODY_FIELDS_RANDOM_SOUNDING                  = "cldb.account.body.fields.random.sounding";
  public static final String CLDB_ACCOUNT_BODY_FIELDS_PAPER_INV_MIN_TRHLD              = "cldb.account.body.fields.paper.inv.min.trhld";
  public static final String CLDB_ACCOUNT_BODY_FIELDS_EXCL_FROM_DIR_EXP_REP            = "cldb.account.body.fields.excl.from.dir.exp.rep";

  public static final String FE_CHECK_EXCL_FROM_DIR_EXP_REP                            = "fe.check.excl.from.dir.exp.rep";
  public static final String FE_CHECK_IS_TRANSACTIONS_AVAILABLE                        = "fe.check.is.transactions.available";

  //ClClNumbersResourceTest
  public static final String CLCL_NUMBER                    = "clcl.number";
  public static final String CLCL_NUMBERS_UNIQ_ID           = "clcl.numbers.uniq.id";
  public static final String CLCL_NUMBERS_CONTRACT_NO       = "clcl.numbers.contract.no";
  public static final String CLCL_NUMBERS_CONTRACT_NO_ARRAY = "clcl.numbers.contract.no.array";

  public static final String DB_REFUSES_FACTORING_UNIQ_ID      = "db.refuses.factoring.uniq.id";
  public static final String DB_REFUSES_FACTORING_START_DATE   = "db.refuses.factoring.start.date";
  public static final String DB_REFUSES_FACTORING_END_DATE     = "db.refuses.factoring.end.date";

  public static final String DB_DOMICILIATION_REF                        = "db.domiciliation.ref";
  public static final String DB_DOMICILIATION_TYPE                       = "db.domiciliation.type";
  public static final String DB_DOMICILIATION_DISP_TYPE                  = "db.domiciliation.disp.type";
  public static final String DB_DOMICILIATION_NAME                       = "db.domiciliation.name";
  public static final String DB_DOMICILIATION_START_DATE                 = "db.domiciliation.start.date";
  public static final String DB_DOMICILIATION_END_DATE                   = "db.domiciliation.end.date";
  public static final String DB_DOMICILIATION_AUTO_NUM_OF_BANK_TRANSFER  = "db.domiciliation.auto.num.of.bank.transfer";
  public static final String DB_DOMICILIATION_BANK_NAME                  = "db.domiciliation.bank.name";
  public static final String DB_DOMICILIATION_ACCOUNT_NUM                = "db.domiciliation.account.num";


  public static final String CLDB_SEIZURE_REF        = "cldb.seizure.ref";
  public static final String CLDB_SEIZURE_NAME       = "cldb.seizure.name";
  public static final String CLDB_SEIZURE_AMOUNT     = "cldb.seizure.amount";
  public static final String CLDB_SEIZURE_START_DATE = "cldb.seizure.start.date";
  public static final String CLDB_SEIZURE_END_DATE   = "cldb.seizure.end.date";
  public static final String CLDB_SEIZURE_CURR       = "cldb.seizure.curr";
  public static final String CLDB_SEIZURE_DISP_CURR  = "cldb.seizure.disp.curr";

  public static final String PAYMENT_SCHEME_WEEK_PMT_REF       = "payment.scheme.week.pmt.ref";
  public static final String PAYMENT_SCHEME_WEEK_PMT_DAY       = "payment.scheme.week.pmt.day";
  public static final String PAYMENT_SCHEME_WEEK_DISP_PMT_DAY  = "payment.scheme.week.disp.pmt.day";

  public static final String PAYMENT_SCHEME_MONTH_PMT_REF    = "payment.scheme.month.pmt.ref";
  public static final String PAYMENT_SCHEME_MONTH_PMT_DAY    = "payment.scheme.month.pmt.day";
  public static final String PAYMENT_SCHEME_MONTH_START_DAY  = "payment.scheme.month.start.day";
  public static final String PAYMENT_SCHEME_MONTH_END_DAY    = "payment.scheme.month.end.day";

  public static final String PAYMENT_SCHEME_SPECIAL_PMT_REF               = "payment.scheme.special.pmt.ref";
  public static final String PAYMENT_SCHEME_SPECIAL_START_DAY             = "payment.scheme.special.start.day";
  public static final String PAYMENT_SCHEME_SPECIAL_END_DAY               = "payment.scheme.special.end.day";
  public static final String PAYMENT_SCHEME_SPECIAL_DAY_OF_WEEK           = "payment.scheme.special.day.of.week";
  public static final String PAYMENT_SCHEME_SPECIAL_DISP_DAY_OF_WEEK      = "payment.scheme.special.disp.day.of.week";
  public static final String PAYMENT_SCHEME_SPECIAL_NB_WEEK_OF_MONTH      = "payment.scheme.special.nb.week.of.month";
  public static final String PAYMENT_SCHEME_SPECIAL_DISP_NB_WEEK_OF_MONTH = "payment.scheme.special.disp.nb.week.of.month";

  public static final String PAYMENT_SCHEME_DISC_PMT_REF     = "payment.scheme.disc.pmt.ref";
  public static final String PAYMENT_SCHEME_DISC_FROM        = "payment.scheme.disc.from";
  public static final String PAYMENT_SCHEME_DISC_TO          = "payment.scheme.disc.to";
  public static final String PAYMENT_SCHEME_DISC_PERIOD_DAYS = "payment.scheme.disc.period.days";

  public static final String CLDB_BANK_LIST_BANK_REF    = "cldb.bank.list.bank.ref";
  public static final String CLDB_BANK_LIST_BANK_CODE   = "cldb.bank.list.bank.code";
  public static final String CLDB_BANK_LIST_BRANCH_CODE = "cldb.bank.list.branch.code";
  public static final String CLDB_BANK_LIST_BIC         = "cldb.bank.list.bic";
  public static final String CLDB_BANK_LIST_RESID_CODE  = "cldb.bank.list.resid.code";
  public static final String CLDB_BANK_LIST_NAME        = "cldb.bank.list.name";
  public static final String CLDB_BANK_LIST_ACC_NUM     = "cldb.bank.list.acc.num";
  public static final String CLDB_BANK_LIST_ACTIVE      = "cldb.bank.list.active";
  public static final String CLDB_BANK_LIST_AUT_TRAN    = "cldb.bank.list.aut.tran";
  public static final String CLDB_BANK_LIST_INDIV_NAME  = "cldb.bank.list.indiv.name";

  public static final String DB_FACTORING_FEE_REF               = "db.factoring.fee.ref";
  public static final String DB_FACTORING_FEE_DOC_TYPE          = "db.factoring.fee.doc.type";
  public static final String DB_FACTORING_FEE_DISP_DOC_TYPE     = "db.factoring.fee.disp.doc.type";
  public static final String DB_FACTORING_FEE_PERC_ADMIN        = "db.factoring.fee.perc.admin";
  public static final String DB_FACTORING_FEE_PERC_CREDIT       = "db.factoring.fee.perc.credit";
  public static final String DB_FACTORING_FEE_FLAT_FEE          = "db.factoring.fee.flat.fee";
  public static final String DB_FACTORING_FEE_MIN_AMT           = "db.factoring.fee.min.amt";
  public static final String DB_FACTORING_FEE_GLOBAL_FLAT_FEE   = "db.factoring.fee.global.flat.fee";
  public static final String DB_FACTORING_FEE_INV_PERIOD        = "db.factoring.fee.inv.period";
  public static final String DB_FACTORING_FEE_DESC_INV_PERIOD   = "db.factoring.fee.desc.inv.period";
  public static final String DB_FACTORING_FEE_START_DATE        = "db.factoring.fee.start.date";
  public static final String DB_FACTORING_FEE_FROM              = "db.factoring.fee.from";
  public static final String DB_FACTORING_FEE_TO                = "db.factoring.fee.to";
  public static final String DB_FACTORING_FEE_FROM_ENTRY_DATE   = "db.factoring.fee.from.entry.date";

  public static final String DB_PENALTY_INT_REF              = "db.penalty.int.ref";
  public static final String DB_PENALTY_INT_BASE_RATE        = "db.penalty.int.base.rate";
  public static final String DB_PENALTY_INT_DISP_BASE_RATE   = "db.penalty.int.disp.base.rate";
  public static final String DB_PENALTY_INT_MARGIN           = "db.penalty.int.margin";
  public static final String DB_PENALTY_INT_START_DATE       = "db.penalty.int.start.date";
  public static final String DB_PENALTY_INT_CL               = "db.penalty.int.cl";
  public static final String DB_PENALTY_INT_GRACE_DAYS       = "db.penalty.int.grace.days";
  public static final String DB_PENALTY_INT_MIN_AMT          = "db.penalty.int.min.amt";

  public static final String DB_CRED_PROT_FEE_REF           = "db.cred.prot.fee.ref";
  public static final String DB_CRED_PROT_FEE_DOC_TYPE      = "db.cred.prot.fee.doc.type";
  public static final String DB_CRED_PROT_DISP_FEE_DOC_TYPE = "db.cred.prot.disp.fee.doc.type";
  public static final String DB_CRED_PROT_FEE_ACTIVITY      = "db.cred.prot.fee.activity";
  public static final String DB_CRED_PROT_DISP_FEE_ACTIVITY = "db.cred.prot.disp.fee.activity";
  public static final String DB_CRED_PROT_FEE_OPTION        = "db.cred.prot.fee.start.option";
  public static final String DB_CRED_PROT_DISP_FEE_OPTION   = "db.cred.prot.disp.fee.start.option";
  public static final String DB_CRED_PROT_FEE_PERC          = "db.cred.prot.fee.perc";
  public static final String DB_CRED_PROT_FEE_MIN_INV_AMT   = "db.cred.prot.fee.min.inv.amt";
  public static final String DB_CRED_PROT_FEE_START_DATE    = "db.cred.prot.fee.start.date";

  public static final String DB_DEDUCTIONS_REF          = "db.deductions.ref";
  public static final String DB_DEDUCTIONS_START_PERIOD = "db.deductions.start.period";
  public static final String DB_DEDUCTIONS_END_PERIOD   = "db.deductions.end.period";
  public static final String DB_DEDUCTIONS_MARGIN       = "db.deductions.margin";
  public static final String DB_DEDUCTIONS_PERC         = "db.deductions.perc";

  public static final String DB_FACTORING_MODE_REF               = "db.factoring.mode.ref";
  public static final String DB_FACTORING_MODE_COUNTRY           = "db.factoring.mode.country";
  public static final String DB_FACTORING_MODE_DISPLAY_COUNTRY   = "db.factoring.mode.display.country";
  public static final String DB_FACTORING_MODE                   = "db.factoring.mode";
  public static final String DB_FACTORING_DISPLAY_MODE           = "db.factoring.mode.display.more";
  public static final String DB_FACTORING_MODE_END_DATE          = "db.factoring.mode.end.date";

  public static final String DB_FINANCING_RATE_REF               = "db.financing.rate.ref";
  public static final String DB_FINANCING_RATE_CRIT1             = "db.financing.rate.crit1";
  public static final String DB_FINANCING_RATE_DISP_CRIT1        = "db.financing.rate.disp.crit1";
  public static final String DB_FINANCING_RATE_CRIT2             = "db.financing.rate.crit2";
  public static final String DB_FINANCING_RATE_DISP_CRIT2        = "db.financing.rate.disp.crit2";
  public static final String DB_FINANCING_RATE_FIN_RATE          = "db.financing.rate.fin.rate";
  public static final String DB_FINANCING_RATE_START_DATE        = "db.financing.rate.start.date";
  public static final String DB_FINANCING_RATE_END_DATE          = "db.financing.rate.end.date";

  public static final String DB_FINANCING_RATE_DEFAULT_CRIT      = "db.financing.rate.default.crit";

  public static final String DB_FINANCING_RATE_CRIT2_LIST_ABBREV       = "db.financing.rate.crit2.list.abbrev";
  public static final String DB_FINANCING_RATE_CRIT2_LIST_DISP_ABBREV  = "db.financing.rate.crit2.list.disp.abbrev";
  public static final String DB_FINANCING_RATE_CRIT2_LIST_VALUE        = "db.financing.rate.crit2.list.value";
  public static final String DB_FINANCING_RATE_CRIT2_LIST_DISP_VALUE   = "db.financing.rate.crit2.list.disp.value";
  public static final String DB_FINANCING_RATE_CRIT2_LIST_SUBORDINATE  = "db.financing.rate.crit2.list.subordinate";

  public static final String DB_LIMIT_MAX_CONC    = "db.limit.max.conc";
  public static final String DB_LIMIT_START_DATE  = "db.limit.start.date";
  public static final String DB_LIMIT_END_DATE    = "db.limit.end.date";

  public static final String DB_FIN_COMM_REF                = "db.fin.comm.ref";
  public static final String DB_FIN_COMM_METHOD             = "db.fin.comm.method";
  public static final String DB_FIN_COMM_DISP_METHOD        = "db.fin.comm.disp.method";
  public static final String DB_FIN_COMM_CURR               = "db.fin.comm.curr";
  public static final String DB_FIN_COMM_DISP_CURR          = "db.fin.comm.disp.curr";
  public static final String DB_FIN_COMM_BASE_RATE          = "db.fin.comm.base.rate";
  public static final String DB_FIN_COMM_DISP_BASE_RATE     = "db.fin.comm.disp.base.rate";
  public static final String DB_FIN_COMM_INT_RATE           = "db.fin.comm.int.rate";
  public static final String DB_FIN_COMM_DISP_INT_RATE      = "db.fin.comm.disp.int.rate";
  public static final String DB_FIN_COMM_FROM               = "db.fin.comm.from";
  public static final String DB_FIN_COMM_TO                 = "db.fin.comm.to";
  public static final String DB_FIN_COMM_MARGIN             = "db.fin.comm.margin";
  public static final String DB_FIN_COMM_MIN_RATE           = "db.fin.comm.min.rate";
  public static final String DB_FIN_COMM_PRE_CALC           = "db.fin.comm.pre.calc";
  public static final String DB_FIN_COMM_POST_CALC          = "db.fin.comm.post.calc";
  public static final String DB_FIN_COMM_TYPE_PRE_CALC      = "db.fin.comm.type.pre.calc";
  public static final String DB_FIN_COMM_DISP_TYPE_PRE_CALC = "db.fin.comm.disp.type.pre.calc";
  public static final String DB_FIN_COMM_START_DATE         = "db.fin.comm.from.start.date";
  public static final String DB_FIN_COMM_BUY_MARGIN         = "db.fin.comm.from.buy.margin";


  //DB request limit history
  public static final String DB_REQUEST_LIMIT_HISTORY_CLIENT_NAME             = "dbRequestLimit.parameterWithName.clientName.description";
  public static final String DB_REQUEST_LIMIT_HISTORY_LIMIT_REQUEST_DATE      = "dbRequestLimit.parameterWithName.limitRequestDate.description";
  public static final String DB_REQUEST_LIMIT_HISTORY_LIMIT_REQUEST_AMOUNT    = "dbRequestLimit.parameterWithName.limitRequestAmount.description";
  public static final String DB_REQUEST_LIMIT_HISTORY_CURRENCY                = "dbRequestLimit.parameterWithName.currencyApprovedLimit.description";
  public static final String DB_REQUEST_LIMIT_HISTORY_STATUS                  = "dbRequestLimit.parameterWithName.limitRequestStatus.description";
  public static final String DB_REQUEST_LIMIT_HISTORY_DECISION                = "dbRequestLimit.parameterWithName.limiRequestDecision.description";
  public static final String DB_REQUEST_LIMIT_HISTORY_APPROVAL_AMOUNT         = "dbRequestLimit.parameterWithName.approvalAmount.description";
  public static final String DB_REQUEST_LIMIT_HISTORY_DECISION_START_DATE     = "dbRequestLimit.parameterWithName.decisionStartDate.description";
  public static final String DB_REQUEST_LIMIT_HISTORY_LIMIT_REQUEST_CURRENCY  = "dbRequestLimit.parameterWithName.limitRequestCurrency.description";
  public static final String DB_REQUEST_LIMIT_HISTORY_DATE_UNTIL              = "dbRequestLimit.parameterWithName.dateUntil.description";

  public static final String E_SELINDIV_SEARCH_CRITERIA_PERSON                                = "selindiv.search.criteria.person";
  public static final String E_SELINDIV_SEARCH_CRITERIA_DISPLAY_PERSON                        = "selindiv.search.criteria.displayPerson";
  public static final String E_SELINDIV_SEARCH_CRITERIA_NAME                                  = "selindiv.search.criteria.name";
  public static final String E_SELINDIV_SEARCH_CRITERIA_PHONETIC_SEARCH                       = "selindiv.search.criteria.phoneticSearch";
  public static final String E_SELINDIV_SEARCH_CRITERIA_ALTERNATIVE_SEARCH                    = "selindiv.search.criteria.alternativeSearch";
  public static final String E_SELINDIV_SEARCH_CRITERIA_LANGUAGE                              = "selindiv.search.criteria.language";
  public static final String E_SELINDIV_SEARCH_CRITERIA_DISPLAY_LANGUAGE                      = "selindiv.search.criteria.displayLanguage";
  public static final String E_SELINDIV_SEARCH_CRITERIA_FIRST_NAME                            = "selindiv.search.criteria.firstName";
  public static final String E_SELINDIV_SEARCH_CRITERIA_PROFESSION                            = "selindiv.search.criteria.profession";
  public static final String E_SELINDIV_SEARCH_CRITERIA_DISPLAY_PROFESSION                    = "selindiv.search.criteria.displayProfession";
  public static final String E_SELINDIV_SEARCH_CRITERIA_PROFESSION_ABREV                      = "selindiv.search.criteria.professionAbrev";
  public static final String E_SELINDIV_SEARCH_CRITERIA_PROFESSION_DISPLAY_ABREV              = "selindiv.search.criteria.professionDisplayAbrev";
  public static final String E_SELINDIV_SEARCH_CRITERIA_SPECIALITY                            = "selindiv.search.criteria.speciality";
  public static final String E_SELINDIV_SEARCH_CRITERIA_DISPLAY_SPECIALITY                    = "selindiv.search.criteria.displaySpeciality";
  public static final String E_SELINDIV_SEARCH_CRITERIA_SPECIALITY_ABREV                      = "selindiv.search.criteria.specialityAbrev";
  public static final String E_SELINDIV_SEARCH_CRITERIA_SPECIALITY_DISPLAY_ABREV              = "selindiv.search.criteria.specialityDisplayabrev";
  public static final String E_SELINDIV_SEARCH_CRITERIA_EXT_REF                               = "selindiv.search.criteria.extRef";
  public static final String E_SELINDIV_SEARCH_CRITERIA_ACTIF                                 = "selindiv.search.criteria.actif";
  public static final String E_SELINDIV_SEARCH_CRITERIA_LEGAL_ID                              = "selindiv.search.criteria.legalId";
  public static final String E_SELINDIV_SEARCH_CRITERIA_PHONE                                 = "selindiv.search.criteria.phone";
  public static final String E_SELINDIV_SEARCH_CRITERIA_EMAIL                                 = "selindiv.search.criteria.email";
  public static final String E_SELINDIV_SEARCH_CRITERIA_COUNTRY                               = "selindiv.search.criteria.country";
  public static final String E_SELINDIV_SEARCH_CRITERIA_DISPLAY_COUNTRY                       = "selindiv.search.criteria.displayCountry";
  public static final String E_SELINDIV_SEARCH_CRITERIA_COUNTRY_ABREV                         = "selindiv.search.criteria.countryAbrev";
  public static final String E_SELINDIV_SEARCH_CRITERIA_COUNTRY_DISPLAY_ABREV                 = "selindiv.search.criteria.countryDisplayAbrev";
  public static final String E_SELINDIV_SEARCH_CRITERIA_STATE                                 = "selindiv.search.criteria.state";
  public static final String E_SELINDIV_SEARCH_CRITERIA_DISPLAY_STATE                         = "selindiv.search.criteria.displayState";
  public static final String E_SELINDIV_SEARCH_CRITERIA_STATE_ABREV                           = "selindiv.search.criteria.stateAbrev";
  public static final String E_SELINDIV_SEARCH_CRITERIA_STATE_DISPLAY_ABREV                   = "selindiv.search.criteria.stateDisplayAbrev";
  public static final String E_SELINDIV_SEARCH_CRITERIA_CNIL_GROUPING_CODE                    = "selindiv.search.criteria.cnilGroupingCode";
  public static final String E_SELINDIV_SEARCH_CRITERIA_CNIL_GROUPING_NAME                    = "selindiv.search.criteria.cnilGroupingName";
  public static final String E_SELINDIV_SEARCH_CRITERIA_POST_CODE                             = "selindiv.search.criteria.postCode";
  public static final String E_SELINDIV_SEARCH_CRITERIA_CITY                                  = "selindiv.search.criteria.city";
  public static final String E_SELINDIV_SEARCH_CRITERIA_ADDRESS                               = "selindiv.search.criteria.address";
  public static final String E_SELINDIV_SEARCH_CRITERIA_BID                                   = "selindiv.search.criteria.bid";
  public static final String E_SELINDIV_SEARCH_CRITERIA_BIRTH_OR_FOUNDATION_DT                = "selindiv.search.criteria.birthOrFoundationDt";
  public static final String E_SELINDIV_SEARCH_CRITERIA_INDIV_REF                             = "selindiv.search.criteria.indivRef";
  public static final String E_SELINDIV_SEARCH_CRITERIA_FORM_MASK                             = "selindiv.search.criteria.formMask";
  public static final String E_SELINDIV_SEARCH_CRITERIA_DISPLAY_FORM_MASK                     = "selindiv.search.criteria.displayFormMask";
  public static final String E_SELINDIV_SEARCH_CRITERIA_MISSIONNABLE                          = "selindiv.search.criteria.missionnable";
  public static final String E_SELINDIV_SEARCH_CRITERIA_ACTIVE                                = "selindiv.search.criteria.active";
  public static final String E_SELINDIV_SEARCH_CRITERIA_ALLOCATION_SITE                       = "selindiv.search.criteria.allocationSite";
  public static final String E_SELINDIV_SEARCH_CRITERIA_THIRD_TO_VALIDATE                     = "selindiv.search.criteria.thirdToValidate";
  public static final String E_SELINDIV_SEARCH_CRITERIA_AGSID_CODE                            = "selindiv.search.criteria.agsidCode";
  public static final String E_SELINDIV_SEARCH_CRITERIA_FILTER_REF_TYPE                       = "selindiv.search.criteria.filterRefType";
  public static final String E_SELINDIV_SEARCH_CRITERIA_FILTER_DEBITOR_NUMBER                 = "selindiv.search.criteria.filterDebitorNumber";
  public static final String E_SELINDIV_SEARCH_CRITERIA_FILTER_NO_IDENT                       = "selindiv.search.criteria.filterNoIdent";
  public static final String E_SELINDIV_SEARCH_CRITERIA_FITLER_INDIV_COUNTRY                  = "selindiv.search.criteria.fitlerIndivCountry";
  public static final String E_SELINDIV_SEARCH_CRITERIA_FITLER_INDIV_COUNTRY_ABREV            = "selindiv.search.criteria.filterIndivDisplayCountry";
  public static final String E_SELINDIV_SEARCH_CRITERIA_FILTER_INDIV_DISPLAY_COUNTRY          = "selindiv.search.criteria.fitlerIndivCountryAbrev";
  public static final String E_SELINDIV_SEARCH_CRITERIA_FILTER_INDIV_COUNTRY_DISPLAY_ABREV    = "selindiv.search.criteria.filterIndivCountryDisplayAbrev";
  public static final String E_SELINDIV_SEARCH_CRITERIA_FILTER_EXT_REF                        = "selindiv.search.criteria.filterExtRef";
  public static final String E_SELINDIV_SEARCH_CRITERIA_FILTER_INDIV_DIVISION                 = "selindiv.search.criteria.filterIndivDivision";
  public static final String E_SELINDIV_SEARCH_CRITERIA_FILTER_INDIV_DISPLAY_DIVISION         = "selindiv.search.criteria.filterIndivDisplayDivision";
  public static final String E_SELINDIV_SEARCH_CRITERIA_FILTER_CNIL_GROUPING_CODE             = "selindiv.search.criteria.filterCnilGroupingCode";
  public static final String E_SELINDIV_SEARCH_CRITERIA_FILTER_CNIL_GROUPING_NAME             = "selindiv.search.criteria.filterCnilGroupingName";

  public static final String E_SELINDIV_PERSON                      = "selindiv.person";
  public static final String E_SELINDIV_NAME                        = "selindiv.name";
  public static final String E_SELINDIV_FIRST_NAME                  = "selindiv.firstName";
  public static final String E_SELINDIV_PHONE                       = "selindiv.phone";
  public static final String E_SELINDIV_POST_CODE                   = "selindiv.postCode";
  public static final String E_SELINDIV_CITY                        = "selindiv.city";
  public static final String E_SELINDIV_INDIV_COUNTRY               = "selindiv.indivCountry";
  public static final String E_SELINDIV_ADDRESS                     = "selindiv.address";
  public static final String E_SELINDIV_EXT_REF                     = "selindiv.extRef";
  public static final String E_SELINDIV_NO_IDENT                    = "selindiv.noIdent";
  public static final String E_SELINDIV_SUSPENSION_DT               = "selindiv.suspensionDt";
  public static final String E_SELINDIV_REF_TYPE                    = "selindiv.refType";
  public static final String E_SELINDIV_INDIV_REF                   = "selindiv.indivRef";
  public static final String E_SELINDIV_BIRTH_OR_FOUNDATION_DATE    = "selindiv.birthOrFoundationDate";
  public static final String E_SELINDIV_SOCIETE                     = "selindiv.societe";
  public static final String E_SELINDIV_RFC                         = "selindiv.rfc";
  public static final String E_SELINDIV_INTERNATIONAL_NB            = "selindiv.internationalNb";
  public static final String E_SELINDIV_SIRET                       = "selindiv.siret";
  public static final String E_SELINDIV_CORP_NAME                   = "selindiv.corpName";
  public static final String E_SELINDIV_SIGN                        = "selindiv.sign";
  public static final String E_SELINDIV_PC_BIRTH                    = "selindiv.pcBirth";
  public static final String E_SELINDIV_DISPLAY_STATE               = "selindiv.displayState";
  public static final String E_SELINDIV_CONTENT                     = "selindiv.content";
  public static final String E_SELINDIV_SEARCH_TEMP_RESOURCE        = "selindiv.tempResId";

  //CaseInterestService properties
  public static final String CASE_INTEREST_DATA_ARRAY       = "caseInterestResource.data.array";
  public static final String CASE_INTEREST_LINE_NUM         = "caseInterestResource.line.num";
  public static final String CASE_INTEREST_DATE_SIMUL       = "caseInterestResource.date.simul";
  public static final String CASE_INTEREST_AMT_BASE         = "caseInterestResource.amt.base";
  public static final String CASE_INTEREST_DAYS_NB          = "caseInterestResource.days.number";
  public static final String CASE_INTEREST_RATE             = "caseInterestResource.rate";
  public static final String CASE_INTEREST_AMT_INTEREST     = "caseInterestResource.amt.interest";
  public static final String CASE_INTEREST_CUMUL_INTER      = "caseInterestResource.cumul.inter";
  public static final String CASE_INTEREST_INV_REF          = "caseInterestResource.inv.ref";
  public static final String CASE_INTEREST_BASE_FDG         = "caseInterestResource.base.fdg";
  public static final String CASE_INTEREST_INTER_OC         = "caseInterestResource.inter.oc";
  public static final String CASE_INTEREST_INTER_FDG        = "caseInterestResource.inter.fdg";
  public static final String CASE_INTEREST_TITLE            = "caseInterestResource.title";
  public static final String CASE_INTEREST_COUNTERS         = "caseInterestResource.counters";
  public static final String CASE_INTEREST_PRINCIPAL_AMOUNT = "caseInterestResource.principal.amount";
  public static final String CASE_INTEREST_CALC_INTEREST    = "caseInterestResource.calc.interest";
  public static final String CASE_INTEREST_OUTSTANDING_AMT  = "caseInterestResource.outstanding.amt";
  public static final String CASE_INTEREST_CURRENCY         = "caseInterestResource.currency";
  public static final String CASE_INTEREST_TEXT_REF         = "caseInterestResource.text.ref";
  public static final String CASE_INTEREST_LIB_INFO         = "caseInterestResource.lib.info";


  public static final String LAWYER_FEES_CURRENCY   = "lawyer.fees.currency";
  public static final String LAWYER_FEES_INDIVIDUAL = "lawyer.fees.individual";
  public static final String LAWYER_FEES_FROM       = "lawyer.fees.from";
  public static final String LAWYER_FEES_TO         = "lawyer.fees.to";
  public static final String LAWYER_FEES_PERCENTAGE = "lawyer.fees.percentage";
  public static final String LAWYER_FEES_OVERALLSUM = "lawyer.fees.overallsum";
  public static final String LAWYER_FEES_FIXEDCOSTS = "lawyer.fees.fixedcosts";


  //DebtorConsolidationInfoResourceTest properties
  // 1.4.1	Upper part "Debtor information"
  public static final String DEBTOR_CONSOLIDATION_INFO_DEBTOR_NUMBER           = "debtor.consolidation.info.debtor.number";
  public static final String DEBTOR_CONSOLIDATION_INFO_DEBTOR_NAME             = "debtor.consolidation.info.debtor.name";
  public static final String DEBTOR_CONSOLIDATION_INFO_BU_CURRENCY             = "debtor.consolidation.info.bu.currency";
  public static final String DEBTOR_CONSOLIDATION_INFO_CLIENT_NUMBER	         = "debtor.consolidation.info.client.number";
  public static final String DEBTOR_CONSOLIDATION_INFO_CLIENT_REF              = "debtor.consolidation.info.client.ref";
  public static final String DEBTOR_CONSOLIDATION_INFO_CLIENT_NAME             = "debtor.consolidation.info.client.name";
  // 1.4.4	Bottom part - statistics
  public static final String DEBTOR_CONSOLIDATION_INFO_AVERAGE_DELAY           = "debtor.consolidation.info.average.delay";
  public static final String DEBTOR_CONSOLIDATION_INFO_AVG_PMT_DELAY           = "debtor.consolidation.info.avg.pmt.delay";
  public static final String DEBTOR_CONSOLIDATION_INFO_AVG_PMT_COND            = "debtor.consolidation.info.avg.pmt.cond";
  public static final String DEBTOR_CONSOLIDATION_INFO_DB_IS_CLIENT            = "debtor.consolidation.info.db.is.client";
  public static final String DEBTOR_CONSOLIDATION_INFO_DB_IS_CLT_REF_CASE      = "debtor.consolidation.info.db.is.clt.ref.case";
  public static final String DEBTOR_CONSOLIDATION_INFO_DB_IS_LINKED_TO_COMPANY = "debtor.consolidation.info.db.is.linked.to.company";
  public static final String DEBTOR_CONSOLIDATION_INFO_DB_IS_LINKED_TO_GRP     = "debtor.consolidation.info.db.is.linked.to.grp";
  // parameters
  public static final String DEBTOR_CONSOLIDATION_INFO_REF_CASE                = "debtor.consolidation.info.ref.case";

  // 1.4.2	Middle part "Debtor portfolio details"
  public static final String CONSOLIDATION_BY_CLIENT_CLIENT_CODE               = "consolidation.by.client.client.code";
  public static final String CONSOLIDATION_BY_CLIENT_CLIENT_NAME               = "consolidation.by.client.client.name";
  public static final String CONSOLIDATION_BY_CLIENT_TOTAL_APPROVED_CREDIT     = "consolidation.by.client.total.approved.credit";
  public static final String CONSOLIDATION_BY_CLIENT_AMT_COVERED_BY_CI         = "consolidation.by.client.amt.covered.by.ci";
  public static final String CONSOLIDATION_BY_CLIENT_USED_APPROVED_CREDIT      = "consolidation.by.client.used.approved.credit";
  public static final String CONSOLIDATION_BY_CLIENT_INV_NOT_DUE               = "consolidation.by.client.inv.not.due";
  public static final String CONSOLIDATION_BY_CLIENT_DUE_LESS30                = "consolidation.by.client.due.less30";
  public static final String CONSOLIDATION_BY_CLIENT_DUE30TO59                 = "consolidation.by.client.due30to59";
  public static final String CONSOLIDATION_BY_CLIENT_DUE60TO89                 = "consolidation.by.client.due60to89";
  public static final String CONSOLIDATION_BY_CLIENT_DUE90_AND_MORE            = "consolidation.by.client.due90.and.more";
  public static final String CONSOLIDATION_BY_CLIENT_DISPUTES                  = "consolidation.by.client.disputes";
  public static final String CONSOLIDATION_BY_CLIENT_NON_MATCHED_PAYMENTS      = "consolidation.by.client.non.matched.payments";
  public static final String CONSOLIDATION_BY_CLIENT_TOTAL_OUTSTANDING_DEBT    = "consolidation.by.client.total.outstanding.debt";
  // parameters
  public static final String CONSOLIDATION_BY_CLIENT_REF_DEBTOR                = "consolidation.by.client.ref.debtor";

  // 1.4.3.A  InvoicesTotalAmounts properties
  public static final String INVOICES_TOTAL_AMOUNTS_NB_MONTHS_INV              = "invoices.total.amounts.nb.months.inv";
  public static final String INVOICES_TOTAL_AMOUNTS_TOTAL_INVOICES             = "invoices.total.amounts.total.invoices";
  public static final String INVOICES_TOTAL_AMOUNTS_TOTAL_INVOICES_ALL_CLIENTS = "invoices.total.amounts.total.invoices.all.clients";
  // parameters
  public static final String INVOICES_TOTAL_AMOUNTS_REF_DEBTOR                 = "invoices.total.amounts.ref.debtor";

  // 1.4.3.B  PaymentsTotalAmounts properties
  public static final String PAYMENTS_TOTAL_AMOUNTS_NB_MONTHS_PMTS             = "payments.total.amounts.nb.months.pmts";
  public static final String PAYMENTS_TOTAL_AMOUNTS_TOTAL_PAYMENTS             = "payments.total.amounts.total.payments";
  public static final String PAYMENTS_TOTAL_AMOUNTS_TOTAL_PAYMENTS_ALL_CLIENTS = "payments.total.amounts.total.payments.all.clients";
  // parameters
  public static final String PAYMENTS_TOTAL_AMOUNTS_REF_DEBTOR                 = "payments.total.amounts.ref.debtor";

  // 1.4.3.C  AmountCreditNotes properties
  public static final String AMOUNT_CREDIT_NOTES_NB_MONTHS_CN                  = "amount.credit.notes.nb.months.cn";
  public static final String AMOUNT_CREDIT_NOTES_TOTAL_CN                      = "amount.credit.notes.total.cn";
  public static final String AMOUNT_CREDIT_NOTES_TOTAL_CN_ALL_CLIENTS          = "amount.credit.notes.total.cn.all.clients";
  // parameters
  public static final String AMOUNT_CREDIT_NOTES_REF_DEBTOR                    = "amount.credit.notes.ref.debtor";

  // 1.4.3.D  AmountOfBalancedInvoices properties
  public static final String AMOUNT_OF_BALANCED_INVOICES_NB_MONTHS_BAL_INV          = "amount.of.balanced.invoices.nb.months.bal.inv";
  public static final String AMOUNT_OF_BALANCED_INVOICES_TOTAL_BAL_INV              = "amount.of.balanced.invoices.total.bal.inv";
  public static final String AMOUNT_OF_BALANCED_INVOICES_TOTAL_BAL_INV_ALL_CLIENTS  = "amount.of.balanced.invoices.total.bal.inv.all.clients";
  // parameters
  public static final String AMOUNT_OF_BALANCED_INVOICES_REF_DEBTOR            = "amount.of.balanced.invoices.ref.debtor";

  // 1.4.3.E  AmountOfSalesOrders properties
  public static final String AMOUNT_OF_SALES_ORDERS_NB_MONTHS_SALES_ORDERS     = "amount.of.sales.orders.nb.months.sales.orders";
  public static final String AMOUNT_OF_SALES_ORDERS_TOTAL_SO                   = "amount.of.sales.orders.total.so";
  public static final String AMOUNT_OF_SALES_ORDERS_TOTAL_SO_ALL_CLIENTS       = "amount.of.sales.orders.total.so.all.clients";
  // parameters
  public static final String AMOUNT_OF_SALES_ORDERS_REF_DEBTOR                 = "amount.of.sales.orders.ref.debtor";


  //CreditInsurancePUGResourceTest properties
  public static final String CREDIT_INSURANCE_PUG_CASEREF              = "credit.insurance.pug.case";
  public static final String CREDIT_INSURANCE_PUG_CASE_HISTORY_ID      = "credit.insurance.pug.case.history.id";
  public static final String CREDIT_INSURANCE_PUG_DATE                 = "credit.insurance.pug.date";
  public static final String CREDIT_INSURANCE_PUG_AMOUNT               = "credit.insurance.pug.amount";
  public static final String CREDIT_INSURANCE_PUG_FREECOMMENT          = "credit.insurance.pug.freecomment";
  public static final String CREDIT_INSURANCE_PUG_IMAGE                = "credit.insurance.pug.image";
  public static final String CREDIT_INSURANCE_PUG_PAGE                 = "credit.insurance.pug.page";
  public static final String CREDIT_INSURANCE_PUG_NBPAGE               = "credit.insurance.pug.nbpage";
  public static final String CREDIT_INSURANCE_PUG_HAS_ATTACHED_FILE    = "credit.insurance.pug.has.attached.file";
  public static final String CREDIT_INSURANCE_PUG_MESSAGE_LINE         = "credit.insurance.pug.message.line";
}

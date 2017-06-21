package fm.common;

import fm.common.beans.HeaderLang;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

public final class HeaderLangHandlerMethodArgumentResolver implements HandlerMethodArgumentResolver {
  private final String defaultLang = "EN";

  @Override
  public boolean supportsParameter(MethodParameter parameter) {
    return null != parameter.getParameterAnnotation(HeaderLang.class);
  }

  @Override
  public Object resolveArgument(
       MethodParameter parameter
      ,ModelAndViewContainer mavContainer
      ,NativeWebRequest webRequest
      ,WebDataBinderFactory binderFactory
  ) throws Exception {
    String acceptLang = webRequest.getHeader(HttpHeaders.ACCEPT_LANGUAGE);

    if (StringUtils.isEmpty(acceptLang)) {
      return defaultLang;
    }

    return parseAcceptLang(acceptLang);
  }

  /**
   * For the moment en->AN, all others capitalised version.
   *
   * @param acceptLang
   * @return language as used by iMX
   */
  private String parseAcceptLang(String acceptLang) {
    if ("en".equals(acceptLang)) {
      return defaultLang;
    }

    //TODO: Vasko says this should be replaces with real parsing
    return acceptLang.toUpperCase();
  }
}

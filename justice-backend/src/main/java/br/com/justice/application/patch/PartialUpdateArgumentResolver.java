package br.com.justice.application.patch;

import br.com.justice.application.util.Mappable;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.context.ApplicationContext;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.web.servlet.HandlerMapping;

import static java.util.Objects.isNull;

@Component
@Slf4j
@RequiredArgsConstructor
public class PartialUpdateArgumentResolver implements HandlerMethodArgumentResolver, Mappable {

  final ObjectMapper objectMapper;
  final ApplicationContext context;

  @Override
  public boolean supportsParameter(MethodParameter parameter) {
    val methodAnot = parameter.getMethodAnnotation(PatchMapping.class);
    if (isNull(methodAnot)) {
      return false;
    }
    return parameter.hasParameterAnnotation(PatchRequestBody.class);
  }

  @Override
  public Object resolveArgument(MethodParameter parameter,
                                ModelAndViewContainer mavContainer,
                                NativeWebRequest webRequest,
                                WebDataBinderFactory binderFactory) throws Exception {
    val req = createInputMessage(webRequest);

    Patch patch = parameter.getMethodAnnotation(Patch.class);
    val parameterClass = parameter.getParameterType();
    val serviceClass = patch.service();
    val idClass = patch.id();
    val mapClass = patch.mapper();
    val find = patch.findMethod();
    val map = patch.mapMethod();

    val service = context.getBean(serviceClass);

    val idStr = getPathVariables(webRequest).get("id");
    val id = castId(idClass, idStr);
    val method = ReflectionUtils.findMethod(serviceClass, find, idClass);

    Object obj = ReflectionUtils.invokeMethod(method, service, id);
    obj = mapToJavaType(obj, mapClass, map, req, parameterClass);
    return obj;
  }

  private Object mapToJavaType(Object obj,
                               Class mapClass,
                               String map,
                               ServletServerHttpRequest req,
                               Class parameterClass) {
    val javaObj = readJavaType(obj, req);
    try {
      if (!javaObj.getClass().equals(parameterClass)) {
        if (mapClass.equals(Mappable.class)) {
          return map(obj, parameterClass);
        } else {
          val mapper = context.getBean(mapClass);
          Method method = ReflectionUtils.findMethod(mapClass, map);
          return ReflectionUtils.invokeMethod(method, mapper, javaObj, parameterClass);
        }
      }
    } catch (Exception e) {
      log.error("Error mapper", e);
    }
    return javaObj;
  }

  private Object castId(Class idClass, String idStr) {
    if (idClass.equals(Long.class)) {
      return Long.valueOf(idStr);
    }
    if (idClass.equals(Double.class)) {
      return Double.valueOf(idStr);
    }
    if (idClass.equals(Float.class)) {
      return Long.valueOf(idStr);
    }
    if (idClass.equals(Integer.class)) {
      return Integer.valueOf(idStr);
    }
    return idClass.cast(idStr);
  }

  private Map<String, String> getPathVariables(NativeWebRequest webRequest) {

    HttpServletRequest httpServletRequest = webRequest.getNativeRequest(HttpServletRequest.class);
    return (Map<String, String>) httpServletRequest.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
  }

  protected ServletServerHttpRequest createInputMessage(NativeWebRequest webRequest) {
    HttpServletRequest servletRequest = webRequest.getNativeRequest(HttpServletRequest.class);
    return new ServletServerHttpRequest(servletRequest);
  }

  private Object readJavaType(Object object, HttpInputMessage inputMessage) {
    try {
      return this.objectMapper.readerForUpdating(object).readValue(inputMessage.getBody());
    } catch (IOException ex) {
      throw new HttpMessageNotReadableException("Could not read object: " + ex.getMessage(), ex);
    }
  }
}
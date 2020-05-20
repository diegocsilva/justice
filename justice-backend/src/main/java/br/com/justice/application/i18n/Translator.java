package br.com.justice.application.i18n;

import java.util.Locale;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

@Component
public class Translator {

  private final MessageSource source;
  private static MessageSource SOURCE_STATIC;

  public Translator(@Qualifier("messageSourceDefault") MessageSource source) {
    this.source = source;
  }

  public String toLocale(String code, Object... args) {
    val locale = LocaleContextHolder.getLocale();
    return toLocaleDefault(code, locale, args);
  }

  public static String toLocaleStatic(String code, Object... args) {
    val locale = LocaleContextHolder.getLocale();
    return toLocaleDefaultStatic(code, locale, args);
  }

  @Autowired
  public static void setSourceStatic(@Qualifier("messageSourceDefault") MessageSource messageSource) {
    SOURCE_STATIC = messageSource;
  }

  private String toLocaleDefault(String code, Locale locale, Object... args) {
    return source.getMessage(code, args, locale);
  }

  private static String toLocaleDefaultStatic(String code, Locale locale, Object... args) {
    return SOURCE_STATIC.getMessage(code, args, locale);
  }
}

package br.com.justice.application.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static java.util.Objects.isNull;

public class DateUtil {

  public static final String PATTERN_DATE = "yyyy-MM-dd";

  private DateUtil() {
    super();
  }

  public static String formate(String pattern, Date date) {
    if (isNull(date)) {
      return null;
    }
    SimpleDateFormat format = new SimpleDateFormat(pattern);
    return format.format(date);
  }

  public static Date toDate(String date) {
    if (isNull(date)) {
      return null;
    }
    try {
      SimpleDateFormat sf = new SimpleDateFormat(PATTERN_DATE);
      return sf.parse(date);
    } catch (ParseException e) {
      throw new RuntimeException(e);
    }
  }
}

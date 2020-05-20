package br.com.justice.application.util;

import br.com.justice.application.configuration.ModelMapperConfiguration;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.val;

public interface Mappable {

  default <T> T map(Object source, Class<T> clazz) {
    val mapper = new ModelMapperConfiguration().modelMapper();
    return mapper.map(source, clazz);
  }

  default <T> List<T> map(Collection<?> source, Class<T> clazz) {
    return source
        .stream()
        .map(t -> map(t, clazz))
        .collect(Collectors.toList());
  }

  default <T> Set<T> map(Set<?> source, Class<T> clazz) {
    return source
        .stream()
        .map(t -> map(t, clazz))
        .collect(Collectors.toSet());
  }
}
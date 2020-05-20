package br.com.justice.application.configuration;

import lombok.val;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfiguration {

  @Bean
  public ModelMapper modelMapper() {
    val mapper = new ModelMapper();

    mapper.getConfiguration()
        .setFieldMatchingEnabled(true)
        .setMatchingStrategy(MatchingStrategies.STRICT);

    return mapper;
  }
}

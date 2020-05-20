package br.com.justice.infrastructure.impl;

import br.com.justice.domain.entity.CustomCase;
import br.com.justice.domain.interf.repository.CaseRepository;
import br.com.justice.infrastructure.repository.CaseRepositoryJpa;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import static java.util.Objects.isNull;

@Repository
@RequiredArgsConstructor
public class CaseRepositoryImpl implements CaseRepository {

  private final CaseRepositoryJpa jpa;

  @Override
  public Page<CustomCase> findAll(Pageable pageable) {
    return jpa.findAll(pageable);
  }

  @Override
  public Page<CustomCase> filter(CustomCase customCase, Pageable pageable) {
    Example<CustomCase> example;
    if (isNull(customCase.getId())) {
      example = Example.of(customCase, ExampleMatcher.matching()
          .withIgnoreNullValues()
          .withStringMatcher(ExampleMatcher.StringMatcher.DEFAULT)
          .withIgnorePaths("id"));
    } else {
      example = Example.of(customCase, ExampleMatcher.matching()
          .withIgnoreNullValues());
    }
    return jpa.findAll(example, pageable);
  }

  @Override
  public CustomCase save(CustomCase customCase) {
    return jpa.save(customCase);
  }

  @Override
  public Iterable<CustomCase> saveAll(Iterable<CustomCase> cases) {
    return jpa.saveAll(cases);
  }

  @Override
  public Optional<CustomCase> findById(Long id) {
    return Optional.empty();
  }
}

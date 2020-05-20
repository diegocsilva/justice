package br.com.justice.domain.interf.repository;

import br.com.justice.domain.entity.CustomCase;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CaseRepository {

  Page<CustomCase> findAll(Pageable pageable);

  Page<CustomCase> filter(CustomCase customCase, Pageable pageable);

  CustomCase save(CustomCase customCase);

  Iterable<CustomCase> saveAll(Iterable<CustomCase> cases);

  Optional<CustomCase> findById(Long id);
}

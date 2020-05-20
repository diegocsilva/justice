package br.com.justice.infrastructure.repository;

import br.com.justice.domain.entity.CustomCase;
import org.springframework.cloud.gcp.data.datastore.repository.DatastoreRepository;

public interface CaseRepositoryJpa extends DatastoreRepository<CustomCase, Long> {

}

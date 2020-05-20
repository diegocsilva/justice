package br.com.justice.domain.interf.service;

import br.com.justice.api.dto.CaseDTO;
import br.com.justice.domain.entity.CustomCase;
import java.io.IOException;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

public interface CaseService {

  Page<CustomCase> list(Pageable pageable);

  Page<CustomCase> filter(CustomCase example, Pageable pageable);

  CustomCase save(CaseDTO caseDTO);

  CustomCase findById(Long id);

  CustomCase update(Long id, CaseDTO caseDTO);

  List<CustomCase> saveAll(MultipartFile file) throws IOException;
}

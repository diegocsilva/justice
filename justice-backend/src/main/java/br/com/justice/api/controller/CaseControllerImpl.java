package br.com.justice.api.controller;

import br.com.justice.api.dto.CaseDTO;
import br.com.justice.api.interf.CaseController;
import br.com.justice.application.patch.Patch;
import br.com.justice.application.patch.PatchMediaType;
import br.com.justice.application.patch.PatchRequestBody;
import br.com.justice.application.util.Mappable;
import br.com.justice.application.util.Response;
import br.com.justice.domain.entity.CustomCase;
import br.com.justice.domain.interf.service.CaseService;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import lombok.val;
import lombok.var;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.annotation.RequestScope;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.multipart.MultipartFile;

import static java.util.Objects.nonNull;

@RequestScope
@RestController
@RequestMapping("/case")
public class CaseControllerImpl implements CaseController, Mappable, Response<CaseDTO> {

  final List<HandlerMethodArgumentResolver> resolvers;

  public final CaseService caseService;

  public CaseControllerImpl(CaseService caseService, List<HandlerMethodArgumentResolver> resolvers) {
    this.caseService = caseService;
    this.resolvers = resolvers;
  }

  @Override
  public Page<CaseDTO> list(Pageable pageable, CaseDTO filter) {
    Page<CustomCase> results;
    if (filterNonNull(filter)) {
      results = caseService.filter(map(filter, CustomCase.class), pageable);
    } else {
      results = caseService.list(pageable);
    }
    var data = results.getContent().stream()
        .map(r -> map(r, CaseDTO.class))
        .collect(Collectors.toList());
    return new PageImpl<>(data, results.getPageable(), results.getTotalElements());
  }

  private boolean filterNonNull(CaseDTO filter) {
    return Arrays.asList(
        nonNull(filter.getId()),
        nonNull(filter.getAccess()),
        nonNull(filter.getComments()),
        nonNull(filter.getCreationDate()),
        nonNull(filter.getCustomers()),
        nonNull(filter.getDescription()),
        nonNull(filter.getFolder()),
        nonNull(filter.getResponsible()),
        nonNull(filter.getTitle()),
        nonNull(filter.getTag())
    ).contains(Boolean.TRUE);
  }

  @Override
  public ResponseEntity<CaseDTO> save(CaseDTO caseDTO) {
    val newCase = caseService.save(caseDTO);
    return buildResponse(map(newCase, CaseDTO.class));
  }

  @Override
  public ResponseEntity<CaseDTO> findById(Long id) {
    val result = caseService.findById(id);
    return buildResponse(map(result, CaseDTO.class));
  }

  @Override
  @PatchMapping(value = "/{id}", consumes = PatchMediaType.APPLICATION_MERGE_PATCH_VALUE)
  @Patch(service = CaseService.class, id = Long.class)
  public ResponseEntity<CaseDTO> update(@PathVariable Long id, @RequestBody @PatchRequestBody CaseDTO caseDTO) {
    val result = caseService.update(id, caseDTO);
    return buildResponse(map(result, CaseDTO.class));
  }

  @Override
  public ResponseEntity<List<CaseDTO>> saveAll(MultipartFile file) throws IOException {
    val cases = caseService.saveAll(file);
    var data = cases.stream()
        .map(r -> map(r, CaseDTO.class))
        .collect(Collectors.toList());
    return buildResponse(data);
  }
}

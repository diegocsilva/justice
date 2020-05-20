package br.com.justice.api.interf;

import br.com.justice.api.dto.CaseDTO;
import br.com.justice.application.patch.Patch;
import br.com.justice.application.patch.PatchMediaType;
import br.com.justice.application.patch.PatchRequestBody;
import br.com.justice.domain.interf.service.CaseService;
import io.swagger.annotations.ApiOperation;
import java.io.IOException;
import java.util.List;
import javax.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.multipart.MultipartFile;

public interface CaseController {

  @GetMapping
  @ResponseBody
  @ResponseStatus(HttpStatus.OK)
  @ApiOperation(
      value = "Paged list of CASE entities",
      response = Page.class
  )
  Page<CaseDTO> list(Pageable pageable, CaseDTO filter);

  @PostMapping
  @ResponseBody
  @ResponseStatus(HttpStatus.OK)
  @ApiOperation(
      value = "Save a new CASE entity",
      response = CaseDTO.class
  )
  ResponseEntity<CaseDTO> save(@Valid @RequestBody CaseDTO caseDTO);

  @GetMapping("/{id}")
  @ResponseBody
  @ResponseStatus(HttpStatus.OK)
  @ApiOperation(
      value = "Find a CASE entity by id",
      response = CaseDTO.class
  )
  ResponseEntity<CaseDTO> findById(@PathVariable Long id);

  @PatchMapping(value = "/{id}", consumes = PatchMediaType.APPLICATION_MERGE_PATCH_VALUE)
  @ResponseBody
  @ResponseStatus(HttpStatus.OK)
  @ApiOperation(
      value = "Update values a CASE entity",
      response = CaseDTO.class
  )
  @Patch(service = CaseService.class, id = Long.class)
  ResponseEntity<CaseDTO> update(@PathVariable Long id,
                                 @RequestBody @PatchRequestBody CaseDTO caseDTO);

  @PostMapping("/all/save")
  @ResponseBody
  @ResponseStatus(HttpStatus.OK)
  @ApiOperation(
      value = "Save all new CASE entities",
      response = List.class
  )
  ResponseEntity<List<CaseDTO>> saveAll(@RequestParam("file") MultipartFile file) throws IOException;
}
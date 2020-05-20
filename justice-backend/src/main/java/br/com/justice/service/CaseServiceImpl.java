package br.com.justice.service;

import br.com.justice.api.dto.CaseDTO;
import br.com.justice.application.exception.ApiException;
import br.com.justice.application.util.DateUtil;
import br.com.justice.application.util.Mappable;
import br.com.justice.domain.entity.Access;
import br.com.justice.domain.entity.CustomCase;
import br.com.justice.domain.interf.repository.CaseRepository;
import br.com.justice.domain.interf.service.CaseService;
import com.google.common.collect.Lists;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.apache.commons.io.IOUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import static br.com.justice.domain.enums.CaseFileLayout.ACCESS;
import static br.com.justice.domain.enums.CaseFileLayout.COMMENTS;
import static br.com.justice.domain.enums.CaseFileLayout.CREATIONDATE;
import static br.com.justice.domain.enums.CaseFileLayout.CUSTOMERS;
import static br.com.justice.domain.enums.CaseFileLayout.DESCRIPTION;
import static br.com.justice.domain.enums.CaseFileLayout.FOLDER;
import static br.com.justice.domain.enums.CaseFileLayout.RESPONSIBLE;
import static br.com.justice.domain.enums.CaseFileLayout.TAG;
import static br.com.justice.domain.enums.CaseFileLayout.TITLE;

@Service
@RequiredArgsConstructor
public class CaseServiceImpl implements CaseService, Mappable {

  public static final int INDEX_HEADER = 0;
  public static final double PERCENT_20 = 0.2;
  private final CaseRepository repository;
  private final String DATE_PATTERN = "MM-dd-yyyy";

  @Override
  public Page<CustomCase> list(Pageable pageable) {
    Page<CustomCase> list = repository.findAll(pageable);
    formatCreationDate(list);
    return list;
  }

  @Override
  public Page<CustomCase> filter(CustomCase example, Pageable pageable) {
    return repository.filter(example, pageable);
  }

  @Override
  public CustomCase save(CaseDTO caseDTO) {
    return repository.save(map(caseDTO, CustomCase.class));
  }

  @Override
  public CustomCase findById(Long id) {
    return repository.findById(id)
        .orElseThrow(() -> new ApiException("case.not.fount", HttpStatus.NOT_FOUND));
  }

  @Override
  public CustomCase update(Long id, CaseDTO caseDTO) {
    caseDTO.setId(id);
    return repository.save(map(caseDTO, CustomCase.class));
  }

  @Override
  public List<CustomCase> saveAll(MultipartFile file) throws IOException {
    val fileString = IOUtils.toString(file.getInputStream(), Charset.defaultCharset());
    val lines = new ArrayList<>(Arrays.asList(fileString.split("\n")));
    lines.remove(INDEX_HEADER);
    val cases = Collections.synchronizedList(new ArrayList<CustomCase>());
    lines.parallelStream().forEachOrdered(line -> {
      val columns = Arrays.asList(line.split(";"));
      cases.add(CustomCase.builder()
          .folder(columns.get(FOLDER.getColumn()))
          .customers(columns.get(CUSTOMERS.getColumn()))
          .title(columns.get(TITLE.getColumn()))
          .tag(Arrays.asList(columns.get(TAG.getColumn()).split(",")))
          .description(columns.get(DESCRIPTION.getColumn()))
          .comments(columns.get(COMMENTS.getColumn()))
          .responsible(columns.get(RESPONSIBLE.getColumn()))
          .access(Access.valueOf(columns.get(ACCESS.getColumn())))
          .creationDate(DateUtil.toDate(columns.get(CREATIONDATE.getColumn())))
          .build());
    });

    val result = new ArrayList<CustomCase>();
    val size = cases.size();

    Lists.partition(cases, Double.valueOf(size * PERCENT_20).intValue())
        .parallelStream()
        .map(repository::saveAll)
        .collect(Collectors.toList())
        .forEach(iterable ->
            iterable
                .iterator()
                .forEachRemaining(result::add)
        );
    return result;
  }

  private void formatCreationDate(Page<CustomCase> list) {
    list.getContent().forEach(customCase ->
        customCase.setFormattedDate(
            DateUtil.formate(DATE_PATTERN, customCase.getCreationDate()))
    );
  }
}

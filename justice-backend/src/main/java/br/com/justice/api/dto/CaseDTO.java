package br.com.justice.api.dto;

import br.com.justice.domain.entity.Access;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;

@Data
public class CaseDTO implements Serializable {

  @Id
  Long id;

  @Size(max = 40, message = "{size.max.folder.validation}")
  @NotBlank(message = "{not.blank.folder.validation}")
  String folder;

  @NotBlank(message = "{not.blank.customers.validation}")
  String customers;

  @NotBlank(message = "{not.blank.title.validation}")
  String title;

  List<String> tag;

  String description;

  String comments;

  @NotBlank(message = "{not.blank.responsible.validation}")
  String responsible;

  @NotNull(message = "{not.null.access.validation}")
  Access access;

  @NotNull(message = "{not.null.creation.date.validation}")
  Date creationDate;

  String formattedDate;
}
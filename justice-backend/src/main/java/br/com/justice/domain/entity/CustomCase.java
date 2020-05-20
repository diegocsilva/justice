package br.com.justice.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.Date;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.cloud.gcp.data.datastore.core.mapping.Entity;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;

@Entity(name = "case")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CustomCase {
  @Id
  public Long id;

  public String folder;
  String customers;
  String title;
  List<String> tag;
  String description;
  String comments;
  String responsible;
  Access access;
  Date creationDate;

  @JsonIgnore
  @Transient
  String formattedDate;
}
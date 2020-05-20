package br.com.justice.domain.enums;

import lombok.Getter;

@Getter
public enum CaseFileLayout {
  FOLDER(0),
  CUSTOMERS(1),
  TITLE(2),
  TAG(3),
  DESCRIPTION(4),
  COMMENTS(5),
  RESPONSIBLE(6),
  ACCESS(7),
  CREATIONDATE(8);

  CaseFileLayout(Integer column) {
    this.column = column;
  }

  private Integer column;
}

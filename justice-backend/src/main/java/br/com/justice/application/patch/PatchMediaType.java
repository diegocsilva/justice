package br.com.justice.application.patch;

import org.springframework.http.MediaType;

public interface PatchMediaType {

  String APPLICATION_MERGE_PATCH_VALUE = "application/merge-patch+json";

  MediaType APPLICATION_MERGE_PATCH = MediaType.valueOf(APPLICATION_MERGE_PATCH_VALUE);

}

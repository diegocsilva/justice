package br.com.justice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gcp.data.datastore.repository.config.EnableDatastoreRepositories;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableDatastoreRepositories
@EnableSwagger2
public class JusticeApplication {

  public static void main(String[] args) {
    SpringApplication.run(JusticeApplication.class, args);
  }

}

package tech.jhipster.sampleapp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;
import tech.jhipster.sampleapp.shared.generation.domain.ExcludeFromGeneratedCodeCoverage;

@SpringBootApplication
@ExcludeFromGeneratedCodeCoverage(reason = "Not testing logs")
public class SampleappApp {

  private static final Logger log = LoggerFactory.getLogger(SampleappApp.class);

  public static void main(String[] args) {
    Environment env = SpringApplication.run(SampleappApp.class, args).getEnvironment();

    if (log.isInfoEnabled()) {
      log.info(ApplicationStartupTraces.of(env));
    }
  }
}

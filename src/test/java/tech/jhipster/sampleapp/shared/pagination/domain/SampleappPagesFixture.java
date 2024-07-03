package tech.jhipster.sampleapp.shared.pagination.domain;

import java.util.List;

import tech.jhipster.sampleapp.shared.pagination.domain.SampleappPage.SampleappPageBuilder;

public final class SampleappPagesFixture {

  private SampleappPagesFixture() {}

  public static SampleappPage<String> page() {
    return pageBuilder().build();
  }

  public static SampleappPageBuilder<String> pageBuilder() {
    return SampleappPage.builder(List.of("test")).currentPage(2).pageSize(10).totalElementsCount(21);
  }
}

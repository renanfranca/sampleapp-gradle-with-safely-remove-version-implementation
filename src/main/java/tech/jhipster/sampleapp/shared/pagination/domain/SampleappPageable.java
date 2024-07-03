package tech.jhipster.sampleapp.shared.pagination.domain;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import tech.jhipster.sampleapp.shared.generation.domain.ExcludeFromGeneratedCodeCoverage;
import tech.jhipster.sampleapp.shared.error.domain.Assert;

public class SampleappPageable {

  private final int page;
  private final int pageSize;
  private final int offset;

  public SampleappPageable(int page, int pageSize) {
    Assert.field("page", page).min(0);
    Assert.field("pageSize", pageSize).min(1).max(100);

    this.page = page;
    this.pageSize = pageSize;
    offset = page * pageSize;
  }

  public int page() {
    return page;
  }

  public int pageSize() {
    return pageSize;
  }

  public int offset() {
    return offset;
  }

  @Override
  @ExcludeFromGeneratedCodeCoverage
  public int hashCode() {
    return new HashCodeBuilder().append(page).append(pageSize).build();
  }

  @Override
  @ExcludeFromGeneratedCodeCoverage
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }

    if (obj == null || getClass() != obj.getClass()) {
      return false;
    }

    SampleappPageable other = (SampleappPageable) obj;
    return new EqualsBuilder().append(page, other.page).append(pageSize, other.pageSize).build();
  }
}

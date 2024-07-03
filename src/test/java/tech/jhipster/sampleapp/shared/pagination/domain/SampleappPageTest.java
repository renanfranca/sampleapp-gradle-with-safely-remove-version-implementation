package tech.jhipster.sampleapp.shared.pagination.domain;

import static org.assertj.core.api.Assertions.*;
import static tech.jhipster.sampleapp.shared.pagination.domain.SampleappPagesFixture.*;

import java.util.List;

import org.junit.jupiter.api.Test;

import tech.jhipster.sampleapp.UnitTest;
import tech.jhipster.sampleapp.shared.error.domain.MissingMandatoryValueException;

@UnitTest
class SampleappPageTest {

  @Test
  void shouldGetEmptySinglePageWithoutContent() {
    SampleappPage<String> page = SampleappPage.singlePage(null);

    assertEmptyPage(page);
  }

  @Test
  void shouldGetEmptySinglePageFromBuilderWithoutContent() {
    SampleappPage<?> page = SampleappPage.builder(null).build();

    assertEmptyPage(page);
  }

  private void assertEmptyPage(SampleappPage<?> page) {
    assertThat(page.content()).isEmpty();
    assertThat(page.currentPage()).isZero();
    assertThat(page.pageSize()).isZero();
    assertThat(page.totalElementsCount()).isZero();
  }

  @Test
  void shouldGetSinglePage() {
    SampleappPage<String> page = SampleappPage.singlePage(List.of("test", "dummy"));

    assertSinglePage(page);
  }

  @Test
  void shouldGetSinglePageFromBuilderWithContentOnly() {
    SampleappPage<String> page = SampleappPage.builder(List.of("test", "dummy")).build();

    assertSinglePage(page);
  }

  private void assertSinglePage(SampleappPage<String> page) {
    assertThat(page.content()).containsExactly("test", "dummy");
    assertThat(page.currentPage()).isZero();
    assertThat(page.pageSize()).isEqualTo(2);
    assertThat(page.totalElementsCount()).isEqualTo(2);
    assertThat(page.pageCount()).isEqualTo(1);
  }

  @Test
  void shouldGetFullPage() {
    SampleappPage<String> page = pageBuilder().build();

    assertThat(page.content()).containsExactly("test");
    assertThat(page.currentPage()).isEqualTo(2);
    assertThat(page.pageSize()).isEqualTo(10);
    assertThat(page.totalElementsCount()).isEqualTo(21);
    assertThat(page.pageCount()).isEqualTo(3);
  }

  @Test
  void shouldNotMapWithoutMapper() {
    assertThatThrownBy(() -> pageBuilder().build().map(null))
      .isExactlyInstanceOf(MissingMandatoryValueException.class)
      .hasMessageContaining("mapper");
  }

  @Test
  void shouldMapPage() {
    SampleappPage<String> page = pageBuilder().build().map(entry -> "hey");

    assertThat(page.content()).containsExactly("hey");
    assertThat(page.currentPage()).isEqualTo(2);
    assertThat(page.pageSize()).isEqualTo(10);
    assertThat(page.totalElementsCount()).isEqualTo(21);
    assertThat(page.pageCount()).isEqualTo(3);
  }

  @Test
  void shouldNotBeLastForFirstPage() {
    assertThat(pageBuilder().currentPage(0).build().isNotLast()).isTrue();
  }

  @Test
  void shouldBeLastWithOnePage() {
    assertThat(SampleappPage.singlePage(List.of("d")).isNotLast()).isFalse();
  }

  @Test
  void shouldBeLastPageWithoutContent() {
    SampleappPage<Object> page = SampleappPage.builder(List.of()).currentPage(0).pageSize(1).totalElementsCount(0).build();
    assertThat(page.isNotLast()).isFalse();
  }

  @Test
  void shouldBeLastForLastPage() {
    assertThat(pageBuilder().currentPage(2).build().isNotLast()).isFalse();
  }

  @Test
  void shouldGetPageFromElements() {
    SampleappPage<String> page = SampleappPage.of(List.of("hello", "java", "world"), new SampleappPageable(1, 1));

    assertThat(page.currentPage()).isEqualTo(1);
    assertThat(page.hasNext()).isTrue();
    assertThat(page.hasPrevious()).isTrue();
    assertThat(page.pageCount()).isEqualTo(3);
    assertThat(page.pageSize()).isEqualTo(1);
    assertThat(page.content()).containsExactly("java");
  }

  @Test
  void shouldGetEmptyPageFromOutOfBoundElements() {
    SampleappPage<String> page = SampleappPage.of(List.of("hello", "java", "world"), new SampleappPageable(4, 1));

    assertThat(page.currentPage()).isEqualTo(4);
    assertThat(page.hasNext()).isFalse();
    assertThat(page.hasPrevious()).isTrue();
    assertThat(page.pageCount()).isEqualTo(3);
    assertThat(page.pageSize()).isEqualTo(1);
    assertThat(page.content()).isEmpty();
  }

  @Test
  void shouldGetPageWithLessThanExpectedElements() {
    SampleappPage<String> page = SampleappPage.of(List.of("hello", "java", "world"), new SampleappPageable(0, 4));

    assertThat(page.currentPage()).isZero();
    assertThat(page.hasNext()).isFalse();
    assertThat(page.hasPrevious()).isFalse();
    assertThat(page.pageCount()).isEqualTo(1);
    assertThat(page.pageSize()).isEqualTo(4);
    assertThat(page.content()).hasSize(3);
  }
}

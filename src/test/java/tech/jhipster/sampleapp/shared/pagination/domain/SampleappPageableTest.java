package tech.jhipster.sampleapp.shared.pagination.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;

import tech.jhipster.sampleapp.UnitTest;
import tech.jhipster.sampleapp.shared.error.domain.NumberValueTooHighException;
import tech.jhipster.sampleapp.shared.error.domain.NumberValueTooLowException;

@UnitTest
class SampleappPageableTest {

  @Test
  void shouldNotBuildWithNegativePage() {
    assertThatThrownBy(() -> new SampleappPageable(-1, 10))
      .isExactlyInstanceOf(NumberValueTooLowException.class)
      .hasMessageContaining("page");
  }

  @Test
  void shouldNotBuildWithPageSizeAtZero() {
    assertThatThrownBy(() -> new SampleappPageable(0, 0))
      .isExactlyInstanceOf(NumberValueTooLowException.class)
      .hasMessageContaining("pageSize");
  }

  @Test
  void shouldNotBuildWithPageSizeOverHundred() {
    assertThatThrownBy(() -> new SampleappPageable(0, 101))
      .isExactlyInstanceOf(NumberValueTooHighException.class)
      .hasMessageContaining("pageSize");
  }

  @Test
  void shouldGetFirstPageInformation() {
    SampleappPageable pageable = new SampleappPageable(0, 15);

    assertThat(pageable.page()).isZero();
    assertThat(pageable.pageSize()).isEqualTo(15);
    assertThat(pageable.offset()).isZero();
  }

  @Test
  void shouldGetPageableInformation() {
    SampleappPageable pageable = new SampleappPageable(2, 15);

    assertThat(pageable.page()).isEqualTo(2);
    assertThat(pageable.pageSize()).isEqualTo(15);
    assertThat(pageable.offset()).isEqualTo(30);
  }
}

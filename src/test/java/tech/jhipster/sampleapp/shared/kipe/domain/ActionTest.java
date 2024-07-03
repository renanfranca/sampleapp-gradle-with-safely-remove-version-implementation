package tech.jhipster.sampleapp.shared.kipe.domain;

import static org.assertj.core.api.Assertions.*;

import tech.jhipster.sampleapp.UnitTest;
import org.junit.jupiter.api.Test;

@UnitTest
class ActionTest {

  @Test
  void shouldGetActionAsToString() {
    assertThat(new Action("act")).hasToString("act");
  }
}

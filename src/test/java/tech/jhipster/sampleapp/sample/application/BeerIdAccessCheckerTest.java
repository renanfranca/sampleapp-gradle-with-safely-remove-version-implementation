package tech.jhipster.sampleapp.sample.application;

import static tech.jhipster.sampleapp.sample.domain.BeersIdentityFixture.*;
import static tech.jhipster.sampleapp.shared.kipe.application.TestAuthentications.*;
import static org.assertj.core.api.Assertions.*;

import tech.jhipster.sampleapp.UnitTest;
import tech.jhipster.sampleapp.shared.kipe.application.AccessContext;
import tech.jhipster.sampleapp.shared.kipe.application.SampleappAuthorizations;
import java.util.List;
import org.junit.jupiter.api.Test;

@UnitTest
class BeerIdAccessCheckerTest {

  private static final BeerIdAccessChecker checker = new BeerIdAccessChecker(
    new SampleappAuthorizations(List.of(new BeersAccessesConfiguration().beersAccesses()))
  );

  @Test
  void shouldNotAuthorizedUnauthorizedAction() {
    assertThat(checker.can(AccessContext.of(admin(), "unauthorized", cloackOfFeathersId()))).isFalse();
  }

  @Test
  void shouldAuthorizedAuthorizedAction() {
    assertThat(checker.can(AccessContext.of(admin(), "create", cloackOfFeathersId()))).isTrue();
  }
}

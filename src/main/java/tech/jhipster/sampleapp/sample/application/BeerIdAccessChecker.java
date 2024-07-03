package tech.jhipster.sampleapp.sample.application;

import tech.jhipster.sampleapp.sample.domain.BeerId;
import tech.jhipster.sampleapp.shared.kipe.application.AccessChecker;
import tech.jhipster.sampleapp.shared.kipe.application.AccessContext;
import tech.jhipster.sampleapp.shared.kipe.application.SampleappAuthorizations;
import org.springframework.stereotype.Component;

@Component
class BeerIdAccessChecker implements AccessChecker<BeerId> {

  private final SampleappAuthorizations authorizations;

  public BeerIdAccessChecker(SampleappAuthorizations authorizations) {
    this.authorizations = authorizations;
  }

  @Override
  public boolean can(AccessContext<BeerId> access) {
    return authorizations.allAuthorized(access.authentication(), access.action(), BeerResource.BEERS);
  }
}

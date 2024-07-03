package tech.jhipster.sampleapp.sample.application;

import tech.jhipster.sampleapp.sample.domain.beer.BeerToCreate;
import tech.jhipster.sampleapp.shared.kipe.application.AccessChecker;
import tech.jhipster.sampleapp.shared.kipe.application.AccessContext;
import tech.jhipster.sampleapp.shared.kipe.application.SampleappAuthorizations;
import org.springframework.stereotype.Component;

@Component
class BeerToCreateAccessChecker implements AccessChecker<BeerToCreate> {

  private final SampleappAuthorizations authorizations;

  public BeerToCreateAccessChecker(SampleappAuthorizations authorizations) {
    this.authorizations = authorizations;
  }

  @Override
  public boolean can(AccessContext<BeerToCreate> access) {
    return authorizations.allAuthorized(access.authentication(), access.action(), BeerResource.BEERS);
  }
}

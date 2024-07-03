package tech.jhipster.sampleapp.sample.domain.order;

import tech.jhipster.sampleapp.sample.domain.Amount;
import tech.jhipster.sampleapp.sample.domain.BeerId;
import tech.jhipster.sampleapp.shared.error.domain.Assert;

public record OrderedBeer(BeerId beer, Amount unitPrice) {
  public OrderedBeer {
    Assert.notNull("beer", beer);
    Assert.notNull("unitPrice", unitPrice);
  }
}

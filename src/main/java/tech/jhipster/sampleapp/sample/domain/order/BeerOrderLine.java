package tech.jhipster.sampleapp.sample.domain.order;

import tech.jhipster.sampleapp.sample.domain.Amount;
import tech.jhipster.sampleapp.shared.error.domain.Assert;

public record BeerOrderLine(OrderedBeer beer, int quantity) {
  public BeerOrderLine {
    Assert.notNull("beer", beer);
    Assert.field("quantity", quantity).min(1);
  }

  Amount amount() {
    return beer().unitPrice().times(quantity());
  }
}

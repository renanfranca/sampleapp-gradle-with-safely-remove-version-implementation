package tech.jhipster.sampleapp.sample.domain.beer;

import tech.jhipster.sampleapp.sample.domain.BeerId;

class UnknownBeerException extends RuntimeException {

  public UnknownBeerException(BeerId id) {
    super("Beer " + id.get() + " is unknown");
  }
}

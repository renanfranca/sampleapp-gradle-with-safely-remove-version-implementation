package tech.jhipster.sampleapp.shared.kipe.domain;

import tech.jhipster.sampleapp.shared.error.domain.Assert;

public record Action(String action) {
  public Action {
    Assert.notBlank("action", action);
  }
  
  @Override
  public String toString() {
    return action();
  }
}

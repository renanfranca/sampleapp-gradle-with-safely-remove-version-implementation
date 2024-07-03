package tech.jhipster.sampleapp;

import static tech.jhipster.sampleapp.cucumber.rest.CucumberRestAssertions.*;

import org.springframework.http.HttpStatus;

import io.cucumber.java.en.Then;

public class HttpSteps {

  @Then("I should be forbidden")
  public void shouldBeForbidden() {
    assertThatLastResponse().hasHttpStatus(HttpStatus.FORBIDDEN);
  }
}

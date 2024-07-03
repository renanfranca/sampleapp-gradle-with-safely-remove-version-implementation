package tech.jhipster.sampleapp.sample.infrastructure.primary.beer;

import static org.assertj.core.api.Assertions.*;

import tech.jhipster.sampleapp.JsonHelper;
import tech.jhipster.sampleapp.UnitTest;
import tech.jhipster.sampleapp.sample.domain.beer.Beers;
import tech.jhipster.sampleapp.sample.domain.beer.BeersFixture;
import java.util.List;
import org.junit.jupiter.api.Test;

@UnitTest
class RestBeersTest {

  @Test
  void shouldSerializeToJson() {
    assertThat(JsonHelper.writeAsString(RestBeers.from(new Beers(List.of(BeersFixture.beer()))))).isEqualTo(json());
  }

  private String json() {
    return "{\"beers\":[" + RestBeerTest.json() + "]}";
  }
}

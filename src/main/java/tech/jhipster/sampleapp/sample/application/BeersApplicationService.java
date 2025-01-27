package tech.jhipster.sampleapp.sample.application;

import tech.jhipster.sampleapp.sample.domain.BeerId;
import tech.jhipster.sampleapp.sample.domain.beer.Beer;
import tech.jhipster.sampleapp.sample.domain.beer.BeerToCreate;
import tech.jhipster.sampleapp.sample.domain.beer.Beers;
import tech.jhipster.sampleapp.sample.domain.beer.BeersCreator;
import tech.jhipster.sampleapp.sample.domain.beer.BeersRemover;
import tech.jhipster.sampleapp.sample.domain.beer.BeersRepository;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

@Service
public class BeersApplicationService {

  private final BeersRepository beers;
  private final BeersCreator creator;
  private final BeersRemover remover;

  public BeersApplicationService(BeersRepository beers) {
    this.beers = beers;

    creator = new BeersCreator(beers);
    remover = new BeersRemover(beers);
  }

  @PreAuthorize("can('create', #beerToCreate)")
  public Beer create(BeerToCreate beerToCreate) {
    return creator.create(beerToCreate);
  }

  @PreAuthorize("can('remove', #beer)")
  public void remove(BeerId beer) {
    remover.remove(beer);
  }

  public Beers catalog() {
    return beers.catalog();
  }

}

package tech.jhipster.sampleapp.account.domain;

import static tech.jhipster.sampleapp.shared.useridentity.domain.UsersIdentitiesFixture.*;

import java.util.List;

import tech.jhipster.sampleapp.shared.authentication.domain.Role;

public final class AccountsFixture {

  private AccountsFixture() {
  }

  public static Account account() {
    return Account.builder()
        .username(username())
        .firstname(firstname())
        .lastname(lastname())
        .email(email())
        .roles(List.of(Role.ADMIN.key()))
        .build();
  }

}

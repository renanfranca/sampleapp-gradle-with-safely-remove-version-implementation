package tech.jhipster.sampleapp.account.infrastructure.secondary;

import static tech.jhipster.sampleapp.account.domain.AccountsFixture.*;
import static tech.jhipster.sampleapp.account.infrastructure.OAuth2TokenFixture.*;
import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

import tech.jhipster.sampleapp.UnitTest;
import java.util.Map;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

@UnitTest
class OAuth2AuthenticationReaderTest {

  private static final SecurityContext context = SecurityContextHolder.getContext();
  private static final OAuth2AuthenticationReader tokens = new OAuth2AuthenticationReader();

  @Test
  void shouldNotReadAccountForNotAuthenticationUser() {
    context.setAuthentication(null);

    assertThat(tokens.authenticatedUserAccount()).isEmpty();
  }

  @Test
  void shouldNotReadAuthenticationFromUnknownAuthenticationType() {
    Authentication authentication = mock(Authentication.class);
    when(authentication.getPrincipal()).thenReturn("hey");

    context.setAuthentication(authentication);

    assertThatThrownBy(tokens::authenticatedUserAccount).isExactlyInstanceOf(UnknownAuthenticationSchemeException.class);
  }

  @Test
  void shouldReadOAuth2Authentication() {
    context.setAuthentication(testAuthenticationToken());

    assertThat(tokens.authenticatedUserAccount().get()).usingRecursiveComparison().isEqualTo(account());
  }

  @Test
  void shouldReadOAuth2AuthenticationWithFirstnameInNameField() {
    Map<String, Object> claims = testAuthenticationClaims();
    claims.remove("given_name");
    claims.put("name", "Paul");

    context.setAuthentication(buildToken(claims));

    assertThat(tokens.authenticatedUserAccount().get()).usingRecursiveComparison().isEqualTo(account());
  }

  @Test
  void shouldReadJwtAuthentication() {
    context.setAuthentication(testJwtAuthenticationToken());

    assertThat(tokens.authenticatedUserAccount().get()).usingRecursiveComparison().isEqualTo(account());
  }
}

import { KeycloakOptions, KeycloakService } from 'keycloak-angular';
import { environment } from '../environment/environment';

export function initializeKeycloak(keycloak: KeycloakService) {
  return () =>
    keycloak.init({
      config: environment.keycloakConfig,
      initOptions: {
        onLoad: 'login-required', // 'login-required' hoặc 'check-sso'
        checkLoginIframe: false,
      },
    });
}

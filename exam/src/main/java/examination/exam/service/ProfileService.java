package examination.exam.service;

import examination.exam.dto.identity.Credential;
import examination.exam.dto.identity.TokenExchangeClientParam;
import examination.exam.dto.identity.TokenExchangeParam;
import examination.exam.dto.identity.UserCreationParam;
import examination.exam.dto.profile.LoginRequest;
import examination.exam.dto.profile.LoginResponse;
import examination.exam.dto.profile.ProfileDto;
import examination.exam.dto.profile.RegistrationRequest;
import examination.exam.entity.profile_aggregate.Profile;
import examination.exam.entity.profile_aggregate.ProfileRepository;
import examination.exam.exception.ErrorNormalizer;
import examination.exam.repository.IdentityClient;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import lombok.experimental.NonFinal;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProfileService {
    private final ProfileRepository profileRepository;

    private final IdentityClient identityClient;
    private final ErrorNormalizer errorNormalizer;

    @Value("${idp.client_id}")
    @NonFinal
    private String clientId;

    @Value("${idp.client_secret}")
    @NonFinal
    private String clientSecret;

    public List<ProfileDto> getAllProfiles() {
        return profileRepository.findAll().stream().map(Profile::getProfileDto).toList();
    }

    public ProfileDto register(RegistrationRequest request){
        try {
            var token = identityClient.exchangeTokenClient(TokenExchangeParam.builder()
                            .client_id(clientId)
                            .client_secret(clientSecret)
                            .grant_type("client_credentials")
                            .scope("openid")
                    .build());

            var response = identityClient.createUser("Bearer "+token.getAccessToken(), UserCreationParam.builder()
                            .username(request.getUserName())
                            .firstName(request.getFirstName())
                            .lastName(request.getLastName())
                            .email(request.getEmail())
                            .enabled(true)
                            .emailVerified(false)
                            .credentials(List.of(Credential.builder().type("password").value(request.getPassword()).temporary(false).build()))
                            .build());

            var profile = Profile.builder()
                    .userName(request.getUserName())
                    .userId(extractUserId(response))
                    .email(request.getEmail())
                    .firstName(request.getFirstName())
                    .lastName(request.getLastName())
                    .build();

            profile = profileRepository.save(profile);
            return profile.getProfileDto();
        }catch (FeignException e){
            throw errorNormalizer.handleKeyCloakException(e);
        }
    }

    private String extractUserId(ResponseEntity<?> response) {
        var headers = response.getHeaders();
        var locationHeader = headers.get("Location");
        if (locationHeader != null) {
            var location = locationHeader.getFirst();
            if (location != null) {
                var splitStr = location.split("/");
                if (splitStr.length > 0) {
                    return splitStr[splitStr.length - 1];
                }
            }
        }
        return null;
    }

    public LoginResponse login(LoginRequest request){
        try{
            var token = identityClient.login(TokenExchangeClientParam.builder()
                            .client_id(clientId)
                            .client_secret(clientSecret)
                            .grant_type("password")
                            .scope("openid")
                            .username(request.getUserName())
                            .password(request.getPassword())
                        .build());

            return LoginResponse.builder()
                    .expiresIn(token.getExpiresIn())
                    .accessToken(token.getAccessToken())
                    .refreshToken(token.getRefreshToken())
                    .build();
        }catch (FeignException e){
            throw errorNormalizer.handleKeyCloakException(e);
        }
    }
}

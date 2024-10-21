package examination.exam.repository;

import examination.exam.dto.identity.*;
import feign.QueryMap;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "identity-client", url = "${idp.url}")
public interface IdentityClient {
    @PostMapping(value = "/realms/exam/protocol/openid-connect/token", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    TokenExchangeResponse exchangeTokenClient(@QueryMap TokenExchangeParam param);
    @PostMapping(value = "/realms/exam/protocol/openid-connect/token", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    TokenExchangeClientResponse login(@QueryMap TokenExchangeClientParam param);
    @PostMapping(value = "/admin/realms/exam/users", consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<?> createUser(@RequestHeader("Authorization") String token, @RequestBody UserCreationParam param);
}

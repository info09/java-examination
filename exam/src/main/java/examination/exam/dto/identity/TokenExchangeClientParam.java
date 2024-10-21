package examination.exam.dto.identity;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TokenExchangeClientParam {
    private String grant_type;
    private String client_id;
    private String client_secret;
    private String scope;
    private String username;
    private String password;
}

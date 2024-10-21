package examination.exam.dto.profile;

import lombok.*;

@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class LoginRequest {
    private String userName;
    private String password;
}

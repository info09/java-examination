package examination.exam.dto.profile;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RegistrationRequest {
    @Size(min = 4, message = "INVALID_USERNAME")
    private String userName;

    @Size(min = 6, message = "INVALID_PASSWORD")
    private String password;

    private String email;

    private String firstName;

    private String lastName;
}

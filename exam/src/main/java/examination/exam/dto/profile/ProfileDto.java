package examination.exam.dto.profile;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProfileDto {
    private String profileId;
    private String userId;
    private String email;
    private String username;
    private String firstName;
    private String lastName;
}

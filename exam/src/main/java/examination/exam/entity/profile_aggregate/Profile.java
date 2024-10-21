package examination.exam.entity.profile_aggregate;

import examination.exam.dto.profile.ProfileDto;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Document("profile")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Profile {
    @MongoId
    private String id;
    private String userId;
    private String email;
    private String userName;
    private String firstName;
    private String lastName;

    public static Profile createUser(String userName, String firstName, String lastName) {
        return Profile.builder().userName(userName).firstName(firstName).lastName(lastName).build();
    }

    public ProfileDto getProfileDto() {
        return ProfileDto.builder()
                .profileId(id)
                .userId(userId)
                .email(email)
                .username(userName)
                .firstName(firstName)
                .lastName(lastName)
                .build();
    }
}

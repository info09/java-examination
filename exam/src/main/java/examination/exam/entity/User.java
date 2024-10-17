package examination.exam.entity;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Document("user")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @MongoId
    private String id;
    private String userName;
    private String firstName;
    private String lastName;

    public static User createUser(String userName, String firstName, String lastName) {
        return User.builder().userName(userName).firstName(firstName).lastName(lastName).build();
    }
}

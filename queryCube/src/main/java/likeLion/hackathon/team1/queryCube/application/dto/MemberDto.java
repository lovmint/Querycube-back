package likeLion.hackathon.team1.queryCube.application.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MemberDto {

    private String username;
    private String password;
    private String googleId;

}




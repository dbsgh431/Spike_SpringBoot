package Spike.springboot.first.dto;

import Spike.springboot.first.entity.Member;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
@ToString
public class MemberForm {

    private Long id;

    private String email;

    private String password;



    public Member toEntity() {
        return new Member(id, email, password);
    }
}

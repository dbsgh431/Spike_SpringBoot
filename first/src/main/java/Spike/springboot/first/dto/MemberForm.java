package Spike.springboot.first.dto;

import Spike.springboot.first.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@Getter @Setter
@ToString
public class MemberForm {
    private String email;

    private String password;


    public Member toEntity() {
        return new Member(null, email, password);
    }
}

package Spike.springboot.first.dto;

import Spike.springboot.first.entity.Member;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
@ToString
public class MemberForm {

    private Long id;
    @NotBlank
    private String email;
    @NotBlank
    private String password;



    public Member toEntity() {
        return new Member(id, email, password);
    }
}

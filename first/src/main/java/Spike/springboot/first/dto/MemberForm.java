package Spike.springboot.first.dto;

import Spike.springboot.first.entity.Member;

public class MemberForm {
    private String email;

    private String password;

    public MemberForm(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public Member toEntity() {
        return new Member(null, email, password);
    }
}

package Spike.springboot.first.controller;

import Spike.springboot.first.dto.MemberForm;
import Spike.springboot.first.entity.Member;
import Spike.springboot.first.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MemberController {

    @Autowired
    private MemberRepository memberRepository;

    @GetMapping("/members/signup")
    public String singup() {
        return "members/signup";
    }

    @PostMapping("/members/join")
    public String join(MemberForm memberForm) {
        System.out.println("MemberController.join");
        Member savedmember = memberForm.toEntity();
        memberRepository.save(savedmember);
        return "members/done";
    }
}

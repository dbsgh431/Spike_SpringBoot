package Spike.springboot.first.controller;

import Spike.springboot.first.dto.MemberForm;
import Spike.springboot.first.entity.Member;
import Spike.springboot.first.repository.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;


@Slf4j
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
       log.info(memberForm.toString());
        Member member = memberForm.toEntity();
        log.info(member.toString());
        Member savedMember = memberRepository.save(member);
        log.info(savedMember.toString());
        return "members/done";
    }
}

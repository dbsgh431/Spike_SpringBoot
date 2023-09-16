package Spike.springboot.first.controller;

import Spike.springboot.first.dto.MemberForm;
import Spike.springboot.first.entity.Member;
import Spike.springboot.first.repository.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;



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
        return "redirect:/members/" + member.getId();
    }

    @GetMapping("/members/{id}")
    public String showid(@PathVariable Long id, Model model) {
        Member foundMember = memberRepository.findById(id).orElse(null);
        log.info("id = {}", id);
        model.addAttribute("member", foundMember);
        return "members/member";
    }

    @GetMapping("/members")
    public String showAll(Model model) {
        List<Member> memberList = memberRepository.findAll();
        model.addAttribute("memberList", memberList);
        return "members/memberlist";
    }

}

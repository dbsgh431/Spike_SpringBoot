package Spike.springboot.first.api.service;


import Spike.springboot.first.dto.MemberForm;
import Spike.springboot.first.entity.Member;
import Spike.springboot.first.repository.MemberRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class MemberService {

    @Autowired
    private final MemberRepository memberRepository;

    @PostConstruct
    public void init() {
        memberRepository.save(new Member(1L, "aaa@naver.com", "1234"));
        memberRepository.save(new Member(2L, "bbb@naver.com", "3434"));
    }

    public Member save(MemberForm form) {
        Member member = form.toEntity();
        return memberRepository.save(member);
    }

    public Member findById(Long id) {
        return memberRepository.findById(id).orElse(null);
    }

    public List<Member> findAll() {
        return memberRepository.findAll();
    }

    public Member update(@PathVariable Long id, @RequestBody MemberForm form) {
        log.info("요청 id={}, 엔티티={}", id, form.toEntity());
        Member member = form.toEntity();
        if (id != member.getId() || id == null) {
            log.info("잘못된 요청입니다. id={}, 엔티티={}", id, form.toEntity());
            return null;
        }
        // 수정 로직
        Member target = memberRepository.findById(id).orElse(null);
        target.patch(member);
        Member save = memberRepository.save(target);
        return save;
    }

    public Member delete(@PathVariable Long id) {
        Member member = memberRepository.findById(id).orElse(null);

        if (member == null) {
            return null;
        }
        memberRepository.delete(member);
        return member;

    }
}

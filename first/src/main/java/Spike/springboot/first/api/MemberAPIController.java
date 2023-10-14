package Spike.springboot.first.api;

import Spike.springboot.first.dto.MemberForm;
import Spike.springboot.first.entity.Member;
import Spike.springboot.first.repository.MemberRepository;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@AllArgsConstructor
public class MemberAPIController {

    // 생성자 자동 주입
    private final MemberRepository memberRepository;

    // 더미 데이터 생성
    @PostConstruct
    public void init() {
        memberRepository.save(new Member(1L, "qwer@gmail.com", "1234"));
        memberRepository.save(new Member(2L, "qwer22@gmail.com", "1234444"));
    }

    @GetMapping("/hello/member")
    public String hello() {
        return "hello : member";
    }

    // 전체 조회
    @GetMapping("/api/members")
    public List<Member> members() {
        return memberRepository.findAll();
    }

    // id로 조회
    @GetMapping("/api/members/{id}")
    public Member findById(@PathVariable Long id) {
        return memberRepository.findById(id).orElse(null);
    }

    // 생성
    @PostMapping("/api/members/create")
    public Member create(@RequestBody MemberForm memberForm) {
        Member member = memberForm.toEntity();
        Member savedMember = memberRepository.save(member);
        return savedMember;
    }

    // 수정
    @PatchMapping("/api/members/{id}")
    public ResponseEntity<Object> updateById(@PathVariable Long id, @RequestBody MemberForm form) {
        log.info("요청 id={}, 엔티티={}", id, form.toEntity());
        Member member = form.toEntity();
        if (id != member.getId() || id == null) {
            log.info("잘못된 요청입니다. id={}, 엔티티={}", id, form.toEntity());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        // 수정 로직
        Member target = memberRepository.findById(id).orElse(null);
        target.update(member);
        Member save = memberRepository.save(target);
        return ResponseEntity.status(HttpStatus.OK).body(save);
    }

    //삭제
    @DeleteMapping("/api/members/{id}")
    public ResponseEntity<Object> deleteById(@PathVariable Long id, @RequestBody MemberForm form) {
        Member member = memberRepository.findById(id).orElse(null);

        if (member == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("잘못된 삭제 요청입니다.");
        }
        memberRepository.delete(member);
        return ResponseEntity.status(HttpStatus.OK).build(); //build == body(null)


    }
}

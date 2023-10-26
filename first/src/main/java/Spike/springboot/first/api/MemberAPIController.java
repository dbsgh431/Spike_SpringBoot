package Spike.springboot.first.api;

import Spike.springboot.first.api.service.MemberService;
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
    private final MemberService memberservice;


    @GetMapping("/hello/member")
    public String hello() {
        return "hello : member";
    }

    // 전체 조회
    @GetMapping("/api/members")
    public List<Member> members() {
        return memberservice.findAll();
    }

    // id로 조회
    @GetMapping("/api/members/{id}")
    public Member findById(@PathVariable Long id) {
        return memberservice.findById(id);
    }

    // 생성
    @PostMapping("/api/members/create")
    public Member create(@RequestBody MemberForm memberForm) {
        return memberservice.save(memberForm);
    }

    // 수정
    @PatchMapping("/api/members/{id}")
    public ResponseEntity<Object> updateById(@PathVariable Long id, @RequestBody MemberForm form) {

        Member updatedMember = memberservice.update(id, form);

        if (updatedMember == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);}

        return ResponseEntity.status(HttpStatus.OK).body(updatedMember);

    }

    //삭제
    @DeleteMapping("/api/members/{id}")
    public ResponseEntity<Object> deleteById(@PathVariable Long id) {
        Member member = memberservice.delete(id);
        if (member == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("잘못된 삭제 요청입니다.");
        }
        return ResponseEntity.status(HttpStatus.OK).build(); //build == body(null)
    }
}

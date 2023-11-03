package Spike.springboot.first.api.service;

import Spike.springboot.first.dto.MemberForm;
import Spike.springboot.first.entity.Member;


import Spike.springboot.first.service.MemberService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

import static org.assertj.core.api.Assertions.*;


@SpringBootTest
class MemberServiceTest {

    @Autowired
    MemberService memberService;

    @Test
    @Transactional
    void save() {

        //given
        String email = "bbb@naver.com";
        String password = "3434";
        MemberForm form = new MemberForm(null, email, password);
        Member expected = new Member(3L, email, password);
        //when
        Member target = memberService.save(form);
        //then
        assertThat(expected.getEmail()).isEqualTo(target.getEmail());
    }

    @Test
    void findById() {
        //given
        Long id = 2L;
        String email = "bbb@naver.com";
        String password = "3434";
        Member saved = new Member(id, email, password);
        //when
        Member target = memberService.findById(id);
        //then
        assertThat(saved.getEmail()).isEqualTo(target.getEmail());
    }

    @Test
    @Transactional
    void findAll() {
        //given
        //when
        List<Member> target = memberService.findAll();
        //then
        assertThat(target.size()).isEqualTo(2);
    }

    @Test
    @Transactional
    void update() {
        //given
        Long id = 2L;
        String email = "bbb@naver.com";
        String password = "3434";
        Member saved = new Member(id, email, password);
        //when
        MemberForm updateForm = new MemberForm(id, "fff@gmail.com", "4444");
        Member target = memberService.update(id, updateForm);
        Member updated = memberService.findById(id);

        //then
        assertThat(updated.getEmail()).isEqualTo(target.getEmail());
    }

    @Test
    @Transactional
    void delete() {
        //given
        Long id = 2L;
        String email = "bbb@naver.com";
        String password = "3434";
        Member saved = new Member(id, email, password);

        //when
        Member deleted = memberService.delete(id);

        //then
        assertThat(deleted.getEmail()).isEqualTo(saved.getEmail());

    }
}
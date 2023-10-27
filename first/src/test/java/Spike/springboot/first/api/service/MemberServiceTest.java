package Spike.springboot.first.api.service;

import Spike.springboot.first.dto.MemberForm;
import Spike.springboot.first.entity.Member;



import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

import static org.assertj.core.api.Assertions.*;


@SpringBootTest
class MemberServiceTest {

    @Autowired
    private MemberService memberService;

    @Test
    @Transactional
    void save() {

        //given
        Long id = 3L;
        String email = "ccc@gmail.com";
        String password = "3333";
        Member saved = memberService.save(new MemberForm(id, email, password));
        //when
        Member target = new Member(id, email, password);
        //then
        assertThat(saved.getEmail()).isEqualTo(target.getEmail());
    }

    @Test
    @Transactional
    void findById() {
        //given
        Long id = 3L;
        String email = "ccc@gmail.com";
        String password = "3333";
        Member saved = memberService.save(new MemberForm(id, email, password));
        //when
        Member target = memberService.findById(id);
        //then
        assertThat(saved.getEmail()).isEqualTo(target.getEmail());
    }

    @Test
    @Transactional
    void findAll() {
        //given
        Long id = 3L;
        String email = "ccc@gmail.com";
        String password = "3333";
        Member saved = memberService.save(new MemberForm(id, email, password));
        //when
        List<Member> target = memberService.findAll();
        //then
        assertThat(target.size()).isEqualTo(3);
    }

    @Test
    @Transactional
    void update() {
        //given
        Long id = 3L;
        String email = "ccc@gmail.com";
        String password = "3333";
        Member saved = memberService.save(new MemberForm(id, email, password));
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
        Long id = 3L;
        String email = "ccc@gmail.com";
        String password = "3333";
        Member saved = memberService.save(new MemberForm(id, email, password));

        //when
        Member deleted = memberService.delete(id);

        //then
        assertThat(deleted.getEmail()).isEqualTo(saved.getEmail());

    }
}
package jpabook.jpashop6.service;

import jpabook.jpashop6.domain.Member;
import jpabook.jpashop6.domain.repository.MemberRepository;
import jpabook.jpashop6.domain.service.MemberService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;


import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class MemberServiceTest {

    @Autowired
    private MemberService memberService;

    @Autowired
    private MemberRepository memberRepository;

    @Test
    void 회원가입() {
        // given
        final Member member = new Member();
        member.setName("kim");

        // when
        final Long saveId = memberService.join(member);

        // then
        assertEquals(member, memberRepository.findOne(saveId));
    }

    @Test
    void 중복_회원_예외() {
        // given
        Member member1 = new Member();
        member1.setName("kim");

        Member member2 = new Member();
        member2.setName("kim");

        // when & then
        memberService.join(member1);
        assertThatThrownBy(() -> memberService.join(member2))
                .isInstanceOf(IllegalStateException.class);
    }


}
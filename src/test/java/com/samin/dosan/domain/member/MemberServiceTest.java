package com.samin.dosan.domain.member;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.samin.dosan.web.back.member.dto.user.UserDto;
import com.samin.dosan.web.back.member.dto.user.UserSaveForm;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class MemberServiceTest {

    @Autowired
    private JPAQueryFactory queryFactory;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private MemberService memberService;
    private QMember member = QMember.member;

    @BeforeEach
    public void init() {
        memberRepository.deleteAll();
    }

    @Test
    void findAllUser() {
        //given
        Member member = Member.builder()
                .userId("test")
                .password("1111")
                .userNm("test1")
                .gender("남")
                .phoneNum("01012345678")
                .build();

        Member member2 = Member.builder()
                .userId("test2")
                .password("1111")
                .userNm("test1")
                .gender("남")
                .phoneNum("01012345678")
                .build();

        //when
        memberRepository.save(member);
        List<UserDto> users = memberService.findAllUser();

        //then
        assertThat(users.size()).isEqualTo(1);
        validateMember(member, users.get(0));

        //when
        memberRepository.save(member2);
        List<UserDto> users2 = memberService.findAllUser();

        //then
        assertThat(users2.size()).isEqualTo(2);

        validateMember(member, users2.get(0));
        validateMember(member2, users2.get(1));
    }

    private void validateMember(Member member, UserDto users) {
        assertThat(users.getUserNm()).isEqualTo(member.getUserNm());
        assertThat(users.getGender()).isEqualTo(member.getGender());
        assertThat(users.getPhoneNum()).isEqualTo(member.getPhoneNum());
    }

    @Test
    void saveTest() {
        //given
        UserSaveForm userSaveForm = new UserSaveForm();
        userSaveForm.setUserId("userId");
        userSaveForm.setUserNm("userNm");
        userSaveForm.setPhoneNum("phone");
        userSaveForm.setGender("MAN");

        //when
        memberService.save(userSaveForm);

        //then
        Member member = memberService.findById(userSaveForm.getUserId());
        assertThat(member.getUserId()).isEqualTo(userSaveForm.getUserId());
        assertThat(member.getUserNm()).isEqualTo(userSaveForm.getUserNm());
        assertThat(member.getPhoneNum()).isEqualTo(userSaveForm.getPhoneNum());
        assertThat(member.getGender()).isEqualTo(userSaveForm.getGender());
    }
}

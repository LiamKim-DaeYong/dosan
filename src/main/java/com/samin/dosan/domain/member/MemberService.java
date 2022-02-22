package com.samin.dosan.domain.member;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.samin.dosan.web.member.dto.user.QUserDto;
import com.samin.dosan.web.member.dto.user.UserDto;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final JPAQueryFactory queryFactory;
    private final ModelMapper modelMapper;
    private QMember member = QMember.member;

    public List<UserDto> findAllUser() {
        return queryFactory.select(new QUserDto(
                    this.member.userNm,
                    this.member.gender,
                    this.member.phoneNum))
                .from(this.member)
                .fetch();
    }

    public <T extends Object> String save(T saveDto) {
        Member member = modelMapper.map(saveDto, Member.class);
        String initPassword = null;

        if (member.getPassword() == null) {
            initPassword = member.initPassword();
        }

        memberRepository.save(member);
        return initPassword;
    }

    public Member findById(String userId) {
        return memberRepository.findById(userId).get();
    }
}
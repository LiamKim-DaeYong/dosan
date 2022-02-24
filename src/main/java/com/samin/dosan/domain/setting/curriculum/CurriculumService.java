package com.samin.dosan.domain.setting.curriculum;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.samin.dosan.domain.type.CurriculumType;
import com.samin.dosan.web.param.SearchParam;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.samin.dosan.domain.setting.curriculum.QCurriculum.curriculum;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CurriculumService {

    private final CurriculumRepository curriculumRepository;
    private final JPAQueryFactory queryFactory;

    public List<Curriculum> findAll(String type, SearchParam searchParam) {
        BooleanBuilder builder = new BooleanBuilder();
        builder.and(curriculum.curriculumType.eq(CurriculumType.valueOf(type.toUpperCase())));

        if (searchParam.getSearchWorld() != null) {
            builder.and(curriculum.subject.contains(searchParam.getSearchWorld())
                    .or(curriculum.content.contains(searchParam.getSearchWorld())));
        }

        return queryFactory.selectFrom(curriculum)
                .where(builder).fetch();
    }

    @Transactional
    public void save(Curriculum curriculum) {
        curriculumRepository.save(curriculum);
    }
}

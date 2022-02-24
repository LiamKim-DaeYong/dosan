package com.samin.dosan.web.setting.curriculum.dto;

import com.samin.dosan.domain.setting.curriculum.Curriculum;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@Getter @Setter
@NoArgsConstructor
public class CurriculumDto {

    private List<CurriculumData> list;

    private String curriculumType;

    public CurriculumDto(List<Curriculum> curriculumList, String curriculumType) {
        this.list = curriculumList.stream()
                .map(curriculum -> new CurriculumData(curriculum)).collect(Collectors.toList());
        this.curriculumType = curriculumType;
    }
}

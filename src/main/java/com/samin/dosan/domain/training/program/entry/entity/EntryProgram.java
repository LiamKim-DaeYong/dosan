package com.samin.dosan.domain.training.program.entry.entity;

import com.samin.dosan.core.domain.BaseEntity;
import com.samin.dosan.domain.training.inquiry_records.InquiryRecords;
import com.samin.dosan.domain.training.inquiry_records.training_target.TargetKey;
import com.samin.dosan.domain.training.inquiry_records.training_target.TrainingTarget;
import com.samin.dosan.domain.user.entity.User;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Entity
@Table(name = "entry_program")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class EntryProgram extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "entry_program_id")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "training_inquiry_record_id")
    private InquiryRecords inquiryRecords;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "manager_id")
    private User manager;

    private Integer totalStudent;

    @Column(columnDefinition = "TEXT")
    private String leaders;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private InputType inputType;

    private Integer groups;

    private Boolean createdForm;

    @OneToMany(mappedBy = "entryProgram", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<EntryProgramSchedule> scheduleList = new ArrayList<>();

    @Column(columnDefinition = "TEXT")
    private String teachers;

    @Column(columnDefinition = "TEXT")
    private String educators;

    @Column(columnDefinition = "TEXT")
    private String etc;

    //================== 연관 관계 메서드 ==================//
    public void addGroups(EntryProgramSchedule entryProgramSchedule) {
        entryProgramSchedule.setEntryProgram(this);
        this.scheduleList.add(entryProgramSchedule);
    }

    //==================   생성 메서드   ==================//
    public static EntryProgram of(InquiryRecords inquiryRecords) {
        EntryProgram entryProgram = new EntryProgram();
        entryProgram.inquiryRecords = inquiryRecords;
        entryProgram.totalStudent = Integer.valueOf(TrainingTarget.getTotal(inquiryRecords.getTrainingTargetList(),
                TargetKey.MAN.getGroup()));
        entryProgram.leaders = inquiryRecords.getContactNm() + "(" + inquiryRecords.getContactPhoneNum() + ")";
        entryProgram.inputType = InputType.DIRECT;
        entryProgram.createdForm = false;

        return entryProgram;
    }

    //==================  비즈니스 로직  ==================//
    public void createdForm(InputType inputType, Integer groups) {
        this.inputType = inputType;
        this.groups = groups;
        this.createdForm = true;

        if (inputType.equals(InputType.DIRECT)) {
            createGroups(groups);
        }
    }

    public void removeForm(InputType inputType) {
        if (inputType.equals(InputType.DIRECT)) {
            this.scheduleList.clear();
        } else {
            /* 파일 업로드 방식 -> 삭제 로직 추가 */
        }
    }

    private void createGroups(Integer groups) {
        LocalDate startDate = this.inquiryRecords.getTrainingStartDate();
        LocalDate endDate = this.inquiryRecords.getTrainingEndDate();

        for (int i = 1; i <= groups; i++) {
            for (LocalDate date = startDate; !date.isAfter(endDate); date = date.plusDays(1)) {
                addGroups(EntryProgramSchedule.of(i, date));
            }
        }
    }

    public void update(EntryProgram updateData) {
        this.manager = updateData.getManager();
        this.totalStudent = updateData.getTotalStudent();
        this.leaders = updateData.getLeaders();
        this.teachers = updateData.getTeachers();
        this.educators = updateData.getEducators();
        this.etc = updateData.getEtc();

        updateChildList(updateData.getScheduleList());
    }

    private void updateChildList(List<EntryProgramSchedule> updateDataList) {
        List<EntryProgramSchedule> resultList = new ArrayList<>();

        updateDataList.stream().forEach(updateData -> {
            EntryProgramSchedule findData = this.scheduleList.stream()
                    .filter(saved -> saved.getId().equals(updateData.getId()))
                    .findFirst().orElse(null);

            if (findData == null) {
                updateData.setEntryProgram(this);
                resultList.add(updateData);
            } else {
                findData.update(updateData);
                resultList.add(findData);
            }
        });

        this.scheduleList.clear();
        this.scheduleList.addAll(resultList);
    }

    //==================   조회 메서드   ==================//
    public List<Groups> getGroupList() {
        List<Groups> resultList = new ArrayList<>();

        for (int i = 1; i <= this.groups; i++) {
            resultList.add(new Groups(i, getGroupScheduleList(i)));
        }

        return resultList;
    }

    private List<GroupSchedule> getGroupScheduleList(Integer groupNum) {
        List<GroupSchedule> resultList = new ArrayList<>();

        LocalDate startDate = this.inquiryRecords.getTrainingStartDate();
        LocalDate endDate = this.inquiryRecords.getTrainingEndDate();

        for (LocalDate date = startDate; !date.isAfter(endDate); date = date.plusDays(1)) {
            final LocalDate compareDate = date;

            List<EntryProgramSchedule> entryProgramScheduleList = this.scheduleList.stream().filter(item -> {
                return item.getGroups().equals(groupNum) && item.getDate().toLocalDate().isEqual(compareDate);
            }).collect(Collectors.toList());

            resultList.add(new GroupSchedule(date, entryProgramScheduleList));
        }

        return resultList;
    }

    @Getter
    static class Groups {
        private Integer groupNum;

        private List<GroupSchedule> groupSchedules;

        public Groups(Integer groupNum, List<GroupSchedule> groupSchedules) {
            this.groupNum = groupNum;
            this.groupSchedules = groupSchedules;
        }
    }

    @Getter
    static class GroupSchedule {

        @DateTimeFormat(pattern = "yyyy년 MM일 dd일")
        private LocalDate date;

        private List<EntryProgramSchedule> entryProgramSchedules;

        public GroupSchedule(LocalDate date, List<EntryProgramSchedule> entryProgramSchedules) {
            this.date = date;
            this.entryProgramSchedules = entryProgramSchedules;
        }
    }
}

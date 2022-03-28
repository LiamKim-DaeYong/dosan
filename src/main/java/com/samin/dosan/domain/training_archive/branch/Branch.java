package com.samin.dosan.domain.training_archive.branch;

import com.samin.dosan.core.code.Used;
import com.samin.dosan.core.domain.BaseEntity;
import com.samin.dosan.core.utils.StrUtils;
import com.samin.dosan.domain.user.entity.User;
import com.samin.dosan.web.dto.training_archive.BranchSave;
import lombok.*;

import javax.persistence.*;

@Getter
@Entity
@Builder
@Table(name = "branch_archive")
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Branch extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(precision = 19, nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private BranchType branchType;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private NoticeYnType noticeYnType;

    @Column(nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT")
    private String content;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Used used;

    /*================== Business Logic ==================*/
    public static Branch of(BranchSave saveData) {
        Branch branch = new Branch();
        branch.user = saveData.getUser();
        branch.used = Used.Y;

        branch.branchType = BranchType.valueOf(StrUtils.urlToEnumName(saveData.getBranchType()));
        branch.noticeYnType = NoticeYnType.valueOf(StrUtils.urlToEnumName(saveData.getNoticeYnType()));
        branch.title = saveData.getTitle();
        branch.content = saveData.getContent();

        return branch;
    }

    public void update(BranchSave updateData) {
        this.noticeYnType = NoticeYnType.valueOf(StrUtils.urlToEnumName(updateData.getNoticeYnType()));
        this.title = updateData.getTitle();
        this.content = updateData.getContent();
    }

    public void delete() {
        this.used = Used.N;
    }
}

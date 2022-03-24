package com.samin.dosan.domain.user.entity;

import com.samin.dosan.core.domain.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor
public class UserEducational extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_educational_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(length = 100, nullable = false)
    private String schoolNm;

    @Column(length = 4, nullable = false)
    private String graduationYear;

    @Column(length = 50)
    private String major;

    @Column(length = 10)
    private String degree;

    /*================== Business Logic ==================*/
    public void setParent(User user) {
        this.user = user;
    }
}

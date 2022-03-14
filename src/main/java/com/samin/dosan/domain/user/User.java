package com.samin.dosan.domain.user;

import com.samin.dosan.core.code.Used;
import com.samin.dosan.core.domain.BaseEntity;
import lombok.*;

import javax.persistence.*;

@Getter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User extends BaseEntity {

    @Id
    @Column(length = 100, nullable = false)
    private String userId;

    @Column(nullable = false)
    private String password;

    @Column(length = 100, nullable = false)
    private String userNm;

    @Column(length = 20, nullable = false)
    private String role;

    @Enumerated(EnumType.STRING)
    private UserType userType;

    @Enumerated(EnumType.STRING)
    @Column(length = 1, nullable = false)
    private Used used;

    /*================== Business Logic ==================*/
    public void updatePassword(String password) {
        this.password = password;
    }
}

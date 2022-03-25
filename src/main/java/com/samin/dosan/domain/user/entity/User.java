package com.samin.dosan.domain.user.entity;

import com.samin.dosan.core.code.Used;
import com.samin.dosan.core.domain.BaseEntity;
import com.samin.dosan.domain.user.Role;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor
@Table(name = "users")
public class User extends BaseEntity {

    @Id
    @Column(length = 100, nullable = false)
    private String userId;

    @Column(nullable = false)
    private String password;

    @Column(length = 100, nullable = false)
    private String userNm;

    @Enumerated(EnumType.STRING)
    @Column(length = 20, nullable = false)
    private Role role;

    @Enumerated(EnumType.STRING)
    @Column(length = 1, nullable = false)
    private Used used;

    /*================== Business Logic ==================*/
    public static User of(String userId, String password, String userNm, Role role) {
        User user = new User();
        user.userId = userId;
        user.password = password;
        user.userNm = userNm;
        user.role = role;
        user.used = Used.Y;

        return user;
    }

    public void updateUserNm(String userNm) {
        this.userNm = userNm;
    }

    public void updatePassword(String password) {
        this.password = password;
    }
}

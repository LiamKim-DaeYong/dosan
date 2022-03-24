package com.samin.dosan.domain.homepage.board;

import com.samin.dosan.core.code.Used;
import com.samin.dosan.domain.homepage.type.BoardType;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

@Getter
@Entity
@Builder
@Table(name = "homepage_board")
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class HomepageBoard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(precision = 19, nullable = false)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private BoardType boardType;

    @Column(nullable = false)
    private Integer hit;

    @Column(length = 100, nullable = false)
    private String author;

    @NotBlank
    @Column(nullable = false)
    private String title;

    @NotBlank
    @Column(columnDefinition="TEXT", nullable = false)
    private String content;

    @Column(nullable = false)
    private LocalDate regDt;

    @Enumerated(EnumType.STRING)
    @Column(length = 1, nullable = false)
    private Used used;

    /*================== Business Logic ==================*/
    public static HomepageBoard of(HomepageBoard saveData, BoardType boardType) {
        HomepageBoard board = new HomepageBoard();
        board.hit = 0;
        board.author = "도산서원 선비문화수련원";
        board.regDt = LocalDate.now();
        board.used = Used.Y;

        board.title = saveData.getTitle();
        board.content = saveData.getContent();
        board.boardType = boardType;

        return board;
    }

    public void update(HomepageBoard updateData) {
        this.title = updateData.getTitle();
        this.content = updateData.getContent();
    }

    public void delete() {
        this.used = Used.N;
    }

    public void hit() {
        this.hit += 1;
    }
}

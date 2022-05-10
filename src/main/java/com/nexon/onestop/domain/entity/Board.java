package com.nexon.onestop.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String title;

    private String content;

    @ManyToOne(fetch = FetchType.EAGER )        // Many=board , User=Category
    @JoinColumn(name="categoryId")              //board 테이블에 Category 테이블을 참조할 수 있는 FOREIGN KEY 자동으로 생성된다.
    private Category category;                  //DB는 오브젝트를 저장할 수 없다.FK,자바는 오브젝트를 저장할 수 있다.

    // @OneToMany(mappedBy = "board" ,fetch = FetchType.EAGER)  //mappedBy 연관관계의 주인이 아니다(FK가 아니다) DB컬럼에 만들지 않아도된다. 명시
    // private List<BoardDetail> boardDetails;

}

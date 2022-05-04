package com.nexon.onestop.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;

@Entity
@Data
@Builder
@AllArgsConstructor
@ToString(exclude = {"delegate"})
@EqualsAndHashCode(of = "id")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class DelegateUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String username;

    @ManyToOne(fetch = FetchType.EAGER )        // Many=board , User=One
    @JoinColumn(name="delegateId")              // board 테이블에 User 테이블을 참조할 수 있는 FOREIGN KEY 자동으로 생성된다.
    private Delegate delegate;                  // DB는 오브젝트를 저장할 수 없다.FK,자바는 오브젝트를 저장할 수 있다.

}

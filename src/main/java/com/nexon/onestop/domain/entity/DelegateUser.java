package com.nexon.onestop.domain.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Data
@Builder
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class DelegateUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne                   // 하나의 게시글에 여러개의댓글을 가질수 있다.
    @JoinColumn(name="delegateId")
    private Delegate delegate;

    @ManyToOne            // 하나의 유저는 여러개 댓글을 작성할 수 있따.
    @JoinColumn(name="accoutId")
    private Account account;
}

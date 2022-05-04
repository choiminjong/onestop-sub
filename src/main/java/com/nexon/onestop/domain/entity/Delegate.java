package com.nexon.onestop.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Builder
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Delegate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String groups;

    @OneToMany(mappedBy = "delegate" ,fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)  //mappedBy 연관관계의 주인이 아니다(FK가 아니다) DB컬럼에 만들지 않아도된다. 명시
    @JsonIgnoreProperties({"delegate"})
    @OrderBy("id desc")
    private List<DelegateUser> delegateUsers;

    @ManyToOne(fetch = FetchType.EAGER )
    @JoinColumn(name="accoutId")
    private Account account;


}

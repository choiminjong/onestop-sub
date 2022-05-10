package com.nexon.onestop.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@ToString(exclude = {"delegate"})
@EqualsAndHashCode(of = "id")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Delegate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String groupname;

    @OneToMany(mappedBy = "delegate")
    private List<DelegateUser> delegateUsers = new ArrayList<>();

    @Builder
    private Delegate(String groupname) {
        this.groupname = groupname;
    }

    @Builder
    private Delegate(Long id,String groupname) {
        this.id = id;
        this.groupname = groupname;
    }

}



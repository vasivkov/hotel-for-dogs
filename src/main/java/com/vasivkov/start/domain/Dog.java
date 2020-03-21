package com.vasivkov.start.domain;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import java.util.Date;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "dogs")
public class Dog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private Integer weight;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner")
    private Owner owner;

    @Column(name = "created_at")
    private Date createdAt;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "dog", cascade = CascadeType.ALL)
    private List<Reserve> reserves;
}

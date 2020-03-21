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
@Table(name = "rooms")
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Float square;
    private Date created_at;
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "room", cascade = CascadeType.ALL)
    private List<Reserve> reserves;

}

package com.flab.yeogiseoja.domain.accommodation.location;

import lombok.*;

import javax.persistence.*;


@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class State {
    @Id
    @Column(name = "state_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;

    public State(String name) {
        this.name = name;
    }
}

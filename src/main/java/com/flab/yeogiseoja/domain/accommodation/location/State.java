package com.flab.yeogiseoja.domain.accommodation.location;

import com.flab.yeogiseoja.domain.AbstractEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class State extends AbstractEntity {
    @Id
    @Column(name = "state_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;

    public State(String name) {
        this.name = name;
    }
}

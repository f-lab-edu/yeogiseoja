package com.flab.yeogiseoja.domain.room.option;


import com.flab.yeogiseoja.domain.room.Room;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Getter
@Entity
@DiscriminatorColumn(name = "ROOMOPTION")
@Inheritance(strategy = InheritanceType.JOINED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class RoomOption {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="room_option_id")
    private long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_id")
    private Room room;
    private long price;
    public RoomOption(Room room,long price){
        this.room=room;
        this.price=price;
    }
}

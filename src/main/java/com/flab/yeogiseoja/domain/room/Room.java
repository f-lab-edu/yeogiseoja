package com.flab.yeogiseoja.domain.room;

import com.flab.yeogiseoja.domain.AbstractEntity;
import com.flab.yeogiseoja.domain.accommodation.Accommodation;
import com.flab.yeogiseoja.domain.room.option.RoomOption;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Room extends AbstractEntity {
    @Id
    @GeneratedValue
    @Column(name = "room_id")
    private long id;
    private String name;
    private String shortDescription;
    private long roomCount;

    @OneToMany(mappedBy = "room", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private final List<RoomNotice> roomNotices = new ArrayList<>();

    @OneToMany(mappedBy = "room", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private final List<RoomOption> roomOptions = new ArrayList<>();

    public Room(String name, String shortDescription, long roomCount) {
        this.name = name;
        this.shortDescription = shortDescription;
        this.roomCount = roomCount;
    }

    public boolean isEqualAccommodation(Accommodation accommodation){

        // TODO: accommodation hashCode와 equals 재정의 하기 
        return accommodation.equals(accommodation);
    }
}

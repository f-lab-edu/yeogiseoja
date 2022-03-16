package com.flab.yeogiseoja.domain.accommodation.room;

import com.flab.yeogiseoja.domain.accommodation.room.option.RoomOption;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Room {
    @Id
    @GeneratedValue
    @Column(name = "room_id")
    private long id;
    private String name;
    private String shortDescription;
    private long roomCount;

    @OneToMany(mappedBy = "room", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<RoomNotice> roomNotices = new ArrayList<>();

    @OneToMany(mappedBy = "room", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<RoomOption> roomOptions = new ArrayList<>();

    public Room(String name, String shortDescription, long roomCount) {
        this.name = name;
        this.shortDescription = shortDescription;
        this.roomCount = roomCount;
    }
}

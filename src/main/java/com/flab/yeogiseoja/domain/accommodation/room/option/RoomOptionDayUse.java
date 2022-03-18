package com.flab.yeogiseoja.domain.accommodation.room.option;


import com.flab.yeogiseoja.domain.accommodation.room.Room;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@Getter
@DiscriminatorValue("DAYUSE")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RoomOptionDayUse extends RoomOption {
    private String maxUsageTime;
    private String availableStartTime;
    private String availableEndTime;

    public RoomOptionDayUse(
            String maxUsageTime,
            String availableStartTime,
            String availableEndTime,
            int price,
            Room room
    ) {
        super(room, price);
        this.maxUsageTime = maxUsageTime;
        this.availableStartTime = availableStartTime;
        this.availableEndTime = availableEndTime;
    }
}

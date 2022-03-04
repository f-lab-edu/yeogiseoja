package com.flab.yeogiseoja.domain.room.option;


import com.flab.yeogiseoja.domain.room.Room;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

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

package com.flab.yeogiseoja.domain.room;

import com.flab.yeogiseoja.common.response.messages.error.ErrorCode;
import com.flab.yeogiseoja.domain.AbstractEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.util.Assert;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RoomNotice extends AbstractEntity {

    @Id
    @GeneratedValue
    @Column(name = "room_notice_id")
    private Long id;

    @Enumerated(EnumType.STRING)
    private RoomNoticeCategory roomNoticeCategory;

    @Column(name = "intermediate_title")
    private String intermediateTitle;

    @ElementCollection
    @CollectionTable(name = "content", joinColumns = @JoinColumn(name = "intermediate_title"))
    private Set<String> contents = new HashSet<>();
    @ManyToOne
    @JoinColumn(name = "room_id")
    private Room room;

    public RoomNotice(
            RoomNoticeCategory roomNoticeCategory,
            String intermediateTitle,
            HashSet<String> contents,
            Room room) {
        Assert.notNull(roomNoticeCategory, ErrorCode.NOTICE_CATEGORY_EMPTY.getErrorMsg());
        this.roomNoticeCategory = roomNoticeCategory;
        this.intermediateTitle = intermediateTitle;
        this.contents = contents;
        this.room = room;
    }

    @Getter
    @RequiredArgsConstructor
    public enum RoomNoticeCategory {
        RESERVATION("예약공지"),
        CANCEL("취소규정");
        private final String description;
    }
}
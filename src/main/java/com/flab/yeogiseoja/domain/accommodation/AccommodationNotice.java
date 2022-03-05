package com.flab.yeogiseoja.domain.accommodation;

import com.flab.yeogiseoja.common.response.messages.error.ErrorCode;
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
public class AccommodationNotice {

    @Id
    @GeneratedValue
    @Column(name = "accommodation_notice_id")
    private Long id;

    @Enumerated(EnumType.STRING)
    private AccommodationNoticeCategory accommodationNoticeCategory;

    @Column(name = "intermediate_title")
    private String intermediateTitle;

    @ElementCollection
    @CollectionTable(name = "content", joinColumns = @JoinColumn(name = "intermediate_title"))
    private Set<String> contents = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "accommodation_id")
    private Accommodation accommodation;

    public AccommodationNotice(
            String intermediateTitle,
            HashSet<String> contents,
            Accommodation accommodation,
            AccommodationNoticeCategory accommodationNoticeCategory
    ) {
        Assert.notNull(accommodationNoticeCategory, ErrorCode.NOTICE_CATEGORY_EMPTY.getErrorMsg());
        this.accommodationNoticeCategory = accommodationNoticeCategory;
        this.intermediateTitle = intermediateTitle;
        this.accommodation = accommodation;
        this.contents = contents;
    }

    @Getter
    @RequiredArgsConstructor
    public enum AccommodationNoticeCategory {
        BASIC("기본 규정"),
        ANNOUNCEMENT("공지사항"),
        CONVENIENCE_FACILITIES_AND_SERVICES("편의 시설 및  서비스");
        private final String description;
    }
}
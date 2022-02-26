package com.flab.yeogiseoja.domain.accommodation.model;

import com.flab.yeogiseoja.common.response.messages.error.ErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.util.Assert;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

// The entity class must have a no-arg constructor.
// The entity class may have other constructors as well.
// The no-arg constructor must be public or protected.
@Entity
public class AccommodationRegulation {

    @Id
    @GeneratedValue
    @Column(name = "accommodationRegulation_id")
    private Long id;

    @Enumerated(EnumType.STRING)
    private AccommodationRegulationCategory accommodationRegulationCategory;

    @Column(name = "intermediateTitle", nullable = false)
    private String intermediateTitle;

    @ElementCollection
    @CollectionTable(name = "content", joinColumns = @JoinColumn(name = "regulation_id"))
    private Set<String> contents = new HashSet<>();

    public AccommodationRegulation(String intermediateTitle, HashSet<String> contents, AccommodationRegulationCategory accommodationRegulationCategory) {
        Assert.notNull(accommodationRegulationCategory, ErrorCode.ACCOMMODATION_REGULATION_EMPTY.getErrorMsg());
        this.accommodationRegulationCategory = accommodationRegulationCategory;
        this.intermediateTitle = intermediateTitle;
        this.contents = contents;
    }

    @ManyToOne
    @JoinColumn(name = "accommodation_id")
    private Accommodation accommodation;

    @Getter
    @RequiredArgsConstructor
    public enum AccommodationRegulationCategory {
        BASIC("기본 규정"),
        ANNOUNCEMENT("공지사항"),
        CONVENIENCE_FACILITIES_AND_SERVICES("편의 시설 및  서비스");
        private final String description;
    }
}

// 도메인 (모델링) : 기능이라 함은 요구사항을 반영한 시스템...

// 고객 - Customer
// 사장님 - Owner
// 이메일 - Email
// 은행 - Bank
// 숙소 - Accommodation

// 예약 
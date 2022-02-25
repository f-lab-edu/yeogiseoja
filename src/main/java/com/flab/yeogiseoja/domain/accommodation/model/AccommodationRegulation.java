package com.flab.yeogiseoja.domain.accommodation.model;

import com.flab.yeogiseoja.common.response.messages.error.ErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.util.Assert;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

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

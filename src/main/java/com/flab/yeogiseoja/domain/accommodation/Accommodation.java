package com.flab.yeogiseoja.domain.accommodation;

import com.flab.yeogiseoja.common.response.messages.error.ErrorCode;
import com.flab.yeogiseoja.domain.accommodation.location.City;
import com.flab.yeogiseoja.domain.accommodation.location.State;
import com.flab.yeogiseoja.domain.owner.Owner;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.util.Assert;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Accommodation {
    @Id
    @GeneratedValue
    @Column(name = "accommodation_id")
    private long id;
    private String name;
    private String contact;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "state_id")
    private State state;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "city_id")
    private City city;
    private String detailAddress;
    private String shortDescription;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id")
    private Owner owner;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;
    @OneToMany(
            mappedBy = "accommodation",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<AccommodationNotice> accommodationNotices = new ArrayList<>();

    public Accommodation(String name, String shortDescription,String contact, State state, City city, Owner owner, Category category, String detailAddress) {
        Assert.hasLength(name, ErrorCode.ACCOMMODATION_NAME_IS_EMPTY.getErrorMsg());
        Assert.hasLength(contact, ErrorCode.CONTACT_IS_EMPTY.getErrorMsg());
        Assert.hasText(detailAddress, ErrorCode.DETAIL_ADDRESS_IS_EMPTY.getErrorMsg());
        Assert.notNull(state, ErrorCode.STATE_IS_EMPTY.getErrorMsg());
        Assert.notNull(city, ErrorCode.CITY_IS_EMPTY.getErrorMsg());
        Assert.notNull(category, ErrorCode.CATEGORY_IS_EMPTY.getErrorMsg());
        this.shortDescription=shortDescription;
        this.name = name;
        this.contact = contact;
        this.state = state;
        this.city = city;
        this.owner = owner;
        this.category = category;
        this.detailAddress = detailAddress;
    }
}

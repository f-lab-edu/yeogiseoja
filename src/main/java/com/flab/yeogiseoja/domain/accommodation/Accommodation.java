package com.flab.yeogiseoja.domain.accommodation;

import com.flab.yeogiseoja.domain.owner.Owner;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

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
    private String businessName;
    private String address;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id")
    private Owner owner;

    @OneToMany(
            mappedBy = "accommodation",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<AccommodationRegulation> accommodationRegulation = new ArrayList<>();

    public Accommodation(String businessName, String address, Owner owner) {
        this.businessName = businessName;
        this.address = address;
        this.owner = owner;
    }
}

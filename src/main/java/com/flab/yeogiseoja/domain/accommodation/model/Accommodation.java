package com.flab.yeogiseoja.domain.accommodation.model;

import com.flab.yeogiseoja.domain.member.model.Owner;
import lombok.Getter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
public class Accommodation {
    @Id
    @GeneratedValue
    @Column(name = "accommodation_id")
    private long id;
    private String businessName;
    private String address;
    @ManyToOne
    @JoinColumn(name = "owner_id")
    private Owner owner;
    @OneToMany(mappedBy = "accommodation", fetch = FetchType.LAZY)
    private Set<AccommodationRegulation> accommodationRegulation;

    public Accommodation(String businessName, String address, Owner owner) {
        this.businessName = businessName;
        this.address = address;
        this.owner = owner;
    }
}

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

        // dto -> command -> init entity (owner_id)  --->

        // Accommodation 를 저장한다고 할 때....
        // AccommodationCommand 안에서... Owner 의 builder 이건 고민해보기

        // var owner = ownerRepository.findById(ownerId) / owner 를 이용해서 accomodation 저장

        this.businessName = businessName;
        this.address = address;
        this.owner = owner;
    }
}

package com.flab.yeogiseoja.domain.owner;

import lombok.Getter;

@Getter
public class OwnerInfo {
    private final String businessLicenseNumber;
    private final String email;
    private final String representationName;

    public OwnerInfo(Owner owner) {
        this.businessLicenseNumber = owner.getBusinessLicenseNumber();
        this.email = owner.getEmail();
        this.representationName = owner.getRepresentationName();
    }
}

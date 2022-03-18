package com.flab.yeogiseoja.infrastructure.owner;

import com.flab.yeogiseoja.domain.owner.Owner;
import com.flab.yeogiseoja.domain.owner.OwnerStore;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class OwnerStoreImpl implements OwnerStore {
    @Override
    public Owner storeOwner(Owner initOwner) {
        return null;
    }
}

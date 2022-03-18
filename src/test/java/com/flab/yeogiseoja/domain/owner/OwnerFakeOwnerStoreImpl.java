package com.flab.yeogiseoja.domain.owner;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;


public class OwnerFakeOwnerStoreImpl implements OwnerStore {
    private final Map<Long, Owner> fakePersistenceContext = new ConcurrentHashMap<>();
    private final AtomicLong idGenerator = new AtomicLong();

    @Override
    public Owner storeOwner(Owner owner) {
        return fakePersistenceContext.put(idGenerator.getAndIncrement(), owner);
    }
}

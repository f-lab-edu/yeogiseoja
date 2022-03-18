package com.flab.yeogiseoja.domain.owner;

import java.util.Optional;

public interface OwnerReader {
    Owner getByEmail(String email);

    Optional<Owner> findByEmail(String email);
}

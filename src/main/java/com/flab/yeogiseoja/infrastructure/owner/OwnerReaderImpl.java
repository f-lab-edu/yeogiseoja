package com.flab.yeogiseoja.infrastructure.owner;

import com.flab.yeogiseoja.domain.owner.Owner;
import com.flab.yeogiseoja.domain.owner.OwnerReader;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Slf4j
@Component
@RequiredArgsConstructor
public class OwnerReaderImpl implements OwnerReader {

    @Override
    public Owner getByEmail(String email) {
        return null;
    }

    @Override
    public Optional<Owner> findByEmail(String email) {
        return Optional.empty();
    }
}

package com.flab.yeogiseoja.infrastructure.owner;

import com.flab.yeogiseoja.domain.owner.OwnerReader;
import com.flab.yeogiseoja.domain.owner.OwnerValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class OwnerValidatorImpl implements OwnerValidator {
    private final OwnerReader ownerReader;

    @Override
    public void checkDuplicateEmail(String ownerEmail) {
        var optOwner = ownerReader.findByEmail(ownerEmail);
        if (optOwner.isPresent()) throw new RuntimeException("이미 가입된 계정입니다");

        // 사업자등록번호

        // ㅁㄴㅇㅁㄴㅇㅁㄴㅇㅁㄴㅇㅁㅇ
    }
}

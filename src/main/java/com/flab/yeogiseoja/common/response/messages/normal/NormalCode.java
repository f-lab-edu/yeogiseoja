package com.flab.yeogiseoja.common.response.messages.normal;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum NormalCode {
    REQUIRE_AUTH("메일로 전송된 인증코드를 입력하셔야 회원가입이 완료됩니다!");

    private final String normalMsg;

    public String getNormalMsg() {
        return normalMsg;
    }
}

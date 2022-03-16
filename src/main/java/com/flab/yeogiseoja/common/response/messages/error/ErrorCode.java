package com.flab.yeogiseoja.common.response.messages.error;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {
    EMAIL_SEND_FAIL("메일 시스템에 문제가 있어 해당 이메일 발송에 실패하였습니다. 잠시 후에 다시 시도해 주세요"),
    NOT_EMAIL_FORMAT("이메일 형식을 확인해주세요"),
    BUSINESS_LICENSE_NUMBER_INCLUDE_STRING("사업자 등록번호에 문자열이 존재합니다"),
    COMMON_SYSTEM_ERROR("일시적인 오류가 발생했습니다. 잠시 후 다시 시도해주세요."),
    EMAIL_IS_EMPTY("이메일을 입력하지 않으셨습니다"),
    DUPLICATE_EMAIL("이메일이 중복되었습니다"),
    CONTACT_IS_EMPTY("연락처를 입력하지 않으셨습니다"),
    ACCOMMODATION_NAME_IS_EMPTY("상호를 입력하지 않으셨습니다"),
    DETAIL_ADDRESS_IS_EMPTY("상세 주소를 입력하지 않으셨습니다"),
    STATE_IS_EMPTY("지역을 입력하지 않으셨습니다"),
    CITY_IS_EMPTY("도시를 입력하지 않으셨습니다"),
    NICKNAME_IS_EMPTY("닉네임을 입력하지 않으셨습니다"),
    NAME_IS_EMPTY("성명을 입력하지 않으셨습니다"),
    PASSWORD_IS_EMPTY("비밀번호를 입력하지 않으셨습니다"),
    UNREGISTER_BANK("전산에 등록되지 않은 은행 입니다"),
    BANK_CODE_IS_EMPTY("은행명을 입력해주세요"),
    ACCOUNT_NUMBER_IS_EMPTY("계좌번호를 입력해주세요"),
    ACCOUNT_HOLDER_IS_EMPTY("계좌 소유주를 입력해주세요"),
    NOTICE_CATEGORY_EMPTY("규정 분류를 입력해주세요"),
    CATEGORY_IS_EMPTY("카테고리를 선택하지 않았습니다"),
    BUSINESS_LICENSE_NUMBER_IS_EMPTY("사업자 등록번호를 입력해주세요");

    private final String errorMsg;

    public String getErrorMsg(Object... arg) {
        return String.format(errorMsg, arg);
    }
}

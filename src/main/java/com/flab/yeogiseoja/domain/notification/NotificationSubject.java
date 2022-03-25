package com.flab.yeogiseoja.domain.notification;

public enum NotificationSubject {

    REQUIRE_AUTH("회원 가입 인증해주세요"),
    CHANGE_PASSWORD("변경된 비밀번호입니다");

    private final String subjectDesc;

    NotificationSubject(String subjectDesc) {
        this.subjectDesc = subjectDesc;
    }

    public String getSubjectDesc() {
        return subjectDesc;
    }
}

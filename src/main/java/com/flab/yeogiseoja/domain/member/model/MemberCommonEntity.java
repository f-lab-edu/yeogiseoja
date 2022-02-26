package com.flab.yeogiseoja.domain.member.model;

import com.flab.yeogiseoja.common.response.messages.error.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.util.Assert;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;

// TODO - MemberCommonEntity 는 제거하고, 각자의 로직으로 다시 옮기자 Customer, Owner 로
@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor
public abstract class MemberCommonEntity {

    private String email;
    @Column(updatable = false)
    private String userName;
    private String password;
    private String phoneNumber;

    @CreationTimestamp
    private LocalDateTime createAt;
    @UpdateTimestamp
    private LocalDateTime updateAt;

    public MemberCommonEntity(
            String email,
            String userName,
            String password,
            String phoneNumber
    ) {
        Assert.hasLength(email, ErrorCode.EMAIL_IS_EMPTY.getErrorMsg());
        Assert.hasLength(userName, ErrorCode.NAME_IS_EMPTY.getErrorMsg());
        Assert.hasLength(password, ErrorCode.PASSWORD_IS_EMPTY.getErrorMsg());

        this.email = email;
        this.userName = userName;
        this.password = password;
        this.phoneNumber = phoneNumber;
    }

    public MemberCommonEntity(String email) {
        this.email = email;
    }

    // TODO - 아래 로직을 주석처리해도 createdAt, updatedAt 에 저장되는지 확인하기
//    @PrePersist
//    public void insertDateInitial() {
//        this.createAt = LocalDateTime.now();
//        this.updateAt = LocalDateTime.now();
//    }
//
//    @PreUpdate
//    public void updateDateInitial() {
//        this.updateAt = LocalDateTime.now();
//    }
}

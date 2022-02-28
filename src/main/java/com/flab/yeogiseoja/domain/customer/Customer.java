package com.flab.yeogiseoja.domain.customer;

import com.flab.yeogiseoja.common.response.messages.error.ErrorCode;
import com.flab.yeogiseoja.domain.owner.Owner;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.util.Assert;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)  // TODO - JPA 가 No-Arg Constructor 를 요구하는 이유 찾기
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // TODO - 전략별로 어떻게 동작하는지?
    @Column(name = "customer_id")
    private long id;
    private String nickName;
    private String authToken;
    private String email;
    private String name;
    private String password;
    private String phoneNumber;
    @CreationTimestamp
    private LocalDateTime createAt;
    @UpdateTimestamp
    private LocalDateTime updateAt;
    @Enumerated(EnumType.STRING)
    private Owner.Status status;
    private LocalDateTime authenticatedAt;


    public Customer(
            String email,
            String nickName,
            String name,
            String password,
            String phoneNumber
    ) {
        Assert.hasLength(nickName, ErrorCode.NICKNAME_IS_EMPTY.getErrorMsg());

        this.email = email;
        this.name = name;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.nickName = nickName;
    }

    public String getAuthTokenForAuthentication() {
        var authToken = UUID.randomUUID().toString();
        this.authToken = authToken;
        return authToken;
    }

    public void confirmAuthToken(String authToken) {
        if (!this.authToken.equals(authToken)) throw new IllegalArgumentException();

        this.authenticatedAt = LocalDateTime.now();
        this.status = Owner.Status.AUTH_COMPLETE;
    }


    @Getter
    @RequiredArgsConstructor
    public enum Status {
        AUTH_NOT_YET("미인증"), AUTH_COMPLETE("인증완료");
        private final String description;
    }
}

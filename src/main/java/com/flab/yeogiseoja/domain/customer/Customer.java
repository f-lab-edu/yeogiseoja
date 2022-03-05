package com.flab.yeogiseoja.domain.customer;

import com.flab.yeogiseoja.common.response.messages.error.ErrorCode;
import com.flab.yeogiseoja.domain.owner.Owner;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.util.Assert;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Entity
/*
   JPA는 Entity객체를 생성할때 "리플렉션"을 사용하여 해당 Entity객체의 기본 생성자를 호출하여
   객체를 생성한다 만약 가본 생성자가 존재 하지 않을 경우에는 JPA는 Entity객체를 생성하지 못할 것이다....
 */
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id")
    private long id;
    private String nickName;
    private String authToken;
    private String email;
    private String name;
    private String password;
    private String phoneNumber;
    @Enumerated(EnumType.STRING)
    private Owner.Status status;
    private LocalDateTime authenticatedAt;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @Builder
    public Customer(
            String email,
            String nickName,
            String name,
            String password,
            String phoneNumber
    ) {
        Assert.hasLength(nickName, ErrorCode.NICKNAME_IS_EMPTY.getErrorMsg());
        Assert.hasLength(email, ErrorCode.EMAIL_IS_EMPTY.getErrorMsg());
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

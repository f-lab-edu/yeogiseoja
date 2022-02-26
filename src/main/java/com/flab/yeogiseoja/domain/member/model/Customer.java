package com.flab.yeogiseoja.domain.member.model;

import com.flab.yeogiseoja.common.response.messages.error.ErrorCode;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.util.Assert;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)  // TODO - JPA 가 No-Arg Constructor 를 요구하는 이유 찾기
public class Customer extends MemberCommonEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // TODO - 전략별로 어떻게 동작하는지?
    @Column(name = "customer_id")
    private long id;
    private String nickName;

    @Embedded
    private EmailAuthentication emailAuthentication;

    public Customer(
            String email,
            String nickName,
            String userName,
            String password,
            String phoneNumber
    ) {
        super(email, userName, password, phoneNumber);
        Assert.hasLength(nickName, ErrorCode.NICKNAME_IS_EMPTY.getErrorMsg());
        this.nickName = nickName;
    }

    public void prepareEmailAuthentication() {
        this.emailAuthentication = EmailAuthentication.prepareAuthentication();
    }

    public void doneEmailAuthentication() {
        this.emailAuthentication = EmailAuthentication.doneAuthentication();
    }

    public boolean isGenerateToken(String token) {
        return this.emailAuthentication.getAuthToken().equals(token);
    }
}

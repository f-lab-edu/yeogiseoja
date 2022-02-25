package com.flab.yeogiseoja.domain.member.model;

import com.flab.yeogiseoja.common.response.messages.error.ErrorCode;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.Assert;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Customer extends MemberCommonEntity {
    @Id
    @GeneratedValue
    @Column(name = "customer_id")
    private long id;
    private String nickName;
    @Embedded
    private EmailAuthentication emailAuthentication;

    public Customer(String email, String nickName, String userName, String password, String phoneNumber) {
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
        return this.emailAuthentication.getEmailAuthenticationToken().equals(token);
    }
}

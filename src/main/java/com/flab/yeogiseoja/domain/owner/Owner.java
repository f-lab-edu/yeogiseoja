package com.flab.yeogiseoja.domain.owner;

import com.flab.yeogiseoja.domain.accommodation.Accommodation;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Owner {

    @Id
    @GeneratedValue
    @Column(name = "owner_id")
    private long id;
    private String businessLicenseNumber;
    private String authToken;
    private String email;
    private String name;
    private String password;
    private String phoneNumber;
    @CreationTimestamp
    private LocalDateTime createAt;
    @UpdateTimestamp
    private LocalDateTime updateAt;
    private LocalDateTime authenticatedAt;
    private LocalDateTime verifiedAt;
    private LocalDateTime deletedAt;
    @OneToMany(
            mappedBy = "owner",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            orphanRemoval = true
    )
    private List<Accommodation> accommodations = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private Status status;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "accountBankCode", column = @Column(name = "deposit_account_code")),
            @AttributeOverride(name = "accountNumber", column = @Column(name = "deposit_account_number")),
            @AttributeOverride(name = "accountHolderName", column = @Column(name = "deposit_account_holder_name"))
    })
    private Account depositAccountInfo;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "accountBankCode", column = @Column(name = "settled_account_coe")),
            @AttributeOverride(name = "accountNumber", column = @Column(name = "settled_account_number")),
            @AttributeOverride(name = "accountHolderName", column = @Column(name = "settled_account_holder_name"))
    })
    private Account settledAccountInfo;

    @Getter
    @RequiredArgsConstructor
    public enum Status {
        AUTH_NOT_YET("미인증"), AUTH_COMPLETE("인증완료"),
        VERIFIED("검증완료"), DELETED("삭제");

        private final String description;
    }

    @Builder
    public Owner(
            String email,
            String businessLicenseNumber,
            String name, String password,
            String phoneNumber, Account depositAccountInfo,
            Account settledAccountInfo
    ) {
        this.email = email;
        this.name = name;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.depositAccountInfo = depositAccountInfo;
        this.settledAccountInfo = settledAccountInfo;
        this.businessLicenseNumber = businessLicenseNumber;
    }

    public String getAuthTokenForAuthentication() {
        var authToken = UUID.randomUUID().toString();
        this.authToken = authToken;
        return authToken;
    }

    public void confirmAuthToken(String authToken) {
        if (!this.authToken.equals(authToken)) throw new IllegalArgumentException();

        this.authenticatedAt = LocalDateTime.now();
        this.status = Status.AUTH_COMPLETE;
    }

    public void verified() {
        if (this.status == Status.DELETED) throw new RuntimeException("한번 삭제된 Owner 는 다시 검증할 수 없습니다");
        if (this.status != Status.AUTH_COMPLETE) throw new RuntimeException();

        this.status = Status.VERIFIED;
        this.verifiedAt = LocalDateTime.now();
    }

    public void deleted() {
        this.status = Status.DELETED;
        this.deletedAt = LocalDateTime.now();
    }
}

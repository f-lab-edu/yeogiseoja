package com.flab.yeogiseoja.domain.customer;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

public class CustomerCommand {
    @Getter
    @Builder
    @ToString
    public static class RegisterCustomerRequest {
        private final String email;
        private final String nickName;
        private final String name;
        private final String password;
        private final String phoneNumber;

        public Customer toEntity() {
            return Customer.builder()
                    .customerName(name)
                    .nickName(nickName)
                    .email(email)
                    .password(password)
                    .phoneNumber(phoneNumber).build();
        }
    }
}

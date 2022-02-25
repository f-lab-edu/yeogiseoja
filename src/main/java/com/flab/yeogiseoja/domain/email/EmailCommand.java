package com.flab.yeogiseoja.domain.email;

import com.flab.yeogiseoja.domain.email.model.Email;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Builder
@Getter
@ToString
public class EmailCommand {
    private final String toEmailAddress;
    private final String emailTitle;
    private final String emailBody;

    public Email toValueObject() {
        return Email.builder()
                .toEmailAddress(toEmailAddress)
                .title(emailTitle)
                .body(emailBody)
                .build();
    }
}

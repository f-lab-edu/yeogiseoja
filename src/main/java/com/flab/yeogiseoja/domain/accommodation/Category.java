package com.flab.yeogiseoja.domain.accommodation;

import com.flab.yeogiseoja.common.response.messages.error.ErrorCode;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.util.Assert;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private Long id;
    @Enumerated(EnumType.STRING)
    private Type type;

    public Category(Type type) {
        Assert.notNull(type, ErrorCode.CATEGORY_IS_EMPTY.getErrorMsg());
        this.type = type;
    }

    @Getter
    public enum Type {
        MOTEL("모텔"), HOTEL_RESORT("호텔/리조트"), RENTAL_COTTAGE_FULL_VILLA("팬션/풀빌바"), GUEST_HOUSE("게스트 하우스");
        private final String description;

        Type(String description) {
            this.description = description;
        }
    }
}

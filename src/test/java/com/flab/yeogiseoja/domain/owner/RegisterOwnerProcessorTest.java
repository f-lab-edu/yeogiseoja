package com.flab.yeogiseoja.domain.owner;

import com.flab.yeogiseoja.common.response.messages.error.ErrorCode;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("사장님 회원가입 테스트")
class RegisterOwnerProcessorTest {
    private static OwnerFakeOwnerStoreImpl ownerStore;

    @BeforeAll
    public static void dependencyInjection() {
        ownerStore = new OwnerFakeOwnerStoreImpl();
        assertNotNull(ownerStore);

    }


    @Nested
    @DisplayName("사장님이 회원 가입시")
    class WhenOwnerJoin {
        @Nested
        @DisplayName("내부 규정된 Email정책 중")
        class EmailPolicy {
            @Test
            @DisplayName("Email 주소를 입력하지 않으면 가입에 실패한다")
            public void emailEmpty() {
                Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
                    Owner owner = OwnerCommand.RegisterOwnerRequest
                            .builder()
                            .email("")
                            .build()
                            .toEntity();
                    ownerStore.storeOwner(owner);
                });
                assertEquals(ErrorCode.EMAIL_IS_EMPTY.getErrorMsg(), exception.getMessage());
            }

            @Test
            @DisplayName("Email 포맷이 맞지 않으면 가입에 실패한다")
            public void emailFormat() {
                Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
                    Owner owner = OwnerCommand.RegisterOwnerRequest
                            .builder()
                            .email("lbs243")
                            .build()
                            .toEntity();
                    ownerStore.storeOwner(owner);
                });
                assertEquals(ErrorCode.NOT_EMAIL_FORMAT.getErrorMsg(), exception.getMessage());
            }
        }

    }
}
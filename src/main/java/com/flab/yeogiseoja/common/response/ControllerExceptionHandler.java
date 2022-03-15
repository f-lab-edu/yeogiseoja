package com.flab.yeogiseoja.common.response;


import com.flab.yeogiseoja.common.response.exception.CustomInternalServerError;
import com.flab.yeogiseoja.common.response.messages.error.ErrorCode;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Slf4j
@ControllerAdvice
public class ControllerExceptionHandler {

    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = IllegalArgumentException.class)
    public CommonResponse onIllegalArgumentException(IllegalArgumentException e) {
        return CommonResponse.fail(e.getMessage());
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(value = CustomInternalServerError.class)
    public CommonResponse onCustomInternalServerError(CustomInternalServerError e) {
        String eventId = MDC.get(CommonHttpRequestInterceptor.HEADER_REQUEST_UUID_KEY);
        log.error("eventId = {}, e = {}, errorMessage = {}  ", eventId, e, e.getMessage());
        return CommonResponse.fail(ErrorCode.COMMON_SYSTEM_ERROR.getErrorMsg());
    }
}

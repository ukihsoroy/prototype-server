package org.ko.sigma.core.exception;

import org.apache.commons.lang3.StringUtils;
import org.ko.sigma.core.support.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
@ResponseBody
public class ExceptionHandle extends ResponseEntityExceptionHandler {

    private final static Logger logger = LoggerFactory.getLogger(ExceptionHandle.class);

    @ExceptionHandler
    public ResponseEntity<Object> generalException(Exception ex, WebRequest request) throws Exception {
        ResponseEntity<Object> responseEntity = this.handleException(ex, request);
        assert responseEntity != null;
        HttpStatus httpStatus = responseEntity.getStatusCode();
        List<String> errorMsg = new ArrayList<>();
        // 自定义异常设定Http异常代码
        if (ex instanceof GeneralException) { //通用异常
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        } else if (ex instanceof ValidateException) { //参数请求异常
            httpStatus = HttpStatus.BAD_REQUEST;
            errorMsg = ((ValidateException) ex).getValidateMsg();
        }
        logger.error(ex.getClass().getName(), ex);
        Response response = new Response<>(Response.FAILED, errorMsg,
                ex instanceof GeneralException ? ((GeneralException) ex).getCode() : httpStatus.value(),
                StringUtils.isNotBlank(ex.getMessage()) ? ex.toString() : ex.getMessage());
        return super.handleExceptionInternal(ex, response, responseEntity.getHeaders(), httpStatus, request);
    }
}

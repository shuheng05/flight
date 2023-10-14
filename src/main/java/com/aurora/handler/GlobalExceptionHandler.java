package com.aurora.handler;

import com.aurora.common.Result;
import com.aurora.constant.MessageConstant;
import com.aurora.exception.AccountNotFoundException;
import com.aurora.exception.BaseException;
import com.aurora.exception.PasswordErrorException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLIntegrityConstraintViolationException;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    /**
     * 捕获业务异常
     */
    @ExceptionHandler
    public Result<String> exceptionHandler(BaseException ex) {
        log.error("异常信息：{}", ex.getMessage());
        return Result.error(ex.getMessage());
    }

    @ExceptionHandler
    public Result<String> exceptionHandler(AccountNotFoundException ex) {
        log.error("异常信息：{}", ex.getMessage());
        return Result.error(ex.getMessage());
    }

    @ExceptionHandler
    public Result<String> exceptionHandler(PasswordErrorException ex) {
        log.error("异常信息：{}", ex.getMessage());
        return Result.error(ex.getMessage());
    }


    /**
     * 处理sql异常
     */
    @ExceptionHandler
    public Result<String> exceptionHandler(SQLIntegrityConstraintViolationException ex) {
        String message = ex.getMessage();
        if (message.contains("Duplicate entry")) {
            //String[] split = message.split(" ");
            //String username = split[2];
           // String msg = username + MessageConstant.ALREADY_EXISTS;
            return Result.error("测试");
        } else {
            return Result.error(MessageConstant.UNKNOWN_ERROR);
        }
    }
}
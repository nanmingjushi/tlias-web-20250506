package org.example.tliasweb.exception;

/* 
    @author nanchao 
    @date 2025/4/22
*/


import lombok.extern.slf4j.Slf4j;
import org.example.tliasweb.controller.LoginController;
import org.example.tliasweb.pojo.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

//全局异常处理器

@RestControllerAdvice
public class GlobalExceptionHandler {

    private static Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);


    @ExceptionHandler
    public Result handlerException(Exception e) {
        log.error("程序出错啦：{}", e.getMessage());
        return Result.error("程序出现异常，请联系管理员");
    }
}

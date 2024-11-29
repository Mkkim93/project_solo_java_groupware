package com.group.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CustomException.class)
    public String handleCustomPassWordException(CustomException customException,
                                                RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("failPassWord", customException.getMessage());
        return "redirect:/board/qna/list";
    }
}

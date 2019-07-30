package com.min.springboot.community.handle;


import com.alibaba.fastjson.JSON;
import com.min.springboot.community.dto.R;
import com.min.springboot.community.error.MyException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@ControllerAdvice
public class GlobalDefaultExceptionHandler {

    @ExceptionHandler(Exception.class)
    public Object handleException(HttpServletRequest request, HttpServletResponse response, Exception e) {

        String contentType = request.getContentType();
        if ("application/json".equals(contentType)) {
            R result;
            if (e instanceof MyException) {
                result = R.error((MyException) e);
            } else {
                result = R.error(-1, e.getMessage());
            }
            try {
                response.setContentType("application/json");
                response.setStatus(200);
                response.setCharacterEncoding("utf-8");
                PrintWriter writer = response.getWriter();
                writer.write(JSON.toJSONString(result));
                writer.close();
            } catch (IOException ioe) {
            }
            return null;

        } else {
            ModelAndView modelAndView = new ModelAndView();
            modelAndView.addObject("msg", e.getMessage());
            modelAndView.addObject("url", request.getRequestURL());
            modelAndView.addObject("stackTrace", e.getStackTrace());
            modelAndView.setViewName("error");
            return modelAndView;
        }

    }

}

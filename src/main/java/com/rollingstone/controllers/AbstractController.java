package com.rollingstone.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;

import com.rollingstone.exceptions.HTTP400Exception;
import com.rollingstone.exceptions.HTTP404Exception;
import com.rollingstone.exceptions.RestAPIExceptionInfo;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.Metrics;

import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.UUID;

/*
http://localhost:8080/stores?error=na
http://localhost:8080/stores?error=null
http://localhost:8080/stores?error=others
http://localhost:8080/stores?error=400
http://localhost:8080/stores?error=404
 */
public abstract class AbstractController implements ApplicationEventPublisherAware {

    public static UUID INSTANCE_ID = UUID.randomUUID();

    public static String SERVICE_HOST = "https://service.walgreens.com";

    protected final Logger log = LoggerFactory.getLogger(this.getClass());

    protected ApplicationEventPublisher eventPublisher;

    protected static final String DEFAULT_PAGE_SIZE = "20";

    protected static final String DEFAULT_PAGE_NUMBER = "0";

    Counter http400ExceptionCounter = Metrics.counter("com.rollingstone.AccountController.HTTP400");

    Counter http404ExceptionCounter = Metrics.counter("com.rollingstone.AccountController.HTTP404");

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(HTTP400Exception.class)
    public @ResponseBody RestAPIExceptionInfo handleBadRequestException(HTTP400Exception ex, WebRequest webRequest, HttpServletRequest request, HttpServletResponse response) {
        log.info(INSTANCE_ID + " Received Bad Request Exception Info for URI :" + SERVICE_HOST+request.getRequestURI() +"?"+ request.getQueryString()+ " Request Path :"+ webRequest.getContextPath() + " Exception :"+ ex.getLocalizedMessage());
        http400ExceptionCounter.increment();
        return new RestAPIExceptionInfo(ex.getLocalizedMessage(), "This Request did not have the correct paramaters");
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(HTTP404Exception.class)
    public @ResponseBody RestAPIExceptionInfo handleResourceNotFoundException(HTTP404Exception ex, WebRequest webRequest, HttpServletRequest request, HttpServletResponse response) {
        log.info(INSTANCE_ID + " Received Resource Not FoundException Info for URI :" + SERVICE_HOST+request.getRequestURI() +"?"+ request.getQueryString()+ " Request Path :"+ webRequest.getContextPath() + " Exception :"+ ex.getLocalizedMessage());

        http404ExceptionCounter.increment();
        return new RestAPIExceptionInfo(ex.getLocalizedMessage(), "This Requested Resource Was Not found");
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(NullPointerException.class)
    public @ResponseBody RestAPIExceptionInfo handleNullPointerException(NullPointerException ex, WebRequest webRequest, HttpServletRequest request, HttpServletResponse response) {
        String exceptionMessage = "";

        String newline = System.getProperty("line.separator");


        StringWriter errors = new StringWriter();
        ex.printStackTrace(new PrintWriter(errors));

        //String stacktrace =
          //      errors.toString().substring(1, errors.toString().indexOf("at",2));

        String stacktrace =
                errors.toString().substring(1, 200);

        log.info("stacktrace :"+stacktrace);

        log.info(INSTANCE_ID + " Received NullPointerException Info for URI :" + SERVICE_HOST+request.getRequestURI() +"?"+ request.getQueryString()
                + " Request Path :"+ webRequest.getContextPath() + " Exception :"+ stacktrace);
        http400ExceptionCounter.increment();
        log.info(" ex.getLocalizedMessage() :" + ex.getLocalizedMessage());
        if (ex.getLocalizedMessage() == null){
            exceptionMessage = stacktrace;
        }else {
            exceptionMessage = ex.getLocalizedMessage();
        }
        return new RestAPIExceptionInfo(stacktrace, "This Request did not have the correct paramaters");
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(Exception.class)
    public @ResponseBody RestAPIExceptionInfo handleException(Exception ex, WebRequest webRequest, HttpServletRequest request, HttpServletResponse response) {
        String exceptionMessage = "";
        log.info(INSTANCE_ID + " Received Exception Info for URI :" + SERVICE_HOST+request.getRequestURI() +"?"+ request.getQueryString()+  " Request Path :"+ webRequest.getContextPath() + " Exception :"+ ex.getLocalizedMessage());
        http400ExceptionCounter.increment();
        log.info(" ex.getLocalizedMessage() :" + ex.getLocalizedMessage());
        if (ex.getLocalizedMessage() == null){
            exceptionMessage = ex.getStackTrace().toString();
        }else {
            exceptionMessage = ex.getLocalizedMessage();
        }
        return new RestAPIExceptionInfo(exceptionMessage, "This Request did not have the correct paramaters");
    }

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher eventPublisher) {
        this.eventPublisher = eventPublisher;
    }

    public static <T> T checkResourceFound(final T resource){
        if (resource ==  null) {
            throw new HTTP404Exception("Resource Not Found");
        }
        return resource;
    }

}
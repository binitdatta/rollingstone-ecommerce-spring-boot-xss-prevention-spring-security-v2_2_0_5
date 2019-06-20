package com.rollingstone.controllers;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.rollingstone.domain.CustomerForm;

@Controller
public class WebController  {

	Logger log = LoggerFactory.getLogger("WebController");
	
  
    @GetMapping("/customer")
    public String showForm(CustomerForm customerForm) {
    	log.info("Invoked showForm");
        return "form";
    }

    @PostMapping("/customer")
    public String checkPersonInfo(CustomerForm customerForm) {
    	log.info("Invoked checkPersonInfo");

        return "/results";

    }
}
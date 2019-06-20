package com.rollingstone.controllers;

import com.rollingstone.domain.Store;
import com.rollingstone.service.StoreService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class StoresController extends AbstractController {

    protected final Logger log = LoggerFactory.getLogger(this.getClass());

    StoreService storeService;

    public StoresController(StoreService storeService){
        this.storeService = storeService;
    }
    @ResponseBody
    @GetMapping("/stores")
    public List<Store> getStores(@RequestParam("error") String error) throws Exception {

        log.info("Reached in Controller");

        return storeService.getStores(error);

    }
}

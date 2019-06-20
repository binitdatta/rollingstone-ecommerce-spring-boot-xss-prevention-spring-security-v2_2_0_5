package com.rollingstone.service;

import com.rollingstone.domain.Store;
import com.rollingstone.exceptions.HTTP400Exception;
import com.rollingstone.exceptions.HTTP404Exception;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class StoreService {

    protected final Logger log = LoggerFactory.getLogger(this.getClass());

    private static List<Store> stores = new ArrayList<Store>();

    static {
        Store store1 = new Store(1,
                "ST_0001","123 Main Street",
                "123", "Deerfield", "IL","60015");

        Store store2 = new Store(2,
                "ST_0002","Lincoln Avenue",
                "789", "SpringField", "IL","63234");

        Store store3 = new Store(3,
                "ST_0003","Main Street",
                "765", "Palo Alto", "IL","94650");

        Store store4 = new Store(4,
                "ST_0004","Chicago Avenue",
                "456", "Naperville", "IL","60565");

        stores.add(store1);
        stores.add(store2);
        stores.add(store3);
        stores.add(store4);


    }

    public List<Store> getStores(String errorFlag) throws Exception {

        List<Store> modifiedStores = new ArrayList<Store>();

        log.info("Reached Service");
        if (StringUtils.isEmpty(errorFlag)){
            log.info("Reached Service No Error");

            return stores;
        }else if (errorFlag.startsWith("null")){
            log.info("Reached Service NPE "+ errorFlag);

            throw new NullPointerException("Thrown From StoreService");
        }
        else if (errorFlag.startsWith("others")){
            log.info("Reached Service Others "+ errorFlag);

            throw new Exception("Thrown From StoreService");
        }
        else if (errorFlag.startsWith("404")){
            log.info("Reached Service Others "+ errorFlag);

            throw new HTTP404Exception("Thrown HTTP404Exception From StoreService");
        }
        else if (errorFlag.startsWith("400")){
            log.info("Reached Service Others "+ errorFlag);

            throw new HTTP400Exception("Thrown HTTP400Exception From StoreService");
        }

        for (Store originalStore: stores){
            Store store = new Store(originalStore.getId(),originalStore.getStoreHourseNumber(),
                    originalStore.getStoreStreetAddress(), originalStore.getStoreNumber(),originalStore.getStoreCity(),
                    originalStore.getStoreState(),originalStore.getStoreZipCode());

            modifiedStores.add(store);

            Store anotherstore = new Store();

            modifiedStores.add(anotherstore);

            for (Store modifiedStore : modifiedStores){
                log.info("Store = "+ modifiedStore.toString());
                log.info("Store City = "+ modifiedStore.getStoreCity().toLowerCase());

            }

        }
        return stores;
    }

}

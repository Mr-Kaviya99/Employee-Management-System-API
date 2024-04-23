package com.developers.serviceApi.util;

import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class Generator {

    //=====================================

    public String generateNewId(String prefix, String lastId){
        String propertyId = prefix+"1";
        if (null != lastId) {
            int i = (Integer.parseInt(lastId.split(prefix)[1])) + 1;
            propertyId = prefix+ i;
        }
        return propertyId;
    }
}

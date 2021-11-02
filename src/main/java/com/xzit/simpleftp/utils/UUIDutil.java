package com.xzit.simpleftp.utils;

import java.util.UUID;

public class UUIDutil {

    public static String getUUID(){
        return UUID.randomUUID().toString().replaceAll("-", "");
    }
}

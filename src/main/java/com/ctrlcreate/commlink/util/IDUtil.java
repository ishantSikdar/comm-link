package com.ctrlcreate.commlink.util;

import java.util.concurrent.atomic.AtomicLong;

public class IDUtil {

    static AtomicLong requestId = new AtomicLong();

    public static String generateRequestId() {
        return String.valueOf(requestId.getAndIncrement());
    }
}

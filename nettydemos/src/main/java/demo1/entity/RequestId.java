package demo1.entity;

import java.util.UUID;

public class RequestId {
    public static String next() {
        return UUID.randomUUID().toString();
    }
}

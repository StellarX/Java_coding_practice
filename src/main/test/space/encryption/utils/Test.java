package space.encryption.utils;

import org.apache.commons.codec.binary.Base64;

import java.util.UUID;

public class Test {
    public static void main(String[] args) {
        String encodingAESKey = Base64.encodeBase64String(UUID.randomUUID().toString().replaceAll("-", "").getBytes());
        System.out.println(encodingAESKey);
    }
}

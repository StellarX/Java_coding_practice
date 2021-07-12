package com.space.encryption.utils;

import java.util.Base64;

/**
 * base64编码解码工具类
 */
public class Base64Utils {
    public static final Base64.Encoder encoder = Base64.getEncoder();
    public static final Base64.Decoder decoder = Base64.getDecoder();

    /**
     * base64 编码
     * @param encodeText 明文
     */
    public static byte[] encoder(byte[] encodeText) {
        return encoder.encode(encodeText);
    }

    /**
     * base64 解码
     * @param decodeText 密文
     */
    public static byte[] decoder(byte[] decodeText) {
        return decoder.decode(decodeText);
    }

}

package com.space.encryption.utils;

import org.apache.commons.codec.binary.Base64;
import javax.crypto.Cipher;
import java.io.ByteArrayOutputStream;
import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

/**
 * @comment: RSA密码加密工具类
 */
public class RSAUtils {
    public static final String ALGORITHM_NAME = "RSA";
    public static final String MD5_RSA = "MD5withRSA";
    /**
     * RSA最大加密明文大小
     */
    private static final int MAX_ENCRYPT_BLOCK = 117;

    /**
     * RSA最大解密密文大小
     */
    private static final int MAX_DECRYPT_BLOCK = 128;

    /**
     * 获取密钥对
     *
     * @return java.security.KeyPair
     * @author zhouxinlei
     * @date 2019-09-12 15:25:55
     */
    public static KeyPair getKeyPair() throws Exception {
        KeyPairGenerator generator = KeyPairGenerator.getInstance(ALGORITHM_NAME);
        generator.initialize(1024);
        return generator.generateKeyPair();
    }

    /**
     * 获取私钥
     *
     * @param privateKey 私钥字符串
     * @return java.security.PrivateKey
     * @author zhouxinlei
     * @date 2019-09-12 15:26:15
     */
    public static PrivateKey getPrivateKey(String privateKey) throws Exception {
        KeyFactory keyFactory = KeyFactory.getInstance(ALGORITHM_NAME);
        byte[] decodedKey = Base64Utils.decoder(privateKey.getBytes());
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(decodedKey);
        return keyFactory.generatePrivate(keySpec);
    }

    /**
     * 获取公钥
     *
     * @param publicKey 公钥字符串
     * @return java.security.PublicKey
     * @author zhouxinlei
     * @date 2019-09-12 15:26:40
     */
    public static PublicKey getPublicKey(String publicKey) throws Exception {
        KeyFactory keyFactory = KeyFactory.getInstance(ALGORITHM_NAME);
        byte[] decodedKey = Base64Utils.decoder(publicKey.getBytes());
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(decodedKey);
        return keyFactory.generatePublic(keySpec);
    }

    /**
     * RSA加密
     *
     * @param data      待加密数据
     * @param publicKey 公钥
     * @return java.lang.String
     * @author zhouxinlei
     * @date 2019-09-12 15:27:07
     */
    public static String encrypt(String data, PublicKey publicKey) throws Exception {
        Cipher cipher = Cipher.getInstance(ALGORITHM_NAME);
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        int inputLen = data.getBytes().length;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int offset = 0;
        byte[] cache;
        int i = 0;
        // 对数据分段加密
        while (inputLen - offset > 0) {
            if (inputLen - offset > MAX_ENCRYPT_BLOCK) {
                cache = cipher.doFinal(data.getBytes(), offset, MAX_ENCRYPT_BLOCK);
            } else {
                cache = cipher.doFinal(data.getBytes(), offset, inputLen - offset);
            }
            out.write(cache, 0, cache.length);
            i++;
            offset = i * MAX_ENCRYPT_BLOCK;
        }
        byte[] encryptedData = out.toByteArray();
        out.close();
        // 获取加密内容使用base64进行编码,并以UTF-8为标准转化成字符串
        // 加密后的字符串
        return new String(Base64Utils.encoder(encryptedData));
    }

    /**
     * RSA解密
     *
     * @param data       待解密数据
     * @param privateKey 私钥
     * @return java.lang.String
     * @author zhouxinlei
     * @date 2019-09-12 15:27:29
     */
    public static String decrypt(String data, PrivateKey privateKey) throws Exception {
        Cipher cipher = Cipher.getInstance(ALGORITHM_NAME);
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        byte[] dataBytes = Base64.decodeBase64(data);
        int inputLen = dataBytes.length;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int offset = 0;
        byte[] cache;
        int i = 0;
        //对数据分段解密
        while (inputLen - offset > 0) {
            if (inputLen - offset > MAX_DECRYPT_BLOCK) {
                cache = cipher.doFinal(dataBytes, offset, MAX_DECRYPT_BLOCK);
            } else {
                cache = cipher.doFinal(dataBytes, offset, inputLen - offset);
            }
            out.write(cache, 0, cache.length);
            i++;
            offset = i * MAX_DECRYPT_BLOCK;
        }
        byte[] decryptedData = out.toByteArray();
        out.close();
        // 解密后的内容
        return new String(decryptedData, "UTF-8");
    }

    /**
     * 签名
     *
     * @param data       待签名数据
     * @param privateKey 私钥
     * @return java.lang.String
     * @author zhouxinlei
     * @date 2019-09-12 15:24:08
     */
    public static String sign(String data, PrivateKey privateKey) throws Exception {
        byte[] keyBytes = privateKey.getEncoded();
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(ALGORITHM_NAME);
        PrivateKey key = keyFactory.generatePrivate(keySpec);
        Signature signature = Signature.getInstance(MD5_RSA);
        signature.initSign(key);
        signature.update(data.getBytes());
        return new String(Base64Utils.encoder(signature.sign()));
    }

    /**
     * 验签
     *
     * @param srcData   原始字符串
     * @param publicKey 公钥
     * @param sign      签名
     * @return boolean 是否验签通过
     * @author zhouxinlei
     * @date 2019-09-12 15:23:38
     */
    public static boolean verify(String srcData, PublicKey publicKey, String sign) throws Exception {
        byte[] keyBytes = publicKey.getEncoded();
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(ALGORITHM_NAME);
        PublicKey key = keyFactory.generatePublic(keySpec);
        Signature signature = Signature.getInstance(MD5_RSA);
        signature.initVerify(key);
        signature.update(srcData.getBytes());
        return signature.verify(Base64Utils.decoder(sign.getBytes()));
    }

    public static void main(String[] args) {
        try {
            // 生成密钥对
            KeyPair keyPair = getKeyPair();
//            String privateKey = new String(Base64Utils.encoder(keyPair.getPrivate().getEncoded()));
//            String publicKey = new String(Base64Utils.encoder(keyPair.getPublic().getEncoded()));
            String privateKey = "MIICeAIBADANBgkqhkiG9w0BAQEFAASCAmIwggJeAgEAAoGBAJvX3i95cmybxxebH1QpwuB2N1j1BDTSTHltkNeN6Gy8Hgop353f4cmvcdCzxQ7uq9BYxy6GkKFizsE0RxFM59pWNc67yomnXjuv47GbOfLnTciZ+jpucxrcxRyN3va/2IV3wxmqaUt9j03QJ4g2UdLp+Lvc574jGPAZ+6TKuIqlAgMBAAECgYB5j3iQL3tBnIE/uJBHZgvMamnhybzuNtvQG9bPTLHvnw0PgHoc+3VzjJaK5K7hZuo+KgG0GOLX/+qmadDFfeea/8Xk/XGji6VlFMXFdHYYp6nX9UpB+HBH3DkBM+M+HvxquElm87oNrcIyjvxvHjdp474ETArBJ5XTiC8ALqO0AQJBAM3D935Tts4K8dF5SRu9VVQMMfRkjCUO8/9c/i7wWxCmJu3a3sf/llsD6zJp7QXqP4uX67yIdZc2IGQWVzX93bUCQQDB49XH96j1AP8Po2f3S9VvBvBPrewhPLqNmQIFpnxDnbA+Kw7o+mgYcwaL4LXlxnvBiJhPFwZFa8UQWCkixY8xAkEAyRMmiMlvy3uAga4fFj934fEk4+lZ1mD9aV9LeT0iEGv9gNQwfoqyOHDk5tgCapRV0qj8EbncB486KfR29ukFPQJBALGjmF4ZXcb7HrSpNbuw0t9iMBIQeuMIzLKmmN+sZPVeJ/cNZB+4LF8aI8lMG9tlOkVabJEpuyknT+llnGqrLkECQQC8LJCZ71akOn4KYnVY8Eqa2UK5siBb48hnln/14bh0VrgzY3+8PqHAEeg+gCehcqK62BVAXm4S9QJr+B7hl55d";
            String publicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCb194veXJsm8cXmx9UKcLgdjdY9QQ00kx5bZDXjehsvB4KKd+d3+HJr3HQs8UO7qvQWMcuhpChYs7BNEcRTOfaVjXOu8qJp147r+Oxmzny503Imfo6bnMa3MUcjd72v9iFd8MZqmlLfY9N0CeINlHS6fi73Oe+IxjwGfukyriKpQIDAQAB";
            System.out.println("private key:" + privateKey);
            System.out.println("public key:" + publicKey);
            // RSA加密
            String data = "1234";
            String encryptData = encrypt(data, getPublicKey(publicKey));
            System.out.println("after encrypt:" + encryptData);
            // RSA解密
            String decryptData = decrypt(encryptData, getPrivateKey(privateKey));
            System.out.println("after decrypt:" + decryptData);
            // RSA签名
            String sign = sign(data, getPrivateKey(privateKey));
            // RSA验签 todo
            boolean result = verify(data, getPublicKey(publicKey), sign);
            System.out.print("verify signature:" + result);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.print("加解密异常");
        }
    }

}

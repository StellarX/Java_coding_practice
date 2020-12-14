package space.encryption.utils;

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
            String privateKey = "MIICeQIBADANBgkqhkiG9w0BAQEFAASCAmMwggJfAgEAAoGBAI1GEm+ldIOGBBvdBvlfaffwdtQ4ns2tBdu7cQOSFIDzlaTlg4voS85Xk5k+pvuWjIwMBjpGo0DaD8a6oMBqfc+mVlqwoh3CSvfN5mZ/uNSESZ/UOKyKyUDW581nGGp1+DtG85DvnC2eSHa+Bw0ansoMXXwu4jUe6lkV8+IlFEAlAgMBAAECgYEAg494xb34Ji9cjjIkN4DSZ1Ho5zMfN/xkEz+YPDICpB+zAM+31EdsqOjHjBNbX7nQgu8Dsatsho1L2c6cuvOw2pWDvjg6UebjRZVcsqtOneSJ4KOEBmASRIzrdDnywe/CZ3GvaBNJ+v1VmOiHnLF9cdYc/54m4PMVSEX0N8iE8yECQQDWvhBGYdt1irLuX7iREYv3u2sOftkoDXyGAsgg/eqCGqtIEnau6oCv7Lkgj/CmfPVZSkIeuV1RyTbek+LwdD2JAkEAqGqCAqAnt8M/2X0oVrdAWRSYif38/t/WdPFhpkxQs9vavASnF25/fE8n6N+Y8ca8T1Dc/R5Dh/WtHFsHBFnCvQJBAIO0kIDKVfJSfgnLdYSr8V+geDmsvP1ewCflHt91PFZNiw2qHVaMhx7fHnAFOEC0VTpNXB5B7CVUNq3un/qAIJkCQQCLo7vmd2pXKHHRX7/Jnqmg8r3DdK+HmIqVbeqcWxxy9LC5QlZUzE6yAGPD51lF5pUOYqrIyIYPvfjp0OPRjKc1AkEA0bzDpIHM+x6pNOSsJtingeieouMCwEncouGg8PIWhkgZBXiq2s2wbtA1+805jLkXiaiDWIwQMSOk48Xthy4rng==";
            String publicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDet7xWZtSnLcZkrCH1NRF1okaugAvwav3JBUsuxLRrH1X9FUyjRSZUBnVP+7ehf/IeCiUGoNpSZN210dzWCMa3mUdRQtw+B1hpNm9K280gVDuoqwj94D2z5t+N10CrO157sie7sIu8yhjavC47V3jqABA0HvDNMV1XW3xgVL3nKwIDAQAB";
            System.out.println("私钥:" + privateKey);
            System.out.println("公钥:" + publicKey);
            // RSA加密
            String data = "stu";
            String encryptData = encrypt(data, getPublicKey(publicKey));
            System.out.println("加密后内容:" + encryptData);
            // RSA解密
            String decryptData = decrypt(encryptData, getPrivateKey(privateKey));
            System.out.println("解密后内容:" + decryptData);
            // RSA签名
            String sign = sign(data, getPrivateKey(privateKey));
            // RSA验签
            boolean result = verify(data, getPublicKey(publicKey), sign);
            System.out.print("验签结果:" + result);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.print("加解密异常");
        }
    }

}

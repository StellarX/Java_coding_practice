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
            String privateKey = "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAKcegglU6V3yV7eVE2qoDNsa1eJr1RtWSbmic0PpEWFqlW5cU/9QUKGG9xiokVHNcS40NPlE32XixsHodDRE2hmDd+M1jhcQi7NPFIpN8+TkcAbcGjhlXOpMN2/XWQewgvUFQ1e9f62P8PGZNFT037P2iDDQVmfm25ZwCeoWki3PAgMBAAECgYB5hxv1AUR7RwI2xDfMdcxzgP5MmzHroUIXtmSv28sB1mo4SPfkUFlyPR1vVxXmMMm/WcO0CTuTcegrloj6gZ8/hCt1cOT1xCN5sB0vc/bj+hS+ZLEVLBuKpGq8I0RCWAOazeOHRz27JD1Jreuu+W5ME6hgCitFMgmYrIvAR4SKoQJBAOQ9woBq+liWMa+b/RGD6Q53xApV0B/Nk4RzGDxkNq9qmymAbANr73zuRk3A0cRMVHEBGLz+H99031iul3nQoecCQQC7cbnZo/O0sng5jlzdLO+HlVtAo9Wp2A5GTcmyvVnvj/5neZ/VKoQaosPQo2Pgx204LJWIKNT+i2kCQTkXI2fZAkEAsZjI5snXIOVFpiKXqXBeAU758RzSxFxfQ/mWyy8jSh2GvKVb5tGu2SRC+i9mSfsm3YN/71dCD8tOGyBHZ5cK0wJBAIVeJ6QpOW9qRIx/NKfSHyPTKzhU2eOc7JstIcPhYipHw/WP3h9nvtLM2JKMjrYSby222zYkB1iNjMuj6KED+hECQBewQIqTR4T6wX9lHoUkzewTFJQZXktDU97HcArRRdmOAJOYGoPznDfutrnAVJMx2qb0FdvhxlvS9X+YGdo8dNc=";
            String publicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCnHoIJVOld8le3lRNqqAzbGtXia9UbVkm5onND6RFhapVuXFP/UFChhvcYqJFRzXEuNDT5RN9l4sbB6HQ0RNoZg3fjNY4XEIuzTxSKTfPk5HAG3Bo4ZVzqTDdv11kHsIL1BUNXvX+tj/DxmTRU9N+z9ogw0FZn5tuWcAnqFpItzwIDAQAB";
            System.out.println("私钥:" + privateKey);
            System.out.println("公钥:" + publicKey);
            // RSA加密
            String data = "1234";
            String encryptData = encrypt(data, getPublicKey(publicKey));
            System.out.println("加密后内容:" + encryptData);
            // RSA解密
            String decryptData = decrypt(encryptData, getPrivateKey(privateKey));
            System.out.println("解密后内容:" + decryptData);
            // RSA签名
            String sign = sign(data, getPrivateKey(privateKey));
            // RSA验签 todo
            boolean result = verify(data, getPublicKey(publicKey), sign);
            System.out.print("验签结果:" + result);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.print("加解密异常");
        }
    }

}

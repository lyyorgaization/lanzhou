package com.lucien.dap.framework.core.utils;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName RSAUtil
 * @Description
 * @Author MALUHAN
 * @Date 2019/5/9 13:52
 * @Version 1.0
 */
public class RSAUtil {

    private static Map<String, String> keyMap = new HashMap<String, String>();  //用于封装随机产生的公钥与私钥

    public static void main(String[] args) throws Exception {
//        System.out.println(System.currentTimeMillis());
//        System.out.println(UUID.randomUUID());
        //生成公钥和私钥
//        Map<String, String> keyMap = genKeyPair();
//        //加密字符串
//        String message = "testapikey";
////        keyMap.put(0, "");
//        System.out.println("随机生成的公钥为:" + keyMap.get("publicKey"));
//        System.out.println("随机生成的私钥为:" + keyMap.get("privateKey"));
//        String messageEn = encryptByPrivateKey(keyMap.get("privateKey"), message);
//        System.out.println(message + "\t加密后的字符串为:" + messageEn);
//        String messageDe = decryptByPublicKey(keyMap.get("publicKey"), messageEn);
//        System.out.println("还原后的字符串为:" + messageDe);
//
        String privateKey = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAIODqPxOgq7PhVAkNC97dbgLsdYmvDj6AB8G3f5Ug+QdpbgNzAtccwgW35AMJi/0peMEScvXAINg9utgUYf5Kg8n/7dmJoWRy38l18SFxTez27KTnpF1ZBXh1lTYEfTz+o2N1/hY2oKEKymw8Ac6wYeb+gCSNSaHuoLYnhyLpH+TAgMBAAECgYBMed1kissXmHlvdv4ZtnNoCPTrES9W+pnjcPzajo77sfnSI8PJv4Ls61Ap1cbHED7+2lmHfYlA3Lv5d9MxfMTll0LbZ4+pYxfmFUrqtlfhLJXYwSazoFAZsSlL+ArJqQ/juReOZliRTWAhxwjmoLuR9MLiaMPpMloiYNaPhEHdsQJBAPuM5WFw9cBlYSAODxpWyx2mOiqlkf28b4A0hMzeLTm092/Xl2WZF8+Xu1TKHg4i5Yj6z9ss2hKkQ2b6Ofdwv78CQQCF1zNgpljkNmAkAm4qnjAnZ79u38n9d57Zc0s36H5ceR3zJjpaSz1xpcZ4juEP6H4zef1o652aDO6/fQ5e7/UtAkEAkbCv5XisDMtqrksWOjYMm9Z7l9mfLWsm1QUV6SXA4cxLBw7Bk8lQPh5uFBSfxaY2dNh27EUU5HkYxJqmWgaGNwJAHobrLQxEEilk3FHBlu7lxNchDsT0aV+zV524dlQNHkJx1lfNz3KB3T4WesD8DIjW4B7e8eNr2wtAGWUItha9MQJAb2emw1wsNhHfhU9mqkqk1E3KiPHiiL2fZG5fztAklbeDs7ZX1noLB9Oz8bXvIzIF8hmPcgGsAAeADfDPgMpbpg==";
        String publicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCDg6j8ToKuz4VQJDQve3W4C7HWJrw4+gAfBt3+VIPkHaW4DcwLXHMIFt+QDCYv9KXjBEnL1wCDYPbrYFGH+SoPJ/+3ZiaFkct/JdfEhcU3s9uyk56RdWQV4dZU2BH08/qNjdf4WNqChCspsPAHOsGHm/oAkjUmh7qC2J4ci6R/kwIDAQAB";
        String content = "AmYvEuFst6hXr587hSj1a1KusApyPxWJr3pm5SWucYzmdBScwiBaou8SUm2Y+2YdPj+40s0doGebXfzOC/Gy0BmAbf7WjRIAgl3IOaFOY7CYfZdNSflvs7xmNIutavqrjzuQv9Cfu6/1TDMYRJfRFQ6cvwdn8K03TKl71+n8z6M=" ;
        //System.out.println(decryptByPublicKey(publicKey, content));
//        System.out.println(encryptByPrivateKey(privateKey, "{\"expireSeconds\":\"21600\",\"aes\":\"AT7zYELZbgdcBrddLVGZQg==\",\"token\":\"36b64fce1b8244b1bd3bc9ce8f8b483e\"}"));
        String s = decryptByPublicKey(publicKey, content);
        System.out.println(s);
    }

    /**
     * 随机生成密钥对
     *
     * @throws NoSuchAlgorithmException
     */
    public static Map<String, String> genKeyPair() throws NoSuchAlgorithmException {
        // KeyPairGenerator类用于生成公钥和私钥对，基于RSA算法生成对象
        KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance("RSA");
        // 初始化密钥对生成器，密钥大小为96-1024位
        keyPairGen.initialize(1024, new SecureRandom());
        // 生成一个密钥对，保存在keyPair中
        KeyPair keyPair = keyPairGen.generateKeyPair();
        RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();   // 得到私钥
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();  // 得到公钥
        String publicKeyString = new String(Base64.encodeBase64(publicKey.getEncoded()));
        // 得到私钥字符串
        String privateKeyString = new String(Base64.encodeBase64((privateKey.getEncoded())));
        // 将公钥和私钥保存到Map
        keyMap.put("publicKey", publicKeyString);  //publicKey表示公钥
        keyMap.put("privateKey", privateKeyString);//privateKey表示私钥

        return keyMap;
    }

    /**
     * RSA公钥加密
     *
     * @param str       加密字符串
     * @param publicKey 公钥
     * @return 密文
     * @throws Exception 加密过程中的异常信息
     */
    public static String encryptByPublicKey(String publicKey, String str) throws Exception {
        //base64编码的公钥
        byte[] decoded = Base64.decodeBase64(publicKey);
        RSAPublicKey pubKey = (RSAPublicKey) KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(decoded));
        //RSA加密
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, pubKey);
        return Base64.encodeBase64String(cipher.doFinal(str.getBytes(StandardCharsets.UTF_8)));
    }

    /**
     * RSA私钥解密
     *
     * @param str        加密字符串
     * @param privateKey 私钥
     * @return 铭文
     * @throws Exception 解密过程中的异常信息
     */
    public static String decryptByPrivateKey(String privateKey, String str) throws Exception {
        //64位解码加密后的字符串
        byte[] inputByte = Base64.decodeBase64(str.getBytes(StandardCharsets.UTF_8));
        //base64编码的私钥
        byte[] decoded = Base64.decodeBase64(privateKey);
        RSAPrivateKey priKey = (RSAPrivateKey) KeyFactory.getInstance("RSA").generatePrivate(new PKCS8EncodedKeySpec(decoded));
        //RSA解密
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, priKey);
        return new String(cipher.doFinal(inputByte));
    }

    /**
     * 使用私钥加密数据
     * 用一个私钥加密数据，即数字签名
     *
     * @param privateKey 私钥
     * @param data       要签名的数据，一般应是数字摘要
     * @return 签名 byte[]
     */
    public static String encryptByPrivateKey(String privateKey, String data) throws Exception {
        //base64编码的私钥
        byte[] decoded = Base64.decodeBase64(privateKey);
        RSAPrivateKey rsaPrivateKey = (RSAPrivateKey) KeyFactory.getInstance("RSA").generatePrivate(new PKCS8EncodedKeySpec(decoded));
        //RSA加密
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, rsaPrivateKey);
        return Base64.encodeBase64String(cipher.doFinal(data.getBytes(StandardCharsets.UTF_8)));
    }

    /**
     * 用RSA公钥解密
     *
     * @param publicKey 公钥
     * @param data      要解密的数据
     * @return 解密数据
     */
    public static String decryptByPublicKey(String publicKey, String data) throws Exception {
        //64位解码加密后的字符串
        byte[] inputByte = Base64.decodeBase64(data.getBytes(StandardCharsets.UTF_8));
        //base64编码的公钥
        byte[] decoded = Base64.decodeBase64(publicKey);
        RSAPublicKey priKey = (RSAPublicKey) KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(decoded));
        //RSA解密
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, priKey);
        return new String(cipher.doFinal(inputByte));
    }
}

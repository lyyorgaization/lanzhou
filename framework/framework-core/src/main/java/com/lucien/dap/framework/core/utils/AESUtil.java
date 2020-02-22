package com.lucien.dap.framework.core.utils;

import com.lucien.dap.framework.common.constants.RetEnum;
import com.lucien.dap.framework.core.exception.ApplicationException;
import org.apache.commons.codec.binary.Base64;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.Security;

/**
 * @ClassName AESUtil
 * @Description
 * @Author MALUHAN
 * @Date 2019/5/9 13:57
 * @Version 1.0
 */
public class AESUtil {

    public static final String ALGORITHM = "AES/ECB/PKCS5Padding";

    /**
     * AES加密字符串
     *
     * @param content  需要被加密的字符串
     * @param password 加密需要的密码
     * @return 密文
     */
    public static byte[] encrypt(String content, String password) {
        try {
            byte[] enCodeFormat = new Base64().decode(password);
            SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");// 转换为AES专用密钥

            Cipher cipher = Cipher.getInstance("AES");// 创建密码器

            byte[] byteContent = content.getBytes(StandardCharsets.UTF_8);

            cipher.init(Cipher.ENCRYPT_MODE, key);// 初始化为加密模式的密码器

            return cipher.doFinal(byteContent);// 加密

        } catch (NoSuchPaddingException |
                NoSuchAlgorithmException |
                InvalidKeyException |
                IllegalBlockSizeException |
                BadPaddingException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static byte[] encrypt(byte[] content, String key) {
        if (content == null || key == null) {
            return null;
        }
        try {
            SecretKeySpec keySpec = new SecretKeySpec(key.getBytes(), "AES");
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.ENCRYPT_MODE, keySpec);
            return cipher.doFinal(content);
        } catch (NoSuchPaddingException | NoSuchAlgorithmException
                | InvalidKeyException | IllegalBlockSizeException | BadPaddingException e) {
            return null;
        }
    }

    /**
     * 解密AES加密过的内容
     *
     * @param content  AES加密过过的内容
     * @param password 加密时的秘钥
     * @return 明文
     */
    public static byte[] decrypt(byte[] content, String password) {
        try {
            byte[] enCodeFormat = password.getBytes();
            SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");// 转换为AES专用密钥
            Cipher cipher = Cipher.getInstance("AES");// 创建密码器
            cipher.init(Cipher.DECRYPT_MODE, key);// 初始化为解密模式的密码器
            return cipher.doFinal(content); // 明文
        } catch (NoSuchAlgorithmException |
                NoSuchPaddingException |
                InvalidKeyException |
                IllegalBlockSizeException |
                BadPaddingException e) {
            throw new ApplicationException(RetEnum.SystemError.getCode(), e.toString());
        }
    }

    /**
     * 生成key
     *
     * @param length 长度
     * @return 字符串
     */
    public static String generateDesKey(int length) {
        try {
            //实例化
            KeyGenerator kgen = null;
            kgen = KeyGenerator.getInstance("AES");
            //设置密钥长度
            kgen.init(length);
            //生成密钥
            SecretKey skey = kgen.generateKey();
            //返回密钥的二进制编码字符串
            return new Base64().encodeToString(skey.getEncoded());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * AES加密
     *
     * @param str 字符串
     * @param key key字节数组
     * @return 加密后字节数组
     */
    public static byte[] Aes256Encode(String str, byte[] key) {
        try {
            Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
            Cipher cipher = Cipher.getInstance(ALGORITHM, "BC");
            SecretKeySpec keySpec = new SecretKeySpec(key, "AES"); // 生成加密解密需要的Key
            cipher.init(Cipher.ENCRYPT_MODE, keySpec);
            return cipher.doFinal(str.getBytes(StandardCharsets.UTF_8));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public static void main(String[] args) {
        byte[] bytes = readFile("C:\\Users\\Administrator\\Downloads\\download.zip");
        byte[] decrypt = decrypt(bytes, "RaslOdIngOACwzj+8P8f6g==");
        writeFile(decrypt, "F:\\test\\", "decrypt.zip");
    }

    private static void writeFile(byte[] bytes, String filePath, String fileName) {
        BufferedOutputStream bos = null;
        FileOutputStream fos = null;
        File file = null;
        try {
            File dir = new File(filePath);
            if (!dir.exists() && dir.isDirectory()) {// 判断文件目录是否存在
                dir.mkdirs();
            }
            file = new File(filePath + "\\" + fileName);
            fos = new FileOutputStream(file);
            bos = new BufferedOutputStream(fos);
            bos.write(bytes);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (bos != null) {
                try {
                    bos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static byte[] readFile(String path) {
        try {
            FileInputStream inputStream = new FileInputStream(path);
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int len = -1;
            while ((len = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, len);
            }
            outputStream.close();
            inputStream.close();
            return outputStream.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}

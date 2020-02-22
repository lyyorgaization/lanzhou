package com.lucien.dap.framework.core.utils;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.SystemUtils;
import sun.misc.BASE64Encoder;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author qinbin.wan
 * @version v1.0.0
 * @date 2017/4/19
 */
public class Md5Util {

    public static String MD5(String sourceStr) {

        String result = "";
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(sourceStr.getBytes());
            byte[] b = md.digest();
            int i;
            StringBuffer buf = new StringBuffer();
            for (int offset = 0; offset < b.length; offset++) {
                i = b[offset];
                if (i < 0)
                    i += 256;
                if (i < 16)
                    buf.append("0");
                buf.append(Integer.toHexString(i));
            }
            result = buf.toString();
        } catch (NoSuchAlgorithmException e) {
            System.out.println(e);
        }
        return result;
    }

    public static String sha256Hex(String sourceStr) {
        if (StringUtils.isEmpty(sourceStr)) {
            return "";
        }

        return DigestUtils.sha256Hex(sourceStr);
    }

    public static String base64Encode(String data) {
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
            BASE64Encoder base64en = new BASE64Encoder();
            // 加密后的字符串
            return base64en.encode(md5.digest(data.getBytes(StandardCharsets.UTF_8)));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        String imei = "AAA";
        String appkey = "30001";
        Long timestamp = System.currentTimeMillis();
        String token = "e2d7181e828847a8982d825f85045655";
        String sign = "69a11bb705000bd558858fa30aea3a0a";
        //验证签名
        System.out.println(timestamp);
        String md5Sign = MD5(imei + timestamp + appkey + token);
        System.out.println(md5Sign);
        System.out.println(md5Sign.equals(sign));

    }
}

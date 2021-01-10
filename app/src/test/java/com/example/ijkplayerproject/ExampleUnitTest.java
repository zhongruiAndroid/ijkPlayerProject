package com.example.ijkplayerproject;

import org.junit.Test;

import java.math.BigInteger;
import java.security.MessageDigest;

import static org.junit.Assert.assertEquals;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }
    @Test
    public void asdf() {
        long l = System.currentTimeMillis();
        String encode = encode("http://flashmedia.eastday.com/newdate/news/2016-11/shznews1125-19.mp4");
        for (int i = 0; i < 1000; i++) {
            encode("http://flashmedia.eastday.com/newdate/news/2016-11/shznews1125-19.mp4");
        }
        long l2 = System.currentTimeMillis();
        System.out.println(l2-l);
        System.out.println(encode);

    }
    @Test
    public void as2df() {
        long l = System.currentTimeMillis();
        String encode2 = getMD5String("http://flashmedia.eastday.com/newdate/news/2016-11/shznews1125-19.mp4");
        for (int i = 0; i < 1000; i++) {
            getMD5String("http://flashmedia.eastday.com/newdate/news/2016-11/shznews1125-19.mp4");
        }
        long l2 = System.currentTimeMillis();
        System.out.println(l2-l);
        System.out.println(encode2);

        //1d269889fd5d2cc197221f63db4d7343
        //1d269889fd5d2cc197221f63db4d7343

    }
    public static String encode(String str) {
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            md5.update(str.getBytes("UTF-8"));
            byte[] messageDigest = md5.digest();
            StringBuilder hexString = new StringBuilder();
            byte[] var4 = messageDigest;
            int var5 = messageDigest.length;

            for(int var6 = 0; var6 < var5; ++var6) {
                byte b = var4[var6];
                hexString.append(String.format("%02X", b));
            }

            return hexString.toString().toLowerCase();
        } catch (Exception var8) {
            var8.printStackTrace();
            return "";
        }
    }
    public static String getMD5String(String str) {
        try {
            // 生成一个MD5加密计算摘要
            MessageDigest md = MessageDigest.getInstance("MD5");
            // 计算md5函数
            md.update(str.getBytes());
            // digest()最后确定返回md5 hash值，返回值为8位字符串。因为md5 hash值是16位的hex值，实际上就是8位的字符
            // BigInteger函数则将8位的字符串转换成16位hex值，用字符串来表示；得到字符串形式的hash值
            //一个byte是八位二进制，也就是2位十六进制字符（2的8次方等于16的2次方）
            return new BigInteger(1, md.digest()).toString(16);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
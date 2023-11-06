package com.lagou.utils;


import org.apache.commons.codec.digest.DigestUtils;

import java.util.UUID;

public class Md5 {

    public final static  String md5key = "lagou";
    /**
     * MD5方法
     * @param text 明文
     * @param key 密钥
     * @return 密文
     * @throws Exception
     */
    public static String md5(String text, String key) throws Exception {
        //加密后的字符串
        String encodeStr= DigestUtils.md5Hex(text+key);
        System.out.println("MD5加密后的字符串为:encodeStr="+encodeStr);
        return encodeStr;
    }

    /**
     * MD5验证方法
     * @param text 明文
     * @param key 密钥
     * @param md5 密文
     * @return true/false
     * @throws Exception
     */
    public static boolean verify(String text, String key, String md5) throws Exception {
        //根据传入的密钥进行验证
        String md5Text = md5(text, key);
        if(md5Text.equalsIgnoreCase(md5)) //登录时的密码加密后，和数据库的密文进行对比
        {
            System.out.println("MD5验证通过");
            return true;
        }
        return false;
    }

    public static void main(String[] args) throws Exception {

        /*
            注册：手机号+密码  注册添加用户的时候，要进行加密
         */
        String lagou = Md5.md5("123456", "lagou");//调用加密方法

        /*
            登录：1.根据前端传递的手机号（用户名），先在user表中查询对应的密文密码
                 2.调用verify方进行密码的校验
         */
        boolean b = Md5.verify("123456", "lagou", "密文");//返回值校验是否成功

    }


}

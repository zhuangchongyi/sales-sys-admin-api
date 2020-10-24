package com.dc.test;

import com.dc.common.utils.CodeUtil;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.junit.jupiter.api.Test;

public class UserTests {

    @Test
    public void pwd() {
        String salt = new SecureRandomNumberGenerator().nextBytes().toHex();
        System.out.println("salt=" + salt);
        String pwd = new Md5Hash("admin", salt, 3).toString();
        System.out.println("pwd=" + pwd);
    }

    @Test
    public void random() {
        System.out.println((int) (Math.random() * 9 + 1) * 100000);
        System.out.println(randomCode(6));
    }


    public static String randomCode(int digit) {
        StringBuilder sb = new StringBuilder("1");
        for (int i = 0; i < digit; i++) {
            sb.append("0");
        }
        return String.valueOf((int) ((Math.random() + 1) * Integer.valueOf(sb.toString())));
    }


    @Test
    public void cgetCode() {
        for (int i = 0; i < 10; i++) {
            String code = CodeUtil.getCode("SN");
            System.out.println(code);
            System.out.println(code.length());
        }

    }

}

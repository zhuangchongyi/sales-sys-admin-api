package com.dc.test;

import com.dc.common.utils.CodeUtil;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;

public class UserTests {

    @Test
    public void pwd() {
        String salt = new SecureRandomNumberGenerator().nextBytes().toHex();
        System.out.println("salt=" + salt);
        String pwd = new Md5Hash("root", salt, 3).toString();
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


    @Test
    public void test() {
        ArrayList<Map<String, Object>> list = new ArrayList<>();
        long time = new Date().getTime();
        for (int i = 0; i < 10; i++) {
            HashMap<String, Object> map = new HashMap<>();
            int num = (int) ((Math.random() + 1) * 10000);
            time = time + num;
            map.put("id", i  );
            map.put("timestamp", time);
            map.put("date", new Date(time));
            list.add(map);
        }

        System.out.println("==========排序==========");
//        Collections.sort(list, (o1, o2) -> o2.get("id").toString().compareTo(o1.get("id").toString()));

        List<Map<String, Object>> collect = list.stream()
                .sorted((o1, o2) -> o2.get("id").toString().compareTo(o1.get("id").toString())).collect(Collectors.toList());
        for (Map<String, Object> map : collect) {
            System.out.println(map);
        }

        System.out.println("==========分页==========");
        int current = 5;
        int size= 3;
        List<Map<String, Object>> mapList = collect.stream().skip((current-1)*size).limit(size).collect(Collectors.toList());
        for (Map<String, Object> map : mapList) {
            System.out.println(map);
        }


    }

}

package com.dc.test;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.EnvironmentPBEConfig;
import org.junit.Test;

public class JasyptTest {

    @Test
    public void testEncrypt(){
        StandardPBEStringEncryptor standardPBEStringEncryptor = new StandardPBEStringEncryptor();
        EnvironmentPBEConfig config = new EnvironmentPBEConfig();
        config.setAlgorithm("PBEWithMD5AndDES"); // 加密的算法，这个算法是默认的
        config.setPassword("zhuangcy"); // 加密的密钥
        standardPBEStringEncryptor.setConfig(config);
        String plainText = "root";         //自己的密码
        String encryptedText = standardPBEStringEncryptor.encrypt(plainText);
        System.out.println("加密= "+encryptedText);
        System.out.println("解密= "+standardPBEStringEncryptor.decrypt(encryptedText));
    }

}

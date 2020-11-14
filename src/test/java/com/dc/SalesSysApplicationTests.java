package com.dc;

import com.dc.common.vo.R;
import com.dc.project.common.service.ILoginService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class SalesSysApplicationTests {
    @Autowired
    private ILoginService loginService;
    @Test
    public void getCode() {
        R code = loginService.getCode();
        System.out.println(code.getData());
    }
}

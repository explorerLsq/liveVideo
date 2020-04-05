package com.livevideo.service.impl;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class pushUrlServiceImplTest {
    @Autowired
    private pushUrlServiceImpl pushUrlService;

    @Test
    void getFinallyPushUrl() {
        String url = "ali";
        String resutl = pushUrlService.getFinallyPushUrl(url);
        System.out.println(resutl);
    }
    //测试git提交
}
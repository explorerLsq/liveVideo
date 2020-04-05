package com.livevideo.service.impl;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

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
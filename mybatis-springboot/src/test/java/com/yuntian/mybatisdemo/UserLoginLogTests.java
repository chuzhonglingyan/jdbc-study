package com.yuntian.mybatisdemo;

import com.alibaba.fastjson.JSON;
import com.yuntian.mybatisdemo.model.entity.UserLoginLog;
import com.yuntian.mybatisdemo.service.UserLoginLogService;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import lombok.extern.slf4j.Slf4j;

/**
 * https://www.jianshu.com/p/d6a63500c62a
 */
@SpringBootTest
@Slf4j
class UserLoginLogTests {

    @Resource
    private UserLoginLogService userLoginLogService;

    @Test
    void contextLoads() {
    }

    @Test
    void queryById() {
        long start = System.currentTimeMillis();
        UserLoginLog userLoginLog= userLoginLogService.queryById(100000L);
        log.info((System.currentTimeMillis() - start) + "ms");
        log.info(JSON.toJSONString(userLoginLog));
    }

}

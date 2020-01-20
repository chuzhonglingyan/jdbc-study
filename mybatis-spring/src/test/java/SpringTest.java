import com.alibaba.fastjson.JSON;
import com.yuntian.spring.mybatis.config.AppConfig;
import com.yuntian.spring.mybatis.entity.UserLoginLog;
import com.yuntian.spring.mybatis.service.UserLoginLogService;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * @Auther: yuntian
 * @Date: 2020/1/18 0018 13:29
 * @Description: https://blog.csdn.net/blueboz/article/details/79550020
 */

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = AppConfig.class)
public class SpringTest {

    @Resource
    private  UserLoginLogService userLoginLogService;

    @Test
    public void testUserLoginLogService() {
        long start = System.currentTimeMillis();
        UserLoginLog userLoginLog= userLoginLogService.queryById(100000L);
        System.out.println((System.currentTimeMillis() - start) + "ms");
        System.out.println(JSON.toJSONString(userLoginLog));
    }


}

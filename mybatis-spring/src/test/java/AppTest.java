import com.alibaba.fastjson.JSON;
import com.yuntian.spring.mybatis.config.AppConfig;
import com.yuntian.spring.mybatis.entity.UserLoginLog;
import com.yuntian.spring.mybatis.service.UserLoginLogService;

import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @Auther: yuntian
 * @Date: 2020/1/18 0018 13:16
 * @Description:
 */
public class AppTest {

    @Test
    public void testUserLoginLogService() {
        AnnotationConfigApplicationContext acc = new AnnotationConfigApplicationContext(AppConfig.class);
        long start = System.currentTimeMillis();
        UserLoginLogService userLoginLogService = acc.getBean(UserLoginLogService.class);
        UserLoginLog userLoginLog= userLoginLogService.queryById(100000L);
        System.out.println(JSON.toJSONString(userLoginLog) + "," + (System.currentTimeMillis() - start) + "ms");
    }
}

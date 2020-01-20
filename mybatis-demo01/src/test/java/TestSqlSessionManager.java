import com.alibaba.fastjson.JSON;
import com.yuntian.demo01.dao.UserLoginLogDao;
import com.yuntian.demo01.entity.UserLoginLog;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionManager;
import org.junit.Test;

import java.io.IOException;
import java.io.Reader;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Auther: yuntian
 * @Date: 2020/1/19 0019 23:51
 * @Description:
 */
public class TestSqlSessionManager {

    @Test
    public void testSqlSessionManager() {
        Reader reader = null;
        SqlSessionManager sqlSessionManager = null;
        try {
            reader = Resources.getResourceAsReader("mybatis/mybatis-config.xml");
            sqlSessionManager = SqlSessionManager.newInstance(reader);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        assert sqlSessionManager != null;
        UserLoginLogDao dao = sqlSessionManager.getMapper(UserLoginLogDao.class);

        int taskSize = 5;
        CountDownLatch countDownLatch = new CountDownLatch(taskSize);

        ExecutorService executorService = Executors.newFixedThreadPool(2);
        for (int i = 0; i < taskSize; i++) {
            int finalI = i;
            SqlSessionManager finalSqlSessionManager = sqlSessionManager;
            executorService.submit(() -> {
                queryTaskNoAutoCommit(finalSqlSessionManager, dao, (long) finalI);
                countDownLatch.countDown();
            });
        }
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        executorService.shutdown();
        System.out.println("主线程完成");
    }


    private void queryTaskAutoCommit(SqlSessionManager sqlSessionManager, UserLoginLogDao dao, Long id) {
        try {
            UserLoginLog userLoginLog = dao.queryById(id);
            UserLoginLog userLoginLogX = dao.queryById(10000L);
            System.out.println(Thread.currentThread() + ":" + JSON.toJSONString(userLoginLog));
        } catch (Exception e) {
            sqlSessionManager.rollback();
        }
    }


    private void queryTaskNoAutoCommit(SqlSessionManager sqlSessionManager, UserLoginLogDao dao, Long id) {
        try {
            sqlSessionManager.startManagedSession(false);
            UserLoginLog userLoginLog = dao.queryById(id);
            UserLoginLog userLoginLogX = dao.queryById(10000L);
            sqlSessionManager.commit();
            System.out.println(Thread.currentThread() + ":" + JSON.toJSONString(userLoginLog));
        } catch (Exception e) {
            sqlSessionManager.rollback();
        }
    }
}

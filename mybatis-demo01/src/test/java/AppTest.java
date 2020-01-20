import com.alibaba.fastjson.JSON;
import com.yuntian.demo01.dao.UserLoginLogDao;
import com.yuntian.demo01.entity.UserLoginLog;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.Reader;

/**
 * @Auther: yuntian
 * @Date: 2020/1/16 0016 21:07
 * @Description:
 */

public class AppTest
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void testSqlSessionFactory() {
        SqlSession session=null;
        try {
            //使用MyBatis提供的Resources类加载mybatis的配置文件
            Reader reader = Resources.getResourceAsReader("mybatis/mybatis-config.xml");
            //构建sqlSession的工厂
            SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(reader);
            System.out.println(sessionFactory.toString());
            session= sessionFactory.openSession();
            System.out.println(session.toString());
            UserLoginLogDao dao=session.getMapper(UserLoginLogDao.class);
            long start=System.currentTimeMillis();
            UserLoginLog userLoginLog1=dao.queryById(1L);
            UserLoginLog userLoginLog2=dao.queryById(1000L);
            System.out.println(System.currentTimeMillis()-start+"ms");
            System.out.println(JSON.toJSONString(userLoginLog1));
            System.out.println(JSON.toJSONString(userLoginLog2));
            session.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if (session!=null){
                session.close();
            }
        }
    }


}
import com.alibaba.fastjson.JSON;
import com.yuntian.jdbc.dao.UserLoginLogDao;
import com.yuntian.jdbc.entity.UserLoginLog;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * @author guangleilei.
 * @date Created in 17:09 2020/1/10
 * @description
 */
public class TestDb {

    private static Logger logger = LoggerFactory.getLogger(TestDb.class);

    public static void  main(String[] args){

        UserLoginLogDao userLoginLogDao=new UserLoginLogDao();
        List<UserLoginLog> list=userLoginLogDao.queryListByPage(1,10);
        logger.info(JSON.toJSONString(list));
    }
}

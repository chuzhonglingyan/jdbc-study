import com.alibaba.fastjson.JSON;
import com.yuntian.jdbc.data.IpRandom;
import com.yuntian.jdbc.entity.UserLoginLog;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * @author guangleilei.
 * @date Created in 17:09 2020/1/10
 * @description  maxMemory 能构从操作系统那里挖到的最大的内存
 * https://blog.csdn.net/qq_22194659/article/details/83829891
 */
public class TestDbInsert {

    private static Logger logger = LoggerFactory.getLogger(TestDbInsert.class);

    public static void  main(String[] args){

        long start=System.currentTimeMillis();
        System.out.println("测试开始");
        System.out.println("空闲内存："+ Runtime.getRuntime().freeMemory()/1024/1024 +"M");
        System.out.println("虚拟机进程可占用最大内存："+ Runtime.getRuntime().maxMemory()/1024/1024 +"M");
        System.out.println("虚拟机进程运行当时的占用内存："+ Runtime.getRuntime().totalMemory()/1024/1024 +"M");
        createBigList();
        System.out.println("测试完成");
        System.out.println("空闲内存："+ Runtime.getRuntime().freeMemory()/1024/1024 +"M");
        System.out.println("虚拟机进程可占用最大内存："+ Runtime.getRuntime().maxMemory()/1024/1024 +"M");
        System.out.println("虚拟机进程运行当时的占用内存："+ Runtime.getRuntime().totalMemory()/1024/1024 +"M");
        System.out.println("耗时："+(System.currentTimeMillis()-start)+"ms");

        while (true){
            try {
                Thread.sleep(1000);
                System.out.println("----------------");
                System.out.println("空闲内存："+ Runtime.getRuntime().freeMemory()/1024/1024 +"M");
                System.out.println("虚拟机进程可占用最大内存："+ Runtime.getRuntime().maxMemory()/1024/1024 +"M");
                System.out.println("虚拟机进程运行当时的占用内存："+ Runtime.getRuntime().totalMemory()/1024/1024 +"M");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void createBigList(){
        //生产数据
        Random rm = new Random();
        //1000000
        int count=10;
        int size=1000000;
        for (int j = 0; j < count; j++) {
            List<UserLoginLog> userLoginLogList=new ArrayList<>();
            for (int i = 0; i < size; i++) {
                UserLoginLog userLoginLog=new UserLoginLog();
                userLoginLog.setUserId((long) (rm.nextInt(100000) + 1));
                userLoginLog.setIp(IpRandom.getRandomIp());
                long time=System.currentTimeMillis()+i*(rm.nextInt(1000) + 1);
                userLoginLog.setLoginTime(new Date(time));
                userLoginLogList.add(userLoginLog);
            }
            System.out.println("集合数量:"+userLoginLogList.size());
            userLoginLogList.clear();
        }
    }




}

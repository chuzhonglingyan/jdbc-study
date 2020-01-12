import com.yuntian.jdbc.JDBCUtils;
import com.yuntian.jdbc.data.IpRandom;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Auther: yuntian
 * @Date: 2020/1/11 0011 18:44
 * @Description:
 */
public class TestBigTestData {



    public static void main(String[] args) {
        System.out.println("测试开始");
        System.out.println("空闲内存："+ Runtime.getRuntime().freeMemory()/1024/1024 +"M");
        System.out.println("虚拟机进程可占用最大内存："+ Runtime.getRuntime().maxMemory()/1024/1024 +"M");
        System.out.println("虚拟机进程运行当时的占用内存："+ Runtime.getRuntime().totalMemory()/1024/1024 +"M");
        insertList();
//        insertListMutil();

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

    private static Random rm = new Random();

    private static ThreadLocal<DateFormat> threadLocal = ThreadLocal.withInitial(() -> new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));

    public static String format(Date date) {
        return threadLocal.get().format(date);
    }


    /**
     * 插入1千万数据 分
     */
    public static void insertList() {
        long begin = System.currentTimeMillis();

        Connection conn = JDBCUtils.getConnection();
        String prefix = "insert into user_login_log (user_id,ip,login_time) values";
        PreparedStatement pst = null;
        try {
            conn.setAutoCommit(false);
            pst = conn.prepareStatement("");
            // 保存sql后缀
            StringBuffer suffix = new StringBuffer();
            int count = 10;
            int size = 1000000;
            // 外层循环，总提交事务次数
            for (int i = 0; i < count; i++) {
                for (int j = 0; j < size; j++) {
                    long time = System.currentTimeMillis() + j  * (rm.nextInt(1000) + 1);
                    // 构建sql后缀
                    suffix.append("(").append(rm.nextInt(100000) + 1).append(",").append("'").append(IpRandom.getRandomIp()).append("'").append(",")
                            .append("'").append(format(new Date(time))).append("'")
                            .append("),");
                }
                // 构建完整sql
                String sql = prefix + suffix.substring(0, suffix.length() - 1);
                // 添加执行sql
                pst.addBatch(sql);
                // 执行操作
                pst.executeBatch();
                conn.commit();
                // 清空上一次添加的数据
                suffix = new StringBuffer();
                pst.clearBatch();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.releaseSqlConnection(null, pst, conn);
        }
        //cast : 68066 ms
        System.out.println("cast : " + (System.currentTimeMillis() - begin) + " ms");
    }


    public static void insertListMutil() {
        long begin = System.currentTimeMillis();
        int clientcount = 100;
        CountDownLatch countDownLatch = new CountDownLatch(clientcount);
        ExecutorService executorService = Executors.newFixedThreadPool(clientcount);
        // 保存sql后缀
        String prefix = "insert into user_login_log (user_id,ip,login_time) values";
        for (int i = 0; i < clientcount; i++) {
            executorService.execute(() -> {
                Connection conn = JDBCUtils.getConnection();
                PreparedStatement pst = null;
                try {
                    conn.setAutoCommit(false);
                    pst = conn.prepareStatement("");
                    final StringBuffer suffix = new StringBuffer();
                    int size = 100000;
                    // 外层循环，总提交事务次数
                    for (int j = 0; j < size; j++) {
                        long time = System.currentTimeMillis() + j * (rm.nextInt(1000) + 1);
                        // 构建sql后缀
                        suffix.append("(").append(rm.nextInt(100000) + 1).append(",").append("'").append(IpRandom.getRandomIp()).append("'").append(",")
                                .append("'").append(format(new Date(time))).append("'")
                                .append("),");
                    }
                    // 构建完整sql
                    String sql = prefix + suffix.substring(0, suffix.length() - 1);
                    // 添加执行sql
                    pst.addBatch(sql);
                    // 执行操作
                    pst.executeBatch();
                    conn.commit();
                    // 清空上一次添加的数据
                    pst.clearBatch();

                } catch (SQLException e) {
                    e.printStackTrace();
                } finally {
                    JDBCUtils.releaseSqlConnection(null, pst, conn);
                }
                countDownLatch.countDown();
            });
        }
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //52389 ms
        System.out.println("cast : " + (System.currentTimeMillis() - begin) + " ms");
        executorService.shutdown();
    }

}

package com.yuntian.jdbc.dao;

import com.yuntian.jdbc.JDBCUtils;
import com.yuntian.jdbc.data.IpRandom;
import com.yuntian.jdbc.entity.UserLoginLog;
import com.yuntian.jdbc.handler.BeanHandler;
import com.yuntian.jdbc.handler.BeanListHandler;
import com.yuntian.jdbc.handler.CRUDTemplate;
import com.yuntian.jdbc.util.DateUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.MessageFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * @author guangleilei.
 * @date Created in 17:14 2020/1/10
 * @description https://www.jianshu.com/p/0a7e3055a01f
 */
public class UserLoginLogDao {


    public void queryCondition() {
        String sql = "select * from user_login_log";
        UserLoginLog userLoginLog = CRUDTemplate.executeQuery(sql, new BeanHandler<>(UserLoginLog.class), 1);
    }


    public List<UserLoginLog> queryListByPage(Integer currentPage, Integer limit) {
        String template = "select * from user_login_log ORDER BY id desc LIMIT {0},{1};";
        long start = (currentPage - 1) * limit;
        String sql = MessageFormat.format(template, new Object[]{start, limit});
        return CRUDTemplate.executeQuery(sql, new BeanListHandler<>(UserLoginLog.class));
    }


    /**
     * 插入1千万数据 分
     * @param list
     */
    public void insertList(List<UserLoginLog> list) {

        Connection connection = JDBCUtils.getConnection();
        Random rm = new Random();
        String sql = "insert into user_login_log (user_id,ip,login_time) values(?,?,?)";
        PreparedStatement pst = null;
        try {
            connection.setAutoCommit(false);
            pst = connection.prepareStatement(sql);
            for (int i = 0; i < list.size(); i++) {
                UserLoginLog userLoginLog = list.get(i);
                pst.setLong(1, rm.nextInt(100000) + 1);
                pst.setString(2, IpRandom.getRandomIp());
                pst.setDate(3, DateUtil.convert(new Date()));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

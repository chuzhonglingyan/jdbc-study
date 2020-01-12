package com.yuntian.jdbc.handler;

import com.google.common.base.CaseFormat;

import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.math.BigInteger;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

/**
 * @Auther: yuntian
 * @Date: 2020/1/11 0011 11:20
 * @Description:
 */
public class HandlerUtil {


    public static <T> void  convert(T obj, ResultSet result,PropertyDescriptor[] pds){
        for (PropertyDescriptor pd : pds) {
            //获取结果集中对应字段名的值
            String columnLabel= CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, pd.getName());
            try {
                Object value  = result.getObject(columnLabel);
                if (value instanceof Timestamp){
                    pd.getWriteMethod().invoke(obj, new java.util.Date (((Timestamp) value).getTime()));
                } else if (value instanceof BigInteger){
                    pd.getWriteMethod().invoke(obj, ((BigInteger) value).longValueExact());
                }else {
                    pd.getWriteMethod().invoke(obj, value);
                }
            } catch (SQLException | IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
        }
    }
}

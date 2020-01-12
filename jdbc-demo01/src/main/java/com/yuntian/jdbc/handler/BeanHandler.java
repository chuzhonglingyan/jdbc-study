package com.yuntian.jdbc.handler;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.sql.ResultSet;

/**
 * @Auther: yuntian
 * @Date: 2019/12/7 0007 23:38
 * @Description:
 */
public class BeanHandler<T> implements IResultSetHandler<T> {

    private Class<T> tClass;

    public BeanHandler(Class<T> tClass) {
        this.tClass = tClass;
    }

    @Override
    public T handle(ResultSet result) throws Exception {
        if (result.next()) {
            T obj = tClass.newInstance();
            //获取指定字节码信息
            BeanInfo beanInfo = Introspector.getBeanInfo(tClass, Object.class);
            //获取所有属性描述器
            PropertyDescriptor[] pds = beanInfo.getPropertyDescriptors();
            HandlerUtil.convert(obj,result,pds);
            return obj;
        }
        return null;
    }
}

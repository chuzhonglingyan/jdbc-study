package com.yuntian.jdbc.handler;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: yuntian
 * @Date: 2019/12/7 0007 23:38
 * @Description:
 */
public class BeanListHandler<T> implements IResultSetHandler<List<T>> {

    private Class<T> tClass;

    public BeanListHandler(Class<T> tClass) {
        this.tClass = tClass;
    }

    @Override
    public List<T> handle(ResultSet result) throws Exception {
        List<T> list = new ArrayList<>();
        BeanInfo beanInfo = Introspector.getBeanInfo(tClass, Object.class);
        //获取所有属性描述器
        PropertyDescriptor[] pds = beanInfo.getPropertyDescriptors();
        while (result.next()) {
            T obj = tClass.newInstance();
            //获取指定字节码信息
            HandlerUtil.convert(obj,result,pds);
            list.add(obj);
        }
        return list;
    }
}

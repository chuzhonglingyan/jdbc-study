package com.yuntian.spring.mybatis.service;

import com.yuntian.spring.mybatis.entity.UserLoginLog;
import java.util.List;

/**
 * (UserLoginLog)表服务接口
 *
 * @author makejava
 * @since 2020-01-18 13:24:44
 */
public interface UserLoginLogService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    UserLoginLog queryById(Long id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<UserLoginLog> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param userLoginLog 实例对象
     * @return 实例对象
     */
    UserLoginLog insert(UserLoginLog userLoginLog);

    /**
     * 修改数据
     *
     * @param userLoginLog 实例对象
     * @return 实例对象
     */
    UserLoginLog update(UserLoginLog userLoginLog);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Long id);

}
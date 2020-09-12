package com.yuntian.mybatisdemo.dao;


import com.yuntian.mybatisdemo.model.entity.UserLoginLog;

import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * (UserLoginLog)表数据库访问层
 *
 * @author makejava
 * @since 2020-01-18 13:24:44
 */
public interface UserLoginLogDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    UserLoginLog queryById(Long id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<UserLoginLog> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param userLoginLog 实例对象
     * @return 对象列表
     */
    List<UserLoginLog> queryAll(UserLoginLog userLoginLog);

    /**
     * 新增数据
     *
     * @param userLoginLog 实例对象
     * @return 影响行数
     */
    int insert(UserLoginLog userLoginLog);

    /**
     * 修改数据
     *
     * @param userLoginLog 实例对象
     * @return 影响行数
     */
    int update(UserLoginLog userLoginLog);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Long id);

}
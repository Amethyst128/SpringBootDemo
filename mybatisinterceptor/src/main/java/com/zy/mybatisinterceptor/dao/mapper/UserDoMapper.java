package com.zy.mybatisinterceptor.dao.mapper;

import com.zy.mybatisinterceptor.dao.model.UserDo;

/**
 * 用户mapper文件
 *
 * @Author zhangyu
 * @Date 2019/7/9 15:44
 */
public interface UserDoMapper {

    int deleteByPrimaryKey(Byte id);

    int insert(UserDo record);

    int insertSelective(UserDo record);

    UserDo selectByPrimaryKey(Byte id);

    int updateByPrimaryKeySelective(UserDo record);

    int updateByPrimaryKey(UserDo record);
}
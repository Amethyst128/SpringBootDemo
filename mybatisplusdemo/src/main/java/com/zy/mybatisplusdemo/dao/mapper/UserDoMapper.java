package com.zy.mybatisplusdemo.dao.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.zy.mybatisplusdemo.dao.model.UserDo;

import java.util.List;

/**
 * 用户mapper文件
 *
 * @Author zhangyu
 * @Date 2019/7/15 16:29
 */
public interface UserDoMapper extends BaseMapper<UserDo> {

    List<UserDo> getUserList();

}
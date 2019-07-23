package com.zy.mybatisplusdemo.dao.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.zy.mybatisplusdemo.dao.model.UserDo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


import java.util.List;

/**
 * 用户mapper文件
 *
 * @Author zhangyu
 * @Date 2019/7/15 16:29
 */
@Mapper
public interface UserDoMapper extends BaseMapper<UserDo> {

    List<UserDo> getUserList();

    //sql注入器mapper方法
    List<UserDo> selectListBackEnd(@Param(Constants.WRAPPER) Wrapper queryWrapper);

}
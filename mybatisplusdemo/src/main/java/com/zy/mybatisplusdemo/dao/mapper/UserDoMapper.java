package com.zy.mybatisplusdemo.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zy.mybatisplusdemo.dao.model.UserDo;
import org.apache.ibatis.annotations.Mapper;

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

}
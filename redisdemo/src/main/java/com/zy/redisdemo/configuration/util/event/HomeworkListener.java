package com.zy.redisdemo.configuration.util.event;

/**
 * @Description: 观察者接口---即学生。
 * @Author: zhangyu
 * @Date:Created in 11:48 2019/6/21
 */
public interface HomeworkListener extends EventListener {

    public void update(HomeworkListener o, Object arg);

}

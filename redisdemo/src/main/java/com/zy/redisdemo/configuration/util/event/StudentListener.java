package com.zy.redisdemo.configuration.util.event;

/**
 * @Description:
 * @Author: zhangyu
 * @Date:Created in 11:51 2019/6/21
 */
public class StudentListener implements HomeworkListener {

    private String name;

    public StudentListener(String name) {
        this.name = name;
    }

    @Override
    public void update(HomeworkListener o, Object arg) {
//        Teacher teacher = o.
    }

}

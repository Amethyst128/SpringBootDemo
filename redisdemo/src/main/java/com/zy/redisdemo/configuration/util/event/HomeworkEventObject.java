package com.zy.redisdemo.configuration.util.event;

/**
 * @Description:
 * @Author: zhangyu
 * @Date:Created in 14:08 2019/6/21
 */
public class HomeworkEventObject extends EventObject {


    public HomeworkEventObject(Object source) throws IllegalAccessException {
        super(source);
    }

//    public HomeworkEventObject(Teacher teacher) {
//        super(teacher);
//    }


}

package com.zy.redisdemo.configuration.util.event;

import java.io.Serializable;

/**
 * @Description: 事件 -getResource()方法，返回的是当前的事件源（教师、被监听者）
 * @Author: zhangyu
 * @Date:Created in 11:38 2019/6/21
 */
public class EventObject implements Serializable {

    protected transient Object source;

    public EventObject(Object source) throws IllegalAccessException {
        if (null == source) {
            throw new IllegalAccessException("null source");
        }
        this.source = source;
    }

    public Object getSource() {
        return source;
    }

    @Override
    public String toString() {
        return getClass().getName() + "[source=" + source + "]";
    }

}

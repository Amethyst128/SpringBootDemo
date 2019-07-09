package com.zy.redisdemo.configuration.util.event;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: 老师 被观察者 事件源
 * @Author: zhangyu
 * @Date:Created in 12:03 2019/6/21
 */
public class Teacher extends java.util.Observable {

    private String name;
    private List<String> homeworks;

    public String getName() {
        return this.name;
    }

    public Teacher(String name) {
        this.name = name;
        homeworks = new ArrayList<String>();
    }

    public void setHomework(String homework) {
        System.out.printf("%s布置了作业%s \n", this.name, homework);
        homeworks.add(homework);
        setChanged();
        notifyObservers(homework);
    }

}

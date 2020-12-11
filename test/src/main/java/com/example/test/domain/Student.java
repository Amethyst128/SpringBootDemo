package com.example.test.domain;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * @Desc:
 * @Author: qisu
 * @Date:Created in 2020/11/4
 */
@Data
@Getter
@Setter
@Accessors(chain = true)
public class Student {

    private String userName;
    private Integer age;
    private String sex;
    private Integer grade;

}

package com.example.test.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;
import java.util.Map;

/**
 * @Desc:
 * @Author: zy
 * @Date:Created in 2019/9/29
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
public class UserDo {

    private String userName;
    private Integer age;
    private String sex;
    private Integer grade;
    private List<String> listThings;
    private Map<String,String> MapString;
    private Student student;

}

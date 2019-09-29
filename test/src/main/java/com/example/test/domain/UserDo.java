package com.example.test.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

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

}

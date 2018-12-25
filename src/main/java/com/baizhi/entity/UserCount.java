package com.baizhi.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserCount implements Serializable {
    private Integer id;
    //省份名
    private String name;
    //用户数量
    private Integer value;

}

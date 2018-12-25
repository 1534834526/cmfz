package com.baizhi.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Table(name = "user")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class User implements Serializable {
    private Integer id;
    private String phone;
    private String password;
    private String salt;
    private String sign;
    private String head_pic;
    private String name;
    private String dharma;
    private String sex;
    private String province;
    private String city;
    private Integer status;
    @Column(name = "reg_date")
    private Date regDate;

}

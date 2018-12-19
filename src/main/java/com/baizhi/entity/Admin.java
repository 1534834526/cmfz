package com.baizhi.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Table(name = "admin")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Admin implements Serializable {
    @Id
    @KeySql(useGeneratedKeys = true)
    private Long id;
    private String phone;
    private String password;
}

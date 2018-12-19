package com.baizhi.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.List;

@Table(name = "admin")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Menu implements Serializable {
    @Id
    @KeySql(useGeneratedKeys = true)
    private Long id;
    private String title;
    private String iconcls;
    private String url;
    private List<Menu> list;
}

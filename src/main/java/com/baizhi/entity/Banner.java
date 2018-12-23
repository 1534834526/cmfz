package com.baizhi.entity;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Table(name = "banner")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Banner implements Serializable {
    @Id
    @KeySql(useGeneratedKeys = true)
    //@GeneratedValue(generator = "JDBC")
    private Integer id;
    private String title;
    private String imgPath;
    private String status;
    @JSONField(format = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date pubDate;
    private String description;
}

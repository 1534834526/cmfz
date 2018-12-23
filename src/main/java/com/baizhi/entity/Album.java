package com.baizhi.entity;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@Table(name="album")
@AllArgsConstructor
@NoArgsConstructor
public class Album implements Serializable {
   @Id
   @KeySql(useGeneratedKeys = true)
    private Integer id;
    private String title;
    private Integer count;
    private String url;
    private Double score;
    private String author;
    private String broadcast;
    private String brief;
    @Column(name="pub_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JSONField(format = "yyyy-MM-dd")
    private Date pubDate;
    private List<Chapter> children;

}

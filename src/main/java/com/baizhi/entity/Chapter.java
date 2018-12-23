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

@Data
@Table(name="chapter")
@AllArgsConstructor
@NoArgsConstructor
public class Chapter implements Serializable {
    @Id
    @KeySql(useGeneratedKeys = true)
    private String id;
    private String title;
    private String size;
    @DateTimeFormat(pattern = "HH:mm:ss")
    @JSONField(format = "HH:mm:ss")
    private Date duration;
    private String url;
    @Column(name="upload_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JSONField(format = "yyyy-MM-dd")
    private Date uploadDate;
    private String aid;
}

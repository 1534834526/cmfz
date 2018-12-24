package com.baizhi.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelIgnore;
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
    @Excel(name = "编号")
    private String id;
    @Excel(name = "标题")
    private String title;
    @Excel(name = "文件大小")
    private String size;
    @DateTimeFormat(pattern = "HH:mm:ss")
    @JSONField(format = "HH:mm:ss")
    @Excel(name = "文件时长")
    private String duration;
    @Excel(name = "音频播放")
    private String url;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JSONField(format = "yyyy-MM-dd")
    @Excel(name = "发布日期", format = "YYYY年MM月dd日", width = 20)
    @Column(name = "upload_date")
    private Date uploadDate;
    @ExcelIgnore
    private String aid;
}

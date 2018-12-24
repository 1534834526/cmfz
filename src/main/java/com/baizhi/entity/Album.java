package com.baizhi.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelCollection;
import cn.afterturn.easypoi.excel.annotation.ExcelIgnore;
import cn.afterturn.easypoi.excel.annotation.ExcelTarget;
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
@ExcelTarget(value = "album")
public class Album implements Serializable {
   @Id
   @KeySql(useGeneratedKeys = true)
   @ExcelIgnore
    private Integer id;
    @Excel(name = "专辑标题", needMerge = true)
    private String title;
    @Excel(name = "章节数量", needMerge = true)
    private Integer count;
    @Excel(name = "头像", type = 2, needMerge = true, width = 20, height = 40)
    private String url;
    @Excel(name = "分数", needMerge = true)
    private Double score;
    @Excel(name = "作者", needMerge = true)
    private String author;
    @Excel(name = "播音", needMerge = true)
    private String broadcast;
    @Excel(name = "专辑简介", needMerge = true)
    private String brief;
    @Column(name="pub_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JSONField(format = "yyyy-MM-dd")
    @Excel(name = "发布日期", exportFormat = "YYYY年MM月dd日", width = 20, needMerge = true)
    private Date pubDate;
    @ExcelCollection(name = "音频")
    private List<Chapter> children;

}

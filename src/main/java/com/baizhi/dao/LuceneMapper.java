package com.baizhi.dao;

import com.baizhi.entity.Product;

import java.util.List;

public interface LuceneMapper {
    /**
     * 使用lucene在索引库中建立索引
     */
    public void testInsert(Product product);

    public List<Product> testSearcher(String param);

    public void testDeleteIndex();

    public void updateIndex();
}

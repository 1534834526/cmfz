package com.baizhi.service;

import com.baizhi.entity.Product;

import java.util.List;


public interface LuceneService {
    void testInsert(Product product);

    List<Product> testSearcher(String param);

    void testDeleteIndex();

    void updateIndex();

}

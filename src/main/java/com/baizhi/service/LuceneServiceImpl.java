package com.baizhi.service;

import com.baizhi.dao.LuceneMapper;
import com.baizhi.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LuceneServiceImpl implements LuceneService {

    @Autowired
    private LuceneMapper luceneMapper;

    @Override
    public void testInsert(Product product) {
        luceneMapper.testInsert(product);
    }


    @Override
    public List<Product> testSearcher(String param) {
        List<Product> list = luceneMapper.testSearcher(param);
        return list;
    }

    @Override
    public void testDeleteIndex() {

    }

    @Override
    public void updateIndex() {

    }
}

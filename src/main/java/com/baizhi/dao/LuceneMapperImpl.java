package com.baizhi.dao;

import com.baizhi.entity.Product;
import com.baizhi.util.LuceneUtil;
import org.apache.lucene.document.*;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.*;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * 使用前台测试数据，对索引库进行增删改查
 */
public class LuceneMapperImpl implements LuceneMapper {

   /* @Test
    public void testInsert(){
    Product product = new Product("6", "中国人", 15.5, "地对地导弹", "/images/saa.png", 1, new Date(), "时代大厦");
    //1创建索引写入器
        //2索引写入器的相关配置(分词器)
        //3创建文章对象
        //4将文章对象添加到索引写入器，使用索引写入器创建索引
        IndexWriter indexWriter = LuceneUtil.getIndexWriter();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String format1 = format.format(product.getDate());
        Document document = new Document();
        document.add(new StringField("id",product.getId(), Field.Store.YES));
        document.add(new StringField("productName", product.getProductName(), Field.Store.YES));
        document.add(new DoubleField("productPrice",product.getProductPrice(), Field.Store.YES));
        TextField description = new TextField("description", product.getDescription(), Field.Store.YES);
        description.setBoost(10f);
        document.add(description);
        document.add(new StringField("productImg",product.getProductImg(), Field.Store.YES));
        document.add(new IntField("status",product.getStatus(), Field.Store.YES));
        document.add(new StringField("date",format1, Field.Store.YES));
        document.add(new StringField("place",product.getPlace(), Field.Store.YES));

        try {
            indexWriter.addDocument(document);
            LuceneUtil.commit(indexWriter);
        } catch (IOException e) {
            e.printStackTrace();
            LuceneUtil.close(indexWriter);
        }

    }
*/


    @Override
    public void testInsert(Product product) {
        //1创建索引写入器
        //2索引写入器的相关配置(分词器 IK分词器  可以自定义停用词和扩展词)
        //3创建文章对象
        //4将文章对象添加到索引写入器，使用索引写入器创建索引
        IndexWriter indexWriter = LuceneUtil.getIndexWriter();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String format1 = format.format(product.getDate());
        Document document = new Document();
        document.add(new StringField("id", product.getId(), Field.Store.YES));
        StringField productName = new StringField("productName", product.getProductName(), Field.Store.YES);
        productName.setBoost(10f);
        document.add(productName);
        document.add(new DoubleField("productPrice", product.getProductPrice(), Field.Store.YES));
        document.add(new TextField("description", product.getDescription(), Field.Store.YES));
        document.add(new StringField("productImg", product.getProductImg(), Field.Store.YES));
        document.add(new IntField("status", product.getStatus(), Field.Store.YES));
        document.add(new StringField("date", format1, Field.Store.YES));
        document.add(new StringField("place", product.getPlace(), Field.Store.YES));

        try {
            indexWriter.addDocument(document);
            LuceneUtil.commit(indexWriter);
        } catch (IOException e) {
            e.printStackTrace();
            LuceneUtil.close(indexWriter);
        }

    }


    @Override
    public List<Product> testSearcher(String param) {
        //创建索引搜索器
        //通过索引搜索器去搜索
        //提供搜索条件+搜索数量
        List<Product> list = new ArrayList<>();

        IndexSearcher indexSearcher = LuceneUtil.getIndexSearcher();

        Query query = new TermQuery(new Term("description", param));
        try {
            TopDocs topDocs = indexSearcher.search(query, 100);
            //在索引库查询出的索引编号的数组
            ScoreDoc[] scoreDocs = topDocs.scoreDocs;
            for (int i = 0; i < scoreDocs.length; i++) {
                ScoreDoc scoreDoc = scoreDocs[i];
                //拿到每个索引编号，通过编号去元数据区查询记录，取出
                //拿到分数
                int doc = scoreDoc.doc;
                float score = scoreDoc.score;
                Document document = indexSearcher.doc(doc);
                Product product = LuceneUtil.getProductFromDocument(document);
                list.add(product);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;

    }


    @Override
    public void testDeleteIndex() {
        //拿到索引写入器，通过记录的id删除元数据中的数据
        IndexWriter indexWriter = LuceneUtil.getIndexWriter();

        try {
            //indexWriter.deleteAll();
            indexWriter.deleteDocuments(new Term("id", "0"));
            LuceneUtil.commit(indexWriter);
        } catch (IOException e) {
            e.printStackTrace();
            LuceneUtil.close(indexWriter);
        }


    }


    @Override
    public void updateIndex() {
        IndexWriter indexWriter = LuceneUtil.getIndexWriter();

        Document document = new Document();
        document.add(new StringField("id", "0", Field.Store.YES));
        document.add(new StringField("title", "背影1", Field.Store.YES));
        document.add(new StringField("author", "朱自清1", Field.Store.YES));
        document.add(new TextField("content", "我去给你买几个橘子1", Field.Store.NO));
        document.add(new StringField("date", "2019-01-02", Field.Store.YES));

        try {
            indexWriter.updateDocument(new Term("id", "0"), document);
            LuceneUtil.commit(indexWriter);
        } catch (IOException e) {
            e.printStackTrace();
            LuceneUtil.close(indexWriter);
        }


    }


}

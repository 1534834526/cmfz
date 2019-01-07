package com.baizhi.util;

import com.baizhi.entity.Product;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;
import org.wltea.analyzer.lucene.IKAnalyzer;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class LuceneUtil {
    public static IndexWriterConfig indexWriterConfig = null;
    /**
     * 索引放在磁盘上
     */
    public static Directory dir = null;

    static {
        indexWriterConfig = new IndexWriterConfig(Version.LUCENE_44, new IKAnalyzer());
        try {
            dir = FSDirectory.open(new File("f:/index"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * 索引放在内存中
     */
    /*static {
        indexWriterConfig = new IndexWriterConfig(Version.LUCENE_44, new IKAnalyzer());
        try {
            FSDirectory fsDirectory = FSDirectory.open(new File("f:/index"));
            dir = new RAMDirectory(fsDirectory,new IOContext());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    */
    public static IndexWriter getIndexWriter() {
        IndexWriter indexWriter = null;
        try {
            indexWriter = new IndexWriter(dir, indexWriterConfig);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return indexWriter;
    }


    public static IndexSearcher getIndexSearcher() {
        IndexSearcher indexSearcher = null;
        try {
            IndexReader directoryReader = DirectoryReader.open(FSDirectory.open(new File("f:/index")));
            indexSearcher = new IndexSearcher(directoryReader);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return indexSearcher;

    }


    public static void commit(IndexWriter indexWriter) {
        try {
            indexWriter.commit();
            indexWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void close(IndexWriter indexWriter) {
        try {
            indexWriter.rollback();
            indexWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static Product getProductFromDocument(Document document) {
        Product product = new Product();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String date = document.get("date");
        Date date1 = null;
        try {
            date1 = simpleDateFormat.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        product.setId(document.get("id"));
        product.setProductName(document.get("productName"));
        product.setProductPrice(Double.valueOf(document.get("productPrice")));
        product.setDescription(document.get("description"));
        product.setProductImg(document.get("productImg"));
        product.setStatus(Integer.valueOf(document.get("status")));
        product.setDate(date1);
        product.setPlace(document.get("place"));
        return product;
    }


}

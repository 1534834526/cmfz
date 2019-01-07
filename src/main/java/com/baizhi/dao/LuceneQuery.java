package com.baizhi.dao;

import com.baizhi.entity.Product;
import com.baizhi.util.LuceneUtil;
import org.apache.lucene.document.Document;
import org.apache.lucene.queryparser.classic.MultiFieldQueryParser;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.search.highlight.*;
import org.apache.lucene.util.Version;
import org.wltea.analyzer.lucene.IKAnalyzer;

import java.io.IOException;


/**
 * Lucene的Query实现类对应不同的查询规则 ，一到多个查询条件
 */
public class LuceneQuery {
    public static void main(String[] args) {
        /**第一种查询TermQuery方式*/
      /*  TermQuery termQuery = new TermQuery(new Term("description", "中国人"));
        testQuery(termQuery);*/

        /**第二种查询 多属性查询*/
        String[] strings = {"productName", "description"};
        MultiFieldQueryParser multiFieldQueryParser = new MultiFieldQueryParser(Version.LUCENE_44, strings, new IKAnalyzer());
        Query query1 = null;
        try {
            //查询时会去匹配productName(使用各种分词器时StringField类型的字段都不分词)的全句，会去匹配description(TextField类型字段通过IK分词器会智能分词)分的词
            query1 = multiFieldQueryParser.parse("中国人");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        testQuery(query1);

        /**第三种查询 通配查询  ?单个任意字符   *多个任意字符 */
       /* WildcardQuery wildcardQuery = new WildcardQuery(new Term("description", "实*"));
        testQuery(wildcardQuery);*/

        /**第四种查询 区间查询 */
       /* BooleanQuery booleanClauses = new BooleanQuery();

        NumericRangeQuery<Integer> numericRangeQuery = NumericRangeQuery.newIntRange("id", 1, 2, true, true);
        NumericRangeQuery<Integer> numericRangeQuery2 = NumericRangeQuery.newIntRange("id", 2, 3, true, true);
        booleanClauses.add(numericRangeQuery, BooleanClause.Occur.MUST);
        booleanClauses.add(numericRangeQuery2, BooleanClause.Occur.MUST);

        testQuery(booleanClauses);*/
    }


    public static void testQuery(Query query) {
        //创建索引搜索器
        //通过索引搜索器去搜索
        //提供搜索条件+搜索数量


        //为某些属性字段加高亮，则字段采用的分词方式分好的词对词语进行加高亮
        SimpleHTMLFormatter simpleHTMLFormatter = new SimpleHTMLFormatter("<font color='red'>", "</font>");
        Scorer fragmentScorer = new QueryTermScorer(query);
        Highlighter highlighter = new Highlighter(simpleHTMLFormatter, fragmentScorer);

        IndexSearcher indexSearcher = LuceneUtil.getIndexSearcher();
        //Query query=new TermQuery(new Term("description",param));
        try {
            TopDocs topDocs = indexSearcher.search(query, 100);
            //在索引库查询出的索引编号的数组，索引编号对应文章
            ScoreDoc[] scoreDocs = topDocs.scoreDocs;
            try {
                for (int i = 0; i < scoreDocs.length; i++) {
                    ScoreDoc scoreDoc = scoreDocs[i];
                    /**拿到每个索引编号，通过编号去元数据区查询记录，取出*/
                    /**拿到分数*/
                    int doc = scoreDoc.doc;
                    float score = scoreDoc.score;
                    Document document = indexSearcher.doc(doc);
                    Product product = LuceneUtil.getProductFromDocument(document);
                    System.out.println(score);
                    System.out.println("高亮之前的结果：" + product);
                    System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~");
                    String productName = highlighter.getBestFragment(new IKAnalyzer(), "productName", product.getProductName());
                    String description = highlighter.getBestFragment(new IKAnalyzer(), "description", product.getDescription());
                    System.out.println("高亮之后的结果：" + productName + ":" + description);

                }
            } catch (InvalidTokenOffsetsException e) {
                e.printStackTrace();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }


    }

}

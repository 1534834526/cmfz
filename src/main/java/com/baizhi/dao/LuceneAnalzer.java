package com.baizhi.dao;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.cjk.CJKAnalyzer;
import org.apache.lucene.analysis.cn.smart.SmartChineseAnalyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.apache.lucene.util.Version;
import org.junit.Test;
import org.wltea.analyzer.lucene.IKAnalyzer;

import java.io.IOException;
import java.io.StringReader;

/**
 * 使用自定义测试数据，测试各种分词器的用法
 */
public class LuceneAnalzer {
    String text = "你站在这里不要动，我去给你买几个橘子 百知教育";


    /**
     * 标准分词器，每个字进行分词，为每个字创建索引
     */
    @Test
    public void testStandardAnalyzer() {
        StandardAnalyzer standardAnalyzer = new StandardAnalyzer(Version.LUCENE_44);
        test(standardAnalyzer, text);
        //忽略哪些词，不进行建立索引
        System.out.println(StandardAnalyzer.STOP_WORDS_SET);
    }


    /**
     * 二分法分词器
     */
    @Test
    public void testCJKAnalyzer() {
        CJKAnalyzer cjkAnalyzer = new CJKAnalyzer(Version.LUCENE_44);
        test(cjkAnalyzer, text);
    }


    /**
     * 中文智能分词器
     */
    @Test
    public void testSmartChineseAnalyzer() {
        SmartChineseAnalyzer smartChineseAnalyzer = new SmartChineseAnalyzer(Version.LUCENE_44);
        test(smartChineseAnalyzer, text);
    }


    /**
     * IK分词器 使用IK分词器可以自定义扩展词典和停用词典
     */
    @Test
    public void testIKAnalyzer() {
        IKAnalyzer ikAnalyzer = new IKAnalyzer();
        test(ikAnalyzer, text);
    }


    //分词器，所需分词的文本
    public static void test(Analyzer analyzer, String text) {

        try {
            System.out.println("当前分词器:--->" + analyzer.getClass().getName());

            TokenStream tokenStream = analyzer.tokenStream("content", new StringReader(text));

            tokenStream.addAttribute(CharTermAttribute.class);

            tokenStream.reset();
            while (tokenStream.incrementToken()) {
                CharTermAttribute attribute = tokenStream.getAttribute(CharTermAttribute.class);
                System.out.println(attribute.toString());
            }
            tokenStream.end();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

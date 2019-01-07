package com.baizhi.conf;

import com.baizhi.dao.LuceneMapper;
import com.baizhi.dao.LuceneMapperImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LuceneMapperConf {
    @Bean
    public LuceneMapper getLuceneMapper() {
        return new LuceneMapperImpl();
    }
}

package com.shindhe.config;


import com.shindhe.listener.SkipListenerImpl;
import com.shindhe.postgresql.entity.Student;
import com.shindhe.processor.ItemProcessor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.database.JpaCursorItemReader;
import org.springframework.batch.item.database.JpaItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.JpaTransactionManager;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

@Configuration
public class ApplicationJob {

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Autowired
    private ItemProcessor itemProcessor;

    @Autowired
    @Qualifier("datasource")
    private DataSource datasource;

    @Autowired
    @Qualifier("universitydatasource")
    private DataSource universitydatasource;

    @Autowired
    @Qualifier("postgresdatasource")
    private DataSource postgresdatasource;

    @Autowired
    @Qualifier("postgresqlEntityManagerFactory")
    private EntityManagerFactory postgresqlEntityManagerFactory;

    @Autowired
    @Qualifier("mysqlEntityManagerFactory")
    private EntityManagerFactory mysqlEntityManagerFactory;

    @Autowired
    private SkipListenerImpl skipListenerImpl;

    @Autowired
    private JpaTransactionManager jpaTransactionManager;


    @Bean
    public Job chunkJob() {
        return jobBuilderFactory.get("Chunk Job").incrementer(new RunIdIncrementer()).start(chunkStep()).build();
    }

    private Step chunkStep() {
        return stepBuilderFactory.get("First Chunk Step")
                .<Student, com.shindhe.mysql.entity.Student>chunk(3)
                .reader(jpaCursorItemReader(null, null))
                .processor(itemProcessor)
                .writer(jpaItemWriter())
                .faultTolerant()
                .skip(Throwable.class)
                .skipLimit(100)
                .retryLimit(3)
                .retry(Throwable.class)
                .listener(skipListenerImpl)
                .transactionManager(jpaTransactionManager)
                .build();
    }

    @StepScope
    @Bean
    public JpaCursorItemReader<Student> jpaCursorItemReader(
            @Value("#{jobParameters['currentItemCount']}") Integer currentItemCount,
            @Value("#{jobParameters['maxItemCount']}") Integer maxItemCount) {
        JpaCursorItemReader<Student> jpaCursorItemReader =
                new JpaCursorItemReader<Student>();

        jpaCursorItemReader.setEntityManagerFactory(postgresqlEntityManagerFactory);

        jpaCursorItemReader.setQueryString("From Student");

        jpaCursorItemReader.setCurrentItemCount(currentItemCount);
        jpaCursorItemReader.setMaxItemCount(maxItemCount);

        return jpaCursorItemReader;
    }

    public JpaItemWriter<com.shindhe.mysql.entity.Student> jpaItemWriter() {
        JpaItemWriter<com.shindhe.mysql.entity.Student> jpaItemWriter =
                new JpaItemWriter<com.shindhe.mysql.entity.Student>();

        jpaItemWriter.setEntityManagerFactory(mysqlEntityManagerFactory);

        return jpaItemWriter;
    }
}

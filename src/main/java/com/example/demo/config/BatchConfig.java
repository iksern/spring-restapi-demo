package com.example.demo.config;

import com.example.demo.batch.AccountTransactionProcessor;
import com.example.demo.entity.AccountTransaction;

import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;

/**
 * Main class for batch processing 
 */
@Configuration
@EnableBatchProcessing
public class BatchConfig {
    @Autowired
    public DataSource dataSource;

    @Bean
    public Job job(JobBuilderFactory jobBuilderFactory, StepBuilderFactory stepBuilderFactory,
            ItemReader<AccountTransaction> itemReader,
            ItemProcessor<AccountTransaction, AccountTransaction> itemProcessor,
            ItemWriter<AccountTransaction> itemWriter) {

        Step step = stepBuilderFactory.get("LoadTxtFileStep").<AccountTransaction, AccountTransaction>chunk(100)
                .reader(itemReader).processor(itemProcessor).writer(itemWriter).build();

        return jobBuilderFactory.get("LoadTxtFileJob").incrementer(new RunIdIncrementer()).start(step).build();
    }

    @Bean
    public AccountTransactionProcessor itemProcessor() {
        return new AccountTransactionProcessor();
    }

    @Bean
    public FlatFileItemReader<AccountTransaction> itemReader() {
        FlatFileItemReader<AccountTransaction> flatFileItemReader = new FlatFileItemReader<>();
        flatFileItemReader.setResource(new FileSystemResource("src/main/resources/dataSource.txt"));
        flatFileItemReader.setName("TXT-Reader");
        flatFileItemReader.setLinesToSkip(1);
        flatFileItemReader.setLineMapper(lineMapper());
        return flatFileItemReader;
    }

    @Bean
    public LineMapper<AccountTransaction> lineMapper() {
        DefaultLineMapper<AccountTransaction> defaultLineMapper = new DefaultLineMapper<>();
        DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();

        lineTokenizer.setDelimiter("|");
        lineTokenizer.setStrict(false);
        lineTokenizer.setNames("ACCOUNT_NUMBER", "TRX_AMOUNT", "DESCRIPTION", "TRX_DATE", "TRX_TIME", "CUSTOMER_ID");

        BeanWrapperFieldSetMapper<AccountTransaction> fieldSetMapper = new BeanWrapperFieldSetMapper<>();
        fieldSetMapper.setTargetType(AccountTransaction.class);

        defaultLineMapper.setLineTokenizer(lineTokenizer);
        defaultLineMapper.setFieldSetMapper(fieldSetMapper);

        return defaultLineMapper;
    }

    @Bean
    public JdbcBatchItemWriter<AccountTransaction> itemWriter() {
        JdbcBatchItemWriter<AccountTransaction> writer = new JdbcBatchItemWriter<AccountTransaction>();
        writer.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>());
        writer.setSql(
                "INSERT INTO account_transaction (account_number, trx_amount, description, trx_date, trx_time, customer_id) "
                        + "VALUES (:accountNumber, :trxAmount, :description, :trxDate, :trxTime, :customerId)");
        writer.setDataSource(dataSource);
        return writer;
    }
}

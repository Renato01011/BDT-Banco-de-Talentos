package com.fractal.bancodetalentos.config;

import lombok.RequiredArgsConstructor;
import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import javax.sql.DataSource;


@Configuration
@RequiredArgsConstructor
public class DataSourceConfig {

    private final Environment environment;

    @Bean
    public DataSource dataSource() {
        BasicDataSource dataSource = new BasicDataSource();
        String url = String.format("jdbc:mysql://localhost:%s/%s",
                environment.getProperty("bdt.port"),
                environment.getProperty("bdt.dbname"));
        dataSource.setUrl(url);
        dataSource.setUsername(environment.getProperty("bdt.user"));
        dataSource.setPassword(environment.getProperty("bdt.password"));
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setInitialSize(5);
        dataSource.setMaxTotal(10);
        return dataSource;
    }
}

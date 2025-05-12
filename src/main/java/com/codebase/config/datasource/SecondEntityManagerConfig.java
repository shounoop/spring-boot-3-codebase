package com.codebase.config.datasource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

import javax.sql.DataSource;

@Configuration
@EnableJpaRepositories(
        basePackages = "com.codebase.repository.second",
        entityManagerFactoryRef = "secondLocalContainerEntityManagerFactoryBean"
)
public class SecondEntityManagerConfig {

    @Bean
    LocalContainerEntityManagerFactoryBean secondLocalContainerEntityManagerFactoryBean(EntityManagerFactoryBuilder entityManagerFactoryBuilder, @Qualifier("secondDataSource") DataSource dataSource) {

        return entityManagerFactoryBuilder
                .dataSource(dataSource)
                .packages("com.codebase.model.second.entity")
                .build();
    }
}

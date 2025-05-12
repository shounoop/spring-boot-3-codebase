package com.codebase.config.datasource.second;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.Objects;

/**
 * This class configures the second database entity manager factory and transaction manager.
 * It uses Spring Data JPA to manage the second database.
 */
@Configuration
@EnableJpaRepositories(
        basePackages = "com.codebase.repository.second",
        entityManagerFactoryRef = "secondLocalContainerEntityManagerFactoryBean",
        transactionManagerRef = "secondTransactionManager"
)
public class SecondEntityManagerConfig {

    @Bean
    LocalContainerEntityManagerFactoryBean secondLocalContainerEntityManagerFactoryBean(EntityManagerFactoryBuilder entityManagerFactoryBuilder, @Qualifier("secondDataSource") DataSource dataSource) {

        return entityManagerFactoryBuilder
                .dataSource(dataSource)
                .packages("com.codebase.model.second.entity")
                .build();
    }

    // This is the transaction manager for the secondary database
    @Bean
    PlatformTransactionManager secondTransactionManager(LocalContainerEntityManagerFactoryBean localContainerEntityManagerFactoryBean) {

        // Create a transaction manager for the secondary database using the entity manager factory created above
        return new JpaTransactionManager(Objects.requireNonNull(localContainerEntityManagerFactoryBean.getObject()));
    }
}

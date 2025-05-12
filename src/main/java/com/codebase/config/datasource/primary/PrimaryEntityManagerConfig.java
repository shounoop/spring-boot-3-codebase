package com.codebase.config.datasource.primary;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Objects;

/**
 * This class configures the primary database entity manager factory and transaction manager.
 * It uses Spring Data JPA to manage the primary database.
 */
@EnableTransactionManagement
@Configuration
@EnableJpaRepositories(
        basePackages = "com.codebase.repository.primary",
        entityManagerFactoryRef = "primaryLocalContainerEntityManagerFactoryBean",
        transactionManagerRef = "primaryTransactionManager"
)
public class PrimaryEntityManagerConfig {

    @Primary
    @Bean
    LocalContainerEntityManagerFactoryBean primaryLocalContainerEntityManagerFactoryBean(EntityManagerFactoryBuilder entityManagerFactoryBuilder, @Qualifier("primaryDataSource") DataSource dataSource) {

        return entityManagerFactoryBuilder
                .dataSource(dataSource)
                .packages("com.codebase.model.primary.entity")
                .build();
    }

    // This is the transaction manager for the primary database
    @Bean
    PlatformTransactionManager primaryTransactionManager(@Qualifier("primaryLocalContainerEntityManagerFactoryBean") LocalContainerEntityManagerFactoryBean primaryLocalContainerEntityManagerFactoryBean) {

        // Create a transaction manager for the primary database using the entity manager factory created above
        return new JpaTransactionManager(Objects.requireNonNull(primaryLocalContainerEntityManagerFactoryBean.getObject()));
    }
}

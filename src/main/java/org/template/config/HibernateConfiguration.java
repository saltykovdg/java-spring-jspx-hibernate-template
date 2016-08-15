package org.template.config;

import org.template.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "org.template.dao")
public class HibernateConfiguration {
    @Autowired
    private Environment environment;

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(environment.getProperty(Constants.Property.HIBERNATE_CONNECTION_DRIVER_CLASS));
        dataSource.setUrl(environment.getProperty(Constants.Property.HIBERNATE_CONNECTION_URL));
        dataSource.setUsername(environment.getProperty(Constants.Property.HIBERNATE_CONNECTION_USERNAME));
        dataSource.setPassword(environment.getProperty(Constants.Property.HIBERNATE_CONNECTION_PASSWORD));
        return dataSource;
    }

    @Bean
    public JpaTransactionManager transactionManager() throws ClassNotFoundException {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory());
        return transactionManager;
    }

    @Bean
    public EntityManagerFactory entityManagerFactory() {
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        vendorAdapter.setGenerateDdl(true);

        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setJpaVendorAdapter(vendorAdapter);
        em.setDataSource(dataSource());
        em.setPackagesToScan("org.template.entity");
        em.setJpaProperties(hibernateProperties());
        em.afterPropertiesSet();

        return em.getObject();
    }

    private Properties hibernateProperties() {
        Properties properties = new Properties();
        properties.put(Constants.Property.HIBERNATE_SHOW_SQL, environment.getProperty(Constants.Property.HIBERNATE_SHOW_SQL));
        properties.put(Constants.Property.HIBERNATE_DIALECT, environment.getProperty(Constants.Property.HIBERNATE_DIALECT));
        properties.put(Constants.Property.HIBERNATE_CONNECTION_CHARSET, environment.getProperty(Constants.Property.HIBERNATE_CONNECTION_CHARSET));
        properties.put(Constants.Property.HIBERNATE_HBM2DDL_AUTO, environment.getProperty(Constants.Property.HIBERNATE_HBM2DDL_AUTO));
        properties.put(Constants.Property.HIBERNATE_CONNECTION_RELEASE_MODE, environment.getProperty(Constants.Property.HIBERNATE_CONNECTION_RELEASE_MODE));
        properties.put(Constants.Property.HIBERNATE_FORMAT_SQL, environment.getProperty(Constants.Property.HIBERNATE_FORMAT_SQL));
        properties.put(Constants.Property.HIBERNATE_JDBC_BATCH_SIZE, environment.getProperty(Constants.Property.HIBERNATE_JDBC_BATCH_SIZE));
        properties.put(Constants.Property.HIBERNATE_CONNECTION_POOL_SIZE, environment.getProperty(Constants.Property.HIBERNATE_CONNECTION_POOL_SIZE));
        properties.put(Constants.Property.HIBERNATE_TRANSACTION_FACTORY_CLASS, environment.getProperty(Constants.Property.HIBERNATE_TRANSACTION_FACTORY_CLASS));
        properties.put(Constants.Property.HIBERNATE_CURRENT_SESSION_CONTEXT_CLASS, environment.getProperty(Constants.Property.HIBERNATE_CURRENT_SESSION_CONTEXT_CLASS));
        return properties;
    }
}
package com.heliton.config;

// Metodos //
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

// Package Scan //
import com.heliton.WebApplication;

@EnableJpaRepositories(basePackageClasses = WebApplication.class)
@Configuration 
@EnableTransactionManagement
public class JpaMySqlConfig {
	
	// Atributos (arquivo propriedade) //
    @Value("${spring.datasource.driverClassName}")
	private String DRIVER; 
	@Value("${spring.datasource.url}")
	private String URL; 
	@Value("${spring.datasource.password}")
	private String PASSWORD; 
	@Value("${spring.datasource.username}")
	private String USERNAME; 
	@Value("${spring.jpa.properties.hibernate.dialect}")
	private String DIALECT; 
	@Value("${spring.jpa.properties.hibernate.show_sql}")
	private String SHOW_SQL; 
	@Value("${spring.jpa.hibernate.ddl-auto}")
	private String HBM2DDL_AUTO;
	
	// Fabrica Hibernate //
	@Bean
	public JpaTransactionManager transactionManager(EntityManagerFactory emf) {
		return new JpaTransactionManager(emf);
	}

	// UnitName //
	@Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory (DataSource dataSource, JpaVendorAdapter jpaVendorAdapter) {
		Properties properties = new Properties();
		properties.put("spring.jpa.properties.hibernate.dialect", DIALECT);
		properties.put("spring.jpa.properties.hibernate.show_sql", SHOW_SQL);
		properties.put("spring.jpa.hibernate.ddl-auto", HBM2DDL_AUTO);
		//
		LocalContainerEntityManagerFactoryBean bean = new LocalContainerEntityManagerFactoryBean();
		bean.setDataSource(dataSource);
		bean.setJpaVendorAdapter(jpaVendorAdapter);		
		bean.setPackagesToScan(WebApplication.class.getPackage().getName());		
		bean.setPersistenceUnitName("ConexaoMySQL");
		bean.setJpaProperties(properties);
		return bean;
	}

	// MySql //
	@Bean
	public JpaVendorAdapter jpaVendorAdapter() {HibernateJpaVendorAdapter bean = new HibernateJpaVendorAdapter();
		bean.setDatabase(Database.MYSQL);
		bean.setGenerateDdl(true);
		bean.setShowSql(true);
		return bean;
	}    
 
	// DataSource //
	@Bean
	public DataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(DRIVER);
		dataSource.setUrl(URL);
		dataSource.setUsername(USERNAME);
		dataSource.setPassword(PASSWORD);
		return dataSource;
	}
}
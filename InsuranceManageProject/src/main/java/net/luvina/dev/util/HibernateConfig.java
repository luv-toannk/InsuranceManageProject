package net.luvina.dev.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
public class HibernateConfig {
	
	@Value("${spring.datasource.driver-class-name}")
	private String dbDriver;
	
	@Value("${spring.datasource.url}")
	private String dbURL;
	
	@Value("${spring.datasource.username}")
	private String dbUser;
	
	@Value("${spring.datasource.password}")
	private String dbPassword;
	
	@Value("${spring.jpa.properties.hibernate.dialect}")
	private String hibernateDialect;
	
	@Value("${spring.jpa.show-sql}")
	private String hibernateShowSQL;
	
	@Value("${spring.jpa.properties.hibernate.current_session_context_class}")
	private String hibernateCurrentSession;
	
	@Value("net.luvina.dev.model")
	private String packageToScan;
	
	
	@Bean
	public DataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(dbDriver);
		dataSource.setUrl(dbURL);
		dataSource.setUsername(dbUser);
		dataSource.setPassword(dbPassword);
		return dataSource;
	}
	
	@Bean
	public LocalSessionFactoryBean sessionFactory() {
		LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
		sessionFactory.setDataSource(dataSource());
		sessionFactory.setPackagesToScan(packageToScan);
		Properties hibernateProperties = new Properties();
		hibernateProperties.put("spring.jpa.properties.hibernate.dialect", hibernateDialect);
		hibernateProperties.put("spring.jpa.show-sql", hibernateShowSQL);
		hibernateProperties.put("spring.jpa.properties.hibernate.current_session_context_class",
				hibernateCurrentSession);
		sessionFactory.setHibernateProperties(hibernateProperties);
		return sessionFactory;
	}
	
	@Bean
	public HibernateTransactionManager transactionManager() {
		HibernateTransactionManager txManager = new HibernateTransactionManager();
		txManager.setSessionFactory(sessionFactory().getObject());
		return txManager;
	}
	
}

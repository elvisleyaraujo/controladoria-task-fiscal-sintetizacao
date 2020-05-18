package br.com.ithappens.controladoria.config;

import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

public interface IDBConfig {

  DataSourceTransactionManager dataSourceTransactionManager(DataSource dataSource);

  DataSource dataSource();

  SqlSessionFactoryBean sessionFactoryBean(final DataSource dataSource) throws Exception;
}
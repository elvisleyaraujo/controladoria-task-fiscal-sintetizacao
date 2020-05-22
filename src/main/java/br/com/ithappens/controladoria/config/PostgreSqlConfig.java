package br.com.ithappens.controladoria.config;

import br.com.ithappens.controladoria.mapper.postgresql.ConfiguracaoIntegradorMapper;
import br.com.ithappens.controladoria.mapper.postgresql.FilialMapper;
import br.com.ithappens.controladoria.mapper.postgresql.IntegracaoFiscalMapper;
import br.com.ithappens.controladoria.mapper.postgresql.ValidacaoFiscalMapper;
import com.zaxxer.hikari.HikariDataSource;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.mapper.MapperFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.ResourcePatternUtils;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
@EnableAsync
public class PostgreSqlConfig implements IDBConfig {

  public static final String TRANSACTION_POSTGRESQL     = "transactionPostgresql";
  private static final String SESSION_FACTORY_POSTGRESQL = "postgresqlSessionFactory";
  private static final String DATASOURCE_POSTGRESQL      = "app.datasource.postgresql";
  @Autowired
  private ResourceLoader resourceLoader;
  @Value("${mybatis.type-handlers-package}")
  private String typeHandlerLocation;

  @Bean("execControladoriaFiscalRecuperarTaskBean")
  @Primary
  public ThreadPoolTaskExecutor threadPoolTaskExecutorBean() {
    ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
    executor.setCorePoolSize(10);
    executor.setMaxPoolSize(12);
    executor.setThreadNamePrefix("controladoria-task-fiscal-sintetizacao");
    executor.setWaitForTasksToCompleteOnShutdown(true);
    executor.initialize();
    return executor;
  }

  @Primary
  @Bean(name = DATASOURCE_POSTGRESQL)
  @ConfigurationProperties(prefix = DATASOURCE_POSTGRESQL)
  public DataSource dataSource() {
    return new HikariDataSource();
  }

  @Bean(name = TRANSACTION_POSTGRESQL)
  public DataSourceTransactionManager dataSourceTransactionManager(@Qualifier(DATASOURCE_POSTGRESQL) DataSource dataSource) {
    DataSourceTransactionManager dtm = new DataSourceTransactionManager(dataSource);
    return dtm;
  }

  @Primary
  @Bean(name = SESSION_FACTORY_POSTGRESQL, destroyMethod = "")
  public SqlSessionFactoryBean sessionFactoryBean(@Qualifier(DATASOURCE_POSTGRESQL) final DataSource dataSource) throws Exception {
    final SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
    sqlSessionFactoryBean.setDataSource(dataSource);
    sqlSessionFactoryBean.setMapperLocations(ResourcePatternUtils.getResourcePatternResolver(this.resourceLoader)
            .getResources("classpath*:**/mappers/postgresql/*Mapper.xml"));
    sqlSessionFactoryBean.setTypeHandlersPackage("br.com.ithappens.contabil.task.mapper.typehandler");
    return sqlSessionFactoryBean;
  }

  @Bean
  public MapperFactoryBean<IntegracaoFiscalMapper> integracaoFiscalMapper(
      @Qualifier(SESSION_FACTORY_POSTGRESQL) SqlSessionFactoryBean sessionFactoryBean) throws Exception {
    MapperFactoryBean<IntegracaoFiscalMapper> factoryBean =
        new MapperFactoryBean(IntegracaoFiscalMapper.class);
    factoryBean.setSqlSessionFactory(sessionFactoryBean.getObject());
    return factoryBean;
  }

  @Bean
  public MapperFactoryBean<FilialMapper> filialMapper(
          @Qualifier(SESSION_FACTORY_POSTGRESQL) SqlSessionFactoryBean sessionFactoryBean) throws Exception {
    MapperFactoryBean<FilialMapper> factoryBean =
            new MapperFactoryBean(FilialMapper.class);
    factoryBean.setSqlSessionFactory(sessionFactoryBean.getObject());
    return factoryBean;
  }

  @Bean
  public MapperFactoryBean<ValidacaoFiscalMapper> validacaoFiscalMapper(
          @Qualifier(SESSION_FACTORY_POSTGRESQL) SqlSessionFactoryBean sessionFactoryBean) throws Exception {
    MapperFactoryBean<ValidacaoFiscalMapper> factoryBean =
            new MapperFactoryBean(ValidacaoFiscalMapper.class);
    factoryBean.setSqlSessionFactory(sessionFactoryBean.getObject());
    return factoryBean;
  }

  @Bean
  public MapperFactoryBean<ConfiguracaoIntegradorMapper> configuracaoIntegradorMapper(
          @Qualifier(SESSION_FACTORY_POSTGRESQL) SqlSessionFactoryBean sessionFactoryBean) throws Exception {
    MapperFactoryBean<ConfiguracaoIntegradorMapper> factoryBean =
            new MapperFactoryBean(ConfiguracaoIntegradorMapper.class);
    factoryBean.setSqlSessionFactory(sessionFactoryBean.getObject());
    return factoryBean;
  }


}



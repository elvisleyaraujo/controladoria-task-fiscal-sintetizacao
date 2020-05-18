package br.com.ithappens.controladoria;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.task.configuration.EnableTask;
import org.springframework.cloud.dataflow.rest.client.config.DataFlowClientAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.AsyncConfigurerSupport;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

@SpringBootApplication(
        exclude = {
                DataFlowClientAutoConfiguration.class,
                HibernateJpaAutoConfiguration.class
        })
@EnableTask
public class App {
  public static void main(String[] args) {
    SpringApplication.run(App.class, args);
  }

}
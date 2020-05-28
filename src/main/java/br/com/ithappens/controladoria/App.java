package br.com.ithappens.controladoria;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.cloud.dataflow.rest.client.config.DataFlowClientAutoConfiguration;
import org.springframework.cloud.task.configuration.EnableTask;

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
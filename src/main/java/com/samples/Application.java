package com.samples;

import com.samples.transfer.SampleConsumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * Created by edara on 8/12/17.
 */
@SpringBootApplication
@ComponentScan(basePackages = {"com.samples"})
@EnableScheduling
public class Application implements CommandLineRunner{
    @Autowired
    public ApplicationContext context;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);

    }

    @Override
    public void run(String... args) throws Exception {
        context.getBean(SampleConsumer.class).consume();
    }
}
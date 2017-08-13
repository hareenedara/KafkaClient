package com.samples.config;

import com.samples.model.LineData;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.Arrays;
import java.util.Properties;

/**
 * Created by edara on 8/12/17.
 */
@Configuration
@EnableConfigurationProperties
@EnableAsync
@ComponentScan(basePackages = {"com.samples"})
public class AppConfig {

    @Value("${topic.consumer.groupId}")
    String groupID;
    @Value("${topic.consumer.enableAutoCommit}")
    boolean enableAutoCommit;
    @Value("${topic.consumer.autoCommitIntervalms}")
    int autoCommitIntervalms;
    @Value("${broker.servers}")
    String brokerServers;
    @Value("${topic.com.samples.transfer.acks}")
    String acks;
    @Value("${topic.consumer.name}")
    String topicName;


    // consumer
    @Bean(destroyMethod ="close" )
    public KafkaConsumer<Integer,LineData> consumer() {
        Properties properties = new Properties();
        properties.put("bootstrap.servers", brokerServers);
        properties.put("group.id",groupID);
        properties.put("enable.auto.commit",enableAutoCommit);
        properties.put("auto.commit.interval.ms",autoCommitIntervalms);
        //props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        //props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        properties.put("key.deserializer", "org.apache.kafka.common.serialization.IntegerDeserializer");
        properties.put("value.deserializer", "com.samples.model.LineDataDeserializer");
        KafkaConsumer<Integer,LineData> consumer = new KafkaConsumer(properties);
        consumer.subscribe(Arrays.asList(topicName));
        return consumer;
    }

    // com.samples.transfer
    @Bean(destroyMethod = "close")
    public KafkaProducer<Integer,LineData> producer(){
        Properties properties = new Properties();
        properties.put("bootstrap.servers",brokerServers);
        properties.put("acks","all");
        properties.put("retries",2);
        properties.put("batch.size", 10);
        properties.put("buffer.memory",1000000);
        //props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        //props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        properties.put("key.serializer","org.apache.kafka.common.serialization.IntegerSerializer");
        properties.put("value.serializer","com.samples.model.LineDataSerializer");
        KafkaProducer<Integer,LineData> producer = new KafkaProducer<>(properties);
        return producer;
    }


}

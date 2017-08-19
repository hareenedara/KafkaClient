package com.samples.transfer;

import com.samples.model.LineData;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by edara on 8/12/17.
 */

@Component
public class SampleProducer {
    public static final String dataFile = "/Users/edara/Downloads/test1.txt";
    private static final Logger log = LoggerFactory.getLogger(SampleProducer.class);

    @Value("${topic.consumer.name}")
    public String topicName;

    @Autowired
    KafkaProducer producer;

    BufferedReader reader;
    int count = 1;

    @PostConstruct
    public void readFile() throws FileNotFoundException {
        reader = new BufferedReader(new FileReader(dataFile));

    }
    @PreDestroy
    public void cleanup() throws IOException {
        reader.close();
    }

    @Scheduled(fixedDelay=5)
    public void sendMessages() throws IOException, InterruptedException {
        String msg = "";

        if ((msg = reader.readLine()) != null) {
            ProducerRecord<Integer, LineData> line = new ProducerRecord<Integer, LineData>(topicName, new Integer(count++), new LineData(msg));
            producer.send(line);

        }

    }

}


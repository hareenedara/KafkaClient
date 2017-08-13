package com.samples.transfer;

import com.samples.model.LineData;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * Created by edara on 8/12/17.
 */
@Component
public class SampleConsumer {

    @Autowired
    KafkaConsumer<Integer,LineData> consumer;

    public void consume() throws InterruptedException {
        while(true) {
            ConsumerRecords<Integer,LineData> records = consumer.poll(50);
            for(ConsumerRecord record: records) {
                System.out.printf("offset = %d, key = %s, value = %s%n", record.offset(), record.key(), ((LineData)record.value()).getLine());

            }

        }
    }
}

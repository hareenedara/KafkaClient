package com.samples.transfer;

import com.samples.model.LineData;
import com.samples.service.ConsumerService;
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
    @Autowired
    ConsumerService consumerService;

    public void consume() throws Exception {
        while(true) {
            ConsumerRecords<Integer,LineData> records = consumer.poll(1000);
            for(ConsumerRecord record: records) {
                    try {
                        consumerService.service(record);
                        consumer.commitSync();
                    }catch(Exception ex) {
                        System.out.println(ex);
                    }
            }

        }
    }
}

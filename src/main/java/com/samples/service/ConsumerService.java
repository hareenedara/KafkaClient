package com.samples.service;

import com.samples.model.LineData;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * Created by edara on 8/13/17.
 */
@Component
public class ConsumerService {
    //@Async
    public void service(ConsumerRecord<Integer,LineData> record) throws Exception {
        Thread.sleep(100);

        if(((Integer) record.key()) == 211 ) {
            throw new Exception();
        }
            System.out.printf("Thread = %s ,offset = %d, key = %s, value = %s%n",Thread.currentThread().getName(),record.offset(), record.key(), ((LineData)record.value()).getLine());


    }
}

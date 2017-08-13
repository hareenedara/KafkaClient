package com.samples.model;

import org.apache.kafka.common.serialization.Deserializer;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Map;

/**
 * Created by edara on 8/12/17.
 */
public class LineDataDeserializer implements Deserializer<LineData> {

    public LineDataDeserializer(){}

    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {

    }

    @Override
    public LineData deserialize(String topic, byte[] data) {
        ByteArrayInputStream bin=null;
        ObjectInputStream oin = null;
        try {
             bin = new ByteArrayInputStream(data);
             oin = new ObjectInputStream(bin);
            return (LineData) oin.readObject();
        }catch (Exception ex) {
            ex.printStackTrace();
        }finally{
            if(bin != null)
                try {
                    bin.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            if(oin != null)
                try {
                    oin.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }
        return null;
    }

    @Override
    public void close() {

    }
}

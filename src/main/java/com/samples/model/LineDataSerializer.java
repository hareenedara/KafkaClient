package com.samples.model;

import org.apache.kafka.common.serialization.Serializer;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Map;

/**
 * Created by edara on 8/12/17.
 */
public class LineDataSerializer implements Serializer<LineData> {

    public LineDataSerializer(){}

    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {

    }

    @Override
    public byte[] serialize(String topic, LineData data) {
        ByteArrayOutputStream bout=null;
        ObjectOutputStream oout =null;
        try {
            bout = new ByteArrayOutputStream();
             oout = new ObjectOutputStream(bout);
            oout.writeObject(data);
        }catch(Exception ex) {
            ex.printStackTrace();
        }finally{
            if(bout != null)
                try {
                    bout.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            if(oout != null)
                try {
                    oout.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }
        return bout!= null ? bout.toByteArray():null;
    }

    @Override
    public void close() {

    }
}

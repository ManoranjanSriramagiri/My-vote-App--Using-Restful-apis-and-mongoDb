package demo;


import java.util.*;

import kafka.javaapi.producer.Producer;
import kafka.producer.KeyedMessage;
import kafka.producer.ProducerConfig;
 
public class TestProducer {
	public void Producer(List<String> modEmailIdArray) 
	{
        
 
        Properties props = new Properties();
        props.put("metadata.broker.list", "54.149.84.25:9092");
        props.put("serializer.class", "kafka.serializer.StringEncoder");
        props.put("partitioner.class", "demo.SimplePartitioner");
        props.put("request.required.acks", "1");
 
        ProducerConfig config = new ProducerConfig(props);
 
        Producer<String, String> producer = new Producer<String, String>(config);
        Iterator<String> itModResult = modEmailIdArray.iterator();
        
       
        	while(itModResult.hasNext())
            { 
                   KeyedMessage<String, String> data = new KeyedMessage<String, String>("cmpe273-topic",itModResult.next());
                   producer.send(data);
            }
        	producer.close();
        
        
    }
}
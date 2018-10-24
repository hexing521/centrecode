package kafka.demo;

import org.apache.kafka.clients.producer.*;

import java.util.Properties;
import java.util.concurrent.Future;

/**
 *【Kafka producer Demo】
 */
public class ProdectDemo {


    public static void main(String[] args) {

        Properties props = new Properties();
        //broker地址
        props.put("bootstrap.servers", "node1:9092,node2:9092,node3:9092");
        //请求时候需要验证
        props.put("acks", "all");
        //请求失败时候需要重试
        props.put("retries", 0);
        //指定消息key序列化方式
        props.put("key.serializer",
                "org.apache.kafka.common.serialization.StringSerializer");
        //指定消息本身的序列化方式
        props.put("value.serializer",
                "org.apache.kafka.common.serialization.StringSerializer");


        Producer<String, String> producer = new KafkaProducer<String, String>(props);
        ProducerRecord<String, String> recoder=new ProducerRecord<String, String>("test","test1","test value");
        //同步发送消息
        try {
            RecordMetadata recordMetadata=producer.send(recoder).get();
        } catch (Exception e) {
            e.printStackTrace();
        }

        //异步发送消息
        try {
            producer.send(recoder,new DemoProducerCallback());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

 class DemoProducerCallback implements Callback {
    public void onCompletion(RecordMetadata recordMetadata, Exception e) {
        if (e != null) {//如果Kafka返回一个错误，onCompletion方法抛出一个non null异常。
            e.printStackTrace();//对异常进行一些处理，这里只是简单打印出来
        }
    }
}
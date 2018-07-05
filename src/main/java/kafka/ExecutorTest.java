package kafka;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ExecutorTest {

    public static void main(String[] args) {

        int numConsumers = 3;
        String groupId = "activityPar-test";
        List<String> topics = Arrays.asList("testlwq");
        final  ExecutorService executor = Executors.newFixedThreadPool(numConsumers);

        final List<ConsumerLoop> consumers = new ArrayList<ConsumerLoop>();
        for (int i = 0; i < numConsumers; i++) {
            ConsumerLoop consumer = new ConsumerLoop(i, groupId, topics);
            consumers.add(consumer);
            executor.submit(consumer);
        }

//        Runtime.getRuntime().addShutdownHook(new Thread() {
//            @Override
//            public void run() {
//                for (ConsumerLoop consumer : consumers) {
//                    consumer.shutdown();
//                }
//                executor.shutdown();
//                try {
//                    executor.awaitTermination(5000, TimeUnit.MILLISECONDS);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//        });

    }
}

package com.andrejczyn;


import com.google.cloud.pubsub.v1.Publisher;
import com.google.protobuf.ByteString;
import com.google.pubsub.v1.PubsubMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class EventPublisher {

    private static Logger logger = LoggerFactory.getLogger(EventPublisher.class);

    public void send(Long count) throws IOException, ExecutionException, InterruptedException, TimeoutException {
        Publisher publisher = Publisher.newBuilder("projects/radiant-arcade-268109/topics/spark-result")
                .build();

        ByteString data = ByteString.copyFromUtf8(count.toString());
        PubsubMessage pubsubMessage = PubsubMessage.newBuilder()
                .setData(data)
                .build();
        logger.info(pubsubMessage.toString());
        logger.info("Start submit");
        String result = publisher.publish(pubsubMessage).get(
                30L, TimeUnit.SECONDS
        );

        logger.info("End submit: " + result);
    }
}

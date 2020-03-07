package com.andrejczyn;


import com.google.api.gax.batching.BatchingSettings;
import com.google.cloud.pubsub.v1.Publisher;
import com.google.protobuf.ByteString;
import com.google.pubsub.v1.PubsubMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

public class EventPublisher {

    private static Logger logger = LoggerFactory.getLogger(EventPublisher.class);

    private static final String TOPIC_NAME = "projects/radiant-arcade-268109/topics/spark-result";

    private static EventPublisher instance;

    public void send(String line) throws IOException, ExecutionException, InterruptedException, TimeoutException {
        Publisher publisher = Publisher.newBuilder(TOPIC_NAME)
                .setBatchingSettings(BatchingSettings.newBuilder()
                        .setIsEnabled(true)
                        .build()
                )
                .build();

        ByteString data = ByteString.copyFromUtf8(line);
        PubsubMessage pubsubMessage = PubsubMessage.newBuilder()
                .setData(data)
                .build();

        logger.debug(pubsubMessage.toString());
        logger.debug("Start submit");
        String result = publisher.publish(pubsubMessage).get();

        logger.debug("End submit: " + result);
    }

    public static EventPublisher getInstance() {
        if (instance == null) {
            instance = new EventPublisher();
        }

        return instance;
    }
}

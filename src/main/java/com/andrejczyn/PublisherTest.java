package com.andrejczyn;

import java.io.IOException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

public class PublisherTest {
    public static void main(String[] args) throws InterruptedException, ExecutionException, TimeoutException, IOException {
        EventPublisher publisher = new EventPublisher();
        publisher.send(3L);
    }
}

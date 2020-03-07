package com.andrejczyn;

import org.apache.spark.sql.SparkSession;

import java.io.IOException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;


public class HelloSpark {
    public static void main(String[] args) throws InterruptedException, ExecutionException, IOException, TimeoutException {
        EventPublisher pub = new EventPublisher();
        SparkSession spark = SparkSession.builder()
                .appName("Spark SQL basic example")
                .getOrCreate();

//        long count = spark.read().textFile("gs://radiant-arcade-268109-main/test.txt").count();
        pub.send(4L);

    }
}

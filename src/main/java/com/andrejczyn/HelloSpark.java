package com.andrejczyn;

import org.apache.spark.sql.SparkSession;

import java.io.IOException;
import java.util.Objects;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;


public class HelloSpark {

    public static void main(String[] args) throws InterruptedException, ExecutionException, IOException, TimeoutException {

        SparkSession spark = SparkSession.builder()
                .appName("Spark SQL basic example")
                .getOrCreate();

        spark.read().textFile("gs://radiant-arcade-268109-main/test.txt")
                .foreach((String line) -> {
                    EventPublisher.getInstance().send(line);
                });
    }
}

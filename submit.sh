#!/bin/bash
./gradlew tasks shadowJar

if [ $? -ne 0 ]; then
    echo FAIL
    exit 1
fi

gcloud dataproc jobs submit spark --cluster cluster-7c6d --region europe-west1 --class com.andrejczyn.HelloSpark --jars ./build/libs/SparkFun-all.jar -- 1000
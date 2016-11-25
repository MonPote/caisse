#! /bin/sh
docker build -f Dockerfile_test01 -t="java-build:v01" .
docker build -f Dockerfile_test02 -t="java-test:v01" .
docker run -it --dns="8.8.8.8" --dns="8.8.4.4" -p 8080:8080 java-test:v01
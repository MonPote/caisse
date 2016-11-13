#! /bin/sh
docker build -t="v1" .
docker run -it --dns="8.8.8.8" --dns="8.8.4.4" -p 8080:8080 v1
#! /bin/sh
docker build -t="v1" .
docker run -it -p 8080:8080 v1
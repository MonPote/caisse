#! /bin/sh
docker build -t caisse .
docker run -it --dns="8.8.8.8" --dns="8.8.4.4" -p 8080:8080 caisse

#! /bin/sh

add-apt-repository ppa:webupd8team/java
apt-get update
apt-get install -y oracle-java8-installer
apt-get install -y oracle-java8-set-default

apt-get install -y maven zip
apt-get install -y postgresql postgresql-contrib

wget http://download.jboss.org/wildfly/8.2.0.Final/wildfly-8.2.0.Final.zip
unzip wildfly-8.2.0.Final.zip

postgres bash -c "psql -c \"CREATE DATABASE entrepot;\""
postgres bash -c "psql -c \"CREATE USER dev WITH PASSWORD 'root';\""
postgres bash -c "psql -c \"grant all privileges on database entrepot to dev;\""
psql entrepot -f init.sql

#! /bin/sh

sudo add-apt-repository ppa:webupd8team/java
sudo apt-get update
sudo apt-get install -y oracle-java8-installer
sudo apt-get install -y oracle-java8-set-default

sudo apt-get install -y maven zip
sudo apt-get install -y postgresql postgresql-contrib

wget http://download.jboss.org/wildfly/8.2.0.Final/wildfly-8.2.0.Final.zip
unzip wildfly-8.2.0.Final.zip
x
sudo -u postgres bash -c "psql -c \"CREATE DATABASE entrepot;\""
sudo -u postgres bash -c "psql -c \"CREATE USER dev WITH PASSWORD 'root';\""
sudo -u postgres bash -c "psql -c \"grant all privileges on database entrepot to dev;\""
psql entrepot -f init.sql
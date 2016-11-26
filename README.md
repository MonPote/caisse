# Application blanche java

### Contributors
  - mace_s
  - zheng_v (Nea)
  - ghirig_s (Sikred)
  - claebo_c
  - lasne_g
  - capes_h
  - lebrun_x


### Description
The goal of this project is to create a commun base for every java applications for USRI

### How to use
First you have to download docker with
```sh
sudo apt-get install docker.io
```

To use the project you have to install postgresql on your machine with the command
```sh
sudo apt-get install -y postgresql postgresql-contrib
sudo -u postgres bash -c "psql -c \"CREATE DATABASE entrepot;\""
sudo -u postgres bash -c "psql -c \"CREATE USER dev WITH PASSWORD 'root';\""
sudo -u postgres bash -c "psql -c \"grant all privileges on database entrepot to dev;\""
psql entrepot -f SQL\ script/init.sql
```

To communicate with the database outside of the docker you need to modify two files.
The first one is "pg_hba.conf". You have to add
```sh
# IPv4 local connections:
host    all             all             0.0.0.0/0               md5
```

The second file is "postgresql.conf"
```sh
listen_addresses = '*'		# what IP address(es) to listen on;
```


Then you have to build the docker with the command
```sh
chmod +x test.sh
./test.sh
```

Also you have to pull the docker image of the socletech and run it on your machine
```sh
sudo docker pull canardmouton/socletech
sudo docker run -it -p 8000:8080 canardmouton/socletech
```
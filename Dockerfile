FROM maven:3.3.9-jdk-8

RUN apt-get -y update
RUN apt-get install -y wget

WORKDIR /project

RUN git clone https://NeaFTW@bitbucket.org/sigl2017ursi/java.git project/ 

RUN wget http://download.jboss.org/wildfly/8.2.0.Final/wildfly-8.2.0.Final.zip -P /project 
RUN unzip wildfly-8.2.0.Final.zip

RUN cd project && mvn clean install
RUN cp project/target/"FIXME" wildfly-8.2.0.Final/standalone/deployments/
ENTRYPOINT cd wildfly-8.2.0.Final/bin/ && ./standalone.sh -b 0.0.0.0 -bmanagement 0.0.0.0

Expose 8080

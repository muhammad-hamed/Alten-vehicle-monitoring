FROM java:8-jre
MAINTAINER Muhammad Hamed <muhammad.hammed@gmail.com>

ADD ./target/discovery-service-0.0.1-SNAPSHOT.jar /app/discovery-service-0.0.1-SNAPSHOT.jar
CMD java -jar -Xmx200m -Dspring.profiles.active=docker -Dserver.port=8090 -Dservice.instance.name=discovery /app/discovery-service-0.0.1-SNAPSHOT.jar
FROM java:8-jre
MAINTAINER Muhammad Hamed <muhammad.hammed@gmail.com>

ADD ./target/gateway-service-0.0.1-SNAPSHOT.jar /app/gateway-service-0.0.1-SNAPSHOT.jar

EXPOSE 8080

CMD java -jar -Xmx200m -Dspring.profiles.active=docker -Dserver.port=8080 -Dservice.instance.name=gateway /app/gateway-service-0.0.1-SNAPSHOT.jar
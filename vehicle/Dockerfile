FROM java:8-jre
MAINTAINER Muhammad Hamed <muhammad.hammed@gmail.com>

ADD ./target/vehicle-0.0.1-SNAPSHOT.jar /app/vehicle-0.0.1-SNAPSHOT.jar

EXPOSE 8082

CMD java -jar -Xmx200m -Dspring.profiles.active=docker -Dserver.port=8082 -Dservice.instance.name=vehicle /app/vehicle-0.0.1-SNAPSHOT.jar
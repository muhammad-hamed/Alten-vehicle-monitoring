FROM java:8-jre
MAINTAINER Muhammad Hamed <muhammad.hammed@gmail.com>

ADD ./target/customer-0.0.1-SNAPSHOT.jar /app/customer-0.0.1-SNAPSHOT.jar

CMD java -jar -Xmx200m -Dspring.profiles.active=docker -Dserver.port=8081 -Dservice.instance.name=customer /app/customer-0.0.1-SNAPSHOT.jar
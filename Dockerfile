FROM alpine:edge
RUN apk add --no-cache openjdk8
MAINTAINER ariel.miki@ui.ac.id
COPY target/tkai-0.0.1-SNAPSHOT.jar /opt/spring-cloud/lib/
ENTRYPOINT ["/usr/bin/java"]
CMD ["-jar", "/opt/spring-cloud/lib/tkai-0.0.1-SNAPSHOT.jar"]
VOLUME /var/lib/spring-cloud/config-repo
EXPOSE 8888
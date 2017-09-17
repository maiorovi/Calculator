FROM centos

RUN yum install -y java

volume /tmp

EXPOSE 8080

ADD ./target/calculator-1.0-SNAPSHOT.jar myapp.jar

RUN sh -c 'touch /myapp.jar'

ENTRYPOINT ["java", "-jar", "/myapp.jar"]
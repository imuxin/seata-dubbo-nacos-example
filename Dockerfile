FROM maven:ibmjava-alpine as dependency
COPY pom.xml /seata-dubbo/pom.xml
COPY dubbo/pom.xml /seata-dubbo/dubbo/pom.xml
WORKDIR /seata-dubbo
RUN mvn dependency:go-offline

FROM maven:ibmjava-alpine as build
COPY --from=dependency /root/.m2 /root/.m2
COPY . /seata-dubbo
WORKDIR /seata-dubbo/dubbo
RUN mvn clean package

FROM openjdk:8u302-jre-slim
COPY --from=build /seata-dubbo/dubbo/target /seata-dubbo
WORKDIR /seata-dubbo
